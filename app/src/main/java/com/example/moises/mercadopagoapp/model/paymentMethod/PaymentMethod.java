package com.example.moises.mercadopagoapp.model.paymentMethod;

import android.os.Parcel;
import android.os.Parcelable;

public class PaymentMethod implements Parcelable {
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

        Builder urlLogo(String val) {
            urlLogo = val;
            return this;
        }

        public PaymentMethod build() {
            return new PaymentMethod(this);
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
        dest.writeString(this.urlLogo);
    }

    private PaymentMethod(Parcel in) {
        this.id = in.readString();
        this.name = in.readString();
        this.urlLogo = in.readString();
    }

    public static final Parcelable.Creator<PaymentMethod> CREATOR = new Parcelable.Creator<PaymentMethod>() {
        @Override
        public PaymentMethod createFromParcel(Parcel source) {
            return new PaymentMethod(source);
        }

        @Override
        public PaymentMethod[] newArray(int size) {
            return new PaymentMethod[size];
        }
    };
}
