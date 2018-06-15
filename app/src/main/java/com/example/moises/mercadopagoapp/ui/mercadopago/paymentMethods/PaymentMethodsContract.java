package com.example.moises.mercadopagoapp.ui.mercadopago.paymentMethods;

import com.example.moises.mercadopagoapp.model.PaymentMethod;

import java.util.List;

public interface PaymentMethodsContract {

    interface View {

        void showLoading();

        void hideLoading();

        void showPaymentMethods(List<PaymentMethod> paymentMethods);

        void showPaymentMethodsNotFound();

        void showError(String error);
    }

    interface Presenter {

        void setView(View view);

        void getPaymentMethods();

        void doDispose();
    }
}
