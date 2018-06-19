package com.example.moises.mercadopagoapp.ui.mercadopago.paymentMethods;

import com.example.moises.mercadopagoapp.model.paymentMethod.PaymentMethod;
import com.example.moises.mercadopagoapp.ui.base.BasePresenter;
import com.example.moises.mercadopagoapp.ui.base.BaseView;

import java.util.List;

public interface PaymentMethodsContract {

    interface View extends BaseView {

        void showPaymentMethods(List<PaymentMethod> paymentMethods);

        void showContinueButton();

        void hideContinueButton();

        void selectPaymentMethod(int position);

        void showPaymentMethodsNotFound();
    }

    interface Presenter extends BasePresenter<View> {

        void getPaymentMethods();

        void verifyPaymentMethodSelected(int position);
    }
}
