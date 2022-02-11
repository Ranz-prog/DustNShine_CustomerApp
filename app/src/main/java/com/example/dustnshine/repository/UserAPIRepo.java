package com.example.dustnshine.repository;

import android.util.Log;

import androidx.lifecycle.MutableLiveData;

import com.example.dustnshine.api.RetrofitClient;
import com.example.dustnshine.models.DefaultResponse;
import com.example.dustnshine.models.LoginResponse;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class UserAPIRepo {

    private LoginResponse loginResponse;
    private DefaultResponse defaultResponse;
    MutableLiveData<LoginResponse> mutableLiveData = new MutableLiveData<>();

    //SignIn request
    public void userSignIn(String email, String password){

        Call<LoginResponse> loginResponseCall = RetrofitClient.getInstance().getApi().userLogin(email, password);

        loginResponseCall.enqueue(new Callback<LoginResponse>() {
            @Override
            public void onResponse(Call<LoginResponse> call, Response<LoginResponse> response) {

//                loginResponse = response.body();

                if(response.code() == 200){
                    mutableLiveData.postValue(response.body());
                    Log.d("TAG", loginResponse.toString());
                }
                else {

                }
            }

            @Override
            public void onFailure(Call<LoginResponse> call, Throwable t) {

            }
        });

    }

    //Register user
    public MutableLiveData<DefaultResponse> userSignUp(String firstName, String lastName, String mobileNumber, String email, String password, String password_confirmation){

        MutableLiveData<DefaultResponse> mutableLiveData = new MutableLiveData<>();

        Call<DefaultResponse> call = RetrofitClient
                .getInstance()
                .getApi()
                .registerUser(firstName, lastName, mobileNumber, email, password, password_confirmation);

        call.enqueue(new Callback<DefaultResponse>() {
            @Override
            public void onResponse(Call<DefaultResponse> call, Response<DefaultResponse> response) {
//                defaultResponse = response.body();

                if(response.code() == 201){
                    // Showing the dialog here
                    mutableLiveData.setValue(response.body());
                }if (response.code() == 422) {

                }else {

                }
            }

            @Override
            public void onFailure(Call<DefaultResponse> call, Throwable t) {

            }
        });
        return mutableLiveData;
    }
}
