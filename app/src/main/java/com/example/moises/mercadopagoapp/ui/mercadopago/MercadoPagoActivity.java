package com.example.moises.mercadopagoapp.ui.mercadopago;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.Toolbar;
import com.example.moises.mercadopagoapp.R;
import com.example.moises.mercadopagoapp.ui.base.BaseActivity;
import com.example.moises.mercadopagoapp.ui.mercadopago.enterAmount.EnterAmountContract;
import com.example.moises.mercadopagoapp.ui.mercadopago.paymentMethods.PaymentMethodsFragment;

import javax.inject.Inject;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.Unbinder;
import dagger.android.AndroidInjection;
import dagger.android.AndroidInjector;
import dagger.android.DispatchingAndroidInjector;
import dagger.android.support.HasSupportFragmentInjector;

public class MercadoPagoActivity extends BaseActivity implements HasSupportFragmentInjector,
        OnMercadoPagoFragmentsListener {

    @Inject
    DispatchingAndroidInjector<Fragment> injector;
    @Inject
    EnterAmountContract.View enterAmountView;

    private Unbinder unbinder;

    @BindView(R.id.toolbar)
    Toolbar toolbar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        AndroidInjection.inject(this);
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_mercado_pago);
        unbinder = ButterKnife.bind(this);
        setUp();
    }

    private void setUp(){
        setSupportActionBar(toolbar);
        showFragment(enterAmountView.getFragment(), false);
    }

    private void showFragment(Fragment fragment, boolean stack){
        replaceFragment(fragment, R.id.main_container, stack);
    }

    @Override
    public AndroidInjector<Fragment> supportFragmentInjector() {
        return injector;
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        unbinder.unbind();
    }

    /**
     * Implementation OnMercadoPagoFragmentsListener
     */
    @Override
    public void onSelectPaymentMethodClick(double amount) {
        showFragment(PaymentMethodsFragment.newInstance(amount), true);
    }

    @Override
    public void onBackPressed() {
        if (popBackStack())
            finish();
    }
}
