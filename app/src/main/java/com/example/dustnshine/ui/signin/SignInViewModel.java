package com.example.dustnshine.ui.signin;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.example.dustnshine.response.SignInResponse;
import com.example.dustnshine.repository.UserAPIRepo;

public class SignInViewModel extends ViewModel {

    private UserAPIRepo userAPIRepo;
    private MutableLiveData<SignInResponse> signInResponseMutableLiveData;

    public SignInViewModel() {
        userAPIRepo = new UserAPIRepo();
    }

    public LiveData<SignInResponse> getSignInRequest(String email, String password){
        if (signInResponseMutableLiveData == null) {
            signInResponseMutableLiveData = userAPIRepo.signInRequest(email, password);
        }
        return signInResponseMutableLiveData;
    }
}
