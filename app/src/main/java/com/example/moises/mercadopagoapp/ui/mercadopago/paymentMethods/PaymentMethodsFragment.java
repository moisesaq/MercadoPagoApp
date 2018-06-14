package com.example.moises.mercadopagoapp.ui.mercadopago.paymentMethods;

import android.app.Activity;
import android.content.Context;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.moises.mercadopagoapp.R;
import com.example.moises.mercadopagoapp.ui.base.BaseFragment;
import com.example.moises.mercadopagoapp.ui.mercadopago.OnMercadoPagoFragmentsListener;

import javax.inject.Inject;

import butterknife.ButterKnife;
import butterknife.Unbinder;
import dagger.android.support.AndroidSupportInjection;

/**
 * A placeholder fragment containing a simple view.
 */
public class PaymentMethodsFragment extends BaseFragment implements PaymentMethodsContract.View{

    private static final String PARAM_AMOUNT = "amount";
    @Inject
    PaymentMethodsContract.Presenter presenter;

    private Unbinder unbinder;
    private OnMercadoPagoFragmentsListener listener;
    private double amount;

    public static PaymentMethodsFragment newInstance(double amount){
        PaymentMethodsFragment fragment = new PaymentMethodsFragment();
        fragment.setArguments(prepareBundle(amount));
        return fragment;
    }

    private static Bundle prepareBundle(double amount){
        Bundle bundle = new Bundle();
        bundle.putDouble(PARAM_AMOUNT, amount);
        return bundle;
    }

    @Override
    public void onAttach(Context context) {
        AndroidSupportInjection.inject(this);
        super.onAttach(context);
        if (!(context instanceof Activity))
            throw new RuntimeException("Error: you must implement OnMercadoPagoFragmentsListener");
        listener = (OnMercadoPagoFragmentsListener)context;
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        retrieveAmount();
    }

    private void retrieveAmount(){
        if (getArguments() != null)
            amount = getArguments().getDouble(PARAM_AMOUNT, 0);
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

    }

    @Override
    public void showLoading() {

    }

    @Override
    public void hideLoading() {

    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        unbinder.unbind();
    }
}
