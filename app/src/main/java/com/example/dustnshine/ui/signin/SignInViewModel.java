package com.example.dustnshine.ui.signin;

import android.content.Context;
import android.util.Log;
import android.view.View;
import android.widget.Toast;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.example.dustnshine.SignInCallback;
import com.example.dustnshine.api.RetrofitClient;
import com.example.dustnshine.models.LoginResponse;
import com.example.dustnshine.models.SignInModel;
import com.example.dustnshine.repository.UserAPIRepo;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class SignInViewModel extends ViewModel {

    private UserAPIRepo userAPIRepo;
    private SignInModel signInModel;
    private SignInCallback callback;


    public SignInViewModel() {
        userAPIRepo = new UserAPIRepo();
    }

    public void getSignInRequest(String email, String password) {
        signInRequest(email, password);
    }

    public void signInRequest(String email, String password){
        signInModel = new SignInModel();
        signInModel.setEmail(email);
        signInModel.setPassword(password);

        Call<LoginResponse> loginResponseCall = RetrofitClient.getInstance().getApi().userLogin(email, password);

        loginResponseCall.enqueue(new Callback<LoginResponse>() {
            @Override
            public void onResponse(Call<LoginResponse> call, Response<LoginResponse> response) {
                callback.signInCallback(response.code(), response.body());
            }

            @Override
            public void onFailure(Call<LoginResponse> call, Throwable t) {

            }
        });
    }

    public void setOnSignInListener(SignInCallback signInCallback){
        callback = signInCallback;
    }
}
