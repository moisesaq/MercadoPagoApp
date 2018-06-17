package com.example.moises.mercadopagoapp.ui.mercadopago.installments;

import android.util.Log;

import com.example.moises.mercadopagoapp.data.DataContract;
import com.example.moises.mercadopagoapp.model.Payment;

import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.CompositeDisposable;
import io.reactivex.schedulers.Schedulers;

public class InstallmentsPresenter implements InstallmentsContract.Presenter {

    private static final String TAG = InstallmentsPresenter.class.getSimpleName();
    private final DataContract dataManager;
    private final CompositeDisposable compositeDisposable;
    private InstallmentsContract.View installmentsView;

    public InstallmentsPresenter(DataContract dataManager) {
        this.dataManager = dataManager;
        compositeDisposable = new CompositeDisposable();
    }

    @Override
    public void setView(InstallmentsContract.View view) {
        installmentsView = view;
    }

    @Override
    public void getInstallments(Payment payment) {
        installmentsView.showLoading();
        dataManager.getInstallments(payment.getAmount(), payment.getPaymentMethodId(), payment.getIssuerId())
                .subscribeOn(Schedulers.io())
                .doOnSubscribe(compositeDisposable::add)
                .observeOn(AndroidSchedulers.mainThread())
                .doOnComplete(installmentsView::hideLoading)
                .subscribe(installmentsView::showInstallments, this::error);
    }

    private void error(Throwable throwable) {
        Log.e(TAG, "Error: " + throwable.toString());
        installmentsView.showError(throwable.getMessage());
    }

    @Override
    public void doDispose() {
        compositeDisposable.dispose();
    }
}
