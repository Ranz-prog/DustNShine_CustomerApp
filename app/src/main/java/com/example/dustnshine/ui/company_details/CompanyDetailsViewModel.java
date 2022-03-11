package com.example.dustnshine.ui.company_details;


import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;
;
import com.example.dustnshine.models.ServicesModel;
import com.example.dustnshine.service.BookingAPIService;

import java.util.List;
;

public class CompanyDetailsViewModel extends ViewModel {

    private BookingAPIService bookingAPIService;
    private MutableLiveData<List<ServicesModel>> serviceList;

    public CompanyDetailsViewModel() {
        bookingAPIService = new BookingAPIService();
    }

    public LiveData<List<ServicesModel>> getServicesList(String userToken){
        if (serviceList == null) {
            serviceList = bookingAPIService.getServices(userToken);
        }
        return serviceList;
    }

}
