package com.example.dustnshine.repository;

import android.util.Log;

import androidx.lifecycle.MutableLiveData;

import com.example.dustnshine.api.RetrofitClient;
import com.example.dustnshine.models.BookingHistoryModel;
import com.example.dustnshine.models.BookingServiceData;
import com.example.dustnshine.models.RecommendationModel;
import com.example.dustnshine.models.ServicesModel;
import com.example.dustnshine.response.BookedServiceResponse;
import com.example.dustnshine.response.BookingHistoryResponse;
import com.example.dustnshine.response.BookingServiceResponse;
import com.example.dustnshine.response.CompanyResponse;
import com.example.dustnshine.response.FilteredServiceResponse;
import com.example.dustnshine.response.SearchCompanyResponse;
import com.example.dustnshine.response.ServiceResponse;

import java.util.List;
import java.util.Map;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class BookingAPIRepo {

    public MutableLiveData<BookingServiceResponse> bookingRequest(String userToken, int company_id, String address, String start_datetime, int total, List<Map<Integer, Integer>> services, String notes){

        final MutableLiveData<BookingServiceResponse> bookingServiceResponseMutableLiveData = new MutableLiveData<>();
        Call<BookingServiceResponse> bookingServiceResponseCall = RetrofitClient.getInstance().getApi().bookService("Bearer " + userToken, company_id, address, start_datetime, total, services, notes);
        bookingServiceResponseCall.enqueue(new Callback<BookingServiceResponse>() {
            @Override
            public void onResponse(Call<BookingServiceResponse> call, Response<BookingServiceResponse> response) {
                Log.d("Services", String.valueOf(call.request()));
                if(response.code() == 200){
                    bookingServiceResponseMutableLiveData.setValue(response.body());
                    Log.d("STATUS", String.valueOf(response.code()));
                } else if(response.code() == 422){
                    bookingServiceResponseMutableLiveData.setValue(response.body());
                    Log.d("STATUS", String.valueOf(response.code()));
                } else {
                    Log.d("STATUS", String.valueOf(response.code()));
                }
            }
            @Override
            public void onFailure(Call<BookingServiceResponse> call, Throwable t) {
                Log.d("FAILURE", "Failure to connect");
            }
        });

        return bookingServiceResponseMutableLiveData;
    }

    public MutableLiveData<List<ServicesModel>> getServices(String userToken){
        final MutableLiveData<List<ServicesModel>> servicesList = new MutableLiveData<>();
        Call<ServiceResponse> serviceResponseCall = RetrofitClient.getInstance().getApi().getServices("Bearer " + userToken);
        serviceResponseCall.enqueue(new Callback<ServiceResponse>() {
            @Override
            public void onResponse(Call<ServiceResponse> call, Response<ServiceResponse> response) {
                if(response.code() == 200){
                    servicesList.setValue(response.body().getData());
                    Log.d("TAG", "Success");
                } else {
                    Log.d("TAG", response.body().getMessage());
                }
            }

            @Override
            public void onFailure(Call<ServiceResponse> call, Throwable t) {
                Log.d("FAILURE", "Failure to connect");
            }
        });
        return servicesList;
    }

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
                    Log.d("TAG", response.body().getMessage());
                }
            }

            @Override
            public void onFailure(Call<CompanyResponse> call, Throwable t) {

            }
        });
        return companyList;
    }

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

    public MutableLiveData<List<RecommendationModel>> getSearchedCompany(String companyName, String userToken){
        final MutableLiveData<List<RecommendationModel>> searchedCompany = new MutableLiveData<>();
        Call<SearchCompanyResponse> searchCompanyResponseCall = RetrofitClient.getInstance().getApi().getSearchedCompany(companyName, "Bearer " + userToken);
        searchCompanyResponseCall.enqueue(new Callback<SearchCompanyResponse>() {
            @Override
            public void onResponse(Call<SearchCompanyResponse> call, Response<SearchCompanyResponse> response) {
                if(response.code() == 200){
                    searchedCompany.postValue(response.body().getData());
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
                Log.d("TAG", "Failure to connect");
            }
        });
        return bookingHistory;
    }

}
