package com.example.dustnshine.ui.signin;

import android.content.Intent;
import android.os.Bundle;
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
import androidx.lifecycle.ViewModelProvider;

import com.cometchat.pro.core.AppSettings;
import com.cometchat.pro.core.CometChat;
import com.cometchat.pro.exceptions.CometChatException;
import com.cometchat.pro.models.User;
import com.example.dustnshine.MainActivity;
import com.example.dustnshine.R;
import com.example.dustnshine.SignInCallback;
import com.example.dustnshine.databinding.ActivitySigninBinding;
import com.example.dustnshine.response.SignInResponse;
import com.example.dustnshine.storage.SharedPrefManager;
import com.example.dustnshine.ui.ForgetPasswordActivity;
import com.example.dustnshine.ui.signup.SignUpActivity;
import com.example.dustnshine.utils.AppConstants;

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
    private AppSettings appSettings;
    private static String customerFirstName;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        this.getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);

        activitySigninBinding = DataBindingUtil.setContentView(this, R.layout.activity_signin);
        signInViewModel = new ViewModelProvider(SignInActivity.this).get(SignInViewModel.class);
        pattern = Pattern.compile(AppConstants.regex);

        appSettings = new AppSettings.AppSettingsBuilder().subscribePresenceForAllUsers().setRegion(AppConstants.REGION).build();
        CometChat.init(SignInActivity.this, AppConstants.APP_ID, appSettings, new CometChat.CallbackListener<String>() {
            @Override
            public void onSuccess(String successMessage) {
            }

            @Override
            public void onError(CometChatException e) {

            }
        });

        activitySigninBinding.btnSignIn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                AppConstants.isNetworkConnected(SignInActivity.this);
                email = activitySigninBinding.etEmailAddress.getText().toString();
                password = activitySigninBinding.etPassword.getText().toString();
                matcher = pattern.matcher(email);
                if (TextUtils.isEmpty(email)) {
                    activitySigninBinding.etEmailAddress.setError("Email is required");
                    activitySigninBinding.etEmailAddress.requestFocus();
                } else if (!matcher.matches()) {
                    activitySigninBinding.etEmailAddress.setError("Invalid email");
                    activitySigninBinding.etEmailAddress.requestFocus();
                } else if (TextUtils.isEmpty(password)) {
                    activitySigninBinding.etPassword.setError("Password is required", null);
                    activitySigninBinding.etPassword.requestFocus();
                } else if (password.length() < 8) {
                    activitySigninBinding.etPassword.setError("Password must be at least 8 characters", null);
                    activitySigninBinding.etPassword.requestFocus();
                } else {
                    userSignIn(email, password);
                }
            }
        });

        signInViewModel.setOnSignInListener(new SignInCallback() {
            @Override
            public void signInCallback(Integer statusCode, SignInResponse signInResponse) {
                if (statusCode == 200 && signInResponse.getData().getUser().getRoles().get(0).getName().equals("customer")) {
                    customerFirstName = signInResponse.getData().getUser().getFirst_name().toString().toLowerCase();
                    SharedPrefManager.getInstance(SignInActivity.this).saveUser(signInResponse.getData().getUser());
                    SharedPrefManager.getInstance(SignInActivity.this).saveUserToken(signInResponse.getData().getToken());
                    SharedPrefManager.getInstance(SignInActivity.this).savePassword(activitySigninBinding.etPassword.getText().toString());
                    CometChat.login(customerFirstName, AppConstants.API_KEY, new CometChat.CallbackListener<User>() {

                        @Override
                        public void onSuccess(User user) {
                            AppConstants.alertMessage(1, R.drawable.check, "Success!", "Thank you. You have successfully Signed In!", SignInActivity.this, MainActivity.class, "GONE");
                        }
                        @Override
                        public void onError(CometChatException e) {

                        }
                    });

                } else if (statusCode == 422) {
                    AppConstants.alertMessage(0, R.drawable.ic_error_2, "Failed!", "The given data was invalid", SignInActivity.this, MainActivity.class, "VISIBLE");
                } else if (statusCode == 401) {
                    AppConstants.alertMessage(0, R.drawable.ic_error_2, "Failed!", "Wrong Password or Email", SignInActivity.this, MainActivity.class, "VISIBLE");
                } else if (statusCode == 404) {
                    AppConstants.alertMessage(0, R.drawable.ic_error_2, "Failed!", "User not found", SignInActivity.this, MainActivity.class, "VISIBLE");
                } else {
                    AppConstants.alertMessage(0, R.drawable.ic_error_2, "Failed!", "Try again", SignInActivity.this, MainActivity.class, "VISIBLE");
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

    private void userSignIn(String email, String password) {
        signInViewModel.getSignInRequest(email, password);
    }

    @Override
    protected void onStart() {
        super.onStart();
        if (SharedPrefManager.getInstance(this).isLoggedIn()) {
            Intent intent = new Intent(this, MainActivity.class);
            startActivity(intent);
        }
    }
}
