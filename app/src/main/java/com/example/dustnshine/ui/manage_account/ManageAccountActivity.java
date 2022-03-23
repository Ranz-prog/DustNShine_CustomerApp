package com.example.dustnshine.ui.manage_account;

import androidx.appcompat.app.AppCompatActivity;
import androidx.cardview.widget.CardView;
import androidx.databinding.DataBindingUtil;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;

import android.app.AlertDialog;
import android.app.Dialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.os.Handler;
import android.text.TextUtils;
import android.transition.AutoTransition;
import android.transition.TransitionManager;
import android.util.Log;
import android.view.View;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;

import com.example.dustnshine.MainActivity;
import com.example.dustnshine.R;
import com.example.dustnshine.databinding.ActivityManageAccountBinding;
import com.example.dustnshine.response.ChangePasswordResponse;
import com.example.dustnshine.response.LogoutResponse;
import com.example.dustnshine.response.UserManagementResponse;
import com.example.dustnshine.storage.SharedPrefManager;
import com.example.dustnshine.ui.signin.SignInActivity;
import com.example.dustnshine.utils.AppConstants;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class ManageAccountActivity extends AppCompatActivity {

    private LinearLayout personalInfoView;
    private CardView personalInfoCardView;
    private Dialog changePassword;
    private static String userToken;
    private ActivityManageAccountBinding activityManageAccountBinding;
    private ManageAccountViewModel manageAccountViewModel;
    private static String firstName, lastName, mobileNumber, email, houseNumber, street, barangay, cityMunicipality, province, zipcode, password, passwordConfirmation;
    private static int userID;
    private Pattern pattern;
    private Matcher matcher;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        this.getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,WindowManager.LayoutParams.FLAG_FULLSCREEN);

        activityManageAccountBinding = DataBindingUtil.setContentView(this, R.layout.activity_manage_account);
        manageAccountViewModel =  new ViewModelProvider(ManageAccountActivity.this).get(ManageAccountViewModel.class);
        userToken = SharedPrefManager.getInstance(ManageAccountActivity.this).getUserToken();
        pattern = Pattern.compile(AppConstants.regex);

        personalInfoView = findViewById(R.id.personal_info_view);
        personalInfoCardView = findViewById(R.id.personal_info_cardview);

        getUserInformation(userToken);
        activityManageAccountBinding.btnSaveDetails.setEnabled(false);
        disabled();

        activityManageAccountBinding.btnEditPassword.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                editPasswordDialogBox();
                changePassword.show();
            }
        });

        activityManageAccountBinding.tvPersonalInfo.setOnClickListener(new View.OnClickListener() {
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

        activityManageAccountBinding.btnBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getApplication(), MainActivity.class);
                startActivity(intent);
            }
        });

        activityManageAccountBinding.tvLogout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                logoutDialog();
            }
        });

        activityManageAccountBinding.btnCancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                TransitionManager.beginDelayedTransition(personalInfoCardView, new AutoTransition());
                personalInfoView.setVisibility(View.GONE);
                activityManageAccountBinding.btnEditDetails.setEnabled(true);
                activityManageAccountBinding.btnSaveDetails.setEnabled(false);
                disabled();
            }
        });

        activityManageAccountBinding.btnEditDetails.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                activityManageAccountBinding.btnEditDetails.setEnabled(false);
                activityManageAccountBinding.btnSaveDetails.setEnabled(true);
                enable();
            }
        });

        activityManageAccountBinding.btnSaveDetails.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                firstName = activityManageAccountBinding.etFirstName.getText().toString();
                lastName = activityManageAccountBinding.etLastName.getText().toString();
                mobileNumber = activityManageAccountBinding.etMobileNumber.getText().toString();
                email = activityManageAccountBinding.etEmailAddress.getText().toString();
                houseNumber = activityManageAccountBinding.etHouseNo.getText().toString();
                street = activityManageAccountBinding.etStreet.getText().toString();
                barangay = activityManageAccountBinding.etBarangay.getText().toString();
                cityMunicipality = activityManageAccountBinding.etCityMunicipality.getText().toString();
                province = activityManageAccountBinding.etProvince.getText().toString();
                zipcode = activityManageAccountBinding.etZipCode.getText().toString();
                password = activityManageAccountBinding.etPassword.getText().toString();
                matcher = pattern.matcher(email);

                if (TextUtils.isEmpty(firstName)) {
                    activityManageAccountBinding.etFirstName.setError("Please enter your First Name");
                    activityManageAccountBinding.etFirstName.requestFocus();
                } else if (TextUtils.isEmpty(lastName)) {
                    activityManageAccountBinding.etLastName.setError("Please enter your Last Name");
                    activityManageAccountBinding.etLastName.requestFocus();
                } else if (!matcher.matches()) {
                    activityManageAccountBinding.etEmailAddress.setError("Invalid email");
                    activityManageAccountBinding.etEmailAddress.requestFocus();
                } else if (TextUtils.isEmpty(email)) {
                    activityManageAccountBinding.etEmailAddress.setError("Please enter your Email Address");
                    activityManageAccountBinding.etEmailAddress.requestFocus();
                } else if (TextUtils.isEmpty(mobileNumber)) {
                    activityManageAccountBinding.etMobileNumber.setError("Please enter your Mobile Number");
                    activityManageAccountBinding.etMobileNumber.requestFocus();
                } else if (mobileNumber.length() < 11 || mobileNumber.length() > 11) {
                    activityManageAccountBinding.etMobileNumber.setError("Mobile number must be 11 characters");
                    activityManageAccountBinding.etMobileNumber.requestFocus();
                } else if (TextUtils.isEmpty(houseNumber)) {
                    activityManageAccountBinding.etHouseNo.setError("Please enter your House no.");
                    activityManageAccountBinding.etHouseNo.requestFocus();
                } else if (houseNumber.charAt(0) == '0') {
                    activityManageAccountBinding.etHouseNo.setError("House no. should not start in 0");
                    activityManageAccountBinding.etHouseNo.requestFocus();
                } else if (TextUtils.isEmpty(street)) {
                    activityManageAccountBinding.etStreet.setError("Please enter your Street");
                    activityManageAccountBinding.etStreet.requestFocus();
                } else if (TextUtils.isEmpty(barangay)) {
                    activityManageAccountBinding.etBarangay.setError("Please enter your Barangay");
                    activityManageAccountBinding.etBarangay.requestFocus();
                } else if (TextUtils.isEmpty(cityMunicipality)) {
                    activityManageAccountBinding.etCityMunicipality.setError("Please enter your City/ Municipality");
                    activityManageAccountBinding.etCityMunicipality.requestFocus();
                } else if (TextUtils.isEmpty(province)) {
                    activityManageAccountBinding.etProvince.setError("Please enter your Province");
                    activityManageAccountBinding.etProvince.requestFocus();
                } else if (TextUtils.isEmpty(zipcode)) {
                    activityManageAccountBinding.etZipCode.setError("Please enter your Zipcode");
                    activityManageAccountBinding.etZipCode.requestFocus();
                } else {
                    updateUserInformation(userID, userToken, firstName, lastName, mobileNumber, email, houseNumber, street, barangay, cityMunicipality, province, zipcode, password, password);
                }
            }
        });
    }

    private void disabled(){
        activityManageAccountBinding.etFirstName.setEnabled(false);
        activityManageAccountBinding.etLastName.setEnabled(false);
        activityManageAccountBinding.etMobileNumber.setEnabled(false);
        activityManageAccountBinding.etEmailAddress.setEnabled(false);
        activityManageAccountBinding.etHouseNo.setEnabled(false);
        activityManageAccountBinding.etStreet.setEnabled(false);
        activityManageAccountBinding.etBarangay.setEnabled(false);
        activityManageAccountBinding.etCityMunicipality.setEnabled(false);
        activityManageAccountBinding.etProvince.setEnabled(false);
        activityManageAccountBinding.etZipCode.setEnabled(false);
        activityManageAccountBinding.etPassword.setEnabled(false);
        activityManageAccountBinding.btnEditPassword.setEnabled(false);
    }

    private void enable(){
        activityManageAccountBinding.etFirstName.setEnabled(true);
        activityManageAccountBinding.etLastName.setEnabled(true);
        activityManageAccountBinding.etMobileNumber.setEnabled(true);
        activityManageAccountBinding.etEmailAddress.setEnabled(true);
        activityManageAccountBinding.etHouseNo.setEnabled(true);
        activityManageAccountBinding.etStreet.setEnabled(true);
        activityManageAccountBinding.etBarangay.setEnabled(true);
        activityManageAccountBinding.etCityMunicipality.setEnabled(true);
        activityManageAccountBinding.etProvince.setEnabled(true);
        activityManageAccountBinding.etZipCode.setEnabled(true);
        activityManageAccountBinding.etPassword.setEnabled(true);
        activityManageAccountBinding.btnEditPassword.setEnabled(true);
    }

    private void editPasswordDialogBox(){
        EditText etOldPass, etNewPassword, etConfirmPassword;
        Button btnCancel, btnSave;

        changePassword = new Dialog(this);
        changePassword.setContentView(R.layout.change_password_dialogbox);
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            changePassword.getWindow().setBackgroundDrawable(getDrawable(R.drawable.pop_up_background));
        }
        changePassword.setCancelable(false); //Optional para lang d mag close pag clinick ang labas
        changePassword.getWindow().getAttributes().windowAnimations = R.style.DialogAnimation; //Setting the animations to dialog

        btnCancel = changePassword.findViewById(R.id.cancelNewPassword);
        btnSave = changePassword.findViewById(R.id.saveNewPassword);
        etOldPass = changePassword.findViewById(R.id.etOldPassword);
        etNewPassword = changePassword.findViewById(R.id.etNewPassword);
        etConfirmPassword = changePassword.findViewById(R.id.etConfirmNewPassword);

        btnCancel.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    enable();
                    changePassword.dismiss();
                }
        });

        btnSave.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                if(etOldPass.getText().toString().isEmpty()){
                    etOldPass.setError("Enter your old password", null);
                    etOldPass.requestFocus();
                } else if(!etOldPass.getText().toString().equals(SharedPrefManager.getInstance(ManageAccountActivity.this).getPassword())){
                    etOldPass.setError("Old password not correct", null);
                    etOldPass.requestFocus();
                } else if(etNewPassword.getText().toString().isEmpty()){
                    etNewPassword.setError("Enter your new password", null);
                    etNewPassword.requestFocus();
                } else if(etNewPassword.getText().toString().length() < 8){
                    etNewPassword.setError("Password must be at least 8 characters", null);
                    etNewPassword.requestFocus();
                } else if(etConfirmPassword.getText().toString().isEmpty()){
                    etConfirmPassword.setError("Re-type your new password", null);
                    etConfirmPassword.requestFocus();
                } else if(!etConfirmPassword.getText().toString().equals(etNewPassword.getText().toString())){
                    etConfirmPassword.setError("Password do not match", null);
                    etConfirmPassword.requestFocus();
                } else {
                    userChangePassword(userToken, etOldPass.getText().toString(), etNewPassword.getText().toString(), etConfirmPassword.getText().toString());
                }
            }
        });
    }

    private void logoutDialog(){
        DialogInterface.OnClickListener dialogClickListener = new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                switch (which){
                    case DialogInterface.BUTTON_POSITIVE:
                        logOutUser();
                        break;
                    case DialogInterface.BUTTON_NEGATIVE:
                        break;
                }
            }
        };

        AlertDialog.Builder builder = new AlertDialog.Builder(ManageAccountActivity.this);
        builder.setMessage("Are you sure you want to logout?").setPositiveButton("Yes", dialogClickListener)
                .setNegativeButton("No", dialogClickListener).show();
    }


    public void logOutUser(){
        manageAccountViewModel.getSignOutRequest(userToken).observe(ManageAccountActivity.this, new Observer<LogoutResponse>() {
            @Override
            public void onChanged(LogoutResponse logoutResponse) {
                    if(logoutResponse != null){
                        new Handler().postDelayed(new Runnable() {
                            @Override
                            public void run() {
                                SharedPrefManager.getInstance(ManageAccountActivity.this).clear();
                                Intent intent = new Intent(ManageAccountActivity.this, SignInActivity.class);
                                startActivity(intent);
                                finish();
                            }
                        }, 1000);
                    } else {
                        Log.d("TAG", "Invalid Request");
                    }
            }
        });
    }

    private void getUserInformation(String userToken){
        manageAccountViewModel.getUserInformationRequest(userToken).observe(ManageAccountActivity.this, new Observer<UserManagementResponse>() {
            @Override
            public void onChanged(UserManagementResponse userManagementResponse) {
                if(userManagementResponse == null) {
                    Log.d("TAG", "Invalid Request");
                } else if(userManagementResponse.getData().get(0).getAddress().isEmpty() || userManagementResponse.getData().get(0).getAddress() == null) {
                    userID = userManagementResponse.getData().get(0).getId();
                    activityManageAccountBinding.etFirstName.setText(userManagementResponse.getData().get(0).getFirst_name());
                    activityManageAccountBinding.etLastName.setText(userManagementResponse.getData().get(0).getLast_name());
                    activityManageAccountBinding.etEmailAddress.setText(userManagementResponse.getData().get(0).getEmail());
                    activityManageAccountBinding.etMobileNumber.setText(userManagementResponse.getData().get(0).getMobile_number());
                    activityManageAccountBinding.etPassword.setText(SharedPrefManager.getInstance(ManageAccountActivity.this).getPassword());
                    activityManageAccountBinding.etHouseNo.setText("");
                    activityManageAccountBinding.etStreet.setText("");
                    activityManageAccountBinding.etBarangay.setText("");
                    activityManageAccountBinding.etCityMunicipality.setText("");
                    activityManageAccountBinding.etProvince.setText("");
                    activityManageAccountBinding.etZipCode.setText("");
                } else {
                    userID = userManagementResponse.getData().get(0).getId();
                    activityManageAccountBinding.etFirstName.setText(userManagementResponse.getData().get(0).getFirst_name());
                    activityManageAccountBinding.etLastName.setText(userManagementResponse.getData().get(0).getLast_name());
                    activityManageAccountBinding.etEmailAddress.setText(userManagementResponse.getData().get(0).getEmail());
                    activityManageAccountBinding.etMobileNumber.setText(userManagementResponse.getData().get(0).getMobile_number());
                    activityManageAccountBinding.etPassword.setText(SharedPrefManager.getInstance(ManageAccountActivity.this).getPassword());
                    activityManageAccountBinding.etHouseNo.setText(String.valueOf(userManagementResponse.getData().get(0).getAddress().get(0).getHouse_number()));
                    activityManageAccountBinding.etStreet.setText(userManagementResponse.getData().get(0).getAddress().get(0).getStreet());
                    activityManageAccountBinding.etBarangay.setText(userManagementResponse.getData().get(0).getAddress().get(0).getBarangay());
                    activityManageAccountBinding.etCityMunicipality.setText(userManagementResponse.getData().get(0).getAddress().get(0).getMunicipality());
                    activityManageAccountBinding.etProvince.setText(userManagementResponse.getData().get(0).getAddress().get(0).getProvince());
                    activityManageAccountBinding.etZipCode.setText(userManagementResponse.getData().get(0).getAddress().get(0).getZipcode());
                }
            }
        });
    }

    private void updateUserInformation(int user_id, String userToken, String firstName, String lastName, String mobileNumber, String email, String house_number, String street, String barangay, String municipality, String province, String zipcode,  String password, String passwordConfirmation){
        manageAccountViewModel.userInformationUpdate(user_id, userToken, firstName, lastName, mobileNumber, email, house_number, street, barangay, municipality, province, zipcode, password, passwordConfirmation).observe(ManageAccountActivity.this, new Observer<UserManagementResponse>() {
            @Override
            public void onChanged(UserManagementResponse userManagementResponse) {
                if(userManagementResponse == null){
                    AppConstants.alertMessage(0, R.drawable.ic_error_2, "Failed!", "Try Again", ManageAccountActivity.this, ManageAccountActivity.class, "VISIBLE");
                } else {
                    Log.d("SUCCESS", "SUCCESS");
                    AppConstants.alertMessage(0, R.drawable.check, "Success!", "Thank you. You have successfully changed your information!", ManageAccountActivity.this, ManageAccountActivity.class, "VISIBLE");
                    activityManageAccountBinding.btnEditDetails.setEnabled(true);
                    activityManageAccountBinding.btnSaveDetails.setEnabled(false);
                    disabled();
                }
            }
        });
    }

    private void userChangePassword(String userToken, String oldPassword, String password, String newPassword){
        manageAccountViewModel.getChangePasswordRequest(userToken, oldPassword, password, newPassword).observe(ManageAccountActivity.this, new Observer<ChangePasswordResponse>() {
            @Override
            public void onChanged(ChangePasswordResponse changePasswordResponse) {
                if(changePasswordResponse == null){
                    AppConstants.alertMessage(0, R.drawable.ic_error_2, "Failed!", "Try Again", ManageAccountActivity.this, ManageAccountActivity.class, "VISIBLE");
                    changePassword.dismiss();
                } else {
                    changePassword.dismiss();
                    AppConstants.alertMessage(0, R.drawable.check, "Success!", "Successfully changed. Session expired, please sign in again...", ManageAccountActivity.this, SignInActivity.class, "GONE");
                    logOutUser();
                }
            }
        });
    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
        Intent intent = new Intent(getApplication(), MainActivity.class);
        startActivity(intent);
    }

}
