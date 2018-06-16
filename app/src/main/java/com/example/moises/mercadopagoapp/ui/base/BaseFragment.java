package com.example.moises.mercadopagoapp.ui.base;

import android.os.Bundle;
import android.support.v4.app.Fragment;

import com.example.moises.mercadopagoapp.model.Payment;

/**
 * Created by moises on 13/06/2018.
 *
 */

public abstract class BaseFragment extends Fragment{

    protected static final String PARAM_PAYMENT = "payment";

    protected static Bundle preparePayment(Payment payment) {
        Bundle bundle = new Bundle();
        bundle.putParcelable(PARAM_PAYMENT, payment);
        return bundle;
    }

    protected abstract void setUp();
}
