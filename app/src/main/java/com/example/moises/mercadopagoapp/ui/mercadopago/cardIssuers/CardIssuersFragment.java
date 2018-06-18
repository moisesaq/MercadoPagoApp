package com.example.moises.mercadopagoapp.ui.mercadopago.cardIssuers;

import android.app.Activity;
import android.content.Context;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.Spinner;
import android.widget.TextView;

import com.example.moises.mercadopagoapp.R;
import com.example.moises.mercadopagoapp.model.Payment;
import com.example.moises.mercadopagoapp.model.cardIssuer.CardIssuer;
import com.example.moises.mercadopagoapp.ui.base.PaymentFragment;
import com.example.moises.mercadopagoapp.ui.mercadopago.OnMercadoPagoFragmentsListener;

import java.util.List;

import javax.inject.Inject;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import butterknife.Unbinder;
import dagger.android.support.AndroidSupportInjection;

public class CardIssuersFragment extends PaymentFragment implements CardIssuersContract.View,
        AdapterView.OnItemSelectedListener {

    @Inject
    CardIssuersContract.Presenter presenter;
    @Inject
    CardIssuersAdapter adapter;

    private Unbinder unbinder;
    private OnMercadoPagoFragmentsListener listener;
    private Payment payment;

    @BindView(R.id.pb_loading_card_issuers)
    protected ProgressBar loadingCardIssuers;
    @BindView(R.id.tv_message)
    protected TextView tvMessage;

    @BindView(R.id.layout_data)
    protected View layoutData;
    @BindView(R.id.tv_amount)
    protected TextView tvAmount;
    @BindView(R.id.iv_payment_method)
    protected ImageView ivPaymentMethod;
    @BindView(R.id.tv_payment_method)
    protected TextView tvPaymentMethod;
    @BindView(R.id.sp_card_issuers)
    protected Spinner spCardIssuers;

    public static CardIssuersFragment newInstance(Payment payment) {
        CardIssuersFragment fragment = new CardIssuersFragment();
        fragment.addTransition();
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
        View view = inflater.inflate(R.layout.fragment_card_issuers, container, false);
        unbinder = ButterKnife.bind(this, view);
        setUp();
        return view;
    }

    @Override
    protected void setUp() {
        loadPaymentData();
        spCardIssuers.setAdapter(adapter);
        spCardIssuers.setOnItemSelectedListener(this);
    }

    private void loadPaymentData(){
        tvAmount.setText(String.format("%s $", payment.getAmount()));
        loadImage(payment.getPaymentMethod().getUrlLogo(), ivPaymentMethod);
        tvPaymentMethod.setText(payment.getPaymentMethod().getName());
    }

    @OnClick(R.id.btn_continue)
    public void onContinueClick(){
        listener.showInstallmentsFragment(payment);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        if (adapter.getCount() == 0)
            presenter.getCardIssuers(payment.getPaymentMethod().getId());
    }

    @Override
    public void showLoading() {
        loadingCardIssuers.setVisibility(View.VISIBLE);
        layoutData.setVisibility(View.GONE);
    }

    @Override
    public void hideLoading() {
        loadingCardIssuers.setVisibility(View.GONE);
        layoutData.setVisibility(View.VISIBLE);
    }

    @Override
    public void showCardIssuers(List<CardIssuer> cardIssuers) {
        adapter.addAll(cardIssuers);
    }

    @Override
    public void showCardIssuersNotFound() {
        layoutData.setVisibility(View.GONE);
        tvMessage.setVisibility(View.VISIBLE);
        tvMessage.setText(R.string.card_issuers_not_found);
    }

    @Override
    public void showError(String error) {
        tvMessage.setText(String.format("%s %s", getString(R.string.something_went_wrong), error));
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
        CardIssuer cardIssuer = adapter.getItem(position);
        if (cardIssuer != null)
            payment.setCardIssuer(cardIssuer);
    }

    @Override
    public void onNothingSelected(AdapterView<?> parent) {
    }
}
