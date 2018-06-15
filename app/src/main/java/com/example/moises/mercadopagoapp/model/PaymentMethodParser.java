package com.example.moises.mercadopagoapp.model;

import java.util.List;

/**
 * We should parser just the data necessary,
 * and that way local model is not depend of backend model
 */

public class PaymentMethodParser implements ParserContract<PaymentMethod>{

    public List<String> processing_modes;
    public List<String> financial_institutions;
    public int accreditation_time;
    public double max_allowed_amount;
    public double min_allowed_amount;
    public List<String> additional_info_needed;
    public List<Settings> settings;
    public String deferred_capture;
    public String thumbnail;
    public String secure_thumbnail;
    public String status;
    public String payment_type_id;
    public String name;
    public String id;

    @Override
    public PaymentMethod getItem() {
        return new PaymentMethod.Builder()
                .id(id)
                .name(name)
                .urlLogo(thumbnail)
                .build();
    }

    public static class Settings {
        public Bin bin;
        public Card_number card_number;
        public Security_code security_code;
    }

    public static class Bin {
        public String exclusion_pattern;
        public String installments_pattern;
        public String pattern;
    }

    public static class Card_number {
        public String validation;
        public int length;
    }

    public static class Security_code {
        public int length;
        public String card_location;
        public String mode;
    }
}
