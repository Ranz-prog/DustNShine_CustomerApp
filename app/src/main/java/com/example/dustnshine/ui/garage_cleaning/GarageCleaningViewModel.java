package com.example.dustnshine.ui.garage_cleaning;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.example.dustnshine.models.RecommendationModel;
import com.example.dustnshine.service.BookingAPIRepo;

import java.util.List;

public class GarageCleaningViewModel extends ViewModel {
    private MutableLiveData<List<RecommendationModel>> companyList;
    private BookingAPIRepo bookingAPIRepo;

    public GarageCleaningViewModel() {
        bookingAPIRepo = new BookingAPIRepo();
    }

    public LiveData<List<RecommendationModel>> getFilteredService(int service, String userToken){
        if (companyList == null) {
            companyList = bookingAPIRepo.getFilteredService(service, userToken);
        }
        return companyList;
    }
}
