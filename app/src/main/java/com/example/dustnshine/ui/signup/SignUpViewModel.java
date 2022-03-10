package com.example.dustnshine.ui.signup;

import android.util.Log;

import androidx.lifecycle.ViewModel;


import com.example.dustnshine.SignUpCallback;
import com.example.dustnshine.api.RetrofitClient;
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

    public void getSignUpRequest(String firstName, String lastName, String mobileNumber, String email, String house_number, String street, String barangay, String municipality, String province, double latitude, double longitude, String zipcode, String password, String passwordConfirmation){
        Call<SignUpResponse> signUpResponseCall = RetrofitClient.getInstance().getApi().userSignUp(firstName, lastName, mobileNumber, email, house_number, street, barangay, municipality, province, latitude, longitude, zipcode, password, passwordConfirmation);
        signUpResponseCall.enqueue(new Callback<SignUpResponse>() {
            @Override
            public void onResponse(Call<SignUpResponse> call, Response<SignUpResponse> response) {
                signUpCallback.signUpCallback(response.code());
                Log.d("RESPONSE", String.valueOf(response.code()));
            }

            @Override
            public void onFailure(Call<SignUpResponse> call, Throwable t) {
                Log.d("FAILURE", "Failure to connect");
            }
        });
    }

    public void setOnSignUpListener(SignUpCallback callback){
        signUpCallback = callback;
    }
}
