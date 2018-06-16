package com.example.moises.mercadopagoapp.injection.mercadopago.enterAmount;

import android.support.v4.app.Fragment;

import com.example.moises.mercadopagoapp.injection.util.ScopeFragment;
import com.example.moises.mercadopagoapp.ui.mercadopago.enterAmount.EnterAmountContract;
import com.example.moises.mercadopagoapp.ui.mercadopago.enterAmount.EnterAmountFragment;
import com.example.moises.mercadopagoapp.ui.mercadopago.enterAmount.EnterAmountPresenter;

import dagger.Binds;
import dagger.Module;
import dagger.Provides;
import dagger.android.AndroidInjector;
import dagger.android.support.FragmentKey;
import dagger.multibindings.IntoMap;

@Module
public abstract class EnterAmountFragmentModule {

    @Binds
    @IntoMap
    @FragmentKey(EnterAmountFragment.class)
    abstract AndroidInjector.Factory<? extends Fragment> bindFragmentEnterAmountInjectorFacory
            (EnterAmountFragmentComponent.Builder builder);

    @Provides
    @ScopeFragment
    static EnterAmountContract.Presenter provideEnterAmountPresenter(EnterAmountContract.View enterAmountView){
        return new EnterAmountPresenter(enterAmountView);
    }
}
