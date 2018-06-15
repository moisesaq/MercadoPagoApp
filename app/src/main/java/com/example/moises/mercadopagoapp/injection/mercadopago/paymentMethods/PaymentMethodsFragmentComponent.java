package com.example.moises.mercadopagoapp.injection.mercadopago.paymentMethods;
;
import com.example.moises.mercadopagoapp.injection.util.ScopeFragment;
import com.example.moises.mercadopagoapp.ui.mercadopago.paymentMethods.PaymentMethodsFragment;

import dagger.Subcomponent;
import dagger.android.AndroidInjector;

@ScopeFragment
@Subcomponent(modules = {PaymentMethodsFragmentModule.class})
public interface PaymentMethodsFragmentComponent extends AndroidInjector<PaymentMethodsFragment> {
    @Subcomponent.Builder
    abstract class Builder extends AndroidInjector.Builder<PaymentMethodsFragment> {
    }
}
