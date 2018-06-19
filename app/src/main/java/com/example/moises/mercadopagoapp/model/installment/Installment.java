package com.example.moises.mercadopagoapp.model.installment;

public class Installment {
    private String recommendedMessage;

    private Installment(Builder builder) {
        recommendedMessage = builder.recommendedMessage;
    }

    public String getRecommendedMessage() {
        return recommendedMessage;
    }

    public static final class Builder {
        private String recommendedMessage;

        public Builder() {
        }

        Builder recommendedMessage(String val) {
            recommendedMessage = val;
            return this;
        }

        public Installment build() {
            return new Installment(this);
        }
    }
}
