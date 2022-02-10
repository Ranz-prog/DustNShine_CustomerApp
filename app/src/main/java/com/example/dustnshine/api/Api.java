package com.example.dustnshine.api;

import com.example.dustnshine.models.DefaultResponse;
import com.example.dustnshine.models.LoginResponse;

import retrofit2.Call;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.Headers;
import retrofit2.http.POST;

public interface Api {

    @FormUrlEncoded
    @Headers({"Accept: application/json"})
    @POST("register")
    Call<DefaultResponse> registerUser(
        @Field("first_name") String first_name,
        @Field("last_name") String last_name,
        @Field("mobile_number") String mobile_number,
        @Field("email") String email,
        @Field("password") String password,
        @Field("password_confirmation") String password_confirmation
    );

    @FormUrlEncoded
    @Headers({"Accept: application/json"})
    @POST("login")
    Call<LoginResponse> userLogin(
            @Field("email") String email,
            @Field("password") String password
            );

}
