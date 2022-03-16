package com.example.dustnshine.ui.signin;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.databinding.DataBindingUtil;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;

import com.example.dustnshine.MainActivity;
import com.example.dustnshine.R;
import com.example.dustnshine.SignInCallback;
import com.example.dustnshine.databinding.ActivitySigninBinding;
import com.example.dustnshine.response.SignInResponse;
import com.example.dustnshine.response.UserManagementResponse;
import com.example.dustnshine.storage.SharedPrefManager;
import com.example.dustnshine.ui.ForgetPasswordActivity;
import com.example.dustnshine.ui.signup.SignUpActivity;
import com.example.dustnshine.utils.AppConstants;
import com.google.android.material.snackbar.Snackbar;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class SignInActivity extends AppCompatActivity {

    private long backButtonCount;
    private Button btnSearch;
    private EditText editTextEmailSearch;
    private AlertDialog.Builder dialogBuilder;
    private AlertDialog dialog;
    private SignInViewModel signInViewModel;
    private ActivitySigninBinding activitySigninBinding;
    private static String email, password;
    private Pattern pattern;
    private Matcher matcher;
    private AppConstants appConstants;
    private Snackbar snackbar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        this.getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);

        signInViewModel = new ViewModelProvider(SignInActivity.this).get(SignInViewModel.class);
        activitySigninBinding = DataBindingUtil.setContentView(this, R.layout.activity_signin);
        pattern = Pattern.compile(appConstants.regex);

        activitySigninBinding.btnSignIn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                email = activitySigninBinding.etEmailAddress.getText().toString();
                password = activitySigninBinding.etPassword.getText().toString();
                matcher = pattern.matcher(email);

                if (TextUtils.isEmpty(email)){
                    showMessage("Email is required");
                    activitySigninBinding.etEmailAddress.requestFocus();
                } else if (!matcher.matches()) {
                    showMessage("Invalid email");
                    activitySigninBinding.etEmailAddress.requestFocus();
                } else if (TextUtils.isEmpty(password)) {
                    showMessage("Password is required");
                    activitySigninBinding.etPassword.requestFocus();
                } else if (password.length() < 8) {
                    showMessage("Password must be at least 8 characters");
                    activitySigninBinding.etPassword.requestFocus();
                } else {
                    userSignIn(email, password);
                }
            }
        });

        signInViewModel.setOnSignInListener(new SignInCallback() {
            @Override
            public void signInCallback(Integer statusCode, SignInResponse signInResponse) {
                if(statusCode == 200){
                    showMessage(signInResponse.getMessage());
                    new Handler().postDelayed(new Runnable() {
                        @Override
                        public void run() {
                            getUserInformation(signInResponse.getData().getToken());
                            SharedPrefManager.getInstance(SignInActivity.this).saveUser(signInResponse.getData().getUser());
                            SharedPrefManager.getInstance(SignInActivity.this).saveUserToken(signInResponse.getData().getToken());
                            SharedPrefManager.getInstance(SignInActivity.this).savePassword(activitySigninBinding.etPassword.getText().toString());
                            Intent intent = new Intent(SignInActivity.this, MainActivity.class);
                            startActivity(intent);
                        }
                    }, 2000);
                } else if (statusCode == 422){
                    showMessage("The given data is invalid");
                } else if (statusCode == 401){
                    showMessage("Invalid Credentials");
                } else {
                    showMessage("Try Again");
                }
            }
        });

        activitySigninBinding.txtCreateAccount.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(SignInActivity.this, SignUpActivity.class);
                startActivity(intent);
            }
        });

        activitySigninBinding.btnForgotPassword.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                createNewDialog();
            }
        });
    }

    @Override
    public void onBackPressed() {
        if (backButtonCount >= 1) {
            Intent intent = new Intent(Intent.ACTION_MAIN);
            intent.addCategory(Intent.CATEGORY_HOME);
            finish();
            startActivity(intent);
        } else {
            Toast.makeText(this, "Press the back button once again to close the application.", Toast.LENGTH_SHORT).show();
            backButtonCount++;
        }
    }

    private void createNewDialog() {
        dialogBuilder = new AlertDialog.Builder(this);
        final View searchPopUp = getLayoutInflater().inflate(R.layout.activity_search_email, null);
        editTextEmailSearch = searchPopUp.findViewById(R.id.enterEmailSearch);
        btnSearch = searchPopUp.findViewById(R.id.searchBtn);
        dialogBuilder.setView(searchPopUp);
        dialog = dialogBuilder.create();
        dialog.show();

        btnSearch.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(SignInActivity.this, ForgetPasswordActivity.class);
                startActivity(intent);
            }
        });

    }

    private void userSignIn(String email, String password){
        signInViewModel.getSignInRequest(email, password);
    }

    @Override
    protected void onStart() {
        super.onStart();
        if(SharedPrefManager.getInstance(this).isLoggedIn()){
            Intent intent = new Intent(this, MainActivity.class);
            startActivity(intent);
        };
    }

    private void getUserInformation(String userToken){
        signInViewModel.getUserInformationRequest(userToken).observe(SignInActivity.this, new Observer<UserManagementResponse>() {
            @Override
            public void onChanged(UserManagementResponse userManagementResponse) {
                if(userManagementResponse == null){
                    Log.d("TAG", "Invalid Request");
                } else {
                    SharedPrefManager.getInstance(SignInActivity.this).saveUserAddress(userManagementResponse.getData().get(0).getAddress().get(0));
                }
            }
        });
    }

    private void showMessage(String message){
        snackbar = Snackbar.make(findViewById(android.R.id.content), message, Snackbar.LENGTH_LONG);
        snackbar.show();
    }
}

//        signInViewModel.getSignInRequest(email, password).observe(SignInActivity.this, new Observer<SignInResponse>() {
//            @Override
//            public void onChanged(SignInResponse signInResponse) {
//                if(signInResponse.getMessage() == "Succesfully Logged In"){
//                    Toast.makeText(SignInActivity.this, signInResponse.getMessage(), Toast.LENGTH_SHORT).show();
//                    SharedPrefManager.getInstance(SignInActivity.this).saveUser(signInResponse.getData().getUser());
//                    SharedPrefManager.getInstance(SignInActivity.this).saveUserToken(signInResponse.getData().getToken());
//                    Intent intent = new Intent(SignInActivity.this, MainActivity.class);
//                    startActivity(intent);
//                } else if (signInResponse.getMessage() == "Invalid Credentials") {
//                    Toast.makeText(SignInActivity.this, signInResponse.getMessage(), Toast.LENGTH_SHORT).show();
//                } else {
//                    Toast.makeText(SignInActivity.this, signInResponse.getMessage(), Toast.LENGTH_SHORT).show();
//                }
//            }
//        });