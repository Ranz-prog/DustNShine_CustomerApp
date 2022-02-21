package com.example.dustnshine.ui.signin;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
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
