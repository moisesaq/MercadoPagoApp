package com.example.moises.mercadopagoapp.injection.app;

import com.example.moises.mercadopagoapp.BuildConfig;
import com.example.moises.mercadopagoapp.data.APIServices;
import com.example.moises.mercadopagoapp.data.PublicKeyInterceptor;
import com.jakewharton.retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory;

import java.util.concurrent.TimeUnit;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;
import okhttp3.OkHttpClient;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

@Module
class NetModule {

    private static final long DEFAULT_TIMEOUT = 20;

    @Provides
    @Singleton
    static APIServices provideApiServices(Retrofit retrofit){
        return retrofit.create(APIServices.class);
    }

    @Provides
    @Singleton
    static Retrofit provideRetrofit(OkHttpClient okHttpClient) {
        return new Retrofit.Builder()
                .baseUrl(BuildConfig.BASE_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                .client(okHttpClient)
                .build();
    }

    @Provides
    @Singleton
    static OkHttpClient provideOkHttpClient(HttpLoggingInterceptor loggingInterceptor,
                                            PublicKeyInterceptor publicKeyInterceptor){
        return new OkHttpClient.Builder()
                .addInterceptor(loggingInterceptor)
                .addInterceptor(publicKeyInterceptor)
                .connectTimeout(DEFAULT_TIMEOUT, TimeUnit.SECONDS)
                .writeTimeout(DEFAULT_TIMEOUT, TimeUnit.SECONDS)
                .readTimeout(DEFAULT_TIMEOUT, TimeUnit.SECONDS)
                .build();
    }

    @Provides
    @Singleton
    static HttpLoggingInterceptor provideHttpLoggingInterceptor() {
        HttpLoggingInterceptor httpLoggingInterceptor = new HttpLoggingInterceptor();
        httpLoggingInterceptor.setLevel(HttpLoggingInterceptor.Level.BODY);
        return httpLoggingInterceptor;
    }

    @Provides
    @Singleton
    static PublicKeyInterceptor providerPublicKeyIntereptor(){
        return new PublicKeyInterceptor();
    }
}
