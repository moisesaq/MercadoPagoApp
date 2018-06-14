package com.example.moises.mercadopagoapp.injection.mercadopago.paymentMethods;

import android.support.v4.app.Fragment;

import com.example.moises.mercadopagoapp.injection.util.ScopeFragment;
import com.example.moises.mercadopagoapp.ui.mercadopago.paymentMethods.PaymentMethodsContract;
import com.example.moises.mercadopagoapp.ui.mercadopago.paymentMethods.PaymentMethodsFragment;
import com.example.moises.mercadopagoapp.ui.mercadopago.paymentMethods.PaymentMethodsPresenter;

import dagger.Binds;
import dagger.Module;
import dagger.Provides;
import dagger.android.AndroidInjector;
import dagger.android.support.FragmentKey;
import dagger.multibindings.IntoMap;

/**
 * Created by moises on 13/06/2018.
 */

@Module
public abstract class PaymentMethodsFragmentModule {

    @Binds
    @IntoMap
    @FragmentKey(PaymentMethodsFragment.class)
    abstract AndroidInjector.Factory<? extends Fragment> bindFragmentPaymentMethodsInjectorFacory
            (PaymentMethodsFragmentComponent.Builder builder);

    @Provides
    @ScopeFragment
    static PaymentMethodsContract.Presenter providePaymentMethodsPresenter(){
        return new PaymentMethodsPresenter();
    }
}
