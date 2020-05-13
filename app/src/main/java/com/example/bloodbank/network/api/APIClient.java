package com.example.bloodbank.network.api;

import com.example.bloodbank.network.services.ApiService;

import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

import static com.example.bloodbank.network.api.APIConstants.BASE_URL;

public class APIClient {

    private static Retrofit retrofit = null;

    public static ApiService getClient() {
        if (retrofit == null) {
            retrofit = new Retrofit
                    .Builder()
                    .addConverterFactory(GsonConverterFactory.create())
                    .baseUrl(BASE_URL)
                    .build();
        }
        return retrofit.create(ApiService.class);
    }
}
