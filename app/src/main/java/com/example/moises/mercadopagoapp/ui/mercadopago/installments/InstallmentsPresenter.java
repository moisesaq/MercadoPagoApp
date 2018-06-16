package com.example.moises.mercadopagoapp.ui.mercadopago.installments;

import android.util.Log;

import com.example.moises.mercadopagoapp.data.DataContract;
import com.example.moises.mercadopagoapp.ui.mercadopago.paymentMethods.PaymentMethodsContract;

import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.CompositeDisposable;
import io.reactivex.schedulers.Schedulers;

public class InstallmentsPresenter implements PaymentMethodsContract.Presenter {

    private static final String TAG = InstallmentsPresenter.class.getSimpleName();
    private final DataContract dataManager;
    private final CompositeDisposable compositeDisposable;
    private PaymentMethodsContract.View paymentMethodsView;

    public InstallmentsPresenter(DataContract dataManager) {
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
                .doOnComplete(paymentMethodsView::hideLoading)
                .subscribe(paymentMethodsView::showPaymentMethods, this::error);
    }

    private void error(Throwable throwable) {
        Log.e(TAG, "Error: " + throwable.toString());
        paymentMethodsView.showError(throwable.getMessage());
    }

    @Override
    public void doDispose() {
        compositeDisposable.dispose();
    }
}
