package com.example.dustnshine.api;

<<<<<<< HEAD
import com.example.dustnshine.response.BookedServiceResponse;
import com.example.dustnshine.response.BookingServiceResponse;
import com.example.dustnshine.response.CompanyResponse;
import com.example.dustnshine.response.LogoutResponse;
import com.example.dustnshine.response.ServiceResponse;
import com.example.dustnshine.response.SignUpResponse;
import com.example.dustnshine.response.SignInResponse;

import java.util.List;
import java.util.Map;
=======
import com.example.dustnshine.models.CompanyResponse;
import  com.example.dustnshine.models.DefaultResponse;
import com.example.dustnshine.models.LoginResponse;
import com.example.dustnshine.models.ServiceResponse;

import java.util.List;
>>>>>>> branch_jericho

import retrofit2.Call;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.GET;
<<<<<<< HEAD
import retrofit2.http.Header;
=======
import retrofit2.http.Headers;
>>>>>>> branch_jericho
import retrofit2.http.POST;

public interface Api {

    @FormUrlEncoded
    @POST("register")
    Call<SignUpResponse> userSignUp(
        @Field("first_name") String first_name,
        @Field("last_name") String last_name,
        @Field("mobile_number") String mobile_number,
        @Field("email") String email,
        @Field("password") String password,
        @Field("password_confirmation") String password_confirmation
    );

    @FormUrlEncoded
    @POST("login")
    Call<SignInResponse> userSignIn(
            @Field("email") String email,
            @Field("password") String password
            );

    @POST("logout")
    Call<LogoutResponse> userLogOut(@Header("Authorization") String logoutRequest);

    @FormUrlEncoded
    @POST("bookings")
    Call<BookingServiceResponse> bookService(
            @Header("Authorization") String bookingRequest,
            @Field("company_id") int company_id,
            @Field("address") String address,
            @Field("start_datetime") String start_datetime,
            @Field("total") int total,
            @Field("services") List<Map<Integer, Integer>> services
    );

    @GET("services")
    Call<ServiceResponse> getAllServiceDetails(
    );

    @GET("companies")
<<<<<<< HEAD
    Call<CompanyResponse> getCompanies(@Header("Authorization") String companyRequest);

    @GET("services")
    Call<ServiceResponse> getAllServiceDetails(@Header("Authorization") String serviceRequest);

    @GET("bookings")
    Call<BookedServiceResponse> getBookedService(@Header("Authorization") String bookedServiceRequest);



=======
    Call<CompanyResponse> getAllCompanies(
    );
>>>>>>> branch_jericho
}
