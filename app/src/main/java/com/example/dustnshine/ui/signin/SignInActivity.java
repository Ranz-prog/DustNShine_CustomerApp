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
import com.example.dustnshine.ui.manage_account.ManageAccountActivity;
import com.example.dustnshine.ui.signup.SignUpActivity;

public class SignInActivity extends AppCompatActivity {

    private long backButtonCount;
    private Button btnSearch;
    private EditText editTextEmailSearch;
    private AlertDialog.Builder dialogBuilder;
    private AlertDialog dialog;
    private SignInViewModel signInViewModel;
    private ActivitySigninBinding activitySigninBinding;
    private static String email, password;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        this.getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);

        signInViewModel = new ViewModelProvider(SignInActivity.this).get(SignInViewModel.class);
        activitySigninBinding = DataBindingUtil.setContentView(this, R.layout.activity_signin);

        activitySigninBinding.btnSignIn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                email = activitySigninBinding.etEmailAddress.getText().toString();
                password = activitySigninBinding.etPassword.getText().toString();

                if(TextUtils.isEmpty(email)){
                    activitySigninBinding.etEmailAddress.setError("Email is required");
                    activitySigninBinding.etEmailAddress.requestFocus();
                } if (TextUtils.isEmpty(password)) {
                    activitySigninBinding.etPassword.setError("Password is required");
                    activitySigninBinding.etPassword.requestFocus();
                } if (password.length() < 8) {
                    activitySigninBinding.etPassword.setError("Password must be at least 8 characters");
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
                    Toast.makeText(SignInActivity.this, signInResponse.getMessage(), Toast.LENGTH_SHORT).show();
                    SharedPrefManager.getInstance(SignInActivity.this).saveUser(signInResponse.getData().getUser());
                    SharedPrefManager.getInstance(SignInActivity.this).saveUserToken(signInResponse.getData().getToken());
                    getUserInformation(signInResponse.getData().getToken());
                    Intent intent = new Intent(SignInActivity.this, MainActivity.class);
                    startActivity(intent);
                }
                else if (statusCode == 401){
                    Toast.makeText(SignInActivity.this, "Invalid Credentials", Toast.LENGTH_SHORT).show();
                } else {
                    Toast.makeText(SignInActivity.this, "Try Again", Toast.LENGTH_SHORT).show();
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
//            intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
            finish();
            startActivity(intent);
        } else {
            Toast.makeText(this, "Press the back button once again to close the application.", Toast.LENGTH_SHORT).show();
            backButtonCount++;
        }
    }

    public void createNewDialog() {
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

    public void userSignIn(String email, String password){
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
        signInViewModel.getSignInRequest(email, password);
    }

<<<<<<< Updated upstream
//    @Override
//    protected void onStart() {
//        super.onStart();
//        if(SharedPrefManager.getInstance(this).isLoggedIn()){
//            Intent intent = new Intent(this, MainActivity.class);
//            startActivity(intent);
//        };
//    }
=======
    public void getUserInformation(String userToken){
        signInViewModel.getUserInformationRequest(userToken).observe(SignInActivity.this, new Observer<UserManagementResponse>() {
            @Override
            public void onChanged(UserManagementResponse userManagementResponse) {
                if(userManagementResponse == null){
                    Log.d("TAG", "Invalid Request");
                } else {
                    SharedPrefManager.getInstance(SignInActivity.this).saveUserAddress(userManagementResponse.getData().get(0).getAddress().get(0));
                    Log.d("TAG", userManagementResponse.getData().get(0).getAddress().get(0).getBarangay());
                }
            }
        });
    }

    @Override
    protected void onStart() {
        super.onStart();
        if(SharedPrefManager.getInstance(this).isLoggedIn()){
            Intent intent = new Intent(this, MainActivity.class);
            startActivity(intent);
        };
    }
>>>>>>> Stashed changes
}
