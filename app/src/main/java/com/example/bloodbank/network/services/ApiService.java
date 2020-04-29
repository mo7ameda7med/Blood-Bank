package com.example.bloodbank.network.services;

import com.example.bloodbank.network.models.cities.Cities;
import com.example.bloodbank.network.models.login.Client;
import com.example.bloodbank.network.models.login.Login;
import com.example.bloodbank.network.models.newPassword.NewPassword;


import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.Query;

import static com.example.bloodbank.network.api.APIConstants.SERVICE_BLOOD_TYPES;
import static com.example.bloodbank.network.api.APIConstants.SERVICE_CITIES;
import static com.example.bloodbank.network.api.APIConstants.SERVICE_GOVERNORATES;
import static com.example.bloodbank.network.api.APIConstants.SERVICE_LOGIN;
import static com.example.bloodbank.network.api.APIConstants.SERVICE_NEW_PASSWORD;
import static com.example.bloodbank.network.api.APIConstants.SERVICE_REGISTER;
import static com.example.bloodbank.network.api.APIConstants.SERVICE_RESET_PASSWORD;

public interface ApiService {

    @GET(SERVICE_GOVERNORATES)
    Call<Cities> getGovernorates();

    @GET(SERVICE_CITIES)
    Call<Cities> getCities(@Query("governorate_id") int governorateId );

    @GET(SERVICE_BLOOD_TYPES)
    Call<Cities> getBloodType();

    @POST(SERVICE_REGISTER)
    Call<Login> registerClient(@Body Client signUp);

    @POST(SERVICE_LOGIN)
    Call<Login> loginClient(@Body Client login);

    @POST(SERVICE_RESET_PASSWORD)
    Call<NewPassword> resetPassword(@Body NewPassword resetPassword);

    @POST(SERVICE_NEW_PASSWORD)
    Call<Client> newPassword(@Body Client newPassword);

}
