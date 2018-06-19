package com.example.moises.mercadopagoapp.ui.mercadopago.cardIssuers;

import com.example.moises.mercadopagoapp.model.cardIssuer.CardIssuer;
import com.example.moises.mercadopagoapp.ui.base.BasePresenter;
import com.example.moises.mercadopagoapp.ui.base.BaseView;

import java.util.List;

public interface CardIssuersContract {

    interface View extends BaseView {

        void showCardIssuers(List<CardIssuer> cardIssuers);

        void showContinueButton();

        void hideContinueButton();

        void selectCardIssuer(int position);

        void showCardIssuersNotFound();
    }

    interface Presenter extends BasePresenter<View> {

        void getCardIssuers(String paymentMethodId);

        void verifyCardIssuerIssuerSelected(int position);
    }
}
