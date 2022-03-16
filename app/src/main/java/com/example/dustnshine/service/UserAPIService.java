package com.example.dustnshine.service;

import android.util.Log;

import androidx.lifecycle.MutableLiveData;

import com.example.dustnshine.SignInCallback;
import com.example.dustnshine.api.RetrofitClient;
import com.example.dustnshine.response.ChangePasswordResponse;
import com.example.dustnshine.response.LogoutResponse;
import com.example.dustnshine.response.SignInResponse;
import com.example.dustnshine.response.SignUpResponse;
import com.example.dustnshine.response.UserManagementResponse;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class UserAPIService {

    private SignInCallback signInCallback;
    //SignIn
    public MutableLiveData<SignInResponse> signInRequest(String email, String password){
        final MutableLiveData<SignInResponse> signInResponseMutableLiveData = new MutableLiveData<>();
        Call<SignInResponse> signInResponseCall = RetrofitClient.getInstance().getApi().userSignIn(email, password);
        signInResponseCall.enqueue(new Callback<SignInResponse>() {
            @Override
            public void onResponse(Call<SignInResponse> call, Response<SignInResponse> response) {
                if(response.code() == 200){
                    signInResponseMutableLiveData.setValue(response.body());
                    signInCallback.signInCallback(response.code(), response.body());
                }
                else if (response.code() == 401){
                    signInResponseMutableLiveData.setValue(response.body());
                }
            }

            @Override
            public void onFailure(Call<SignInResponse> call, Throwable t) {
                Log.d("FAILURE", "Failure to connect");
            }
        });
        return signInResponseMutableLiveData;
    }

    //SignUp
    public MutableLiveData<SignUpResponse> signUpRequest(String firstName, String lastName, String mobileNumber, String email, String house_number, String street, String barangay, String municipality, String province, String zipcode, double latitude, double longitude, String password, String passwordConfirmation){
        final MutableLiveData<SignUpResponse> signUpResponseMutableLiveData = new MutableLiveData<>();
        Call<SignUpResponse> signUpResponseCall = RetrofitClient.getInstance().getApi().userSignUp(firstName, lastName, mobileNumber, email, house_number, street, barangay, municipality, province, latitude, longitude, zipcode, password, passwordConfirmation);
        signUpResponseCall.enqueue(new Callback<SignUpResponse>() {
            @Override
            public void onResponse(Call<SignUpResponse> call, Response<SignUpResponse> response) {
                if(response.code() == 200){
                    signUpResponseMutableLiveData.setValue(response.body());
                }
                else if (response.code() == 401){
                    signUpResponseMutableLiveData.setValue(response.body());
                }
            }

            @Override
            public void onFailure(Call<SignUpResponse> call, Throwable t) {
                Log.d("FAILURE", "Failure to connect");
            }
        });
        return signUpResponseMutableLiveData;
    }

    //SignOut
    public MutableLiveData<LogoutResponse> signOutRequest(String userToken){
        final MutableLiveData<LogoutResponse> logoutResponseMutableLiveData = new MutableLiveData<>();
        Call<LogoutResponse> logoutResponseCall = RetrofitClient.getInstance().getApi().userLogOut("Bearer " + userToken);
        logoutResponseCall.enqueue(new Callback<LogoutResponse>() {
            @Override
            public void onResponse(Call<LogoutResponse> call, Response<LogoutResponse> response) {
                if(response.code() == 200){
                    logoutResponseMutableLiveData.setValue(response.body());
                }
                else if (response.code() == 401){
                    logoutResponseMutableLiveData.setValue(response.body());
                }
            }

            @Override
            public void onFailure(Call<LogoutResponse> call, Throwable t) {
                Log.d("FAILURE", "Failure to connect");
            }
        });

        return  logoutResponseMutableLiveData;
    }

    public MutableLiveData<UserManagementResponse> getUserInformation(String userToken){
        final MutableLiveData<UserManagementResponse> userManagementModelMutableLiveData = new MutableLiveData<>();
        Call<UserManagementResponse> userManagementResponseCall = RetrofitClient.getInstance().getApi().getUserInformation("Bearer " + userToken);
        userManagementResponseCall.enqueue(new Callback<UserManagementResponse>() {
            @Override
            public void onResponse(Call<UserManagementResponse> call, Response<UserManagementResponse> response) {
                if(response.code() == 200){
                    userManagementModelMutableLiveData.setValue(response.body());
                    Log.d("TAG", "Getting info success");
                } else {
                    Log.d("TAG", "Getting info unsuccessful");
                }
            }

            @Override
            public void onFailure(Call<UserManagementResponse> call, Throwable t) {
                Log.d("ERROR", t.getLocalizedMessage());
            }
        });
        return userManagementModelMutableLiveData;
    }

    public MutableLiveData<UserManagementResponse> putUserInformationUpdates(int user_id, String userToken, String firstName, String lastName, String mobileNumber, String email, String house_number, String street, String barangay, String municipality, String province, String zipcode,  String password, String passwordConfirmation){
        final MutableLiveData<UserManagementResponse> userManagementResponseMutableLiveData = new MutableLiveData<>();
        Call<UserManagementResponse> userManagementResponseCall = RetrofitClient.getInstance().getApi().userInformationUpdate(user_id, "Bearer " + userToken, firstName, lastName, mobileNumber, email, house_number, street, barangay, municipality, province, zipcode, password, passwordConfirmation);
        userManagementResponseCall.enqueue(new Callback<UserManagementResponse>() {
            @Override
            public void onResponse(Call<UserManagementResponse> call, Response<UserManagementResponse> response) {
                if(response.code() == 200){
                    userManagementResponseMutableLiveData.setValue(response.body());
                    Log.d("TAG", "Updating Info Success");
                } else {
                    Log.d("TAG", String.valueOf(response.code()));
                }
            }

            @Override
            public void onFailure(Call<UserManagementResponse> call, Throwable t) {
                Log.d("FAILURE", "Failure to connect");
            }
        });
        return userManagementResponseMutableLiveData;
    }

    public MutableLiveData<ChangePasswordResponse> userChangePassword(String userToken, String oldPassword, String password, String newPassword){
        final MutableLiveData<ChangePasswordResponse> changePasswordResponseMutableLiveData = new MutableLiveData<>();
        Call<ChangePasswordResponse> changePasswordResponseCall = RetrofitClient.getInstance().getApi().userChangePassword("Bearer " + userToken, oldPassword, password, newPassword);
        changePasswordResponseCall.enqueue(new Callback<ChangePasswordResponse>() {
            @Override
            public void onResponse(Call<ChangePasswordResponse> call, Response<ChangePasswordResponse> response) {
                if(response.code() == 200){
                    changePasswordResponseMutableLiveData.setValue(response.body());
                    Log.d("TAG", "Updating Info Success");
                } else {
                    Log.d("TAG", String.valueOf(response.code()));
                }
            }

            @Override
            public void onFailure(Call<ChangePasswordResponse> call, Throwable t) {
                Log.d("FAILURE", "Failure to connect");
            }
        });

        return changePasswordResponseMutableLiveData;
    }
}
