package com.example.dustnshine.ui.signin;

import android.util.Log;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.example.dustnshine.SignInCallback;
import com.example.dustnshine.api.RetrofitClient;
import com.example.dustnshine.response.SignInResponse;
import com.example.dustnshine.service.UserAPIService;
import com.example.dustnshine.response.UserManagementResponse;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class SignInViewModel extends ViewModel {

    private UserAPIService userAPIService;
    private SignInCallback signInCallback;
    //    private MutableLiveData<SignInResponse> signInResponseMutableLiveData;
    private MutableLiveData<UserManagementResponse> userManagementResponseMutableLiveData;

    public SignInViewModel() {
        userAPIService = new UserAPIService();
    }
//
//    public LiveData<SignInResponse> getSignInRequest(String email, String password){
//        if (signInResponseMutableLiveData == null) {
//            signInResponseMutableLiveData = userAPIRepo.signInRequest(email, password);
//        }
//        return signInResponseMutableLiveData;
//    }

    public void getSignInRequest(String email, String password){
        Call<SignInResponse> signInResponseCall = RetrofitClient.getInstance().getApi().userSignIn(email, password);
        signInResponseCall.enqueue(new Callback<SignInResponse>() {
            @Override
            public void onResponse(Call<SignInResponse> call, Response<SignInResponse> response) {
                signInCallback.signInCallback(response.code(), response.body());
            }

            @Override
            public void onFailure(Call<SignInResponse> call, Throwable t) {
                Log.d("FAILURE", "Failure to connect");
            }
        });
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
