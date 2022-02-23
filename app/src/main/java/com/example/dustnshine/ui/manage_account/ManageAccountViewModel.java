package com.example.dustnshine.ui.manage_account;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.example.dustnshine.response.LogoutResponse;
import com.example.dustnshine.repository.UserAPIRepo;

public class ManageAccountViewModel extends ViewModel {
    private UserAPIRepo userAPIRepo;
    private MutableLiveData<LogoutResponse> logoutResponseMutableLiveData;

    public ManageAccountViewModel() {
        userAPIRepo = new UserAPIRepo();
    }

    public LiveData<LogoutResponse> getSignOutRequest(String userToken){
        if (logoutResponseMutableLiveData == null) {
            logoutResponseMutableLiveData = userAPIRepo.signOutRequest(userToken);
        }
        return logoutResponseMutableLiveData;
    }
}
