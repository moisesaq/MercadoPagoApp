package com.example.moises.mercadopagoapp.ui.mercadopago.installments;

import com.example.moises.mercadopagoapp.model.paymentMethod.PaymentMethod;
import com.example.moises.mercadopagoapp.ui.base.BasePresenter;
import com.example.moises.mercadopagoapp.ui.base.BaseView;

import java.util.List;

public interface InstallmentsContract {

    interface View extends BaseView {

        void showPaymentMethods(List<PaymentMethod> paymentMethods);

        void showPaymentMethodsNotFound();

        void showError(String error);
    }

    interface Presenter extends BasePresenter<View> {

        void getPaymentMethods();
    }
}
