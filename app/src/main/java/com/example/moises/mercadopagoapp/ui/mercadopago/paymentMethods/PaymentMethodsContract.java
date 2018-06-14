package com.example.moises.mercadopagoapp.ui.mercadopago.paymentMethods;

/**
 * Created by moises on 13/06/2018.
 */

public interface PaymentMethodsContract {

    interface View {

        void showLoading();

        void hideLoading();
    }

    interface Presenter {

        void setView(View view);
    }
}
