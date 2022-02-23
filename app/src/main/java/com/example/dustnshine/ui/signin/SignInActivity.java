package com.example.dustnshine.ui.signin;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.databinding.DataBindingUtil;
import androidx.lifecycle.Observer;

import com.example.dustnshine.MainActivity;
import com.example.dustnshine.R;
import com.example.dustnshine.databinding.ActivitySigninBinding;
import com.example.dustnshine.response.SignInResponse;
import com.example.dustnshine.storage.SharedPrefManager;
import com.example.dustnshine.ui.ForgetPasswordActivity;
import com.example.dustnshine.ui.signup.SignUpActivity;

public class SignInActivity extends AppCompatActivity {

    private long backButtonCount;
    private Button btnSearch;
    private EditText editTextEmailSearch;
    private AlertDialog.Builder dialogBuilder;
    private AlertDialog dialog;
    private SignInViewModel signInViewModel;
    private ActivitySigninBinding activitySigninBinding;
    private String email, password;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        this.getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);

        signInViewModel = new SignInViewModel();

        activitySigninBinding = DataBindingUtil.setContentView(this, R.layout.activity_signin);

        activitySigninBinding.btnSignIn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                email = activitySigninBinding.editTextEmailAddress.getText().toString();
                password = activitySigninBinding.editTextPassword.getText().toString();

                if(TextUtils.isEmpty(email)){
                    activitySigninBinding.editTextEmailAddress.setError("Email is required");
                    activitySigninBinding.editTextEmailAddress.requestFocus();
                } if (TextUtils.isEmpty(password)) {
                    activitySigninBinding.editTextPassword.setError("Password is required");
                    activitySigninBinding.editTextPassword.requestFocus();
                } if (password.length() < 8) {
                    activitySigninBinding.editTextPassword.setError("Password must be at least 8 characters");
                    activitySigninBinding.editTextPassword.requestFocus();
                } else {
                    userSignIn(email, password);
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
        signInViewModel.getSignInRequest(email, password).observe(SignInActivity.this, new Observer<SignInResponse>() {
            @Override
            public void onChanged(SignInResponse signInResponse) {
                if(signInResponse == null){
                    Toast.makeText(SignInActivity.this, signInResponse.getMessage(), Toast.LENGTH_SHORT).show();
                } else {
                    Toast.makeText(SignInActivity.this, signInResponse.getMessage(), Toast.LENGTH_SHORT).show();
                    SharedPrefManager.getInstance(SignInActivity.this).saveUser(signInResponse.getData().getUser());
                    SharedPrefManager.getInstance(SignInActivity.this).saveUserToken(signInResponse.getData().getToken());
                    Intent intent = new Intent(SignInActivity.this, MainActivity.class);
                    startActivity(intent);
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
}
