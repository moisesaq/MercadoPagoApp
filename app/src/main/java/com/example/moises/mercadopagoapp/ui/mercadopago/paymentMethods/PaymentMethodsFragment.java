package com.example.moises.mercadopagoapp.ui.mercadopago.paymentMethods;

import android.app.Activity;
import android.content.Context;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ProgressBar;
import android.widget.Spinner;
import android.widget.TextView;

import com.example.moises.mercadopagoapp.R;
import com.example.moises.mercadopagoapp.model.Payment;
import com.example.moises.mercadopagoapp.model.paymentMethod.PaymentMethod;
import com.example.moises.mercadopagoapp.ui.base.BaseFragment;
import com.example.moises.mercadopagoapp.ui.mercadopago.OnMercadoPagoFragmentsListener;

import java.util.List;

import javax.inject.Inject;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import butterknife.Unbinder;
import dagger.android.support.AndroidSupportInjection;

public class PaymentMethodsFragment extends BaseFragment implements PaymentMethodsContract.View,
        AdapterView.OnItemSelectedListener {

    @Inject
    PaymentMethodsContract.Presenter presenter;
    @Inject
    PaymentsAdapter adapter;

    private Unbinder unbinder;
    private OnMercadoPagoFragmentsListener listener;
    private Payment payment;

    @BindView(R.id.pb_loading_payment_methods)
    protected ProgressBar loadingPaymentMethods;
    @BindView(R.id.layout_data)
    protected View layoutData;
    @BindView(R.id.tv_summary)
    protected TextView tvSummary;
    @BindView(R.id.sp_payment_methods)
    protected Spinner spPaymentMethods;

    public static PaymentMethodsFragment newInstance(Payment payment) {
        PaymentMethodsFragment fragment = new PaymentMethodsFragment();
        fragment.setArguments(preparePayment(payment));
        return fragment;
    }

    @Override
    public void onAttach(Context context) {
        AndroidSupportInjection.inject(this);
        super.onAttach(context);
        presenter.setView(this);
        if (!(context instanceof Activity))
            throw new RuntimeException("Error: you must implement OnMercadoPagoFragmentsListener");
        listener = (OnMercadoPagoFragmentsListener) context;
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        retrieveAmount();
    }

    private void retrieveAmount() {
        if (getArguments() != null)
            payment = getArguments().getParcelable(PARAM_PAYMENT);
    }

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_payment_methods, container, false);
        unbinder = ButterKnife.bind(this, view);
        setUp();
        return view;
    }

    @Override
    protected void setUp() {
        tvSummary.setText("Amount entered " + payment.getAmount());
        spPaymentMethods.setAdapter(adapter);
        spPaymentMethods.setOnItemSelectedListener(this);
    }

    @OnClick(R.id.btn_next)
    public void onNextClick(){
        listener.showCardIssuersFragment(payment);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        presenter.getPaymentMethods();
    }

    @Override
    public void showLoading() {
        loadingPaymentMethods.setVisibility(View.VISIBLE);
        layoutData.setVisibility(View.GONE);
    }

    @Override
    public void hideLoading() {
        loadingPaymentMethods.setVisibility(View.GONE);
        layoutData.setVisibility(View.VISIBLE);
    }

    @Override
    public void showPaymentMethods(List<PaymentMethod> paymentMethods) {
        adapter.addAll(paymentMethods);
    }

    @Override
    public void showPaymentMethodsNotFound() {
    }

    @Override
    public void showError(String error) {
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        presenter.doDispose();
        unbinder.unbind();
    }

    /**
     * Implementation AdapterView.OnItemSelectedListener
     */
    @Override
    public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
        PaymentMethod paymentMethod = adapter.getItem(position);
        if (paymentMethod != null)
            payment.setPaymentMethodId(paymentMethod.getId());

    }

    @Override
    public void onNothingSelected(AdapterView<?> parent) {
    }
}
