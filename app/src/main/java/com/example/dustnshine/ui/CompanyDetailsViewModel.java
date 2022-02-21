package com.example.dustnshine.ui;

import android.util.Log;

import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.example.dustnshine.api.RetrofitClient;
import com.example.dustnshine.models.RecommendationModel;
import com.example.dustnshine.models.ServicesModel;
import com.example.dustnshine.response.ServiceResponse;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class CompanyDetailsViewModel extends ViewModel {
    private MutableLiveData<List<ServicesModel>> serviceList;

    public CompanyDetailsViewModel() {
        serviceList = new MutableLiveData<>();
    }

    public MutableLiveData<List<ServicesModel>> getServiceList(){
        return serviceList;
    }

    public void makeAPICall(String userToken) {
        Call<ServiceResponse> serviceResponseCall = RetrofitClient.getInstance().getApi().getServices("Bearer " + userToken);
        serviceResponseCall.enqueue(new Callback<ServiceResponse>() {
            @Override
            public void onResponse(Call<ServiceResponse> call, Response<ServiceResponse> response) {
                serviceList.postValue(response.body().getData());
                Log.d("TAG", response.body().getMessage());
            }

            @Override
            public void onFailure(Call<ServiceResponse> call, Throwable t) {
                Log.d("TAG", "ERROR");
            }
        });

    }
}
