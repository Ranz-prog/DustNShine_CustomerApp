package com.example.dustnshine.ui.signup;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;


import com.example.dustnshine.SignUpCallback;
import com.example.dustnshine.api.RetrofitClient;
import com.example.dustnshine.repository.UserAPIRepo;
import com.example.dustnshine.response.SignInResponse;
import com.example.dustnshine.response.SignUpResponse;
import com.example.dustnshine.models.SignUpModel;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class SignUpViewModel extends ViewModel {

    private UserAPIRepo userAPIRepo;
    private MutableLiveData<SignUpResponse> signUpResponseMutableLiveData;

    public SignUpViewModel() {
        userAPIRepo = new UserAPIRepo();
    }

    public LiveData<SignUpResponse> getSignUpRequest(String firstName, String lastName, String mobileNumber, String email, String password, String passwordConfirmation){
        if (signUpResponseMutableLiveData == null) {
            signUpResponseMutableLiveData = userAPIRepo.signUpRequest(firstName, lastName, mobileNumber, email, password, passwordConfirmation);
        }
        return signUpResponseMutableLiveData;
    }

//    public void getSignUpRequest(String firstName, String lastName, String mobileNumber, String email, String password, String passwordConfirmation) {
//        signUpRequest(firstName, lastName, mobileNumber, email, password, passwordConfirmation);
//    }
//
//    public void signUpRequest(String firstName, String lastName, String mobileNumber, String email, String password, String passwordConfirmation){
//        signUpModel = new SignUpModel();
//        signUpModel.setFirstName(firstName);
//        signUpModel.setLastName(lastName);
//        signUpModel.setMobileNumber(mobileNumber);
//        signUpModel.setEmail(email);
//        signUpModel.setPassword(password);
//        signUpModel.setPasswordConfirmation(passwordConfirmation);
//
//        Call<SignUpResponse> call = RetrofitClient
//                .getInstance()
//                .getApi()
//                .userSignUp(firstName, lastName, mobileNumber, email, password, passwordConfirmation);
//
//        call.enqueue(new Callback<SignUpResponse>() {
//            @Override
//            public void onResponse(Call<SignUpResponse> call, Response<SignUpResponse> response) {
//                callback.signUpCallback(response.code(), response.body());
//            }
//
//            @Override
//            public void onFailure(Call<SignUpResponse> call, Throwable t) {
//
//            }
//        });
//    }
//    public void setOnSignInListener(SignUpCallback signUpCallback){
//        callback = signUpCallback;
//    }
}
