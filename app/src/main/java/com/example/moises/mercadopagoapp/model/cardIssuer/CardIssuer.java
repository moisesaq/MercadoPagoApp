package com.example.moises.mercadopagoapp.model.cardIssuer;

public class CardIssuer {
    private String id;
    private String name;
    private String urlImage;

    private CardIssuer(Builder builder) {
        id = builder.id;
        name = builder.name;
        urlImage = builder.urlImage;
    }

    public String getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public String getUrlImage() {
        return urlImage;
    }

    public static final class Builder {
        private String id;
        private String name;
        private String urlImage;

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

        public Builder urlImage(String val) {
            urlImage = val;
            return this;
        }

        public CardIssuer build() {
            return new CardIssuer(this);
        }
    }
}
