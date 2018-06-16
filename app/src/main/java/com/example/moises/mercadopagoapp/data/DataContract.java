package com.example.moises.mercadopagoapp.data;

import com.example.moises.mercadopagoapp.model.cardIssuer.CardIssuer;
import com.example.moises.mercadopagoapp.model.installment.Installment;
import com.example.moises.mercadopagoapp.model.paymentMethod.PaymentMethod;

import java.util.List;

import io.reactivex.Observable;

public interface DataContract {

    Observable<List<PaymentMethod>> getPaymentMethods();

    Observable<List<CardIssuer>> getCardIssuers(String paymentMethodId);

    Observable<List<Installment>> getInstallments(double amount, String paymentMethodId, String issuerId);
}
