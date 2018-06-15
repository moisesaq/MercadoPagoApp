package com.example.moises.mercadopagoapp.data;

import com.example.moises.mercadopagoapp.BuildConfig;
import com.example.moises.mercadopagoapp.model.PaymentMethod;
import com.example.moises.mercadopagoapp.model.PaymentMethodParser;

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
}
