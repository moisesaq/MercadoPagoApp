package com.example.moises.mercadopagoapp.injection.mercadopago.cardIssuers;

import com.example.moises.mercadopagoapp.injection.util.ScopeFragment;
import com.example.moises.mercadopagoapp.ui.mercadopago.cardIssuers.CardIssuersFragment;

import dagger.Subcomponent;
import dagger.android.AndroidInjector;

;

@ScopeFragment
@Subcomponent(modules = {CardIssuersFragmentModule.class})
public interface CardIssuersFragmentComponent extends AndroidInjector<CardIssuersFragment> {
    @Subcomponent.Builder
    abstract class Builder extends AndroidInjector.Builder<CardIssuersFragment> {
    }
}
