package com.example.dustnshine.ui.home;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.example.dustnshine.models.RecommendationModel;
import com.example.dustnshine.service.BookingAPIRepo;
import com.example.dustnshine.service.UserAPIRepo;
import com.example.dustnshine.response.UserManagementResponse;

import java.util.List;

public class HomeFragmentViewModel extends ViewModel {

    private MutableLiveData<List<RecommendationModel>> companyList;
    private MutableLiveData<UserManagementResponse> userManagementResponseMutableLiveData;
    private BookingAPIRepo bookingAPIRepo;
    private UserAPIRepo userAPIRepo;

    public HomeFragmentViewModel() {
        bookingAPIRepo = new BookingAPIRepo();
        userAPIRepo = new UserAPIRepo();
    }

    public LiveData<List<RecommendationModel>> getCompaniesList(String userToken) {
        if (companyList == null) {
            companyList = bookingAPIRepo.getCompanies(userToken);
        }
        return companyList;
    }

    public LiveData<UserManagementResponse> getUserInformationRequest(String userToken){
        if (userManagementResponseMutableLiveData == null) {
            userManagementResponseMutableLiveData = userAPIRepo.getUserInformation(userToken);
        }
        return userManagementResponseMutableLiveData;
    }

}
