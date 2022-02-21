package com.example.dustnshine.ui;

import androidx.appcompat.app.AppCompatActivity;
import androidx.cardview.widget.CardView;
import androidx.lifecycle.Observer;

import android.app.Dialog;
import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.transition.AutoTransition;
import android.transition.TransitionManager;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.example.dustnshine.R;
import com.example.dustnshine.SignOutViewModel;
import com.example.dustnshine.response.LogoutResponse;
import com.example.dustnshine.models.User;
import com.example.dustnshine.storage.SharedPrefManager;
import com.example.dustnshine.ui.signin.ActivitySignIn;

public class ActivityManageAccount extends AppCompatActivity {

    LinearLayout personalInfoView,returnHome;
    TextView txtPersonalInfo, popText, txtLogout, txtManageCards;
    CardView personalInfoCardView;
    Button reset, edit;
    Dialog dialog;
    EditText fname,lname,mobileNumber,emailAdd;
    EditText pass, retypePass,houseNo,street, barangay, city, province, zipCode; // wala pang data
    int number;
    //Comment ni Jolo

    private String text, userToken;

    private SignOutViewModel signOutViewModel;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        this.getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,WindowManager.LayoutParams.FLAG_FULLSCREEN);

        setContentView(R.layout.activity_manage_acc);

        reset = findViewById(R.id.btnServerLogin);
        returnHome = findViewById(R.id.ReturnBtnOnManageAcc);
        edit = findViewById(R.id.btnEditDetails);

        personalInfoView = findViewById(R.id.personal_info_view);
        txtPersonalInfo = findViewById(R.id.txtPersonalInfo);
        txtLogout = findViewById(R.id.txtLogout);
        txtManageCards = findViewById(R.id.txtManageCards);
        personalInfoCardView = findViewById(R.id.personal_info_cardview);

        fname = findViewById(R.id.FnameET);
        lname = findViewById(R.id.LnameET);
        mobileNumber = findViewById(R.id.MobileNoET);
        emailAdd = findViewById(R.id.EmailAddET);

        pass = findViewById(R.id.PasswordET);
        retypePass = findViewById(R.id.RetypePassET);
        houseNo = findViewById(R.id.HouseNoET);
        street = findViewById(R.id.StreetET);
        barangay = findViewById(R.id.BarangayET);
        city = findViewById(R.id.CityET);
        province = findViewById(R.id.ProvinceET);
        zipCode = findViewById(R.id.ZipCodeET);

        User user = SharedPrefManager.getInstance(this).getUser();
        fname.setText(user.getFirst_name());
        lname.setText(user.getLast_name());
        mobileNumber.setText(user.getMobile_number());
        emailAdd.setText(user.getEmail());

        disabled();

        signOutViewModel = new SignOutViewModel();
        userToken = SharedPrefManager.getInstance(ActivityManageAccount.this).getUserToken();


        txtPersonalInfo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (personalInfoView.getVisibility() == View.GONE){
                    TransitionManager.beginDelayedTransition(personalInfoCardView, new AutoTransition());
                    personalInfoView.setVisibility(View.VISIBLE);
                } else {
                    TransitionManager.beginDelayedTransition(personalInfoCardView, new AutoTransition());
                    personalInfoView.setVisibility(View.GONE);
                }
            }
        });

        txtLogout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                logOutUser();
            }
        });


        edit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                text = "Are you sure you want to change information details?";
                number = 1;
                dialogBox();
                dialog.show(); // Showing the dialog here
            }
        });

        reset.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                text = "Thank you. You have successfully changed your password!";
                number = 2;
                dialogBox();
                dialog.show(); // Showing the dialog here
            }
        });

        returnHome.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });

    }

    public void disabled(){
        fname.setFocusable(false);
        lname.setFocusable(false);
        emailAdd.setFocusable(false);
        mobileNumber.setFocusable(false);

        pass.setFocusable(false);
        retypePass.setFocusable(false);
        houseNo.setFocusable(false);
        street.setFocusable(false);
        barangay.setFocusable(false);
        city.setFocusable(false);
        province.setFocusable(false);
        zipCode.setFocusable(false);

    }

    public void enable(){
        fname.setFocusableInTouchMode(true);
        lname.setFocusableInTouchMode(true);
        emailAdd.setFocusableInTouchMode(true);
        mobileNumber.setFocusableInTouchMode(true);

        pass.setFocusableInTouchMode(true);
        retypePass.setFocusableInTouchMode(true);
        houseNo.setFocusableInTouchMode(true);
        street.setFocusableInTouchMode(true);
        barangay.setFocusableInTouchMode(true);
        city.setFocusableInTouchMode(true);
        province.setFocusableInTouchMode(true);
        zipCode.setFocusableInTouchMode(true);

    }

    public void dialogBox(){

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

        popText.setText(text.toString());//placing message here

        if (number == 1){

            Okay.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    enable();
                    dialog.dismiss();
                }
            });
            //END OF DIALOG BOX
        }

        else {
            Okay.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Toast.makeText(ActivityManageAccount.this, "Success", Toast.LENGTH_SHORT).show();
                    disabled();
                    dialog.dismiss();
                }
            });
            //END OF DIALOG BOX
        }

    }

    private void logOutUser(){
        signOutViewModel.getSignOutRequest(userToken).observe(ActivityManageAccount.this, new Observer<LogoutResponse>() {
            @Override
            public void onChanged(LogoutResponse logoutResponse) {
                    if(logoutResponse == null){
                        Log.d("TAG", "Invalid Request");
                    } else {
                        Toast.makeText(ActivityManageAccount.this, logoutResponse.getMessage(), Toast.LENGTH_SHORT).show();
                        SharedPrefManager.getInstance(ActivityManageAccount.this).clear();
                        Intent intent = new Intent(ActivityManageAccount.this, ActivitySignIn.class);
                        startActivity(intent);
                        finish();
                    }
            }
        });
    }
}
