package com.example.dustnshine.ui.signin;

import androidx.lifecycle.ViewModel;

import com.example.dustnshine.SignInCallback;
import com.example.dustnshine.api.RetrofitClient;
import com.example.dustnshine.response.SignInResponse;
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

        Call<SignInResponse> loginResponseCall = RetrofitClient.getInstance().getApi().userSignIn(email, password);

        loginResponseCall.enqueue(new Callback<SignInResponse>() {
            @Override
            public void onResponse(Call<SignInResponse> call, Response<SignInResponse> response) {
                callback.signInCallback(response.code(), response.body());
            }

            @Override
            public void onFailure(Call<SignInResponse> call, Throwable t) {

            }
        });
    }

    public void setOnSignInListener(SignInCallback signInCallback){
        callback = signInCallback;
    }
}
