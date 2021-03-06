package com.example.moises.mercadopagoapp.ui.mercadopago.enterAmount;

import android.app.Activity;
import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v4.app.Fragment;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;

import com.example.moises.mercadopagoapp.R;
import com.example.moises.mercadopagoapp.model.Payment;
import com.example.moises.mercadopagoapp.ui.base.PaymentFragment;
import com.example.moises.mercadopagoapp.ui.mercadopago.utils.OnMercadoPagoFragmentsListener;
import com.jakewharton.rxbinding2.widget.RxTextView;

import javax.inject.Inject;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import butterknife.Unbinder;
import dagger.android.support.AndroidSupportInjection;

public class EnterAmountFragment extends PaymentFragment implements EnterAmountContract.View {

    @Inject
    EnterAmountContract.Presenter presenter;

    private Unbinder unbinder;
    protected OnMercadoPagoFragmentsListener listener;

    @BindView(R.id.et_amount)
    protected EditText etAmount;
    @BindView(R.id.btn_continue)
    protected Button btnContinue;

    @Override
    public void onAttach(Context context) {
        AndroidSupportInjection.inject(this);
        super.onAttach(context);
        if (!(context instanceof Activity))
            throw new RuntimeException("Error: you must implement OnMercadoPagoFragmentsListener");
        listener = (OnMercadoPagoFragmentsListener) context;
    }

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_enter_amount, container, false);
        unbinder = ButterKnife.bind(this, view);
        setUp();
        return view;
    }

    @Override
    protected void setUp() {
        showOrHideHomeBackButton(false);
        RxTextView.textChanges(etAmount).subscribe(presenter::checkText);
    }

    @OnClick(R.id.btn_continue)
    public void onContinueClick() {
        presenter.createPayment(etAmount.getText().toString());
    }

    @Override
    public void changeButtonContinueVisibility(int visibility) {
        btnContinue.setVisibility(visibility);
    }

    @Override
    public void sendPayment(Payment payment) {
        etAmount.getText().clear();
        listener.showPaymentMethodFragment(payment);
    }

    @Override
    public Fragment getFragment() {
        return this;
    }

    @Override
    public void onDetach() {
        super.onDetach();
        showOrHideHomeBackButton(true);
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        unbinder.unbind();
    }
}
