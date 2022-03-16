package com.example.dustnshine.ui.signup;

import android.app.Dialog;
import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.os.Handler;
import android.text.TextUtils;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.databinding.DataBindingUtil;
import androidx.lifecycle.ViewModelProvider;

import com.example.dustnshine.SignUpCallback;

import com.example.dustnshine.R;
import com.example.dustnshine.databinding.ActivitySignupBinding;
import com.example.dustnshine.ui.signin.SignInActivity;
import com.example.dustnshine.utils.AppConstants;
import com.google.android.material.snackbar.Snackbar;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class SignUpActivity extends AppCompatActivity {

    private ImageView btnBack;
    private Dialog dialog;
    private TextView popText;
    private SignUpViewModel signUpViewModel;
    private ActivitySignupBinding activitySignupBinding;
    private static String firstName, lastName, mobileNumber, email, house_number, street, barangay, municipality, province, zipcode, password, passwordConfirmation;
    private static double latitude, longitude;
    private Pattern pattern;
    private Matcher matcher;
    private Snackbar snackbar;
    private AppConstants appConstants;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        this.getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);

        signUpViewModel = new ViewModelProvider(SignUpActivity.this).get(SignUpViewModel.class);
        activitySignupBinding = DataBindingUtil.setContentView(this, R.layout.activity_signup);
        btnBack = findViewById(R.id.btnBack);
        pattern = Pattern.compile(appConstants.regex);

        activitySignupBinding.btnSignUp.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                firstName = activitySignupBinding.etFirstName.getText().toString();
                lastName = activitySignupBinding.etLastName.getText().toString();
                mobileNumber = activitySignupBinding.etMobileNumber.getText().toString();
                email = activitySignupBinding.etEmailAddress.getText().toString();
                house_number = activitySignupBinding.etHouseNo.getText().toString();
                street = activitySignupBinding.etStreet.getText().toString();
                barangay = activitySignupBinding.etBarangay.getText().toString();
                municipality = activitySignupBinding.etCityMunicipality.getText().toString();
                province = activitySignupBinding.etProvince.getText().toString();
                zipcode = activitySignupBinding.etZipCode.getText().toString();
                password = activitySignupBinding.etPassword.getText().toString();
                passwordConfirmation = activitySignupBinding.etPasswordConfirmation.getText().toString();
                matcher = pattern.matcher(email);

                if (TextUtils.isEmpty(firstName)) {
                    showMessage("Please enter your First Name");
                    activitySignupBinding.etFirstName.requestFocus();
                } else if (TextUtils.isEmpty(lastName)) {
                    showMessage("Please enter your Last Name");
                    activitySignupBinding.etLastName.requestFocus();
                } else if (!matcher.matches()) {
                    showMessage("Invalid email");
                    activitySignupBinding.etEmailAddress.requestFocus();
                } else if (TextUtils.isEmpty(email)) {
                    showMessage("Please enter your Email Address");
                    activitySignupBinding.etEmailAddress.requestFocus();
                } else if (TextUtils.isEmpty(mobileNumber)) {
                    showMessage("Please enter your Mobile Number");
                    activitySignupBinding.etMobileNumber.requestFocus();
                } else if (mobileNumber.length() < 11 || mobileNumber.length() > 11) {
                    showMessage("Mobile number must be 11 characters");
                    activitySignupBinding.etMobileNumber.requestFocus();
                } else if (TextUtils.isEmpty(password)) {
                    showMessage("Please enter your Password");
                    activitySignupBinding.etPassword.requestFocus();
                } else if (password.length() < 8) {
                    showMessage("Password must be at least 8 characters");
                    activitySignupBinding.etPassword.requestFocus();
                } else if (TextUtils.isEmpty(passwordConfirmation)) {
                    showMessage("Password Confirmation is required");
                    activitySignupBinding.etPasswordConfirmation.requestFocus();
                } else if (!(password.equals(passwordConfirmation))) {
                    showMessage("Re-typed password does not match");
                    activitySignupBinding.etPasswordConfirmation.requestFocus();
                } else if (TextUtils.isEmpty(house_number)) {
                    showMessage("Please enter your House No.");
                    activitySignupBinding.etHouseNo.requestFocus();
                } else if (TextUtils.isEmpty(street)) {
                    showMessage("Please enter your Street");
                    activitySignupBinding.etStreet.requestFocus();
                } else if (TextUtils.isEmpty(barangay)) {
                    showMessage("Please enter your Barangay");
                    activitySignupBinding.etBarangay.requestFocus();
                } else if (TextUtils.isEmpty(municipality)) {
                    showMessage("Please enter your City/ Municipality");
                    activitySignupBinding.etCityMunicipality.requestFocus();
                } else if (TextUtils.isEmpty(province)) {
                    showMessage("Please enter your Province");
                    activitySignupBinding.etProvince.requestFocus();
                } else if (TextUtils.isEmpty(zipcode)) {
                    showMessage("Please enter your Zipcode");
                    activitySignupBinding.etZipCode.requestFocus();
                } else {
                    userSignUp(firstName, lastName, mobileNumber, email, house_number, street, barangay, municipality, province, 1234.01, 98.01, zipcode, password, passwordConfirmation);
                }
            }
        });

        signUpViewModel.setOnSignUpListener(new SignUpCallback() {
            @Override
            public void signUpCallback(Integer statusCode) {
                if (statusCode == 200) {
                    showMessage("Successfully Registered");
                    new Handler().postDelayed(new Runnable() {
                        @Override
                        public void run() {
                            Intent intent = new Intent(SignUpActivity.this, SignInActivity.class);
                            startActivity(intent);
                        }
                    }, 2000);
                } else if (statusCode == 422) {
                    showMessage("The given data was invalid.");
                } else if (statusCode == 500) {
                    showMessage("Try Again");
                    new Handler().postDelayed(new Runnable() {
                        @Override
                        public void run() {
                            Intent intent = new Intent(SignUpActivity.this, SignInActivity.class);
                            startActivity(intent);
                        }
                    }, 2000);
                }
            }
        });

        //BackButton
        btnBack.setOnClickListener(new View.OnClickListener() {
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
        String text = "Thank you. You have successfully Signed Up!";// Set Message Here
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

    private void userSignUp(String firstName, String lastName, String mobileNumber, String email, String house_number, String street, String barangay, String municipality, String province, double latitude, double longitude, String zipcode, String password, String passwordConfirmation) {
        signUpViewModel.getSignUpRequest(firstName, lastName, mobileNumber, email, house_number, street, barangay, municipality, province, latitude, longitude, zipcode, password, passwordConfirmation);
    }

    private void showMessage(String message) {
        snackbar = Snackbar.make(findViewById(android.R.id.content), message, Snackbar.LENGTH_LONG);
        snackbar.show();
    }
}

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
