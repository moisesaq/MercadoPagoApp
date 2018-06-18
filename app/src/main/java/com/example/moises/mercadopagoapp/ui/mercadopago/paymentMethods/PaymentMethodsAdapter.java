package com.example.moises.mercadopagoapp.ui.mercadopago.paymentMethods;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;

import com.example.moises.mercadopagoapp.R;
import com.example.moises.mercadopagoapp.model.paymentMethod.PaymentMethod;
import com.example.moises.mercadopagoapp.ui.mercadopago.utils.ViewHolder;

public class PaymentMethodsAdapter extends ArrayAdapter<PaymentMethod> {

    private final Context context;

    public PaymentMethodsAdapter(@NonNull Context context) {
        super(context, R.layout.payment_item);
        this.context = context;
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        return createItemView(position, convertView, parent);
    }

    @Override
    public View getDropDownView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        return createItemView(position, convertView, parent);
    }

    private View createItemView(int position, View view, ViewGroup parent) {
        ViewHolder holder;
        if (view == null) {
            view = LayoutInflater.from(context).inflate(R.layout.payment_item, parent, false);
            holder = new ViewHolder(context, view);
            view.setTag(holder);
        } else {
            holder = (ViewHolder) view.getTag();
        }
        PaymentMethod paymentMethod = getItem(position);
        if (paymentMethod != null)
            holder.bind(paymentMethod.getUrlLogo(), paymentMethod.getName());
        return view;
    }
}
