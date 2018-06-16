package com.example.moises.mercadopagoapp.ui.mercadopago.paymentMethods;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.request.RequestOptions;
import com.example.moises.mercadopagoapp.R;
import com.example.moises.mercadopagoapp.model.paymentMethod.PaymentMethod;

import butterknife.BindView;
import butterknife.ButterKnife;

public class PaymentsAdapter extends ArrayAdapter<PaymentMethod> {

    private Context context;

    public PaymentsAdapter(@NonNull Context context) {
        super(context, R.layout.payment_method_item);
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
            view = LayoutInflater.from(context).inflate(R.layout.payment_method_item, parent, false);
            holder = new ViewHolder(view);
            view.setTag(holder);
        } else {
            holder = (ViewHolder) view.getTag();
        }
        PaymentMethod paymentMethod = getItem(position);
        loadImage(paymentMethod.getUrlLogo(), holder.imageView);
        holder.textView.setText(paymentMethod.getName());
        return view;
    }

    private void loadImage(String urlImage, ImageView imageView) {
        Glide.with(context)
                .load(urlImage)
                .apply(createOptions())
                .into(imageView);
    }

    private RequestOptions createOptions() {
        return new RequestOptions().error(R.mipmap.ic_launcher).placeholder(R.mipmap.ic_launcher);
    }

    protected class ViewHolder {
        @BindView(R.id.iv_icon)
        ImageView imageView;
        @BindView(R.id.tv_name)
        TextView textView;

        ViewHolder(View view) {
            ButterKnife.bind(this, view);
        }
    }
}
