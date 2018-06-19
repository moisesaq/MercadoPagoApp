package com.example.moises.mercadopagoapp.model;

import android.os.Parcel;
import android.os.Parcelable;

import com.example.moises.mercadopagoapp.model.cardIssuer.CardIssuer;
import com.example.moises.mercadopagoapp.model.paymentMethod.PaymentMethod;

public class Payment implements Parcelable{
    private double amount;
    private PaymentMethod paymentMethod;
    private CardIssuer cardIssuer;

    private Payment(Builder builder) {
        amount = builder.amount;
    }

    public double getAmount() {
        return amount;
    }

    public PaymentMethod getPaymentMethod() {
        return paymentMethod;
    }

    public void setPaymentMethod(PaymentMethod paymentMethod) {
        this.paymentMethod = paymentMethod;
    }

    public CardIssuer getCardIssuer() {
        return cardIssuer;
    }

    public void setCardIssuer(CardIssuer cardIssuer) {
        this.cardIssuer = cardIssuer;
    }

    public static final class Builder {
        private double amount;

        public Builder() {
        }

        public Builder amount(double val) {
            amount = val;
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
        dest.writeParcelable(this.paymentMethod, flags);
        dest.writeParcelable(this.cardIssuer, flags);
    }

    protected Payment(Parcel in) {
        this.amount = in.readDouble();
        this.paymentMethod = in.readParcelable(PaymentMethod.class.getClassLoader());
        this.cardIssuer = in.readParcelable(CardIssuer.class.getClassLoader());
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
