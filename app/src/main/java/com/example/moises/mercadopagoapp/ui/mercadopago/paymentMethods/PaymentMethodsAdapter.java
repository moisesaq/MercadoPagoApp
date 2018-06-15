package com.example.moises.mercadopagoapp.ui.mercadopago.paymentMethods;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.request.RequestOptions;
import com.example.moises.mercadopagoapp.R;
import com.example.moises.mercadopagoapp.model.PaymentMethod;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

public class PaymentMethodsAdapter extends RecyclerView.Adapter<PaymentMethodsAdapter.PaymentViewHolder> {

    private Context context;
    private List<PaymentMethod> paymentMethods;

    public PaymentMethodsAdapter(Context context) {
        this.context = context;
        paymentMethods = new ArrayList<>();
    }

    @NonNull
    @Override
    public PaymentViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.payment_method_item, parent, false);
        return new PaymentViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull PaymentViewHolder holder, int position) {
        holder.bind(paymentMethods.get(position));
    }

    @Override
    public int getItemCount() {
        return paymentMethods.size();
    }

    void addItems(List<PaymentMethod> items) {
        paymentMethods = items;
        notifyDataSetChanged();
    }

    public PaymentMethod getItem(int position) {
        return paymentMethods.get(position);
    }

    class PaymentViewHolder extends RecyclerView.ViewHolder {

        @BindView(R.id.image_view_logo)
        ImageView imageViewLogo;
        @BindView(R.id.text_view_name)
        TextView textViewName;

        public PaymentViewHolder(View view) {
            super(view);
            ButterKnife.bind(this, view);
        }

        private void bind(PaymentMethod paymentMethod) {
            textViewName.setText(paymentMethod.getName());
            loadImage(paymentMethod.getUrlLogo(), imageViewLogo);
        }

        void loadImage(String urlImage, ImageView imageView) {
            Glide.with(context)
                    .load(urlImage)
                    .apply(createOptions())
                    .into(imageView);
        }

        private RequestOptions createOptions() {
            return new RequestOptions().error(R.mipmap.ic_launcher).placeholder(R.mipmap.ic_launcher);
        }
    }
}
