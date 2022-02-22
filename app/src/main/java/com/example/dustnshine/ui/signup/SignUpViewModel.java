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
}
