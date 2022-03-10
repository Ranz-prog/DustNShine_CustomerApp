package com.example.dustnshine.ui.manage_account;

import androidx.appcompat.app.AppCompatActivity;
import androidx.cardview.widget.CardView;
import androidx.databinding.DataBindingUtil;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;

import android.app.Dialog;
import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.text.method.CharacterPickerDialog;
import android.transition.AutoTransition;
import android.transition.TransitionManager;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.example.dustnshine.MainActivity;
import com.example.dustnshine.R;
import com.example.dustnshine.databinding.ActivityManageAccountBinding;
import com.example.dustnshine.models.AddressModel;
import com.example.dustnshine.response.ChangePasswordResponse;
import com.example.dustnshine.response.LogoutResponse;
import com.example.dustnshine.models.User;
import com.example.dustnshine.response.UserManagementResponse;
import com.example.dustnshine.storage.SharedPrefManager;
import com.example.dustnshine.ui.company_details.CompanyDetailsActivity;
import com.example.dustnshine.ui.home.HomeFragment;
import com.example.dustnshine.ui.signin.SignInActivity;

public class ManageAccountActivity extends AppCompatActivity {

    private ImageView btnBack;
    private LinearLayout personalInfoView;
    private TextView tvPersonalInfo, popText, tvLogout, tvManageCards;
    private CardView personalInfoCardView;
    private Button reset, edit;
    private Dialog dialog, changePassword;
    private static int number;
    private String text, userToken;
    private ActivityManageAccountBinding activitySignupBinding;
    private ManageAccountViewModel manageAccountViewModel;
    private static String firstName, lastName, mobileNumber, email, houseNumber, street, barangay, cityMunicipality, province, zipcode, password, passwordConfirmation;
    private static int userID;
    private Fragment homeFragment;
    private ImageButton editPass;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        this.getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,WindowManager.LayoutParams.FLAG_FULLSCREEN);

        activitySignupBinding = DataBindingUtil.setContentView(this, R.layout.activity_manage_account);
        manageAccountViewModel =  new ViewModelProvider(ManageAccountActivity.this).get(ManageAccountViewModel.class);
        userToken = SharedPrefManager.getInstance(ManageAccountActivity.this).getUserToken();
        homeFragment = new HomeFragment();

        btnBack = findViewById(R.id.backManageAcc);
        personalInfoView = findViewById(R.id.personal_info_view);
        tvPersonalInfo = findViewById(R.id.tvPersonalInfo);
        tvLogout = findViewById(R.id.tvLogout);
        tvManageCards = findViewById(R.id.tvManageCards);
        personalInfoCardView = findViewById(R.id.personal_info_cardview);

        editPass = findViewById(R.id.btnEditPassword);
        getUserInformation(userToken);
        activitySignupBinding.btnSaveDetails.setEnabled(false);
        disabled();

        editPass.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                editPasswordDialogBox();
                changePassword.show();
            }
        });

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

        btnBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getApplication(), MainActivity.class);
                startActivity(intent);
            }
        });

        tvLogout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                logOutUser();
            }
        });

        activitySignupBinding.btnCancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                TransitionManager.beginDelayedTransition(personalInfoCardView, new AutoTransition());
                personalInfoView.setVisibility(View.GONE);
                activitySignupBinding.btnEditDetails.setEnabled(true);
                activitySignupBinding.btnSaveDetails.setEnabled(false);
                disabled();
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
                text = "Thank you. You have successfully changed your information!";
                number = 2;
                firstName = activitySignupBinding.etFirstName.getText().toString();
                lastName = activitySignupBinding.etLastName.getText().toString();
                mobileNumber = activitySignupBinding.etMobileNumber.getText().toString();
                email = activitySignupBinding.etEmailAddress.getText().toString();
                houseNumber = activitySignupBinding.etHouseNo.getText().toString();
                street = activitySignupBinding.etStreet.getText().toString();
                barangay = activitySignupBinding.etBarangay.getText().toString();
                cityMunicipality = activitySignupBinding.etCityMunicipality.getText().toString();
                province = activitySignupBinding.etProvince.getText().toString();
                zipcode = activitySignupBinding.etZipCode.getText().toString();
                password = activitySignupBinding.etPassword.getText().toString();

                updateUserInformation(userID, userToken, firstName, lastName, mobileNumber, email, houseNumber, street, barangay, cityMunicipality, province, zipcode, password, password);
                dialogBox();
                dialog.show(); // Showing the dialog here
            }
        });
    }

    public void disabled(){
        activitySignupBinding.etFirstName.setEnabled(false);
        activitySignupBinding.etLastName.setEnabled(false);
        activitySignupBinding.etMobileNumber.setEnabled(false);
        activitySignupBinding.etEmailAddress.setEnabled(false);
        activitySignupBinding.etHouseNo.setEnabled(false);
        activitySignupBinding.etStreet.setEnabled(false);
        activitySignupBinding.etBarangay.setEnabled(false);
        activitySignupBinding.etCityMunicipality.setEnabled(false);
        activitySignupBinding.etProvince.setEnabled(false);
        activitySignupBinding.etZipCode.setEnabled(false);
        activitySignupBinding.etPassword.setEnabled(false);
        activitySignupBinding.btnEditPassword.setEnabled(false);
    }

    public void enable(){
        activitySignupBinding.etFirstName.setEnabled(true);
        activitySignupBinding.etLastName.setEnabled(true);
        activitySignupBinding.etMobileNumber.setEnabled(true);
        activitySignupBinding.etEmailAddress.setEnabled(true);
        activitySignupBinding.etHouseNo.setEnabled(true);
        activitySignupBinding.etStreet.setEnabled(true);
        activitySignupBinding.etBarangay.setEnabled(true);
        activitySignupBinding.etCityMunicipality.setEnabled(true);
        activitySignupBinding.etProvince.setEnabled(true);
        activitySignupBinding.etZipCode.setEnabled(true);
        activitySignupBinding.etPassword.setEnabled(true);
        activitySignupBinding.btnEditPassword.setEnabled(true);
    }

    public void editPasswordDialogBox(){

        EditText etOldPass, etNewPassword, etConfirmPassword;
        Button btnCancel, btnSave;

        changePassword = new Dialog(this);
        changePassword.setContentView(R.layout.change_password_dialogbox);
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            changePassword.getWindow().setBackgroundDrawable(getDrawable(R.drawable.pop_up_background));
        }
        changePassword.getWindow().setLayout(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT);
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
                    Toast.makeText(ManageAccountActivity.this, "Enter your old password", Toast.LENGTH_SHORT).show();
                } else if(!etOldPass.getText().toString().equals(SharedPrefManager.getInstance(ManageAccountActivity.this).getPassword())){
                    Toast.makeText(ManageAccountActivity.this, "Old password not correct", Toast.LENGTH_SHORT).show();
                } else if(etNewPassword.getText().toString().isEmpty()){
                    Toast.makeText(ManageAccountActivity.this, "Enter your new password", Toast.LENGTH_SHORT).show();
                } else if(etNewPassword.getText().toString().length() < 8){
                    Toast.makeText(ManageAccountActivity.this, "Password must be at least 8 characters", Toast.LENGTH_SHORT).show();
                } else if(etConfirmPassword.getText().toString().isEmpty()){
                    Toast.makeText(ManageAccountActivity.this, "Re-type your new password", Toast.LENGTH_SHORT).show();
                } else if(!etConfirmPassword.getText().toString().equals(etNewPassword.getText().toString())){
                    Toast.makeText(ManageAccountActivity.this, "Password do not match", Toast.LENGTH_SHORT).show();
                } else {
                    userChangePassword(userToken, etOldPass.getText().toString(), etNewPassword.getText().toString(), etConfirmPassword.getText().toString());
                    Toast.makeText(ManageAccountActivity.this, "Successfully Changed Password", Toast.LENGTH_SHORT).show();
                    changePassword.dismiss();
                }
            }
        });
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
                    activitySignupBinding.btnEditDetails.setEnabled(false);
                    activitySignupBinding.btnSaveDetails.setEnabled(true);
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
                    activitySignupBinding.btnEditDetails.setEnabled(true);
                    activitySignupBinding.btnSaveDetails.setEnabled(false);
                    disabled();
                    dialog.dismiss();
                }
            });
            //END OF DIALOG BOX
        }

    }

    public void logOutUser(){
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

    public void getUserInformation(String userToken){
        manageAccountViewModel.getUserInformationRequest(userToken).observe(ManageAccountActivity.this, new Observer<UserManagementResponse>() {
            @Override
            public void onChanged(UserManagementResponse userManagementResponse) {
                if(userManagementResponse == null){
                    Log.d("TAG", "Invalid Request");
                } else {
//                    SharedPrefManager.getInstance(ManageAccountActivity.this).saveUser();
                    SharedPrefManager.getInstance(ManageAccountActivity.this).saveUserAddress(userManagementResponse.getData().get(0).getAddress().get(0));
                    userID = userManagementResponse.getData().get(0).getId();
                    activitySignupBinding.etFirstName.setText(userManagementResponse.getData().get(0).getFirst_name());
                    activitySignupBinding.etLastName.setText(userManagementResponse.getData().get(0).getLast_name());
                    activitySignupBinding.etEmailAddress.setText(userManagementResponse.getData().get(0).getEmail());
                    activitySignupBinding.etMobileNumber.setText(userManagementResponse.getData().get(0).getMobile_number());
                    activitySignupBinding.etPassword.setText(SharedPrefManager.getInstance(ManageAccountActivity.this).getPassword());
                    activitySignupBinding.etHouseNo.setText(String.valueOf(userManagementResponse.getData().get(0).getAddress().get(0).getHouse_number()));
                    activitySignupBinding.etStreet.setText(userManagementResponse.getData().get(0).getAddress().get(0).getStreet());
                    activitySignupBinding.etBarangay.setText(userManagementResponse.getData().get(0).getAddress().get(0).getBarangay());
                    activitySignupBinding.etCityMunicipality.setText(userManagementResponse.getData().get(0).getAddress().get(0).getMunicipality());
                    activitySignupBinding.etProvince.setText(userManagementResponse.getData().get(0).getAddress().get(0).getProvince());
                    activitySignupBinding.etZipCode.setText(userManagementResponse.getData().get(0).getAddress().get(0).getZipcode());
                }
            }
        });
    }

    public void updateUserInformation(int user_id, String userToken, String firstName, String lastName, String mobileNumber, String email, String house_number, String street, String barangay, String municipality, String province, String zipcode,  String password, String passwordConfirmation){
        manageAccountViewModel.userInformationUpdate(user_id, userToken, firstName, lastName, mobileNumber, email, house_number, street, barangay, municipality, province, zipcode, password, passwordConfirmation).observe(ManageAccountActivity.this, new Observer<UserManagementResponse>() {
            @Override
            public void onChanged(UserManagementResponse userManagementResponse) {
                if(userManagementResponse == null){
                    Log.d("TAG", "Invalid Request");
                } else {
                    Log.d("TAG", "Updated Successfully");
                }
            }
        });
    }

    public void userChangePassword(String userToken, String oldPassword, String password, String newPassword){
        manageAccountViewModel.getChangePasswordRequest(userToken, oldPassword, password, newPassword).observe(ManageAccountActivity.this, new Observer<ChangePasswordResponse>() {
            @Override
            public void onChanged(ChangePasswordResponse changePasswordResponse) {
                if(changePasswordResponse == null){
                    Log.d("TAG", "Invalid Request");
                } else {
                    Log.d("TAG", "Updated Successfully");
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
