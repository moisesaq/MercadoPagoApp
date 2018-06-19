package com.example.moises.mercadopagoapp.injection.mercadopago.enterAmount;

import com.example.moises.mercadopagoapp.injection.util.ScopeFragment;
import com.example.moises.mercadopagoapp.ui.mercadopago.enterAmount.EnterAmountFragment;

import dagger.Subcomponent;
import dagger.android.AndroidInjector;

@ScopeFragment
@Subcomponent(modules = {EnterAmountFragmentModule.class})
public interface EnterAmountFragmentComponent extends AndroidInjector<EnterAmountFragment> {
    @Subcomponent.Builder
    abstract class Builder extends AndroidInjector.Builder<EnterAmountFragment> {
    }
}
