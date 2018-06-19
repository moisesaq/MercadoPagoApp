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
import com.example.moises.mercadopagoapp.ui.base.PaymentAdapter;
import com.example.moises.mercadopagoapp.ui.mercadopago.utils.ViewHolder;

public class CardIssuersAdapter extends PaymentAdapter<CardIssuer> {

    public CardIssuersAdapter(@NonNull Context context) {
        super(context);
    }

    @Override
    public void loadDataInViewHolder(ViewHolder holder, int position) {
        CardIssuer cardIssuer = getItem(position);
        if (cardIssuer == null)
            return;

        if (position == 0) {
            holder.bindHeader(cardIssuer.getName());
            return;
        }
        holder.bindData(cardIssuer.getUrlImage(), cardIssuer.getName());
    }
}
