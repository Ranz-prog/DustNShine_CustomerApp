package com.example.dustnshine.ui.recommendations;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.example.dustnshine.models.RecommendationModel;
import com.example.dustnshine.repository.BookingAPIRepo;

import java.util.List;

public class SeeAllRecommendationsViewModel extends ViewModel {

    private MutableLiveData<List<RecommendationModel>> searchedCompanyList;
    private BookingAPIRepo bookingAPIRepo;

    public SeeAllRecommendationsViewModel() {
        bookingAPIRepo = new BookingAPIRepo();
    }

    public LiveData<List<RecommendationModel>> getSearchedCompany(String companyName,String userToken){
        if (searchedCompanyList == null) {
            searchedCompanyList = bookingAPIRepo.getSearchedCompany(companyName, userToken);
        }
        return searchedCompanyList;
    }
}
