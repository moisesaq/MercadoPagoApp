package com.example.moises.mercadopagoapp.data;

import com.example.moises.mercadopagoapp.model.cardIssuer.CardIssuerParser;
import com.example.moises.mercadopagoapp.model.installment.InstallmentParser;
import com.example.moises.mercadopagoapp.model.paymentMethod.PaymentMethodParser;

import java.util.List;

import io.reactivex.Single;
import retrofit2.http.GET;
import retrofit2.http.Query;

public interface APIServices {

    /**
     * Queries
     */
    String AMOUNT = "amount";
    String PAYMENT_METHOD_ID = "payment_method_id";
    String ISSUER_ID = "issuer.id";

    /**
     * URLs
     */
    String URL_V1 = "v1/";
    String URL_PAYMENT_METHODS = URL_V1 + "payment_methods/";
    String URL_CARD_ISSUERS = URL_PAYMENT_METHODS + "card_issuers/";
    String URL_INSTALLMENTS = URL_PAYMENT_METHODS + "installments/";

    @GET(URL_PAYMENT_METHODS)
    Single<List<PaymentMethodParser>> getPaymentMethods(@Query("public_key") String key);

    @GET(URL_CARD_ISSUERS)
    Single<List<CardIssuerParser>> getCardIssuers(@Query("public_key") String key,
                                                  @Query(PAYMENT_METHOD_ID) String paymentMethodId);

    @GET(URL_INSTALLMENTS)
    Single<List<InstallmentParser>> getInstallments(@Query("public_key") String key,
                                              @Query(AMOUNT) Double amount,
                                              @Query(PAYMENT_METHOD_ID) String paymentMethodId,
                                              @Query(ISSUER_ID) String issuerId);
}
