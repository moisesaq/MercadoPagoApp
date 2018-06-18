package com.example.moises.mercadopagoapp.ui.mercadopago.utils;

import android.content.Context;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.request.RequestOptions;
import com.example.moises.mercadopagoapp.R;

import butterknife.BindView;
import butterknife.ButterKnife;

public class ViewHolder {
    private final Context context;

    @BindView(R.id.iv_icon)
    protected ImageView imageView;
    @BindView(R.id.tv_name)
    protected TextView textView;

    public ViewHolder(Context context, View view) {
        this.context = context;
        ButterKnife.bind(this, view);
    }

    public void bind(String urlImage, String name) {
        loadImage(urlImage);
        textView.setText(name);
    }

    private void loadImage(String urlImage) {
        Glide.with(context)
                .load(urlImage)
                .apply(createOptions())
                .into(imageView);
    }

    private RequestOptions createOptions() {
        return new RequestOptions().error(R.mipmap.ic_launcher).placeholder(R.mipmap.ic_launcher);
    }
}
