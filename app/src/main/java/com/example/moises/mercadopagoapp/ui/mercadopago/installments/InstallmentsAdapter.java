package com.example.moises.mercadopagoapp.ui.mercadopago.installments;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.moises.mercadopagoapp.R;
import com.example.moises.mercadopagoapp.model.installment.Installment;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class InstallmentsAdapter extends RecyclerView.Adapter<InstallmentsAdapter.InstallmentViewHolder> {

    private Context context;
    private List<Installment> installments;
    private OnInstallmentsAdapterListener listener;

    public InstallmentsAdapter(Context context) {
        this.context = context;
        installments = new ArrayList<>();
    }

    @NonNull
    @Override
    public InstallmentViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.installment_item, parent, false);
        return new InstallmentViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull InstallmentViewHolder holder, int position) {
        holder.bind(installments.get(position));
    }

    @Override
    public int getItemCount() {
        return installments.size();
    }

    void addItems(List<Installment> items) {
        installments = items;
        notifyDataSetChanged();
    }

    private Installment getItem(int position) {
        return installments.get(position);
    }

    public void setListener(OnInstallmentsAdapterListener listener){
        this.listener = listener;
    }

    class InstallmentViewHolder extends RecyclerView.ViewHolder {

        @BindView(R.id.tv_recommended_message)
        TextView tvRecommendedMessage;

        InstallmentViewHolder(View view) {
            super(view);
            ButterKnife.bind(this, view);
        }

        private void bind(Installment installment) {
            tvRecommendedMessage.setText(installment.getRecommendedMessage());
        }

        @OnClick(R.id.layout_installment)
        public void OnInstallmentClick() {
            if (listener != null)
                listener.onInstallmentClick(getItem(getAdapterPosition()));
        }
    }

    public interface OnInstallmentsAdapterListener {
        void onInstallmentClick(Installment installment);
    }
}
