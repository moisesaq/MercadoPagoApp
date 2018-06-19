package com.example.moises.mercadopagoapp.injection.app;

import com.example.moises.mercadopagoapp.injection.mercadopago.MercadoPagoActivityModule;
import com.example.moises.mercadopagoapp.ui.MercadoPagoApp;

import javax.inject.Singleton;

import dagger.BindsInstance;
import dagger.Component;

@Singleton
@Component(modules = {AppModule.class, NetModule.class, MercadoPagoActivityModule.class})
public interface AppComponent {
    @Component.Builder
    interface Builder {
        @BindsInstance
        Builder application(MercadoPagoApp mercadoPagoApp);

        AppComponent build();
    }

    void inject(MercadoPagoApp mercadoPagoApp);
}
