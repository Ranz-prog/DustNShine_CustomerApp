package com.example.dustnshine.ui.home;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.example.dustnshine.models.RecommendationModel;
import com.example.dustnshine.repository.BookingAPIRepo;

import java.util.List;

public class HomeFragmentViewModel extends ViewModel {

    private MutableLiveData<List<RecommendationModel>> companyList;
    private BookingAPIRepo bookingAPIRepo;

    public HomeFragmentViewModel() {
        bookingAPIRepo = new BookingAPIRepo();
    }

    public LiveData<List<RecommendationModel>> getCompaniesList(String userToken) {
        if (companyList == null) {
            companyList = bookingAPIRepo.getCompanies(userToken);
        }
        return companyList;
    }
}
