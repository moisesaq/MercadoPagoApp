package com.example.moises.mercadopagoapp.ui.mercadopago.installments;

import com.example.moises.mercadopagoapp.model.Payment;
import com.example.moises.mercadopagoapp.model.installment.Installment;
import com.example.moises.mercadopagoapp.ui.base.BasePresenter;
import com.example.moises.mercadopagoapp.ui.base.BaseView;

import java.util.List;

public interface InstallmentsContract {

    interface View extends BaseView {

        void showInstallments(List<Installment> installments);

        void showInstallmentsNotFound();
    }

    interface Presenter extends BasePresenter<View> {

        void getInstallments(Payment payment);
    }
}
