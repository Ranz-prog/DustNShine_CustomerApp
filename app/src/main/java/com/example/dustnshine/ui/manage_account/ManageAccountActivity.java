package com.example.dustnshine.ui.manage_account;

import androidx.appcompat.app.AppCompatActivity;
import androidx.cardview.widget.CardView;
import androidx.databinding.DataBindingUtil;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;

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
import com.example.dustnshine.databinding.ActivityManageAccountBinding;
import com.example.dustnshine.models.AddressModel;
import com.example.dustnshine.response.LogoutResponse;
import com.example.dustnshine.models.User;
import com.example.dustnshine.response.UserManagementResponse;
import com.example.dustnshine.storage.SharedPrefManager;
import com.example.dustnshine.ui.signin.SignInActivity;
import com.example.dustnshine.ui.signin.SignInViewModel;

public class ManageAccountActivity extends AppCompatActivity {

    private LinearLayout personalInfoView, btnBack;
    private TextView tvPersonalInfo, popText, tvLogout, tvManageCards;
    private CardView personalInfoCardView;
    private Button reset, edit;
    private Dialog dialog;
    private EditText fname, lname, mobileNumber,emailAdd, pass, retypePass, houseNo, street, barangay, city, province, zipCode; // wala pang data
    private static int number;
    private String text, userToken;
    private ActivityManageAccountBinding activitySignupBinding;
    private ManageAccountViewModel manageAccountViewModel;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        this.getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,WindowManager.LayoutParams.FLAG_FULLSCREEN);

        activitySignupBinding = DataBindingUtil.setContentView(this, R.layout.activity_manage_account);
        manageAccountViewModel =  new ViewModelProvider(ManageAccountActivity.this).get(ManageAccountViewModel.class);
        userToken = SharedPrefManager.getInstance(ManageAccountActivity.this).getUserToken();

        btnBack = findViewById(R.id.btnBack);
        personalInfoView = findViewById(R.id.personal_info_view);
        tvPersonalInfo = findViewById(R.id.tvPersonalInfo);
        tvLogout = findViewById(R.id.tvLogout);
        tvManageCards = findViewById(R.id.tvManageCards);
        personalInfoCardView = findViewById(R.id.personal_info_cardview);

        fname = findViewById(R.id.etFirstName);
        lname = findViewById(R.id.etLastName);
        mobileNumber = findViewById(R.id.etMobileNumber);
        emailAdd = findViewById(R.id.etEmailAddress);

        pass = findViewById(R.id.etPassword);
        retypePass = findViewById(R.id.etRetypePassword);
        houseNo = findViewById(R.id.etHouseNo);
        street = findViewById(R.id.etStreet);
        barangay = findViewById(R.id.etBarangay);
        city = findViewById(R.id.etCityMunicipality);
        province = findViewById(R.id.etProvince);
        zipCode = findViewById(R.id.etProvince);

        User user = SharedPrefManager.getInstance(this).getUser();
        AddressModel addressModel = SharedPrefManager.getInstance(this).getUserAddress();

        activitySignupBinding.etFirstName.setText(user.getFirst_name());
        activitySignupBinding.etLastName.setText(user.getLast_name());
        activitySignupBinding.etMobileNumber.setText(user.getMobile_number());
        activitySignupBinding.etEmailAddress.setText(user.getEmail());
        activitySignupBinding.etHouseNo.setText(String.valueOf(addressModel.getHouse_number()));
        activitySignupBinding.etStreet.setText(addressModel.getStreet());
        activitySignupBinding.etBarangay.setText(addressModel.getBarangay());
        activitySignupBinding.etCityMunicipality.setText(addressModel.getMunicipality());
        activitySignupBinding.etProvince.setText(addressModel.getProvince());
        activitySignupBinding.etZipCode.setText(addressModel.getZipcode());

//        disabled();

        tvPersonalInfo.setOnClickListener(new View.OnClickListener() {
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

        tvLogout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                logOutUser();
            }
        });


        activitySignupBinding.btnEditDetails.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                text = "Are you sure you want to change information details?";
                number = 1;
                dialogBox();
                dialog.show(); // Showing the dialog here
            }
        });

        activitySignupBinding.btnSaveDetails.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                text = "Thank you. You have successfully changed your password!";
                number = 2;
                dialogBox();
                dialog.show(); // Showing the dialog here
            }
        });

        btnBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });

    }

//    public void disabled(){
//        fname.setFocusable(false);
//        lname.setFocusable(false);
//        emailAdd.setFocusable(false);
//        mobileNumber.setFocusable(false);
//
//        pass.setFocusable(false);
//        retypePass.setFocusable(false);
//        houseNo.setFocusable(false);
//        street.setFocusable(false);
//        barangay.setFocusable(false);
//        city.setFocusable(false);
//        province.setFocusable(false);
//        zipCode.setFocusable(false);
//
//    }
//
//    public void enable(){
//        fname.setFocusableInTouchMode(true);
//        lname.setFocusableInTouchMode(true);
//        emailAdd.setFocusableInTouchMode(true);
//        mobileNumber.setFocusableInTouchMode(true);
//
//        pass.setFocusableInTouchMode(true);
//        retypePass.setFocusableInTouchMode(true);
//        houseNo.setFocusableInTouchMode(true);
//        street.setFocusableInTouchMode(true);
//        barangay.setFocusableInTouchMode(true);
//        city.setFocusableInTouchMode(true);
//        province.setFocusableInTouchMode(true);
//        zipCode.setFocusableInTouchMode(true);
//
//    }

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
//                    enable();
                    dialog.dismiss();
                }
            });
            //END OF DIALOG BOX
        }

        else {
            Okay.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Toast.makeText(ManageAccountActivity.this, "Success", Toast.LENGTH_SHORT).show();
//                    disabled();
                    dialog.dismiss();
                }
            });
            //END OF DIALOG BOX
        }

    }

    private void logOutUser(){
        manageAccountViewModel.getSignOutRequest(userToken).observe(ManageAccountActivity.this, new Observer<LogoutResponse>() {
            @Override
            public void onChanged(LogoutResponse logoutResponse) {
                    if(logoutResponse == null){
                        Log.d("TAG", "Invalid Request");
                    } else {
                        Toast.makeText(ManageAccountActivity.this, logoutResponse.getMessage(), Toast.LENGTH_SHORT).show();
                        SharedPrefManager.getInstance(ManageAccountActivity.this).clear();
                        Intent intent = new Intent(ManageAccountActivity.this, SignInActivity.class);
                        startActivity(intent);
                        finish();
                    }
            }
        });
    }

}
