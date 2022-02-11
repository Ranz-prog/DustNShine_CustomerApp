package com.example.dustnshine.ui.signin;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;
import android.util.Patterns;
import android.view.View;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.databinding.DataBindingUtil;

import com.example.dustnshine.R;
import com.example.dustnshine.SignInCallback;
import com.example.dustnshine.databinding.ActivitySigninBinding;
import com.example.dustnshine.models.LoginResponse;
import com.example.dustnshine.ui.ActivityForgetPassword;
import com.example.dustnshine.ui.signup.ActivitySignUp;

public class ActivitySignIn extends AppCompatActivity {

    private long backButtonCount;
    private Button btnSearch;
    private EditText editTextEmailSearch;
    private AlertDialog.Builder dialogBuilder;
    private AlertDialog dialog;
    private SignInViewModel signInViewModel;
    private ActivitySigninBinding activitySigninBinding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        this.getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);

        signInViewModel = new SignInViewModel();

       activitySigninBinding = DataBindingUtil.setContentView(this, R.layout.activity_signin);

        activitySigninBinding.btnSignIn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(TextUtils.isEmpty(activitySigninBinding.editTextEmailAddress.getText().toString())){
                    activitySigninBinding.editTextEmailAddress.setError("Email is required");
                } else if(Patterns.EMAIL_ADDRESS.matcher(activitySigninBinding.editTextPassword.getText().toString()).matches()) {
                    activitySigninBinding.editTextPassword.setError("Invalid Email");
                } else if(TextUtils.isEmpty(activitySigninBinding.editTextPassword.getText().toString())) {
                    activitySigninBinding.editTextPassword.setError("Password is required");
                } else if(TextUtils.isEmpty(activitySigninBinding.editTextPassword.getText().toString())) {
                    activitySigninBinding.editTextPassword.setError("Password is required");
                } else {
                    signInViewModel.getSignInRequest(activitySigninBinding.editTextEmailAddress.getText().toString(), activitySigninBinding.editTextPassword.getText().toString());
                }
            }
        });

        signInViewModel.setOnSignInListener(new SignInCallback() {
            @Override
            public void signInCallback(Integer statusCode, LoginResponse loginResponse) {
                Log.d("TAG", statusCode.toString());
                if(statusCode == 200){
                    Toast.makeText(getApplicationContext(), "Successfully Logged In", Toast.LENGTH_LONG).show();
                } else if(statusCode == 401){
                    Toast.makeText(getApplicationContext(), "Invalid Credentials", Toast.LENGTH_LONG).show();
                } else {
                    Toast.makeText(getApplicationContext(), "Login Failed", Toast.LENGTH_LONG).show();
                }
            }
        });


        activitySigninBinding.txtCreateAccount.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(ActivitySignIn.this, ActivitySignUp.class);
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
                Intent intent = new Intent(ActivitySignIn.this, ActivityForgetPassword.class);
                startActivity(intent);
            }
        });

    }

//    @Override
//    protected void onStart() {
//        super.onStart();
//        if(SharedPrefManager.getInstance(this).isLoggedIn()){
//            Intent intent = new Intent(this, MainActivity.class);
//            startActivity(intent);
//        };
//    }
}
