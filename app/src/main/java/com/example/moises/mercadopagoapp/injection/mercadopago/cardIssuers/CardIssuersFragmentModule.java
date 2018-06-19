package com.example.moises.mercadopagoapp.injection.mercadopago.cardIssuers;

import android.content.Context;
import android.support.v4.app.Fragment;

import com.example.moises.mercadopagoapp.data.DataContract;
import com.example.moises.mercadopagoapp.injection.util.ScopeFragment;
import com.example.moises.mercadopagoapp.ui.mercadopago.cardIssuers.CardIssuersAdapter;
import com.example.moises.mercadopagoapp.ui.mercadopago.cardIssuers.CardIssuersContract;
import com.example.moises.mercadopagoapp.ui.mercadopago.cardIssuers.CardIssuersFragment;
import com.example.moises.mercadopagoapp.ui.mercadopago.cardIssuers.CardIssuersPresenter;

import dagger.Binds;
import dagger.Module;
import dagger.Provides;
import dagger.android.AndroidInjector;
import dagger.android.support.FragmentKey;
import dagger.multibindings.IntoMap;

@Module
public abstract class CardIssuersFragmentModule {

    @Binds
    @IntoMap
    @FragmentKey(CardIssuersFragment.class)
    abstract AndroidInjector.Factory<? extends Fragment> bindFragmentCardIssuersInjectorFacory
            (CardIssuersFragmentComponent.Builder builder);

    @Provides
    @ScopeFragment
    static CardIssuersContract.Presenter provideCardIssuersPresenter(Context context, DataContract dataManager) {
        return new CardIssuersPresenter(context, dataManager);
    }

    @Provides
    @ScopeFragment
    static CardIssuersAdapter provideCardIssuersAdapter(Context context) {
        return new CardIssuersAdapter(context);
    }
}
