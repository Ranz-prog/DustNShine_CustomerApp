package com.example.dustnshine.ui.signup;

import android.app.Dialog;
import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.databinding.DataBindingUtil;
import androidx.lifecycle.Observer;

import com.example.dustnshine.MainActivity;
import com.example.dustnshine.SignUpCallback;
import com.example.dustnshine.databinding.ActivitySignupBinding;
import com.example.dustnshine.response.SignUpResponse;
import com.example.dustnshine.R;
import com.example.dustnshine.storage.SharedPrefManager;
import com.example.dustnshine.ui.signin.SignInActivity;

public class SignUpActivity extends AppCompatActivity {

    LinearLayout returnHome;
    Button signupBtn;
    Dialog dialog;
    TextView popText;

    private SignUpViewModel signUpViewModel;
    private ActivitySignupBinding activitySignupBinding;
    private static String firstName, lastName, mobileNumber, email, password, passwordConfirmation;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        this.getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);

        signUpViewModel = new SignUpViewModel();
        activitySignupBinding = DataBindingUtil.setContentView(this, R.layout.activity_signup);
        returnHome = findViewById(R.id.ReturnBtnOnHome);

        activitySignupBinding.btnServerLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                firstName = activitySignupBinding.editTextFirstName.getText().toString();
                lastName = activitySignupBinding.editTextLastName.getText().toString();
                mobileNumber = activitySignupBinding.editTextMobileNumber.getText().toString();
                email = activitySignupBinding.editTextEmailAddress.getText().toString();
                password = activitySignupBinding.editTextPassword.getText().toString();
                passwordConfirmation = activitySignupBinding.editTextPasswordConfirmation.getText().toString();

                if (TextUtils.isEmpty(firstName)) {
                    activitySignupBinding.editTextFirstName.setError("Email is required");
                    activitySignupBinding.editTextFirstName.requestFocus();
                } else if (TextUtils.isEmpty(lastName)) {
                    activitySignupBinding.editTextLastName.setError("Invalid Email");
                    activitySignupBinding.editTextLastName.requestFocus();
                } else if (TextUtils.isEmpty(mobileNumber)) {
                    activitySignupBinding.editTextMobileNumber.setError("Email is required");
                    activitySignupBinding.editTextMobileNumber.requestFocus();
                } else if (TextUtils.isEmpty(email)) {
                    activitySignupBinding.editTextEmailAddress.setError("Email is required");
                    activitySignupBinding.editTextEmailAddress.requestFocus();
                } else if (TextUtils.isEmpty(password)) {
                    activitySignupBinding.editTextPassword.setError("Password is required");
                    activitySignupBinding.editTextPassword.requestFocus();
                } else if (password.length() < 8) {
                    activitySignupBinding.editTextPassword.setError("Password must be at least 8 characters");
                    activitySignupBinding.editTextPassword.requestFocus();
                } else if (TextUtils.isEmpty(passwordConfirmation)) {
                    activitySignupBinding.editTextPasswordConfirmation.setError("Password Confirmation is required");
                    activitySignupBinding.editTextPasswordConfirmation.requestFocus();
                } else {
                    userSignUp(firstName, lastName, mobileNumber, email, password, passwordConfirmation);
                }
            }
        });

        signUpViewModel.setOnSignUpListener(new SignUpCallback() {
            @Override
            public void signUpCallback(Integer statusCode) {
                if(statusCode == 200){
                    Toast.makeText(SignUpActivity.this, "Successfully Registered", Toast.LENGTH_SHORT).show();
                    Intent intent = new Intent(SignUpActivity.this, SignInActivity.class);
                    startActivity(intent);
                } else if (statusCode == 422){
                    Toast.makeText(SignUpActivity.this, "The given data was invalid.", Toast.LENGTH_SHORT).show();
                } else {
                    Toast.makeText(SignUpActivity.this, "Try Again", Toast.LENGTH_SHORT).show();
                }
            }
        });

        //BackButton
        returnHome.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });

        // DIALOG BOX START
        dialog = new Dialog(this);
        dialog.setContentView(R.layout.pop_up_reference);
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            dialog.getWindow().setBackgroundDrawable(getDrawable(R.drawable.pop_up_background));
        }
        dialog.getWindow().setLayout(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT);
        dialog.setCancelable(false); //Optional para lang d mag close pag clinick ang labas
        dialog.getWindow().getAttributes().windowAnimations = R.style.DialogAnimation; //Setting the animations to dialog

        Button Okay = dialog.findViewById(R.id.btn_okay);
        popText = dialog.findViewById(R.id.popUpText);
        String text= "Thank you. You have successfully Signed Up!";// Set Message Here
        popText.setText(text.toString());

        Okay.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(SignUpActivity.this, "Success", Toast.LENGTH_SHORT).show();
                dialog.dismiss();
            }
        });

        //END OF DIALOG BOX

    }

    public void userSignUp(String firstName, String lastName, String mobileNumber, String email, String password, String passwordConfirmation){
        signUpViewModel.getSignUpRequest(firstName, lastName, mobileNumber, email, password, passwordConfirmation);
//        signUpViewModel.getSignUpRequest(firstName, lastName, mobileNumber, email, password, passwordConfirmation).observe(SignUpActivity.this, new Observer<SignUpResponse>() {
//            @Override
//            public void onChanged(SignUpResponse signUpResponse) {
//                if(signUpResponse == null){
//                    Toast.makeText(SignUpActivity.this, signUpResponse.getMessage(), Toast.LENGTH_SHORT).show();
//                } else {
//                    Toast.makeText(SignUpActivity.this, "Successfully Registered", Toast.LENGTH_SHORT).show();
//                    Intent intent = new Intent(SignUpActivity.this, SignInActivity.class);
//                    startActivity(intent);
//                }
//            }
//        });
    }
}
