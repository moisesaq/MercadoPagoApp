package com.example.moises.mercadopagoapp.ui.mercadopago;

import com.example.moises.mercadopagoapp.model.Payment;

public interface OnMercadoPagoFragmentsListener {

    void showPaymentMethodFragment(Payment payment);

    void showCardIssuersFragment(Payment payment);

    void showInstallmentsFragment(Payment payment);

    void paymentFinished(String recommendedMessage);
}
