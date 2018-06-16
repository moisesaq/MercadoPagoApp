package com.example.moises.mercadopagoapp.model;

import android.os.Parcel;
import android.os.Parcelable;

public class Payment implements Parcelable{
    private double amount;
    private String paymentMethodId;
    private String issuerId;

    private Payment(Builder builder) {
        amount = builder.amount;
        paymentMethodId = builder.paymentMethodId;
        issuerId = builder.issuerId;
    }

    public double getAmount() {
        return amount;
    }

    public String getPaymentMethodId() {
        return paymentMethodId;
    }

    public void setPaymentMethodId(String paymentMethodId) {
        this.paymentMethodId = paymentMethodId;
    }

    public String getIssuerId() {
        return issuerId;
    }

    public void setIssuerId(String issuerId) {
        this.issuerId = issuerId;
    }

    public static final class Builder {
        private double amount;
        private String paymentMethodId;
        private String issuerId;

        public Builder() {
        }

        public Builder amount(double val) {
            amount = val;
            return this;
        }

        public Builder paymentMethodId(String val) {
            paymentMethodId = val;
            return this;
        }

        public Builder issuerId(String val) {
            issuerId = val;
            return this;
        }

        public Payment build() {
            return new Payment(this);
        }
    }


    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeDouble(this.amount);
        dest.writeString(this.paymentMethodId);
        dest.writeString(this.issuerId);
    }

    protected Payment(Parcel in) {
        this.amount = in.readDouble();
        this.paymentMethodId = in.readString();
        this.issuerId = in.readString();
    }

    public static final Creator<Payment> CREATOR = new Creator<Payment>() {
        @Override
        public Payment createFromParcel(Parcel source) {
            return new Payment(source);
        }

        @Override
        public Payment[] newArray(int size) {
            return new Payment[size];
        }
    };
}
