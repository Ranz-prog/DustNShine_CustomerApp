package com.example.dustnshine.ui;

import android.app.Dialog;
import android.os.Build;
import android.os.Bundle;
import android.util.Patterns;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.example.dustnshine.models.DefaultResponse;
import com.example.dustnshine.R;
import com.example.dustnshine.api.RetrofitClient;
import com.google.android.material.textfield.TextInputEditText;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class ActivitySignUp extends AppCompatActivity {

    private TextInputEditText editTextFirstName, editTextLastName, editTextMobileNumber, editTextEmailAddress, editTextPassword, editTextPasswordConfirmation;

    Button signupBtn;
    Dialog dialog;
    TextView popText;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        this.getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,WindowManager.LayoutParams.FLAG_FULLSCREEN);
        
        setContentView(R.layout.activity_signup);


        editTextFirstName = findViewById(R.id.editTextFirstName);
        editTextLastName = findViewById(R.id.editTextLastName);
        editTextMobileNumber = findViewById(R.id.editTextMobileNumber);
        editTextEmailAddress = findViewById(R.id.editTextEmailAddress);
        editTextPassword = findViewById(R.id.editTextPassword);
        editTextPasswordConfirmation= findViewById(R.id.editTextPasswordConfirmation);

        signupBtn = findViewById(R.id.btnServerLogin);

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

        signupBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                registerUser();
                dialog.show(); // Showing the dialog here
            }
        });

    }

    // User inputs validation
    private void registerUser(){
        String firstName = editTextFirstName.getText().toString().trim();
        String lastName = editTextLastName.getText().toString().trim();
        String mobileNumber = editTextMobileNumber.getText().toString().trim();
        String email = editTextEmailAddress.getText().toString().trim();
        String password = editTextPassword.getText().toString().trim();
        String password_confirmation = editTextPasswordConfirmation.getText().toString().trim();

        if(firstName.isEmpty()){
            editTextFirstName.setError("Email is required");
            editTextFirstName.requestFocus();
            return;
        }
        if(lastName.isEmpty()){
            editTextLastName.setError("Email is required");
            editTextLastName.requestFocus();
            return;
        }
        if(mobileNumber.isEmpty()){
            editTextMobileNumber.setError("Email is required");
            editTextMobileNumber.requestFocus();
            return;
        }
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
        if(password_confirmation.isEmpty()){
            editTextPassword.setError("Password Confirmation is required");
            editTextPassword.requestFocus();
            return;
        }

        //Api Call
        Call<DefaultResponse> call = RetrofitClient
                .getInstance()
                .getApi()
                .registerUser(firstName, lastName, mobileNumber, email, password, password_confirmation);

        call.enqueue(new Callback<DefaultResponse>() {
            @Override
            public void onResponse(Call<DefaultResponse> call, Response<DefaultResponse> response) {
                if(response.code() == 201){
                    DefaultResponse dr = response.body();
                    Toast.makeText(ActivitySignUp.this, dr.getMessage(), Toast.LENGTH_LONG).show();
                } else if (response.code() == 422) {
                    Toast.makeText(ActivitySignUp.this, "User Already Exist", Toast.LENGTH_LONG).show();
                }else {
                    String s = response.errorBody().toString();
                    Toast.makeText(ActivitySignUp.this, s, Toast.LENGTH_LONG).show();
                }
            }

            @Override
            public void onFailure(Call<DefaultResponse> call, Throwable t) {

            }
        });
    }

}
