package com.example.bloodbank.network.services;

import com.example.bloodbank.network.models.generalResponse.GeneralResponse;
import com.example.bloodbank.network.models.login.Auth;
import com.example.bloodbank.network.models.newPassword.NewPassword;
import com.example.bloodbank.network.models.posts.Posts;


import retrofit2.Call;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.Query;

import static com.example.bloodbank.network.api.APIConstants.SERVICE_BLOOD_TYPES;
import static com.example.bloodbank.network.api.APIConstants.SERVICE_CITIES;
import static com.example.bloodbank.network.api.APIConstants.SERVICE_GOVERNORATES;
import static com.example.bloodbank.network.api.APIConstants.SERVICE_LOGIN;
import static com.example.bloodbank.network.api.APIConstants.SERVICE_NEW_PASSWORD;
import static com.example.bloodbank.network.api.APIConstants.SERVICE_POSTS;
import static com.example.bloodbank.network.api.APIConstants.SERVICE_REGISTER;
import static com.example.bloodbank.network.api.APIConstants.SERVICE_RESET_PASSWORD;
import static com.example.bloodbank.network.api.APIConstants.SERVICE_post_toggle_favourite;

public interface ApiService {

    @GET(SERVICE_GOVERNORATES)
    Call<GeneralResponse> getGovernorates();

    @GET(SERVICE_CITIES)
    Call<GeneralResponse> getCities(@Query("governorate_id") int governorateId);

    @GET(SERVICE_BLOOD_TYPES)
    Call<GeneralResponse> getBloodType();

    @POST(SERVICE_REGISTER)
    @FormUrlEncoded
    Call<Auth> register(@Field("name") String name,
                        @Field("email") String email,
                        @Field("birth_date") String birthDate,
                        @Field("city_id") String city_id,
                        @Field("phone") String phone,
                        @Field("donation_last_date") String donationLastDate,
                        @Field("password") String pass,
                        @Field("password_confirmation") String newPassword,
                        @Field("blood_type_id") String blood_type_id);

    @POST(SERVICE_LOGIN)
    @FormUrlEncoded
    Call<Auth> login(@Field("phone") String phone,
                     @Field("password") String pass);

    @POST(SERVICE_RESET_PASSWORD)
    @FormUrlEncoded
    Call<NewPassword> resetPassword(@Field("phone") String phone);

    @POST(SERVICE_NEW_PASSWORD)
    @FormUrlEncoded
    Call<NewPassword> newPassword(@Field("phone") String phone,
                                  @Field("pin_code") String pin_code,
                                  @Field("password") String password,
                                  @Field("password_confirmation") String newPassword);

    @GET(SERVICE_POSTS)
    Call<Posts> getPost(@Query("api_token") String apiToken,
                        @Query("page") int page);

//    @POST(SERVICE_post_toggle_favourite)
//    Call<>


}
