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

import com.example.moises.mercadopagoapp.R;
import com.example.moises.mercadopagoapp.model.paymentMethod.PaymentMethod;
import com.example.moises.mercadopagoapp.ui.base.BaseFragment;
import com.example.moises.mercadopagoapp.ui.mercadopago.OnMercadoPagoFragmentsListener;
import com.example.moises.mercadopagoapp.ui.mercadopago.paymentMethods.PaymentMethodsAdapter;
import com.example.moises.mercadopagoapp.ui.mercadopago.paymentMethods.PaymentMethodsContract;

import java.util.List;

import javax.inject.Inject;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.Unbinder;
import dagger.android.support.AndroidSupportInjection;

/**
 * A placeholder fragment containing a simple view.
 */
public class InstallmentsFragment extends BaseFragment implements PaymentMethodsContract.View {

    private static final String PARAM_AMOUNT = "amount";
    @Inject
    PaymentMethodsContract.Presenter presenter;
    @Inject
    PaymentMethodsAdapter adapter;

    private Unbinder unbinder;
    private OnMercadoPagoFragmentsListener listener;
    private double amount;

    @BindView(R.id.recycler_view)
    protected RecyclerView recyclerView;
    @BindView(R.id.progress_bar)
    protected ProgressBar progressBar;

    public static InstallmentsFragment newInstance(double amount) {
        InstallmentsFragment fragment = new InstallmentsFragment();
        fragment.setArguments(prepareBundle(amount));
        return fragment;
    }

    private static Bundle prepareBundle(double amount) {
        Bundle bundle = new Bundle();
        bundle.putDouble(PARAM_AMOUNT, amount);
        return bundle;
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
            amount = getArguments().getDouble(PARAM_AMOUNT, 0);
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
        recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
        recyclerView.setAdapter(adapter);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        presenter.getPaymentMethods();
    }

    @Override
    public void showLoading() {
        progressBar.setVisibility(View.VISIBLE);
    }

    @Override
    public void hideLoading() {
        progressBar.setVisibility(View.GONE);
    }

    @Override
    public void showPaymentMethods(List<PaymentMethod> paymentMethods) {
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
}
