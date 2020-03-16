package com.apps.malpv.dogs.api;

import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class ClientApiDog {

    private static Retrofit retrofit;
    private static final String BASE_URL = "https://dog.ceo/";

    public static Retrofit getRetrofitInstance(){
        if (retrofit == null){
            Retrofit.Builder builder = new Retrofit.Builder();
            builder.baseUrl(BASE_URL);
            builder.addConverterFactory(GsonConverterFactory.create());
            retrofit = builder
                    .build();
        }
        return retrofit;
    }
}
