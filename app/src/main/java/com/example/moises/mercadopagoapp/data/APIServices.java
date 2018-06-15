package com.example.moises.mercadopagoapp.data;

import com.example.moises.mercadopagoapp.model.PaymentMethodParser;

import java.util.List;

import io.reactivex.Single;
import retrofit2.http.GET;
import retrofit2.http.Query;

public interface APIServices {

    String URL_V1 = "v1/";
    String URL_PAYMENT_METHODS = URL_V1 + "payment_methods/";
    String URL_CARD_ISSUERS = URL_PAYMENT_METHODS + "card_issuers/";
    String URL_INSTALLMENTS = URL_PAYMENT_METHODS + "installments/";

    @GET(URL_PAYMENT_METHODS)
    Single<List<PaymentMethodParser>> getPaymentMethods(@Query("public_key") String key);
}
