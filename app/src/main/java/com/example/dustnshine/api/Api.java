package com.example.dustnshine.api;

import android.util.ArrayMap;

import com.example.dustnshine.models.SearchCompanyModel;
import com.example.dustnshine.response.BookedServiceResponse;
import com.example.dustnshine.response.BookingServiceResponse;
import com.example.dustnshine.response.CompanyResponse;
import com.example.dustnshine.response.FilteredServiceResponse;
import com.example.dustnshine.response.LogoutResponse;
import com.example.dustnshine.response.SearchCompanyResponse;
import com.example.dustnshine.response.ServiceResponse;
import com.example.dustnshine.response.SignUpResponse;
import com.example.dustnshine.response.SignInResponse;
import com.example.dustnshine.response.UserManagementResponse;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import retrofit2.Call;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.GET;
import retrofit2.http.Header;
import retrofit2.http.POST;
import retrofit2.http.Path;
import retrofit2.http.Query;

public interface Api {

    @FormUrlEncoded
    @POST("register")
    Call<SignUpResponse> userSignUp(
        @Field("first_name") String first_name,
        @Field("last_name") String last_name,
        @Field("mobile_number") String mobile_number,
        @Field("email") String email,
        @Field("house_number") String house_number,
        @Field("street") String street,
        @Field("barangay") String barangay,
        @Field("municipality") String municipality,
        @Field("province") String province,
        @Field("latitude") double latitude,
        @Field("longitude") double longitude,
        @Field("zipcode") String zipcode,
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
            @Field("services") ArrayList<Map<Integer, Integer>> services
    );

    @GET("companies")
    Call<CompanyResponse> getCompanies(@Header("Authorization") String companyRequest);

    @GET("services")
    Call<ServiceResponse> getServices(@Header("Authorization") String serviceRequest);

    @GET("bookings")
    Call<BookedServiceResponse> getBookedService(@Header("Authorization") String bookedServiceRequest);

    @GET("user-management")
    Call<UserManagementResponse> getUserInformation(@Header("Authorization") String userInformationRequest);

    @GET("search-company/{keyword}")
    Call<SearchCompanyResponse> getSearchedCompany(@Path("keyword") String keyword, @Header("Authorization") String searchCompanyRequest);

    @GET("filter-service/{service}")
    Call<FilteredServiceResponse> getFilteredService(@Path("service") int service, @Header("Authorization") String filterServiceRequest);

}
