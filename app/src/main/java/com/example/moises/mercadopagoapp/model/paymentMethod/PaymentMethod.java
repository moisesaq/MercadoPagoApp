package com.example.moises.mercadopagoapp.model.paymentMethod;

public class PaymentMethod {
    private String id;
    private String name;
    private String urlLogo;

    private PaymentMethod(Builder builder) {
        id = builder.id;
        name = builder.name;
        urlLogo = builder.urlLogo;
    }

    public String getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public String getUrlLogo() {
        return urlLogo;
    }

    public static final class Builder {
        private String id;
        private String name;
        private String urlLogo;

        public Builder() {
        }

        public Builder id(String val) {
            id = val;
            return this;
        }

        public Builder name(String val) {
            name = val;
            return this;
        }

        public Builder urlLogo(String val) {
            urlLogo = val;
            return this;
        }

        public PaymentMethod build() {
            return new PaymentMethod(this);
        }
    }
}
