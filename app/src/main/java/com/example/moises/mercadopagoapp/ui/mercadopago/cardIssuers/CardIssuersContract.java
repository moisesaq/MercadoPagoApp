package com.example.moises.mercadopagoapp.ui.mercadopago.cardIssuers;

import com.example.moises.mercadopagoapp.model.cardIssuer.CardIssuer;
import com.example.moises.mercadopagoapp.ui.base.BasePresenter;
import com.example.moises.mercadopagoapp.ui.base.BaseView;

import java.util.List;

public interface CardIssuersContract {

    interface View extends BaseView {

        void showCardIssuers(List<CardIssuer> cardIssuers);

        void showCardIssuersNotFound();

        void showError(String error);
    }

    interface Presenter extends BasePresenter<View> {

        void getCardIssuers(String paymentMethodId);
    }
}
