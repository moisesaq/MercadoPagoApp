package com.example.moises.mercadopagoapp.ui.mercadopago;

import android.app.AlertDialog;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.Toolbar;

import com.example.moises.mercadopagoapp.R;
import com.example.moises.mercadopagoapp.model.Payment;
import com.example.moises.mercadopagoapp.ui.base.BaseActivity;
import com.example.moises.mercadopagoapp.ui.mercadopago.cardIssuers.CardIssuersFragment;
import com.example.moises.mercadopagoapp.ui.mercadopago.enterAmount.EnterAmountContract;
import com.example.moises.mercadopagoapp.ui.mercadopago.installments.InstallmentsFragment;
import com.example.moises.mercadopagoapp.ui.mercadopago.paymentMethods.PaymentMethodsFragment;
import com.example.moises.mercadopagoapp.ui.mercadopago.utils.OnMercadoPagoFragmentsListener;

import javax.inject.Inject;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.Unbinder;
import dagger.android.AndroidInjection;
import dagger.android.AndroidInjector;
import dagger.android.DispatchingAndroidInjector;
import dagger.android.support.HasSupportFragmentInjector;

import static android.support.v4.app.FragmentManager.POP_BACK_STACK_INCLUSIVE;

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

    private void setUp() {
        setSupportActionBar(toolbar);
        showEnterAmountFragment();
    }

    private void showEnterAmountFragment() {
        showFragment(enterAmountView.getFragment(), false);
    }

    private void showFragment(Fragment fragment, boolean stack) {
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

    @Override
    public void onBackPressed() {
        if (!popBackStack())
            finish();
    }

    /**
     * Implementation OnMercadoPagoFragmentsListener
     */
    @Override
    public void showPaymentMethodFragment(Payment payment) {
        showFragment(PaymentMethodsFragment.newInstance(payment), true);
    }

    @Override
    public void showCardIssuersFragment(Payment payment) {
        showFragment(CardIssuersFragment.newInstance(payment), true);
    }

    @Override
    public void showInstallmentsFragment(Payment payment) {
        showFragment(InstallmentsFragment.newInstance(payment), true);
    }

    @Override
    public void paymentFinished(String recommendedMessage) {
        getSupportFragmentManager().popBackStack(null, POP_BACK_STACK_INCLUSIVE);
        showEnterAmountFragment();
        showRecommendedMessage(recommendedMessage);
    }

    private void showRecommendedMessage(String recommendedMessage) {
        AlertDialog.Builder builder = new AlertDialog.Builder(this)
                .setTitle(R.string.app_name)
                .setMessage(recommendedMessage)
                .setNeutralButton(android.R.string.ok, (dialog, which) -> dialog.dismiss());
        builder.create().show();
    }
}
