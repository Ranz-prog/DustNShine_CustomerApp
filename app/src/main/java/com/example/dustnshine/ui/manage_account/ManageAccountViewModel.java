package com.example.dustnshine.ui.manage_account;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.example.dustnshine.response.ChangePasswordResponse;
import com.example.dustnshine.response.LogoutResponse;
import com.example.dustnshine.service.UserAPIService;
import com.example.dustnshine.response.UserManagementResponse;

public class ManageAccountViewModel extends ViewModel {
    private UserAPIService userAPIService;
    private MutableLiveData<LogoutResponse> logoutResponseMutableLiveData;
    private MutableLiveData<UserManagementResponse> userManagementResponseMutableLiveData;
    private MutableLiveData<UserManagementResponse> updateUserInformationMutableLiveData;
    private MutableLiveData<ChangePasswordResponse> changePasswordResponseMutableLiveData;

    public ManageAccountViewModel() {
        userAPIService = new UserAPIService();
    }

    public LiveData<LogoutResponse> getSignOutRequest(String userToken){
        if (logoutResponseMutableLiveData == null) {
            logoutResponseMutableLiveData = userAPIService.signOutRequest(userToken);
        }
        return logoutResponseMutableLiveData;
    }

    public LiveData<UserManagementResponse> userInformationUpdate(int user_id, String userToken, String firstName, String lastName, String mobileNumber, String email, String house_number, String street, String barangay, String municipality, String province, String zipcode, String password, String passwordConfirmation){
        if (updateUserInformationMutableLiveData == null) {
            updateUserInformationMutableLiveData = userAPIService.putUserInformationUpdates(user_id, userToken, firstName, lastName, mobileNumber, email, house_number, street, barangay, municipality, province, zipcode, password, passwordConfirmation);
        }
        return updateUserInformationMutableLiveData;
    }

    public LiveData<UserManagementResponse> getUserInformationRequest(String userToken){
        if (userManagementResponseMutableLiveData == null) {
            userManagementResponseMutableLiveData = userAPIService.getUserInformation(userToken);
        }
        return userManagementResponseMutableLiveData;
    }

    public LiveData<ChangePasswordResponse> getChangePasswordRequest(String userToken, String oldPassword, String password, String newPassword){
        if (changePasswordResponseMutableLiveData == null) {
            changePasswordResponseMutableLiveData = userAPIService.userChangePassword(userToken, oldPassword, password, newPassword);
        }
        return changePasswordResponseMutableLiveData;
    }

}
