package com.example.moises.mercadopagoapp.ui.mercadopago.cardIssuers;

import android.content.Context;
import android.util.Log;

import com.example.moises.mercadopagoapp.R;
import com.example.moises.mercadopagoapp.data.DataContract;
import com.example.moises.mercadopagoapp.model.cardIssuer.CardIssuer;

import java.util.List;

import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.CompositeDisposable;
import io.reactivex.schedulers.Schedulers;

public class CardIssuersPresenter implements CardIssuersContract.Presenter {

    private static final String TAG = CardIssuersPresenter.class.getSimpleName();
    private final Context context;
    private final DataContract dataManager;
    private final CompositeDisposable compositeDisposable;
    private CardIssuersContract.View cardIssuersView;

    public CardIssuersPresenter(Context context, DataContract dataManager) {
        this.context = context;
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
                .subscribe(this::showCardIssuers, this::error);
    }

    private void showCardIssuers(List<CardIssuer> cardIssuers) {
        cardIssuersView.hideLoading();
        if (cardIssuers.isEmpty()) {
            cardIssuersView.showCardIssuersNotFound();
            return;
        }
        cardIssuersView.showCardIssuers(addCardIssuerAsHeader(cardIssuers));
    }

    private List<CardIssuer> addCardIssuerAsHeader(List<CardIssuer> cardIssuers) {
        cardIssuers.add(0, createHeader());
        return cardIssuers;
    }

    private CardIssuer createHeader() {
        return new CardIssuer.Builder()
                .name(context.getString(R.string.select_card_issuer))
                .build();
    }

    private void error(Throwable throwable) {
        Log.e(TAG, "Error: " + throwable.toString());
        cardIssuersView.showError(throwable.getMessage());
    }

    @Override
    public void verifyCardIssuerIssuerSelected(int position) {
        if (position > 0) {
            cardIssuersView.showContinueButton();
            cardIssuersView.selectCardIssuer(position);
            return;
        }
        cardIssuersView.hideContinueButton();
    }

    @Override
    public void doDispose() {
        compositeDisposable.dispose();
    }
}
