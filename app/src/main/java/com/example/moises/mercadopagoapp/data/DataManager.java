package com.example.moises.mercadopagoapp.data;

/**
 * Created by moises on 13/06/2018.
 *
 */

public class DataManager implements DataContract{

    private APIServices apiServices;

    public DataManager(APIServices apiServices){
        this.apiServices = apiServices;
    }
}
