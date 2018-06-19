package com.example.moises.mercadopagoapp.ui.base;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;

import com.example.moises.mercadopagoapp.R;
import com.example.moises.mercadopagoapp.ui.mercadopago.utils.ViewHolder;

public abstract class PaymentAdapter<T> extends ArrayAdapter<T>{

    protected final Context context;

    public PaymentAdapter(@NonNull Context context) {
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
        loadDataInViewHolder(holder, position);
        return view;
    }

    public abstract void loadDataInViewHolder(ViewHolder holder, int position);
}
