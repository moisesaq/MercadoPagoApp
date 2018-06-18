package com.example.moises.mercadopagoapp.ui.mercadopago.cardIssuers;

import android.util.Log;

import com.example.moises.mercadopagoapp.data.DataContract;
import com.example.moises.mercadopagoapp.model.cardIssuer.CardIssuer;

import java.util.List;

import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.CompositeDisposable;
import io.reactivex.schedulers.Schedulers;

public class CardIssuersPresenter implements CardIssuersContract.Presenter {

    private static final String TAG = CardIssuersPresenter.class.getSimpleName();
    private final DataContract dataManager;
    private final CompositeDisposable compositeDisposable;
    private CardIssuersContract.View cardIssuersView;

    public CardIssuersPresenter(DataContract dataManager) {
        this.dataManager = dataManager;
        compositeDisposable = new CompositeDisposable();
    }

    @Override
    public void setView(CardIssuersContract.View view) {
        cardIssuersView = view;
    }

    @Override
    public void getCardIssuers(String paymentMethodId) {
        dataManager.getCardIssuers(paymentMethodId)
                .subscribeOn(Schedulers.io())
                .doOnSubscribe(compositeDisposable::add)
                .observeOn(AndroidSchedulers.mainThread())
                .doOnComplete(cardIssuersView::hideLoading)
                .subscribe(this::showCardIssuers, this::error);
    }

    private void showCardIssuers(List<CardIssuer> cardIssuers) {
        if (cardIssuers.isEmpty()) {
            cardIssuersView.showCardIssuersNotFound();
            return;
        }
        cardIssuersView.showCardIssuers(cardIssuers);
    }

    private void error(Throwable throwable) {
        Log.e(TAG, "Error: " + throwable.toString());
        cardIssuersView.showError(throwable.getMessage());
    }

    @Override
    public void doDispose() {
        compositeDisposable.dispose();
    }
}
