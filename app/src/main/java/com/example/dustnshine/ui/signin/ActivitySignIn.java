package com.example.dustnshine.ui.signin;

import android.os.Bundle;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.databinding.DataBindingUtil;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;

import com.example.dustnshine.R;
import com.example.dustnshine.databinding.ActivitySigninBinding;
import com.example.dustnshine.factory.SignInViewModelFactory;
import com.example.dustnshine.models.LoginResponse;
import com.example.dustnshine.models.SignInModel;

public class ActivitySignIn extends AppCompatActivity {

    private long backButtonCount;

    private EditText editTextEmailAddress, editTextPassword;
    private AlertDialog.Builder dialogBuilder;
    private AlertDialog dialog;
    EditText emailSearch;
    Button search, signInBtn;
    TextView createAcc, forget;

    private SignInViewModel signInViewModel;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        this.getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);

        ActivitySigninBinding activitySigninBinding = DataBindingUtil.setContentView(this, R.layout.activity_signin);
        signInViewModel = new ViewModelProvider(ActivitySignIn.this, new SignInViewModelFactory(new SignInModel(), this )).get(SignInViewModel.class);
        activitySigninBinding.setViewModel(signInViewModel);

        signInViewModel.getResponse().observe(this, new Observer<LoginResponse>() {
            @Override
            public void onChanged(LoginResponse loginResponse) {
                if(loginResponse != null){
                    Toast.makeText(getApplicationContext(), loginResponse.getMessage(), Toast.LENGTH_LONG).show();
                } else {
                    Toast.makeText(getApplicationContext(), "Login Unsuccessful", Toast.LENGTH_LONG).show();
                }
            }
        });


//        setContentView(R.layout.activity_signin);
//
//        editTextEmailAddress = findViewById(R.id.editTextEmailAddress);
//        editTextPassword = findViewById(R.id.editTextPassword);
//        signInBtn = findViewById(R.id.btnServerLogin);
//
//        signInViewModel = new SignInViewModel();
//
//        signInBtn.setOnClickListener(new View.OnClickListener() {
//
//            @Override
//            public void onClick(View view) {
//                if (TextUtils.isEmpty(editTextEmailAddress.getText().toString())) {
//                    editTextEmailAddress.setError("Enter an E-Mail Address");
//                    editTextEmailAddress.requestFocus();
//                } else if (TextUtils.isEmpty(editTextPassword.getText().toString())) {
//                    editTextPassword.setError("Enter an E-Mail Address");
//                    editTextPassword.requestFocus();
//                } else {
//                    userLogin(editTextEmailAddress.getText().toString(), editTextPassword.getText().toString());
//                    Toast.makeText(ActivitySignIn.this, "Login Successfully", Toast.LENGTH_LONG).show();
//                }
//            }
//        });

//        createAcc = findViewById(R.id.createAcc);
//        signInBtn = findViewById(R.id.btnServerLogin);
//        forget = findViewById(R.id.forget);

//        createAcc.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                Intent intent = new Intent(ActivitySignIn.this, ActivitySignUp.class);
//                startActivity(intent);
//            }
//        });
//
//        signInBtn.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                userLogin();
//            }
//        });
//
//        forget.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                createNewDialog();
//            }
//        });
    }

//    @Override
//    public void onBackPressed() {
//        if (backButtonCount >= 1) {
//            Intent intent = new Intent(Intent.ACTION_MAIN);
//            intent.addCategory(Intent.CATEGORY_HOME);
////            intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
//            finish();
//            startActivity(intent);
//        } else {
//            Toast.makeText(this, "Press the back button once again to close the application.", Toast.LENGTH_SHORT).show();
//            backButtonCount++;
//        }
//    }

//    public void createNewDialog() {
//        dialogBuilder = new AlertDialog.Builder(this);
//        final View searchPopUp = getLayoutInflater().inflate(R.layout.activity_search_email, null);
//        emailSearch = searchPopUp.findViewById(R.id.enterEmailSearch);
//
//        search = searchPopUp.findViewById(R.id.searchBtn);
//
//        dialogBuilder.setView(searchPopUp);
//        dialog = dialogBuilder.create();
//        dialog.show();
//
//        search.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                Intent intent = new Intent(ActivitySignIn.this, ActivityForgetPassword.class);
//                startActivity(intent);
//            }
//        });
//
//    }

//    @Override
//    protected void onStart() {
//        super.onStart();
//        if(SharedPrefManager.getInstance(this).isLoggedIn()){
//            Intent intent = new Intent(this, MainActivity.class);
//            startActivity(intent);
//        };
//    }

//     void userLogin(String email, String password) {
//        signInViewModel.getSignInResponse(email, password).observe(this, new Observer<LoginResponse>() {
//            @Override
//            public void onChanged(LoginResponse loginResponse) {
//
//                SharedPrefManager.getInstance(ActivitySignIn.this).saveUser(loginResponse.getData().getUser());
//
//                new Handler().postDelayed(new Runnable() {
//                                              @Override
//                                              public void run() {
//                                                  startActivity(new Intent(ActivitySignIn.this, MainActivity.class));
//                                                  finish();
//                                              }
//
//                                          }, 500
//                );
//            }
//        });
//    }
}
