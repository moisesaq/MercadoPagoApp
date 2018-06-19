package com.example.moises.mercadopagoapp.ui;

import android.app.Activity;
import android.app.Application;
import android.support.v7.app.AppCompatDelegate;

import com.example.moises.mercadopagoapp.injection.app.AppComponent;
import com.example.moises.mercadopagoapp.injection.app.DaggerAppComponent;

import javax.inject.Inject;

import dagger.android.AndroidInjector;
import dagger.android.DispatchingAndroidInjector;
import dagger.android.HasActivityInjector;

public class MercadoPagoApp extends Application implements HasActivityInjector {

    @Inject
    DispatchingAndroidInjector<Activity> injector;

    @Override
    public void onCreate() {
        super.onCreate();
        setUpDependencyInjectionDagger();
        AppCompatDelegate.setCompatVectorFromResourcesEnabled(true);
    }

    private void setUpDependencyInjectionDagger() {
        AppComponent appComponent = DaggerAppComponent
                .builder()
                .application(this)
                .build();

        appComponent.inject(this);
    }

    @Override
    public AndroidInjector<Activity> activityInjector() {
        return injector;
    }
}
