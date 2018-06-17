package com.example.moises.mercadopagoapp.ui.mercadopago;

import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.moises.mercadopagoapp.R;

import butterknife.BindView;
import butterknife.ButterKnife;

public class ViewHolder {
    @BindView(R.id.iv_icon)
    public ImageView imageView;
    @BindView(R.id.tv_name)
    public TextView textView;

    public ViewHolder(View view) {
        ButterKnife.bind(this, view);
    }
}
