package com.example.dustnshine.ui.home;

import android.util.Log;

import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.example.dustnshine.api.RetrofitClient;
import com.example.dustnshine.models.RecommendationModel;
import com.example.dustnshine.response.CompanyResponse;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class FragmentHomeViewModel extends ViewModel {


    private MutableLiveData<List<RecommendationModel>> companyList;

    public FragmentHomeViewModel(){
        companyList = new MutableLiveData<>();
    }

    public MutableLiveData<List<RecommendationModel>> getCompanyList(){
        return companyList;
    }

    public void makeAPiCall(String userToken){
        Call<CompanyResponse> companyResponseCall = RetrofitClient.getInstance().getApi().getCompanies("Bearer " + userToken);

        companyResponseCall.enqueue(new Callback<CompanyResponse>() {
            @Override
            public void onResponse(Call<CompanyResponse> call, Response<CompanyResponse> response) {
                companyList.postValue(response.body().getData());
                Log.d("TAG", response.body().getMessage());
            }

            @Override
            public void onFailure(Call<CompanyResponse> call, Throwable t) {
                Log.d("TAG", "ERROR");
            }
        });
    }
}
