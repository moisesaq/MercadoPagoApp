package com.example.moises.mercadopagoapp.model.cardIssuer;

import android.os.Parcel;
import android.os.Parcelable;

public class CardIssuer implements Parcelable {
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

        Builder urlImage(String val) {
            urlImage = val;
            return this;
        }

        public CardIssuer build() {
            return new CardIssuer(this);
        }
    }


    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(this.id);
        dest.writeString(this.name);
        dest.writeString(this.urlImage);
    }

    private CardIssuer(Parcel in) {
        this.id = in.readString();
        this.name = in.readString();
        this.urlImage = in.readString();
    }

    public static final Parcelable.Creator<CardIssuer> CREATOR = new Parcelable.Creator<CardIssuer>() {
        @Override
        public CardIssuer createFromParcel(Parcel source) {
            return new CardIssuer(source);
        }

        @Override
        public CardIssuer[] newArray(int size) {
            return new CardIssuer[size];
        }
    };
}
