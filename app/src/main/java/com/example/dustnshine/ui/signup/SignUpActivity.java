package com.example.dustnshine.ui.signup;

import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.view.WindowManager;

import androidx.appcompat.app.AppCompatActivity;
import androidx.databinding.DataBindingUtil;
import androidx.lifecycle.ViewModelProvider;

import com.example.dustnshine.SignUpCallback;

import com.example.dustnshine.R;
import com.example.dustnshine.databinding.ActivitySignupBinding;
import com.example.dustnshine.ui.signin.SignInActivity;
import com.example.dustnshine.utils.AppConstants;;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class SignUpActivity extends AppCompatActivity {

    private SignUpViewModel signUpViewModel;
    private ActivitySignupBinding activitySignupBinding;
    private static String firstName, lastName, mobileNumber, email, house_number, street, barangay, municipality, province, zipcode, password, passwordConfirmation;
    private Pattern pattern;
    private Matcher matcher;
    private static int alert = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        this.getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);

        signUpViewModel = new ViewModelProvider(SignUpActivity.this).get(SignUpViewModel.class);
        activitySignupBinding = DataBindingUtil.setContentView(this, R.layout.activity_signup);
        pattern = Pattern.compile(AppConstants.regex);

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
                    activitySignupBinding.etFirstName.setError("Please enter your First Name");
                    activitySignupBinding.etFirstName.requestFocus();
                } else if (TextUtils.isEmpty(lastName)) {
                    activitySignupBinding.etLastName.setError("Please enter your Last Name");
                    activitySignupBinding.etLastName.requestFocus();
                } else if (!matcher.matches()) {
                    activitySignupBinding.etEmailAddress.setError("Invalid email");
                    activitySignupBinding.etEmailAddress.requestFocus();
                } else if (TextUtils.isEmpty(email)) {
                    activitySignupBinding.etEmailAddress.setError("Please enter your Email Address");
                    activitySignupBinding.etEmailAddress.requestFocus();
                } else if (TextUtils.isEmpty(mobileNumber)) {
                    activitySignupBinding.etMobileNumber.setError("Please enter your Mobile Number");
                    activitySignupBinding.etMobileNumber.requestFocus();
                } else if (mobileNumber.length() < 11 || mobileNumber.length() > 11) {
                    activitySignupBinding.etMobileNumber.setError("Mobile number must be 11 characters");
                    activitySignupBinding.etMobileNumber.requestFocus();
                } else if (TextUtils.isEmpty(password)) {
                    activitySignupBinding.etPassword.setError("Please enter your Password", null);
                    activitySignupBinding.etPassword.requestFocus();
                } else if (password.length() < 8) {
                    activitySignupBinding.etPassword.setError("Password must be at least 8 characters", null);
                    activitySignupBinding.etPassword.requestFocus();
                } else if (TextUtils.isEmpty(passwordConfirmation)) {
                    activitySignupBinding.etPasswordConfirmation.setError("Password Confirmation is required", null);
                    activitySignupBinding.etPasswordConfirmation.requestFocus();
                } else if (!(password.equals(passwordConfirmation))) {
                    activitySignupBinding.etPasswordConfirmation.setError("Re-typed password does not match", null);
                    activitySignupBinding.etPasswordConfirmation.requestFocus();
                } else if (TextUtils.isEmpty(house_number)) {
                    activitySignupBinding.etHouseNo.setError("Please enter your House no.");
                    activitySignupBinding.etHouseNo.requestFocus();
                } else if (house_number.charAt(0) == '0') {
                    activitySignupBinding.etHouseNo.setError("House no. should not start in 0");
                    activitySignupBinding.etHouseNo.requestFocus();
                } else if (TextUtils.isEmpty(street)) {
                    activitySignupBinding.etStreet.setError("Please enter your Street");
                    activitySignupBinding.etStreet.requestFocus();
                } else if (TextUtils.isEmpty(barangay)) {
                    activitySignupBinding.etBarangay.setError("Please enter your Barangay");
                    activitySignupBinding.etBarangay.requestFocus();
                } else if (TextUtils.isEmpty(municipality)) {
                    activitySignupBinding.etCityMunicipality.setError("Please enter your City/ Municipality");
                    activitySignupBinding.etCityMunicipality.requestFocus();
                } else if (TextUtils.isEmpty(province)) {
                    activitySignupBinding.etProvince.setError("Please enter your Province");
                    activitySignupBinding.etProvince.requestFocus();
                } else if (TextUtils.isEmpty(zipcode)) {
                    activitySignupBinding.etZipCode.setError("Please enter your Zipcode");
                    activitySignupBinding.etZipCode.requestFocus();
                } else {
                    userSignUp(firstName, lastName, mobileNumber, email, house_number, street, barangay, municipality, province, zipcode, password, passwordConfirmation);
                }
            }
        });

        signUpViewModel.setOnSignUpListener(new SignUpCallback() {
            @Override
            public void signUpCallback(Integer statusCode) {
                if (statusCode == 200) {
                    AppConstants.alertMessage(1, R.drawable.check, "Success!", "Thank you. You have successfully Signed Up!", SignUpActivity.this, SignInActivity.class, "GONE");
                } else if (statusCode == 422) {
                    AppConstants.alertMessage(0, R.drawable.ic_error_2, "Failed!", "The email has been already taken.", SignUpActivity.this, SignInActivity.class, "VISIBLE");
                } else if (statusCode == 500) {
                    AppConstants.alertMessage(0, R.drawable.ic_error_2, "Failed!", "The house no. is already taken for this address", SignUpActivity.this, SignInActivity.class, "VISIBLE");
                } else {
                    AppConstants.alertMessage(1, R.drawable.ic_error_2, "Failed!", "Try Again", SignUpActivity.this, SignInActivity.class, "VISIBLE");
                }
            }
        });

        activitySignupBinding.btnBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });

    }

    private void userSignUp(String firstName, String lastName, String mobileNumber, String email, String house_number, String street, String barangay, String municipality, String province, String zipcode, String password, String passwordConfirmation) {
        signUpViewModel.getSignUpRequest(firstName, lastName, mobileNumber, email, house_number, street, barangay, municipality, province, zipcode, password, passwordConfirmation);
    }

}

