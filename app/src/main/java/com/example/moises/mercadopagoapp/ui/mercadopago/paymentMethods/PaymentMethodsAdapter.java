package com.example.moises.mercadopagoapp.ui.mercadopago.paymentMethods;

import android.content.Context;
import android.support.annotation.NonNull;

import com.example.moises.mercadopagoapp.model.paymentMethod.PaymentMethod;
import com.example.moises.mercadopagoapp.ui.base.PaymentAdapter;
import com.example.moises.mercadopagoapp.ui.mercadopago.utils.ViewHolder;

public class PaymentMethodsAdapter extends PaymentAdapter<PaymentMethod> {

    public PaymentMethodsAdapter(@NonNull Context context) {
        super(context);
    }

    @Override
    public void loadDataInViewHolder(ViewHolder holder, int position) {
        PaymentMethod paymentMethod = getItem(position);
        if (paymentMethod == null)
            return;

        if (position == 0) {
            holder.bindHeader(paymentMethod.getName());
            return;
        }
        holder.bindData(paymentMethod.getUrlLogo(), paymentMethod.getName());
    }
}
