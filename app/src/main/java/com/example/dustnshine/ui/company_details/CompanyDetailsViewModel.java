package com.example.dustnshine.ui.company_details;


import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;
;
import com.example.dustnshine.models.ServicesModel;
import com.example.dustnshine.service.BookingAPIRepo;

import java.util.List;
;

public class CompanyDetailsViewModel extends ViewModel {

    private BookingAPIRepo bookingAPIRepo;
    private MutableLiveData<List<ServicesModel>> serviceList;

    public CompanyDetailsViewModel() {
        bookingAPIRepo = new BookingAPIRepo();
    }

    public LiveData<List<ServicesModel>> getServicesList(String userToken){
        if (serviceList == null) {
            serviceList = bookingAPIRepo.getServices(userToken);
        }
        return serviceList;
    }

}
