package com.example.dustnshine.ui.forgot_password;

import android.util.Log;

import androidx.lifecycle.ViewModel;

import com.example.dustnshine.ResetPasswordCallback;
import com.example.dustnshine.api.RetrofitClient;
import com.example.dustnshine.response.ForgotPasswordResponse;
import com.example.dustnshine.response.ResetPasswordResponse;
import com.example.dustnshine.service.UserAPIService;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class ForgotPasswordViewModel extends ViewModel {

    private UserAPIService userAPIService;
    private ResetPasswordCallback resetPasswordCallback;

    public ForgotPasswordViewModel() {
        userAPIService = new UserAPIService();
    }

    public void resetPasswordRequest(String token, String email, String password, String password_confirmation){
        Call<ResetPasswordResponse> resetPasswordResponseCall = RetrofitClient.getInstance().getApi().resetPassword(token, email, password, password_confirmation);
        resetPasswordResponseCall.enqueue(new Callback<ResetPasswordResponse>() {
            @Override
            public void onResponse(Call<ResetPasswordResponse> call, Response<ResetPasswordResponse> response) {
                resetPasswordCallback.resetPasswordCallback(response.code(), response.body());
            }

            @Override
            public void onFailure(Call<ResetPasswordResponse> call, Throwable t) {
                Log.d("FAILURE", "Failure to connect");
            }
        });
    }

    public void setOnSetPasswordListener(ResetPasswordCallback resetpasswordCallback){
        resetPasswordCallback = resetpasswordCallback;
    }


}
