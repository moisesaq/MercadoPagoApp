package com.example.moises.mercadopagoapp.ui.base;

import android.os.Bundle;
import android.widget.ImageView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.request.RequestOptions;
import com.example.moises.mercadopagoapp.R;
import com.example.moises.mercadopagoapp.model.Payment;

public abstract class PaymentFragment extends BaseFragment {

    protected static final String PARAM_PAYMENT = "payment";

    protected static Bundle preparePayment(Payment payment) {
        Bundle bundle = new Bundle();
        bundle.putParcelable(PARAM_PAYMENT, payment);
        return bundle;
    }

    protected void loadImage(String urlImage, ImageView imageView) {
        if (getContext() == null)
            return;
        Glide.with(getContext())
                .load(urlImage)
                .apply(createOptions())
                .into(imageView);
    }

    private RequestOptions createOptions() {
        return new RequestOptions().error(R.mipmap.ic_launcher).placeholder(R.mipmap.ic_launcher);
    }
}
