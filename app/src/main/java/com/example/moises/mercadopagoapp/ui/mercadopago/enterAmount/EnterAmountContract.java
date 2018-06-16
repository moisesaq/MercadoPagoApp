package com.example.moises.mercadopagoapp.ui.mercadopago.enterAmount;

import android.support.v4.app.Fragment;

import com.example.moises.mercadopagoapp.model.Payment;

public interface EnterAmountContract {

    interface View {

        void sendPayment(Payment payment);

        Fragment getFragment();
    }

    interface Presenter {

        void createPayment(String value);
    }
}
