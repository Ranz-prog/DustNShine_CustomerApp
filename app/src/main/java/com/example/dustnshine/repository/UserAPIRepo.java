package com.example.dustnshine.repository;

import android.util.Log;

import androidx.lifecycle.MutableLiveData;

import com.example.dustnshine.SignInCallback;
import com.example.dustnshine.api.RetrofitClient;
import com.example.dustnshine.response.LogoutResponse;
import com.example.dustnshine.response.SignInResponse;
import com.example.dustnshine.response.SignUpResponse;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class UserAPIRepo {

    //SignIn
    public MutableLiveData<SignInResponse> signInRequest(String email, String password){
        final MutableLiveData<SignInResponse> signInResponseMutableLiveData = new MutableLiveData<>();
        Call<SignInResponse> signInResponseCall = RetrofitClient.getInstance().getApi().userSignIn(email, password);
        signInResponseCall.enqueue(new Callback<SignInResponse>() {
            @Override
            public void onResponse(Call<SignInResponse> call, Response<SignInResponse> response) {
                if(response.code() == 200){
                    signInResponseMutableLiveData.setValue(response.body());

                }
                else if (response.code() == 401){
                    signInResponseMutableLiveData.setValue(response.body());
                }
            }

            @Override
            public void onFailure(Call<SignInResponse> call, Throwable t) {
                Log.d("FAILURE", "Failure to connect");
            }
        });
        return signInResponseMutableLiveData;
    }

    //SignUp
    public MutableLiveData<SignUpResponse> signUpRequest(String firstName, String lastName, String mobileNumber, String email, String password, String passwordConfirmation){
        final MutableLiveData<SignUpResponse> signUpResponseMutableLiveData = new MutableLiveData<>();
        Call<SignUpResponse> signUpResponseCall = RetrofitClient.getInstance().getApi().userSignUp(firstName, lastName, mobileNumber, email, password, passwordConfirmation);
        signUpResponseCall.enqueue(new Callback<SignUpResponse>() {
            @Override
            public void onResponse(Call<SignUpResponse> call, Response<SignUpResponse> response) {
                if(response.code() == 200){
                    signUpResponseMutableLiveData.setValue(response.body());
                }
                else if (response.code() == 401){
                    signUpResponseMutableLiveData.setValue(response.body());
                }
            }

            @Override
            public void onFailure(Call<SignUpResponse> call, Throwable t) {
                Log.d("FAILURE", "Failure to connect");
            }
        });
        return signUpResponseMutableLiveData;
    }

    //SignOut
    public MutableLiveData<LogoutResponse> signOutRequest(String userToken){
        final MutableLiveData<LogoutResponse> logoutResponseMutableLiveData = new MutableLiveData<>();
        Call<LogoutResponse> logoutResponseCall = RetrofitClient.getInstance().getApi().userLogOut("Bearer " + userToken);
        logoutResponseCall.enqueue(new Callback<LogoutResponse>() {
            @Override
            public void onResponse(Call<LogoutResponse> call, Response<LogoutResponse> response) {
                if(response.code() == 200){
                    logoutResponseMutableLiveData.setValue(response.body());
                }
                else if (response.code() == 401){
                    logoutResponseMutableLiveData.setValue(response.body());
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
