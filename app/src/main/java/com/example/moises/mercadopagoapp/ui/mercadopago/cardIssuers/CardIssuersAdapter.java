package com.example.moises.mercadopagoapp.ui.mercadopago.cardIssuers;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;

import com.example.moises.mercadopagoapp.R;
import com.example.moises.mercadopagoapp.model.cardIssuer.CardIssuer;
import com.example.moises.mercadopagoapp.ui.mercadopago.utils.ViewHolder;

public class CardIssuersAdapter extends ArrayAdapter<CardIssuer> {

    private final Context context;

    public CardIssuersAdapter(@NonNull Context context) {
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
        CardIssuer cardIssuer = getItem(position);
        if (cardIssuer != null)
            holder.bind(cardIssuer.getUrlImage(), cardIssuer.getName());
        return view;
    }
}
