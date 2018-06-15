package com.example.moises.mercadopagoapp.injection.mercadopago;

import android.app.Activity;

import com.example.moises.mercadopagoapp.injection.mercadopago.enterAmount.EnterAmountFragmentComponent;
import com.example.moises.mercadopagoapp.injection.mercadopago.paymentMethods.PaymentMethodsFragmentComponent;
import com.example.moises.mercadopagoapp.injection.util.ScopeActivity;
import com.example.moises.mercadopagoapp.ui.mercadopago.MercadoPagoActivity;
import com.example.moises.mercadopagoapp.ui.mercadopago.enterAmount.EnterAmountContract;
import com.example.moises.mercadopagoapp.ui.mercadopago.enterAmount.EnterAmountFragment;

import dagger.Binds;
import dagger.Module;
import dagger.Provides;
import dagger.android.ActivityKey;
import dagger.android.AndroidInjector;
import dagger.multibindings.IntoMap;

@Module(subcomponents = {EnterAmountFragmentComponent.class, PaymentMethodsFragmentComponent.class})
public abstract class MercadoPagoActivityModule {

    @Binds
    @IntoMap
    @ActivityKey(MercadoPagoActivity.class)
    abstract AndroidInjector.Factory<? extends Activity> bindActivityMercadoPagoInjectorFactory(
            MercadoPagoActivityComponent.Builder builder);

    @Provides
    @ScopeActivity
    static EnterAmountContract.View provideEnterAmountFragment() {
        return new EnterAmountFragment();
    }
}
