package com.example.dustnshine.ui.manage_account;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.example.dustnshine.response.ChangePasswordResponse;
import com.example.dustnshine.response.LogoutResponse;
import com.example.dustnshine.service.UserAPIRepo;
import com.example.dustnshine.response.UserManagementResponse;

public class ManageAccountViewModel extends ViewModel {
    private UserAPIRepo userAPIRepo;
    private MutableLiveData<LogoutResponse> logoutResponseMutableLiveData;
    private MutableLiveData<UserManagementResponse> userManagementResponseMutableLiveData;
    private MutableLiveData<UserManagementResponse> updateUserInformationMutableLiveData;
    private MutableLiveData<ChangePasswordResponse> changePasswordResponseMutableLiveData;

    public ManageAccountViewModel() {
        userAPIRepo = new UserAPIRepo();
    }

    public LiveData<LogoutResponse> getSignOutRequest(String userToken){
        if (logoutResponseMutableLiveData == null) {
            logoutResponseMutableLiveData = userAPIRepo.signOutRequest(userToken);
        }
        return logoutResponseMutableLiveData;
    }

    public LiveData<UserManagementResponse> userInformationUpdate(int user_id, String userToken, String firstName, String lastName, String mobileNumber, String email, String house_number, String street, String barangay, String municipality, String province, String zipcode, String password, String passwordConfirmation){
        if (updateUserInformationMutableLiveData == null) {
            updateUserInformationMutableLiveData = userAPIRepo.putUserInformationUpdates(user_id, userToken, firstName, lastName, mobileNumber, email, house_number, street, barangay, municipality, province, zipcode, password, passwordConfirmation);
        }
        return updateUserInformationMutableLiveData;
    }

    public LiveData<UserManagementResponse> getUserInformationRequest(String userToken){
        if (userManagementResponseMutableLiveData == null) {
            userManagementResponseMutableLiveData = userAPIRepo.getUserInformation(userToken);
        }
        return userManagementResponseMutableLiveData;
    }

    public LiveData<ChangePasswordResponse> getChangePasswordRequest(String userToken, String oldPassword, String password, String newPassword){
        if (changePasswordResponseMutableLiveData == null) {
            changePasswordResponseMutableLiveData = userAPIRepo.userChangePassword(userToken, oldPassword, password, newPassword);
        }
        return changePasswordResponseMutableLiveData;
    }

}
