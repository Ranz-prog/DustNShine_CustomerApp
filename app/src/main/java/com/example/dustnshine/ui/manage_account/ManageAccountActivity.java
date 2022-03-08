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
import android.transition.AutoTransition;
import android.transition.TransitionManager;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.EditText;
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
import com.example.dustnshine.ui.home.HomeFragment;
import com.example.dustnshine.ui.signin.SignInActivity;

public class ManageAccountActivity extends AppCompatActivity {

    private ImageView btnBack;
    private LinearLayout personalInfoView;
    private TextView tvPersonalInfo, popText, tvLogout, tvManageCards;
    private CardView personalInfoCardView;
    private Button reset, edit;
    private Dialog dialog;
    private static int number;
    private String text, userToken;
    private ActivityManageAccountBinding activitySignupBinding;
    private ManageAccountViewModel manageAccountViewModel;
    private static String firstName, lastName, mobileNumber, email, houseNumber, street, barangay, cityMunicipality, province, zipcode, password, passwordConfirmation;
    private static int userID;
    private EditText etFirstName;
    private Fragment homeFragment;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        this.getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,WindowManager.LayoutParams.FLAG_FULLSCREEN);

        activitySignupBinding = DataBindingUtil.setContentView(this, R.layout.activity_manage_account);
        manageAccountViewModel =  new ViewModelProvider(ManageAccountActivity.this).get(ManageAccountViewModel.class);
        userToken = SharedPrefManager.getInstance(ManageAccountActivity.this).getUserToken();
        etFirstName = findViewById(R.id.etFirstName);
        homeFragment = new HomeFragment();

        btnBack = findViewById(R.id.backManageAcc);
        personalInfoView = findViewById(R.id.personal_info_view);
        tvPersonalInfo = findViewById(R.id.tvPersonalInfo);
        tvLogout = findViewById(R.id.tvLogout);
        tvManageCards = findViewById(R.id.tvManageCards);
        personalInfoCardView = findViewById(R.id.personal_info_cardview);
        getUserInformation(userToken);
        disabled();

        User user = SharedPrefManager.getInstance(this).getUser();
        AddressModel addressModel = SharedPrefManager.getInstance(this).getUserAddress();
//        activitySignupBinding.etFirstName.setText(user.getFirst_name());
//        activitySignupBinding.etLastName.setText(user.getLast_name());
//        activitySignupBinding.etMobileNumber.setText(user.getMobile_number());
//        activitySignupBinding.etEmailAddress.setText(user.getEmail());
//        activitySignupBinding.etHouseNo.setText(String.valueOf(addressModel.getHouse_number()));
//        activitySignupBinding.etStreet.setText(addressModel.getStreet());
//        activitySignupBinding.etBarangay.setText(addressModel.getBarangay());
//        activitySignupBinding.etCityMunicipality.setText(addressModel.getMunicipality());
//        activitySignupBinding.etProvince.setText(addressModel.getProvince());
//        activitySignupBinding.etZipCode.setText(addressModel.getZipcode());

        disabled();

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
                getSupportFragmentManager().beginTransaction().replace(R.id.container, homeFragment).commit();
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
                passwordConfirmation = activitySignupBinding.etRetypePassword.getText().toString();
                updateUserInformation(userID, userToken, firstName, lastName, mobileNumber, email, houseNumber, street, barangay, cityMunicipality, province, zipcode, "123456789", "123456789");
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
        activitySignupBinding.etRetypePassword.setEnabled(false);
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
        activitySignupBinding.etRetypePassword.setEnabled(true);
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
//                    activitySignupBinding.etPassword.setText(userManagementResponse.getData().get(0).getMobile_number());
//                    activitySignupBinding.etRetypePassword.setText(userManagementResponse.getData().get(0).getMobile_number());
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

}
