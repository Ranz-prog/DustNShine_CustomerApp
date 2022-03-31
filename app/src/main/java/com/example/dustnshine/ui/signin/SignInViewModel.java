package com.example.dustnshine.ui.signin;

import android.util.Log;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.example.dustnshine.ForgotPasswordCallback;
import com.example.dustnshine.SignInCallback;
import com.example.dustnshine.api.RetrofitClient;
import com.example.dustnshine.response.ForgotPasswordResponse;
import com.example.dustnshine.response.SignInResponse;
import com.example.dustnshine.service.UserAPIService;
import com.example.dustnshine.response.UserManagementResponse;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class SignInViewModel extends ViewModel {

    private UserAPIService userAPIService;
    private SignInCallback signInCallback;
    private ForgotPasswordCallback forgotPasswordCallback;
    private MutableLiveData<UserManagementResponse> userManagementResponseMutableLiveData;

    public SignInViewModel() {
        userAPIService = new UserAPIService();
    }

    public void getSignInRequest(String email, String password){
        Call<SignInResponse> signInResponseCall = RetrofitClient.getInstance().getApi().userSignIn(email, password);
        signInResponseCall.enqueue(new Callback<SignInResponse>() {
            @Override
            public void onResponse(Call<SignInResponse> call, Response<SignInResponse> response) {
                signInCallback.signInCallback(response.code(), response.body());
            }

            @Override
            public void onFailure(Call<SignInResponse> call, Throwable t) {
                Log.d("FAILURE", t.getLocalizedMessage());
            }
        });
    }

    public void forgotPasswordRequest(String email){
        Call<ForgotPasswordResponse> forgotPasswordResponseCall = RetrofitClient.getInstance().getApi().forgotPassword(email);
        forgotPasswordResponseCall.enqueue(new Callback<ForgotPasswordResponse>() {
            @Override
            public void onResponse(Call<ForgotPasswordResponse> call, Response<ForgotPasswordResponse> response) {
                forgotPasswordCallback.forgotPasswordCallback(response.code(), response.body());
            }

            @Override
            public void onFailure(Call<ForgotPasswordResponse> call, Throwable t) {
                Log.d("FAILURE", "Failure to connect");
            }
        });
    }

    public void setOnForgotPasswordListener(ForgotPasswordCallback forgotpasswordCallback){
        forgotPasswordCallback = forgotpasswordCallback;
    }

    public void setOnSignInListener(SignInCallback signinCallback){
        signInCallback = signinCallback;
    }

    public LiveData<UserManagementResponse> getUserInformationRequest(String userToken){
        if (userManagementResponseMutableLiveData == null) {
            userManagementResponseMutableLiveData = userAPIService.getUserInformation(userToken);
        }
        return userManagementResponseMutableLiveData;
    }

}
