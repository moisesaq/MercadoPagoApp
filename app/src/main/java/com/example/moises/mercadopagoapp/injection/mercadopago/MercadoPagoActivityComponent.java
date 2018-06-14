package com.example.moises.mercadopagoapp.injection.mercadopago;

import com.example.moises.mercadopagoapp.injection.mercadopago.enterAmount.EnterAmountFragmentModule;
import com.example.moises.mercadopagoapp.injection.mercadopago.paymentMethods.PaymentMethodsFragmentModule;
import com.example.moises.mercadopagoapp.injection.util.ScopeActivity;
import com.example.moises.mercadopagoapp.ui.mercadopago.MercadoPagoActivity;

import dagger.Subcomponent;
import dagger.android.AndroidInjector;

/**
 * Created by moises on 13/06/2018.
 */
@ScopeActivity
@Subcomponent(modules = {MercadoPagoActivityModule.class, EnterAmountFragmentModule.class,
        PaymentMethodsFragmentModule.class})
public interface MercadoPagoActivityComponent extends AndroidInjector<MercadoPagoActivity> {
    @Subcomponent.Builder
    abstract class Builder extends AndroidInjector.Builder<MercadoPagoActivity> {
    }
}
