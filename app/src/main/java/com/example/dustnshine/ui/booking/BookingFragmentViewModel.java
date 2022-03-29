package com.example.dustnshine.ui.booking;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.example.dustnshine.models.BookingServiceData;
import com.example.dustnshine.models.CompanyAndServicesModel;
import com.example.dustnshine.service.BookingAPIService;

import java.util.List;

public class BookingFragmentViewModel extends ViewModel {

    private MutableLiveData<List<BookingServiceData>> bookedServiceList;
    private MutableLiveData<CompanyAndServicesModel> companyAndServicesModelMutableLiveData;
    private BookingAPIService bookingAPIService;

    public BookingFragmentViewModel() {
        bookingAPIService = new BookingAPIService();
    }

    public LiveData<List<BookingServiceData>> getBookedServices(String userToken){
        if (bookedServiceList == null) {
            bookedServiceList = bookingAPIService.getBookedServices(userToken);
        }
        return bookedServiceList;
    }

    public LiveData<CompanyAndServicesModel> getSpecificCompany( int companyId,String userToken){
        if (companyAndServicesModelMutableLiveData == null) {
            companyAndServicesModelMutableLiveData = bookingAPIService.getSpecificCompany(companyId ,userToken);
        }
        return companyAndServicesModelMutableLiveData;
    }

}
