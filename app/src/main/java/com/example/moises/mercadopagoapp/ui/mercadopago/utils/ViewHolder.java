package com.example.moises.mercadopagoapp.ui.mercadopago.utils;

import android.content.Context;
import android.support.v7.widget.AppCompatImageView;
import android.view.View;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.request.RequestOptions;
import com.example.moises.mercadopagoapp.R;

import butterknife.BindColor;
import butterknife.BindView;
import butterknife.ButterKnife;

public class ViewHolder {
    private final Context context;

    @BindView(R.id.iv_icon)
    AppCompatImageView imageView;
    @BindView(R.id.tv_name)
    TextView textView;

    @BindColor(R.color.primaryText)
    int primaryText;
    @BindColor(R.color.secondaryText)
    int secondaryText;

    public ViewHolder(Context context, View view) {
        this.context = context;
        ButterKnife.bind(this, view);
    }

    public void bindHeader(String header) {
        imageView.setImageResource(R.drawable.ic_card);
        textView.setTextColor(secondaryText);
        textView.setText(header);
    }

    public void bindData(String urlImage, String name) {
        loadImage(urlImage);
        textView.setTextColor(primaryText);
        textView.setText(name);
    }

    private void loadImage(String urlImage) {
        Glide.with(context)
                .load(urlImage)
                .apply(createOptions())
                .into(imageView);
    }

    private RequestOptions createOptions() {
        return new RequestOptions().error(R.drawable.ic_card).placeholder(R.drawable.ic_card);
    }
}
