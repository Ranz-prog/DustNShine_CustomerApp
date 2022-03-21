package com.example.dustnshine.ui.recommendations;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.example.dustnshine.models.RecommendationModel;
import com.example.dustnshine.models.RecommendedCompaniesModel;
import com.example.dustnshine.service.BookingAPIService;

import java.util.List;

public class SeeAllRecommendationsViewModel extends ViewModel {

    private MutableLiveData<List<RecommendationModel>> searchedCompanyList;
    private MutableLiveData<List<RecommendationModel>> recommendedCompanyList;
    private BookingAPIService bookingAPIService;

    public SeeAllRecommendationsViewModel() {
        bookingAPIService = new BookingAPIService();
    }

    public LiveData<List<RecommendationModel>> getSearchedCompany(String companyName,String userToken){
        if (searchedCompanyList == null) {
            searchedCompanyList = bookingAPIService.getSearchedCompany(companyName, userToken);
        }
        return searchedCompanyList;
    }

//    public LiveData<List<RecommendedCompaniesModel>> getRecommendedCompaniesList(String userToken){
//        if (recommendedCompanyList == null) {
//            recommendedCompanyList = bookingAPIService.getRecommendedCompanies(userToken);
//        }
//        return recommendedCompanyList;
//    }

    public LiveData<List<RecommendationModel>> getRecommendedCompaniesList(String userToken){
        if (recommendedCompanyList == null) {
            recommendedCompanyList = bookingAPIService.getCompanies(userToken);
        }
        return recommendedCompanyList;
    }
}
