package com.example.moises.mercadopagoapp.data;

import com.example.moises.mercadopagoapp.model.PaymentMethod;

import java.util.List;

import io.reactivex.Observable;

/**
 * Created by moises on 13/06/2018.
 *
 */

public interface DataContract {

    Observable<List<PaymentMethod>> getPaymentMethods();
}
