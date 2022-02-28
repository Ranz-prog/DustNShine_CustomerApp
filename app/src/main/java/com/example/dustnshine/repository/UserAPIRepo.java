package com.example.dustnshine.repository;

import android.util.Log;

import androidx.lifecycle.MutableLiveData;

import com.example.dustnshine.api.RetrofitClient;
import com.example.dustnshine.response.LogoutResponse;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class UserAPIRepo {

    public MutableLiveData<LogoutResponse> signOutRequest(String userToken){
        final MutableLiveData<LogoutResponse> logoutResponseMutableLiveData = new MutableLiveData<>();

        Call<LogoutResponse> logoutResponseCall = RetrofitClient.getInstance().getApi().userLogOut("Bearer " + userToken);
        logoutResponseCall.enqueue(new Callback<LogoutResponse>() {
            @Override
            public void onResponse(Call<LogoutResponse> call, Response<LogoutResponse> response) {
                if(response.code() == 200){
                    logoutResponseMutableLiveData.setValue(response.body());
                } if (response.code() == 401){
                    Log.d("FAILURE", "Unauthenticated");
                }
            }

            @Override
            public void onFailure(Call<LogoutResponse> call, Throwable t) {
                Log.d("FAILURE", "Failure to connect");
            }
        });

        return  logoutResponseMutableLiveData;
    }

}
