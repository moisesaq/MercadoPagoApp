package com.example.moises.mercadopagoapp.ui.mercadopago.installments;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.AppCompatImageView;
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

public class InstallmentsAdapter extends RecyclerView.Adapter<InstallmentsAdapter.InstallmentViewHolder> {

    private final Context context;
    private List<Installment> installments;
    private OnInstallmentsAdapterListener listener;
    private int positionInstallmentSelected = -1;

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
        holder.bind(installments.get(position), checkVisibility(position));
    }

    private boolean checkVisibility(int position) {
        return positionInstallmentSelected == position;
    }

    @Override
    public int getItemCount() {
        return installments.size();
    }

    void addItems(List<Installment> items) {
        installments = items;
        notifyDataSetChanged();
    }

    private void changeSelected(int position) {
        positionInstallmentSelected = position;
        notifyDataSetChanged();
    }

    private Installment getItem(int position) {
        return installments.get(position);
    }

    void setListener(OnInstallmentsAdapterListener listener) {
        this.listener = listener;
    }

    class InstallmentViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {

        @BindView(R.id.tv_recommended_message)
        TextView tvRecommendedMessage;
        @BindView(R.id.iv_check)
        AppCompatImageView ivCheck;

        InstallmentViewHolder(View view) {
            super(view);
            ButterKnife.bind(this, view);
            view.setOnClickListener(this);
        }

        private void bind(Installment installment, boolean checked) {
            tvRecommendedMessage.setText(installment.getRecommendedMessage());
            ivCheck.setVisibility(checked ? View.VISIBLE : View.INVISIBLE);
        }

        @Override
        public void onClick(View v) {
            int position = getAdapterPosition();
            changeSelected(position);
            listener.onInstallmentClick(getItem(position));
        }
    }

    public interface OnInstallmentsAdapterListener {
        void onInstallmentClick(Installment installment);
    }
}
