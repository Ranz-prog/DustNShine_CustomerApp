package com.example.dustnshine.ui.home;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.example.dustnshine.models.RecommendationModel;
import com.example.dustnshine.models.RecommendedCompaniesModel;
import com.example.dustnshine.service.BookingAPIService;
import com.example.dustnshine.service.UserAPIService;
import com.example.dustnshine.response.UserManagementResponse;

import java.util.List;

public class HomeFragmentViewModel extends ViewModel {

    private MutableLiveData<List<RecommendedCompaniesModel>> recommendedCompanyList;
    private MutableLiveData<UserManagementResponse> userManagementResponseMutableLiveData;
    private BookingAPIService bookingAPIService;
    private UserAPIService userAPIService;

    public HomeFragmentViewModel() {
        bookingAPIService = new BookingAPIService();
        userAPIService = new UserAPIService();
    }

    public LiveData<List<RecommendedCompaniesModel>> getRecommendedCompaniesList(String userToken) {
        if (recommendedCompanyList == null) {
            recommendedCompanyList = bookingAPIService.getRecommendedCompanies(userToken);
        }
        return recommendedCompanyList;
    }

    public LiveData<UserManagementResponse> getUserInformationRequest(String userToken){
        if (userManagementResponseMutableLiveData == null) {
            userManagementResponseMutableLiveData = userAPIService.getUserInformation(userToken);
        }
        return userManagementResponseMutableLiveData;
    }

}
