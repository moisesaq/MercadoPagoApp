package com.example.moises.mercadopagoapp.injection.mercadopago.installments;

import android.content.Context;
import android.support.v4.app.Fragment;

import com.example.moises.mercadopagoapp.data.DataContract;
import com.example.moises.mercadopagoapp.injection.util.ScopeFragment;
import com.example.moises.mercadopagoapp.ui.mercadopago.installments.InstallmentsAdapter;
import com.example.moises.mercadopagoapp.ui.mercadopago.installments.InstallmentsContract;
import com.example.moises.mercadopagoapp.ui.mercadopago.installments.InstallmentsFragment;
import com.example.moises.mercadopagoapp.ui.mercadopago.installments.InstallmentsPresenter;

import dagger.Binds;
import dagger.Module;
import dagger.Provides;
import dagger.android.AndroidInjector;
import dagger.android.support.FragmentKey;
import dagger.multibindings.IntoMap;

@Module
public abstract class InstallmentsFragmentModule {

    @Binds
    @IntoMap
    @FragmentKey(InstallmentsFragment.class)
    abstract AndroidInjector.Factory<? extends Fragment> bindFragmentInstallmentsInjectorFacory
            (InstallmentsFragmentComponent.Builder builder);

    @Provides
    @ScopeFragment
    static InstallmentsContract.Presenter provideInstallmentsPresenter(DataContract dataManager) {
        return new InstallmentsPresenter(dataManager);
    }

    @Provides
    @ScopeFragment
    static InstallmentsAdapter provideInstallmetnssAdapter(Context context) {
        return new InstallmentsAdapter(context);
    }
}
