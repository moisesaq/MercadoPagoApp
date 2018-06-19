package com.example.moises.mercadopagoapp.injection.mercadopago.paymentMethods;

import android.content.Context;
import android.support.v4.app.Fragment;

import com.example.moises.mercadopagoapp.data.DataContract;
import com.example.moises.mercadopagoapp.injection.util.ScopeFragment;
import com.example.moises.mercadopagoapp.ui.mercadopago.paymentMethods.PaymentMethodsContract;
import com.example.moises.mercadopagoapp.ui.mercadopago.paymentMethods.PaymentMethodsFragment;
import com.example.moises.mercadopagoapp.ui.mercadopago.paymentMethods.PaymentMethodsPresenter;
import com.example.moises.mercadopagoapp.ui.mercadopago.paymentMethods.PaymentMethodsAdapter;

import dagger.Binds;
import dagger.Module;
import dagger.Provides;
import dagger.android.AndroidInjector;
import dagger.android.support.FragmentKey;
import dagger.multibindings.IntoMap;

@Module
public abstract class PaymentMethodsFragmentModule {

    @Binds
    @IntoMap
    @FragmentKey(PaymentMethodsFragment.class)
    abstract AndroidInjector.Factory<? extends Fragment> bindFragmentPaymentMethodsInjectorFacory
            (PaymentMethodsFragmentComponent.Builder builder);

    @Provides
    @ScopeFragment
    static PaymentMethodsContract.Presenter providePaymentMethodsPresenter(Context context, DataContract dataManager) {
        return new PaymentMethodsPresenter(context, dataManager);
    }

    @Provides
    @ScopeFragment
    static PaymentMethodsAdapter providePaymentMethodsAdapter(Context context) {
        return new PaymentMethodsAdapter(context);
    }
}
