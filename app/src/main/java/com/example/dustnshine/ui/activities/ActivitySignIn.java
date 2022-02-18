package com.example.dustnshine.ui.activities;

import android.content.Intent;
import android.os.Bundle;
import android.util.Patterns;
import android.view.View;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import com.example.dustnshine.MainActivity;
import com.example.dustnshine.R;
import com.example.dustnshine.api.RetrofitClient;
import com.example.dustnshine.models.LoginResponse;
import com.example.dustnshine.storage.SharedPrefManager;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class ActivitySignIn extends AppCompatActivity {

    private long backButtonCount;

    private EditText editTextEmailAddress, editTextPassword;
    private AlertDialog.Builder dialogBuilder;
    private AlertDialog dialog;
    EditText emailSearch;
    Button search, signInBtn;
    TextView createAcc,forget;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        this.getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,WindowManager.LayoutParams.FLAG_FULLSCREEN);

        setContentView(R.layout.activity_signin);

        editTextEmailAddress = findViewById(R.id.editTextEmailAddress);
        editTextPassword = findViewById(R.id.editTextPassword);

        createAcc = findViewById(R.id.createAcc);
        signInBtn = findViewById(R.id.btnServerLogin);
        forget = findViewById(R.id.forget);

        createAcc.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(ActivitySignIn.this, ActivitySignUp.class);
                startActivity(intent);
            }
        });

        signInBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
//                Intent intent = new Intent(ActivitySignIn.this, MainActivity.class);
//                startActivity(intent);
                userLogin();
            }
        });

        forget.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                createNewDialog();
            }
        });
    }

    @Override
    public void onBackPressed()
    {
        if(backButtonCount >= 1)
        {
            Intent intent = new Intent(Intent.ACTION_MAIN);
            intent.addCategory(Intent.CATEGORY_HOME);
//            intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
            finish();
            startActivity(intent);
        }
        else
        {
            Toast.makeText(this, "Press the back button once again to close the application.", Toast.LENGTH_SHORT).show();
            backButtonCount++;
        }
    }

    public void  createNewDialog(){
        dialogBuilder = new AlertDialog.Builder(this);
        final  View searchPopUp = getLayoutInflater().inflate(R.layout.activity_search_email,null);
        emailSearch = searchPopUp.findViewById(R.id.enterEmailSearch);

        search = searchPopUp.findViewById(R.id.searchBtn);

        dialogBuilder.setView(searchPopUp);
        dialog = dialogBuilder.create();
        dialog.show();

        search.setOnClickListener(new View.OnClickListener() {
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

    private void userLogin(){

        String email = editTextEmailAddress.getText().toString().trim();
        String password = editTextPassword.getText().toString().trim();

        if(email.isEmpty()){
            editTextEmailAddress.setError("Email is required");
            editTextEmailAddress.requestFocus();
            return;
        }
        if(!Patterns.EMAIL_ADDRESS.matcher(email).matches()){
            editTextEmailAddress.setError("Enter a valid email");
            editTextEmailAddress.requestFocus();
            return;
        }
        if(password.isEmpty()){
            editTextPassword.setError("Password is required");
            editTextPassword.requestFocus();
            return;
        }
        if(password.length() < 8){
            editTextPassword.setError("Password should 8 character long");
            editTextPassword.requestFocus();
            return;
        }

        Call<LoginResponse> call = RetrofitClient.getInstance().getApi().userLogin(email, password);


        call.enqueue(new Callback<LoginResponse>() {
            @Override
            public void onResponse(Call<LoginResponse> call, Response<LoginResponse> response) {

                LoginResponse loginResponse = response.body();
                if(response.code() == 200){
                    Toast.makeText(ActivitySignIn.this, loginResponse.getMessage(), Toast.LENGTH_LONG).show();
                    SharedPrefManager.getInstance(ActivitySignIn.this).saveUser(loginResponse.getData().getUser());
                    Intent intent = new Intent(ActivitySignIn.this, MainActivity.class);
                    startActivity(intent);
                }
                if(response.code() == 401){
                    Toast.makeText(ActivitySignIn.this, "Invalid Credentials", Toast.LENGTH_LONG).show();
                }
                else {
                    Toast.makeText(ActivitySignIn.this, loginResponse.getMessage(), Toast.LENGTH_LONG).show();
                }
            }

            @Override
            public void onFailure(Call<LoginResponse> call, Throwable t) {

            }
        });
    }
}
