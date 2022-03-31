package com.example.dustnshine.ui.featured_services;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.example.dustnshine.models.RecommendationModel;
import com.example.dustnshine.service.BookingAPIService;

import java.util.List;

public class FeaturedServicesViewModel extends ViewModel {
    private MutableLiveData<List<RecommendationModel>> companyList;
    private BookingAPIService bookingAPIService;

    public FeaturedServicesViewModel() {
        bookingAPIService = new BookingAPIService();
    }

    public LiveData<List<RecommendationModel>> getFilteredService(int service, String userToken){
        if (companyList == null) {
            companyList = bookingAPIService.getFilteredService(service, userToken);
        }
        return companyList;
    }
}
