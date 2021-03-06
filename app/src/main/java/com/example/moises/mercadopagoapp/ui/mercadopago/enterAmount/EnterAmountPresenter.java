package com.example.moises.mercadopagoapp.ui.mercadopago.enterAmount;

import android.view.View;

import com.example.moises.mercadopagoapp.model.Payment;

public class EnterAmountPresenter implements EnterAmountContract.Presenter {

    private final EnterAmountContract.View enterAmountView;

    public EnterAmountPresenter(EnterAmountContract.View enterAmountView) {
        this.enterAmountView = enterAmountView;
    }

    @Override
    public void createPayment(String value) {
        enterAmountView.sendPayment(buildPayment(value));
    }

    private Payment buildPayment(String value) {
        return new Payment.Builder()
                .amount(Double.valueOf(value))
                .build();
    }

    @Override
    public void checkText(CharSequence text) {
        enterAmountView.changeButtonContinueVisibility(text.length() > 0 ? View.VISIBLE : View.GONE);
    }
}
