package com.example.dustnshine.ui.forgot_password;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.view.WindowManager;

import androidx.appcompat.app.AppCompatActivity;
import androidx.databinding.DataBindingUtil;
import androidx.lifecycle.ViewModelProvider;

import com.example.dustnshine.ForgotPasswordCallback;
import com.example.dustnshine.R;
import com.example.dustnshine.ResetPasswordCallback;
import com.example.dustnshine.databinding.ActivityForgotPasswordBinding;
import com.example.dustnshine.response.ForgotPasswordResponse;
import com.example.dustnshine.response.ResetPasswordResponse;
import com.example.dustnshine.ui.signin.SignInActivity;
import com.example.dustnshine.ui.signin.SignInViewModel;
import com.example.dustnshine.utils.AppConstants;

public class ForgotPasswordActivity extends AppCompatActivity {

    private ActivityForgotPasswordBinding activityForgotPasswordBinding;
    private Intent intent;
    private static String emailAddress;
    private ForgotPasswordViewModel forgotPasswordViewModel;
    private static String newPassword, passwordConfirmation, resetToken;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        this.getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,WindowManager.LayoutParams.FLAG_FULLSCREEN);

        activityForgotPasswordBinding = DataBindingUtil.setContentView(this, R.layout.activity_forgot_password);
        forgotPasswordViewModel = new ViewModelProvider(ForgotPasswordActivity.this).get(ForgotPasswordViewModel.class);
        intent = getIntent();

        emailAddress = intent.getStringExtra("EMAIL_ADDRESS");

        activityForgotPasswordBinding.tvEmailAddress.setText(emailAddress);

        forgotPasswordViewModel.setOnSetPasswordListener(new ResetPasswordCallback() {
            @Override
            public void resetPasswordCallback(Integer statusCode, ResetPasswordResponse resetPasswordResponse) {
                if (statusCode == 200) {
                    AppConstants.alertMessage(1, R.drawable.check, "Success!", resetPasswordResponse.getMessage(), ForgotPasswordActivity.this, SignInActivity.class, "GONE");
                } else if (statusCode == 422) {
                    AppConstants.alertMessage(0, R.drawable.ic_error_2, "Failed!", "New Password and Re-type Password do not match", ForgotPasswordActivity.this, SignInActivity.class, "VISIBLE");
                } else if (statusCode == 500) {
                    AppConstants.alertMessage(0, R.drawable.ic_error_2, "Failed!", "This password reset token is invalid.", ForgotPasswordActivity.this, SignInActivity.class, "VISIBLE");
                } else {
                    AppConstants.alertMessage(0, R.drawable.ic_error_2, "Failed!", "Try Again", ForgotPasswordActivity.this, SignInActivity.class, "VISIBLE");
                }
            }
        });

        activityForgotPasswordBinding.btnSave.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                newPassword = activityForgotPasswordBinding.tvNewPassword.getText().toString();
                passwordConfirmation = activityForgotPasswordBinding.tvRetypePassword.getText().toString();
                resetToken = activityForgotPasswordBinding.tvResetToken.getText().toString();

                if (TextUtils.isEmpty(newPassword)){
                    activityForgotPasswordBinding.tvNewPassword.setError("Please enter your new Password", null);
                    activityForgotPasswordBinding.tvNewPassword.requestFocus();
                } else if(newPassword.length() < 8){
                    activityForgotPasswordBinding.tvNewPassword.setError("Password must be at least 8 characters", null);
                    activityForgotPasswordBinding.tvNewPassword.requestFocus();
                } else if(TextUtils.isEmpty(passwordConfirmation)){
                    activityForgotPasswordBinding.tvRetypePassword.setError("Please re-type new Password", null);
                    activityForgotPasswordBinding.tvRetypePassword.requestFocus();
                } else if(!passwordConfirmation.equals(newPassword)){
                    activityForgotPasswordBinding.tvRetypePassword.setError("Re-typed password does not match ", null);
                    activityForgotPasswordBinding.tvRetypePassword.requestFocus();
                } else if (TextUtils.isEmpty(resetToken)){
                    activityForgotPasswordBinding.tvResetToken.setError("Please enter your reset token");
                    activityForgotPasswordBinding.tvResetToken.requestFocus();
                } else {
                    resetPassword(resetToken, emailAddress, newPassword, passwordConfirmation);
                }
            }
        });

        activityForgotPasswordBinding.btnBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(ForgotPasswordActivity.this, SignInActivity.class);
                startActivity(intent);
            }
        });

    }

    private void resetPassword(String token, String email, String password, String password_confirmation){
        forgotPasswordViewModel.resetPasswordRequest(token, email, password, password_confirmation);
    }

}
