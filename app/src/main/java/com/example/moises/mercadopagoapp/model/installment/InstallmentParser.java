package com.example.moises.mercadopagoapp.model.installment;

import com.example.moises.mercadopagoapp.model.ParserContract;

import java.util.ArrayList;
import java.util.List;

public class InstallmentParser implements ParserContract<List<Installment>>{

    public List<PayerCost> payer_costs;
    public String processing_mode;
    public Issuer issuer;
    public String payment_type_id;
    public String payment_method_id;

    @Override
    public List<Installment> getItem() {
        return getInstallments();
    }

    private List<Installment> getInstallments(){
        List<Installment> installments = new ArrayList<>();
        for (PayerCost payerCost: payer_costs)
            installments.add(parsePayerCostToInstallment(payerCost));
        return installments;
    }

    private Installment parsePayerCostToInstallment(InstallmentParser.PayerCost payerCost){
        return new Installment.Builder()
                .recommendedMessage(payerCost.recommended_message)
                .build();
    }

    public static class PayerCost {
        public double total_amount;
        public double installment_amount;
        public String recommended_message;
        public double max_allowed_amount;
        public double min_allowed_amount;
        public List<String> installment_rate_collector;
        public List<String> labels;
        public double discount_rate;
        public double installment_rate;
        public int installments;
    }

    public static class Issuer {
        public String thumbnail;
        public String secure_thumbnail;
        public String name;
        public String id;
    }
}
