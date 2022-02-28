package com.example.dustnshine.ui.signup;

import android.util.Log;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;


import com.example.dustnshine.SignInCallback;
import com.example.dustnshine.SignUpCallback;
import com.example.dustnshine.api.RetrofitClient;
import com.example.dustnshine.repository.UserAPIRepo;
import com.example.dustnshine.response.SignInResponse;
import com.example.dustnshine.response.SignUpResponse;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class SignUpViewModel extends ViewModel {

    private SignUpCallback signUpCallback;

//    private UserAPIRepo userAPIRepo;
//    private MutableLiveData<SignUpResponse> signUpResponseMutableLiveData;
//
//    public SignUpViewModel() {
//        userAPIRepo = new UserAPIRepo();
//    }
//
//    public LiveData<SignUpResponse> getSignUpRequest(String firstName, String lastName, String mobileNumber, String email, String password, String passwordConfirmation){
//        if (signUpResponseMutableLiveData == null) {
//            signUpResponseMutableLiveData = userAPIRepo.signUpRequest(firstName, lastName, mobileNumber, email, password, passwordConfirmation);
//        }
//        return signUpResponseMutableLiveData;
//    }

    public void getSignUpRequest(String firstName, String lastName, String mobileNumber, String email, String password, String passwordConfirmation){
        Call<SignUpResponse> signUpResponseCall = RetrofitClient.getInstance().getApi().userSignUp(firstName, lastName, mobileNumber, email, password, passwordConfirmation);
        signUpResponseCall.enqueue(new Callback<SignUpResponse>() {
            @Override
            public void onResponse(Call<SignUpResponse> call, Response<SignUpResponse> response) {
                signUpCallback.signUpCallback(response.code());
            }

            @Override
            public void onFailure(Call<SignUpResponse> call, Throwable t) {
                Log.d("FAILURE", "Failure to connect");
            }
        });
    }

    public void setOnSignUpListener(SignUpCallback signUpCallback){
        signUpCallback = signUpCallback;
    }
}
