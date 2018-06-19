package com.example.moises.mercadopagoapp.ui.mercadopago.paymentMethods;

import android.content.Context;
import android.util.Log;

import com.example.moises.mercadopagoapp.R;
import com.example.moises.mercadopagoapp.data.DataContract;
import com.example.moises.mercadopagoapp.model.paymentMethod.PaymentMethod;

import java.util.List;

import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.CompositeDisposable;
import io.reactivex.schedulers.Schedulers;

public class PaymentMethodsPresenter implements PaymentMethodsContract.Presenter {

    private static final String TAG = PaymentMethodsPresenter.class.getSimpleName();
    private final Context context;
    private final DataContract dataManager;
    private final CompositeDisposable compositeDisposable;
    private PaymentMethodsContract.View paymentMethodsView;

    public PaymentMethodsPresenter(Context context, DataContract dataManager) {
        this.context = context;
        this.dataManager = dataManager;
        compositeDisposable = new CompositeDisposable();
    }

    @Override
    public void setView(PaymentMethodsContract.View view) {
        paymentMethodsView = view;
    }

    @Override
    public void getPaymentMethods() {
        paymentMethodsView.showLoading();
        dataManager.getPaymentMethods()
                .subscribeOn(Schedulers.io())
                .doOnSubscribe(compositeDisposable::add)
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(this::showPaymentMethods, this::error);
    }

    private void showPaymentMethods(List<PaymentMethod> paymentMethods) {
        paymentMethodsView.hideLoading();
        if (paymentMethods.isEmpty()) {
            paymentMethodsView.showPaymentMethodsNotFound();
            return;
        }
        paymentMethodsView.showPaymentMethods(addPaymentMethodAsHeader(paymentMethods));
    }

    private List<PaymentMethod> addPaymentMethodAsHeader(List<PaymentMethod> paymentMethods) {
        paymentMethods.add(0, createHeader());
        return paymentMethods;
    }

    private PaymentMethod createHeader(){
        return new PaymentMethod.Builder()
                .name(context.getString(R.string.select_payment_method))
                .build();
    }

    private void error(Throwable throwable) {
        Log.e(TAG, "Error: " + throwable.toString());
        paymentMethodsView.showError(throwable.getMessage());
    }

    @Override
    public void verifyPaymentMethodSelected(int position) {
        if (position > 0){
            paymentMethodsView.showContinueButton();
            paymentMethodsView.selectPaymentMethod(position);
            return;
        }
        paymentMethodsView.hideContinueButton();
    }

    @Override
    public void doDispose() {
        compositeDisposable.dispose();
    }
}
