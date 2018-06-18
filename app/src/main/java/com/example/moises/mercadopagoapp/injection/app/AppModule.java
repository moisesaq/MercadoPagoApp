package com.example.moises.mercadopagoapp.injection.app;

import android.content.Context;

import com.example.moises.mercadopagoapp.data.APIServices;
import com.example.moises.mercadopagoapp.data.DataContract;
import com.example.moises.mercadopagoapp.data.DataManager;
import com.example.moises.mercadopagoapp.injection.mercadopago.MercadoPagoActivityComponent;
import com.example.moises.mercadopagoapp.ui.MercadoPagoApp;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;

@Module(subcomponents = {MercadoPagoActivityComponent.class})
class AppModule {

    @Singleton
    @Provides
    Context provideContext(MercadoPagoApp mercadoPagoApp){
        return mercadoPagoApp.getApplicationContext();
    }

    @Singleton
    @Provides
    static DataContract provideDataManager(APIServices apiServices){
        return new DataManager(apiServices);
    }
}
