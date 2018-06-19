package com.example.moises.mercadopagoapp.injection.mercadopago.installments;

import com.example.moises.mercadopagoapp.injection.util.ScopeFragment;
import com.example.moises.mercadopagoapp.ui.mercadopago.installments.InstallmentsFragment;

import dagger.Subcomponent;
import dagger.android.AndroidInjector;

@ScopeFragment
@Subcomponent(modules = {InstallmentsFragmentModule.class})
public interface InstallmentsFragmentComponent extends AndroidInjector<InstallmentsFragment> {
    @Subcomponent.Builder
    abstract class Builder extends AndroidInjector.Builder<InstallmentsFragment> {
    }
}
