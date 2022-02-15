package com.example.dustnshine.api;

import com.example.dustnshine.models.CompanyResponse;
import com.example.dustnshine.models.RecommendationModel;
import com.example.dustnshine.models.ServiceResponse;
import com.example.dustnshine.models.SignUpResponse;
import com.example.dustnshine.models.LoginResponse;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.GET;
import retrofit2.http.Headers;
import retrofit2.http.POST;

public interface Api {

    @FormUrlEncoded
    @POST("register")
    Call<SignUpResponse> registerUser(
        @Field("first_name") String first_name,
        @Field("last_name") String last_name,
        @Field("mobile_number") String mobile_number,
        @Field("email") String email,
        @Field("password") String password,
        @Field("password_confirmation") String password_confirmation
    );

    @FormUrlEncoded
    @POST("login")
    Call<LoginResponse> userLogin(
            @Field("email") String email,
            @Field("password") String password
            );

    @GET("companies")
    Call<CompanyResponse> getCompanies();

    @GET("services")
    Call<ServiceResponse> getAllServiceDetails(
    );
}
