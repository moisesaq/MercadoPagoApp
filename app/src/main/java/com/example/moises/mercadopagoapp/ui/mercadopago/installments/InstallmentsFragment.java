package com.example.moises.mercadopagoapp.ui.mercadopago.installments;

import android.app.Activity;
import android.content.Context;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ProgressBar;
import android.widget.TextView;

import com.example.moises.mercadopagoapp.R;
import com.example.moises.mercadopagoapp.model.Payment;
import com.example.moises.mercadopagoapp.model.installment.Installment;
import com.example.moises.mercadopagoapp.ui.base.BaseFragment;
import com.example.moises.mercadopagoapp.ui.mercadopago.OnMercadoPagoFragmentsListener;

import java.util.List;

import javax.inject.Inject;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import butterknife.Unbinder;
import dagger.android.support.AndroidSupportInjection;

/**
 * A placeholder fragment containing a simple view.
 */
public class InstallmentsFragment extends BaseFragment implements InstallmentsContract.View,
        InstallmentsAdapter.OnInstallmentsAdapterListener {

    @Inject
    InstallmentsContract.Presenter presenter;
    @Inject
    InstallmentsAdapter adapter;

    private Unbinder unbinder;
    private OnMercadoPagoFragmentsListener listener;
    private Payment payment;
    private Installment installmentSelected;

    @BindView(R.id.pb_loading_installments)
    protected ProgressBar loadingInstallments;
    @BindView(R.id.layout_data)
    protected View layoutData;
    @BindView(R.id.tv_summary)
    protected TextView tvSummary;
    @BindView(R.id.rv_installments)
    protected RecyclerView recyclerView;

    public static InstallmentsFragment newInstance(Payment payment) {
        InstallmentsFragment fragment = new InstallmentsFragment();
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
        View view = inflater.inflate(R.layout.fragment_installments, container, false);
        unbinder = ButterKnife.bind(this, view);
        setUp();
        return view;
    }

    @Override
    protected void setUp() {
        adapter.setListener(this);
        recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
        recyclerView.setAdapter(adapter);
    }

    @OnClick(R.id.btn_finish)
    public void onFinishClick(){
        if (installmentSelected!= null)
            listener.paymentFinished(installmentSelected.getRecommendedMessage());
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        presenter.getInstallments(payment);
    }

    @Override
    public void showLoading() {
        loadingInstallments.setVisibility(View.VISIBLE);
        layoutData.setVisibility(View.GONE);
    }

    @Override
    public void hideLoading() {
        loadingInstallments.setVisibility(View.GONE);
        layoutData.setVisibility(View.VISIBLE);
    }

    @Override
    public void showInstallments(List<Installment> installments) {
        adapter.addItems(installments);
    }

    @Override
    public void showInstallmentsNotFound() {
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
     * Implementation InstallmentsAdapter.OnInstallmentsAdapterListener
     */
    @Override
    public void onInstallmentClick(Installment installment) {
        installmentSelected = installment;
    }
}
