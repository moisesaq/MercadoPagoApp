package com.example.moises.mercadopagoapp.data;

import com.example.moises.mercadopagoapp.BuildConfig;
import com.example.moises.mercadopagoapp.model.cardIssuer.CardIssuer;
import com.example.moises.mercadopagoapp.model.cardIssuer.CardIssuerParser;
import com.example.moises.mercadopagoapp.model.installment.Installment;
import com.example.moises.mercadopagoapp.model.paymentMethod.PaymentMethod;
import com.example.moises.mercadopagoapp.model.paymentMethod.PaymentMethodParser;

import java.util.List;

import io.reactivex.Observable;


/**
 * Created by moises on 13/06/2018.
 *
 */

public class DataManager implements DataContract{

    private APIServices apiServices;

    public DataManager(APIServices apiServices){
        this.apiServices = apiServices;
    }

    @Override
    public Observable<List<PaymentMethod>> getPaymentMethods() {
        return apiServices.getPaymentMethods(BuildConfig.PUBLIC_KEY).toObservable()
                .flatMapIterable(paymentMethodParsers -> paymentMethodParsers)
                .map(PaymentMethodParser::getItem)
                .toList().toObservable();
    }

    @Override
    public Observable<List<CardIssuer>> getCardIssuers(String paymentMethodId) {
        return apiServices.getCardIssuers(BuildConfig.PUBLIC_KEY, paymentMethodId).toObservable()
                .flatMapIterable(cardIssuerParsers -> cardIssuerParsers)
                .map(CardIssuerParser::getItem)
                .toList().toObservable();
    }

    @Override
    public Observable<List<Installment>> getInstallments(double amount, String paymentMethodId, String issuerId) {
        return apiServices.getInstallments(BuildConfig.PUBLIC_KEY, amount, paymentMethodId, issuerId).toObservable()
                .map(installmentParsers -> installmentParsers.get(0).getItem());
    }
}
