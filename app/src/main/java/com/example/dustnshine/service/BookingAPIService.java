package com.example.dustnshine.service;

import android.util.Log;

import androidx.lifecycle.MutableLiveData;

import com.example.dustnshine.api.RetrofitClient;
import com.example.dustnshine.models.BookingHistoryModel;
import com.example.dustnshine.models.BookingServiceData;
import com.example.dustnshine.models.CompanyAndServicesModel;
import com.example.dustnshine.models.NotificationModel;
import com.example.dustnshine.models.RecommendationModel;
import com.example.dustnshine.models.RecommendedCompaniesModel;
import com.example.dustnshine.models.ServicesModel;
import com.example.dustnshine.response.BookedServiceResponse;
import com.example.dustnshine.response.BookingHistoryResponse;
import com.example.dustnshine.response.BookingServiceResponse;
import com.example.dustnshine.response.CompanyAndServiceResponse;
import com.example.dustnshine.response.CompanyResponse;
import com.example.dustnshine.response.NotificationResponse;
import com.example.dustnshine.response.RecommendedCompaniesResponse;
import com.example.dustnshine.response.ReviewResponse;
import com.example.dustnshine.response.FilteredServiceResponse;
import com.example.dustnshine.response.SearchCompanyResponse;
import com.example.dustnshine.response.ServiceResponse;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class BookingAPIService {

    // Booking Request
    public MutableLiveData<BookingServiceResponse> bookingRequest(String userToken, int company_id, String address, String start_datetime, int total, List<Integer> services, String notes){
        final MutableLiveData<BookingServiceResponse> bookingServiceResponseMutableLiveData = new MutableLiveData<>();
        Call<BookingServiceResponse> bookingServiceResponseCall = RetrofitClient.getInstance().getApi().bookService("Bearer " + userToken, company_id, address, start_datetime, total, services, notes);
        bookingServiceResponseCall.enqueue(new Callback<BookingServiceResponse>() {
            @Override
            public void onResponse(Call<BookingServiceResponse> call, Response<BookingServiceResponse> response) {
                Log.d("Services", String.valueOf(call.request()));
                if(response.code() == 200){
                    bookingServiceResponseMutableLiveData.setValue(response.body());
                } else if(response.code() == 422){
                    bookingServiceResponseMutableLiveData.setValue(response.body());
                } else if(response.code() == 404) {
                    bookingServiceResponseMutableLiveData.setValue(response.body());
                } else {
                    bookingServiceResponseMutableLiveData.setValue(response.body());
                }
            }
            @Override
            public void onFailure(Call<BookingServiceResponse> call, Throwable t) {
                Log.d("FAILURE", "Failure to connect");
            }
        });

        return bookingServiceResponseMutableLiveData;
    }

    // Get Services
    public MutableLiveData<List<ServicesModel>> getServices(String userToken){
        final MutableLiveData<List<ServicesModel>> servicesList = new MutableLiveData<>();
        Call<ServiceResponse> serviceResponseCall = RetrofitClient.getInstance().getApi().getServices("Bearer " + userToken);
        serviceResponseCall.enqueue(new Callback<ServiceResponse>() {
            @Override
            public void onResponse(Call<ServiceResponse> call, Response<ServiceResponse> response) {
                if(response.code() == 200){
                    servicesList.setValue(response.body().getData());
                } else if (response.code() == 422) {
                    servicesList.setValue(response.body().getData());
                } else {
                    servicesList.setValue(response.body().getData());
                }
            }

            @Override
            public void onFailure(Call<ServiceResponse> call, Throwable t) {
                Log.d("FAILURE", "Failure to connect");
            }
        });
        return servicesList;
    }

    // Get Companies
    public MutableLiveData<List<RecommendationModel>> getCompanies(String userToken){
        final MutableLiveData<List<RecommendationModel>> companyList = new MutableLiveData<>();
        Call<CompanyResponse> companyResponseCall = RetrofitClient.getInstance().getApi().getCompanies("Bearer " + userToken);
        companyResponseCall.enqueue(new Callback<CompanyResponse>() {
            @Override
            public void onResponse(Call<CompanyResponse> call, Response<CompanyResponse> response) {
                if(response.code() == 200){
                    companyList.setValue(response.body().getData());
                    Log.d("TAG", "Success");
                } else {
                    Log.d("TAG", String.valueOf(response.code()));
                }
            }

            @Override
            public void onFailure(Call<CompanyResponse> call, Throwable t) {

            }
        });
        return companyList;
    }

    // Get Specific Company
    public MutableLiveData<CompanyAndServicesModel> getSpecificCompany( int companyId, String userToken ){
        final MutableLiveData<CompanyAndServicesModel> specificCompany = new MutableLiveData<>();
        Call<CompanyAndServiceResponse> companyResponseCall = RetrofitClient.getInstance().getApi().getSpecificCompany(companyId,"Bearer " + userToken );
        companyResponseCall.enqueue(new Callback<CompanyAndServiceResponse>() {
            @Override
            public void onResponse(Call<CompanyAndServiceResponse> call, Response<CompanyAndServiceResponse> response) {
                if(response.code() == 200){
                    specificCompany.setValue(response.body().getData());
                    Log.d("TAG", "Success");
                } else {
                    Log.d("TAG", String.valueOf(response.code()));
                }
            }

            @Override
            public void onFailure(Call<CompanyAndServiceResponse> call, Throwable t) {

            }
        });
        return specificCompany;
    }

    // Get Booked Services
    public MutableLiveData<List<BookingServiceData>> getBookedServices(String userToken){
        final MutableLiveData<List<BookingServiceData>> bookedServicesList = new MutableLiveData<>();
        Call<BookedServiceResponse> bookedServiceResponseCall = RetrofitClient.getInstance().getApi().getBookedService("Bearer " + userToken);
        bookedServiceResponseCall.enqueue(new Callback<BookedServiceResponse>() {
            @Override
            public void onResponse(Call<BookedServiceResponse> call, Response<BookedServiceResponse> response) {
                if(response.code() == 200){
                    bookedServicesList.setValue(response.body().getData());
                    Log.d("TAG", "Success");
                } else {

                }
            }

            @Override
            public void onFailure(Call<BookedServiceResponse> call, Throwable t) {
                Log.d("FAILURE", "Failure to connect");
            }
        });
        return bookedServicesList;
    }

    // Get SearchedCompany
    public MutableLiveData<List<RecommendationModel>> getSearchedCompany(String companyName, String userToken){
        final MutableLiveData<List<RecommendationModel>> searchedCompany = new MutableLiveData<>();
        Call<SearchCompanyResponse> searchCompanyResponseCall = RetrofitClient.getInstance().getApi().getSearchedCompany(companyName, "Bearer " + userToken);
        searchCompanyResponseCall.enqueue(new Callback<SearchCompanyResponse>() {
            @Override
            public void onResponse(Call<SearchCompanyResponse> call, Response<SearchCompanyResponse> response) {
                if(response.code() == 200){
                    searchedCompany.setValue(response.body().getData());
                    Log.d("TAG", "Success");
                } else {
                    Log.d("TAG", "Failed");
                }
            }

            @Override
            public void onFailure(Call<SearchCompanyResponse> call, Throwable t) {


            }
        });
        return searchedCompany;
    }

    //Get Filtered Service by Company
    public MutableLiveData<List<RecommendationModel>> getFilteredService(int service, String userToken){

        final MutableLiveData<List<RecommendationModel>> filteredService = new MutableLiveData<>();
        Call<FilteredServiceResponse> serviceResponseCall = RetrofitClient.getInstance().getApi().getFilteredService(service, "Bearer " + userToken);
        serviceResponseCall.enqueue(new Callback<FilteredServiceResponse>() {
            @Override
            public void onResponse(Call<FilteredServiceResponse> call, Response<FilteredServiceResponse> response) {
                if (response.code() == 200) {
                    filteredService.setValue(response.body().getData());
                    Log.d("TAG", "Success");
                } else {
                    Log.d("TAG", "Failed");
                }
            }

            @Override
            public void onFailure(Call<FilteredServiceResponse> call, Throwable t) {
                Log.d("TAG", "Failure to connect");
            }
        });
        return filteredService;
    }

    // Get Booking History
    public MutableLiveData<List<BookingHistoryModel>> getBookingHistory(String userToken){
        final MutableLiveData<List<BookingHistoryModel>> bookingHistory = new MutableLiveData<>();
        Call<BookingHistoryResponse> bookingHistoryResponseCall = RetrofitClient.getInstance().getApi().getBookingHistory("Bearer " + userToken);
        bookingHistoryResponseCall.enqueue(new Callback<BookingHistoryResponse>() {
            @Override
            public void onResponse(Call<BookingHistoryResponse> call, Response<BookingHistoryResponse> response) {
                if (response.code() == 200) {
                    bookingHistory.setValue(response.body().getData());
                    Log.d("TAG", "Success");
                } else {
                    Log.d("TAG", "Failed");
                }
            }

            @Override
            public void onFailure(Call<BookingHistoryResponse> call, Throwable t) {
                Log.d("TAG", t.getLocalizedMessage());
            }
        });
        return bookingHistory;
    }

    // Put Review
    public MutableLiveData<ReviewResponse> putReview(String userToken, int booking_id, String comment, double rating){
        final MutableLiveData<ReviewResponse> feedbackResponseMutableLiveData = new MutableLiveData<>();
        Call<ReviewResponse> feedbackResponseCall= RetrofitClient.getInstance().getApi().userReview("Bearer " + userToken, booking_id, comment, rating);
        feedbackResponseCall.enqueue(new Callback<ReviewResponse>() {
            @Override
            public void onResponse(Call<ReviewResponse> call, Response<ReviewResponse> response) {
                if (response.code() == 200) {
                    feedbackResponseMutableLiveData.setValue(response.body());
                } else {
                    feedbackResponseMutableLiveData.setValue(response.body());
                }
            }

            @Override
            public void onFailure(Call<ReviewResponse> call, Throwable t) {
                Log.d("TAG", "Failure to connect");
            }
        });
        return feedbackResponseMutableLiveData;
    }

    // Get Done Services
    public MutableLiveData<List<NotificationModel>> getDoneServices(String userToken){
        final MutableLiveData<List<NotificationModel>> doneServices = new MutableLiveData<>();
        Call<NotificationResponse> doneServicesResponseCall = RetrofitClient.getInstance().getApi().getDoneServices("Bearer " + userToken);
        doneServicesResponseCall.enqueue(new Callback<NotificationResponse>() {
            @Override
            public void onResponse(Call<NotificationResponse> call, Response<NotificationResponse> response) {
                if (response.code() == 200) {
                    doneServices.setValue(response.body().getData());
                    Log.d("CODE", String.valueOf(response.code()));
                } else {
                    Log.d("CODE", String.valueOf(response.code()));
                }
            }

            @Override
            public void onFailure(Call<NotificationResponse> call, Throwable t) {
                Log.d("TAG", t.getLocalizedMessage());
            }
        });
        return doneServices;
    }

    // Get Recommended Companies
    public MutableLiveData<List<RecommendedCompaniesModel>> getRecommendedCompanies(String userToken){
        final MutableLiveData<List<RecommendedCompaniesModel>> recommendedCompanies = new MutableLiveData<>();
        Call<RecommendedCompaniesResponse> recommendedCompaniesResponseCall = RetrofitClient.getInstance().getApi().getRecommendedCompanies("Bearer " + userToken);
        recommendedCompaniesResponseCall.enqueue(new Callback<RecommendedCompaniesResponse>() {
            @Override
            public void onResponse(Call<RecommendedCompaniesResponse> call, Response<RecommendedCompaniesResponse> response) {
                if (response.code() == 200) {
                    recommendedCompanies.setValue(response.body().getData());
                    Log.d("CODE", String.valueOf(response.code()));
                } else {
                    Log.d("TAG", "Failed");
                    Log.d("CODE", String.valueOf(response.code()));
                }
            }

            @Override
            public void onFailure(Call<RecommendedCompaniesResponse> call, Throwable t) {
                Log.d("CODE", t.getLocalizedMessage());
            }
        });

        return recommendedCompanies;
    }

}
