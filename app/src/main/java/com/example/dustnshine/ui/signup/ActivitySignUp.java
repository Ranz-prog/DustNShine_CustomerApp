package com.example.dustnshine.ui.signup;

import android.app.Dialog;
import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Patterns;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.databinding.DataBindingUtil;

import com.example.dustnshine.SignUpCallback;
import com.example.dustnshine.databinding.ActivitySignupBinding;
import com.example.dustnshine.models.SignUpResponse;
import com.example.dustnshine.R;
import com.example.dustnshine.api.RetrofitClient;
import com.example.dustnshine.ui.signin.ActivitySignIn;
import com.google.android.material.textfield.TextInputEditText;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class ActivitySignUp extends AppCompatActivity {

    LinearLayout returnHome;
    Button signupBtn;
    Dialog dialog;
    TextView popText;

    private SignUpViewModel signUpViewModel;
    private ActivitySignupBinding activitySignupBinding;
    private String firstName, lastName, mobileNumber, email, password, passwordConfirmation;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        this.getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);

        signUpViewModel = new SignUpViewModel();

        activitySignupBinding = DataBindingUtil.setContentView(this, R.layout.activity_signup);

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
                } if (TextUtils.isEmpty(lastName)) {
                    activitySignupBinding.editTextLastName.setError("Invalid Email");
                    activitySignupBinding.editTextLastName.requestFocus();
                } if (TextUtils.isEmpty(mobileNumber)) {
                    activitySignupBinding.editTextMobileNumber.setError("Email is required");
                    activitySignupBinding.editTextMobileNumber.requestFocus();
                } if (TextUtils.isEmpty(email)) {
                    activitySignupBinding.editTextEmailAddress.setError("Email is required");
                    activitySignupBinding.editTextEmailAddress.requestFocus();
                } if (Patterns.EMAIL_ADDRESS.matcher(email).matches()) {
                    activitySignupBinding.editTextEmailAddress.setError("Invalid Email");
                    activitySignupBinding.editTextEmailAddress.requestFocus();
                } if (TextUtils.isEmpty(password)) {
                    activitySignupBinding.editTextPassword.setError("Password is required");
                    activitySignupBinding.editTextPassword.requestFocus();
                } if (password.length() < 8) {
                    activitySignupBinding.editTextPassword.setError("Password must be at least 8 characters");
                    activitySignupBinding.editTextPassword.requestFocus();
                } if (TextUtils.isEmpty(passwordConfirmation)) {
                    activitySignupBinding.editTextPasswordConfirmation.setError("Password Confirmation is required");
                    activitySignupBinding.editTextPasswordConfirmation.requestFocus();
                } if (password != passwordConfirmation) {
                    activitySignupBinding.editTextPasswordConfirmation.setError("Password not match");
                    activitySignupBinding.editTextPasswordConfirmation.requestFocus();
                } else {
                    signUpViewModel.getSignUpRequest(activitySignupBinding.editTextFirstName.getText().toString(), activitySignupBinding.editTextLastName.getText().toString(), activitySignupBinding.editTextMobileNumber.getText().toString(), activitySignupBinding.editTextEmailAddress.getText().toString(), activitySignupBinding.editTextPassword.getText().toString(), activitySignupBinding.editTextPasswordConfirmation.getText().toString());
                }
            }
        });

        signUpViewModel.setOnSignInListener(new SignUpCallback() {
            @Override
            public void signUpCallback(Integer statusCode, SignUpResponse signUpResponse) {
                if(statusCode == 200){
                    Toast.makeText(getApplicationContext(), "Successfully Register", Toast.LENGTH_LONG).show();
                    Intent intent = new Intent(ActivitySignUp.this, ActivitySignIn.class);
                    startActivity(intent);
                    finish();
                } else if(statusCode == 422){
                    Toast.makeText(getApplicationContext(), "Invalid Credentials", Toast.LENGTH_LONG).show();
                } else {
                    Toast.makeText(getApplicationContext(), "Registration Failed", Toast.LENGTH_LONG).show();
                }
            }
        });


        returnHome = findViewById(R.id.ReturnBtnOnHome);

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
                Toast.makeText(ActivitySignUp.this, "Success", Toast.LENGTH_SHORT).show();
                dialog.dismiss();
            }
        });

        //END OF DIALOG BOX

    }
}
