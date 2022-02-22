package com.example.dustnshine.ui;


import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;
;
import com.example.dustnshine.models.ServicesModel;
import com.example.dustnshine.repository.BookingAPIRepo;
import com.example.dustnshine.response.BookingServiceResponse;

import java.util.List;
import java.util.Map;;

public class CompanyDetailsViewModel extends ViewModel {

    private BookingAPIRepo bookingAPIRepo;
    private MutableLiveData<BookingServiceResponse> bookingServiceResponseMutableLiveData;
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

    public LiveData<BookingServiceResponse> getBookingServiceRequest(String userToken, int company_id, String address, String start_datetime, int total, List<Map<Integer, Integer>> services){
        if (bookingServiceResponseMutableLiveData == null) {
            bookingServiceResponseMutableLiveData = bookingAPIRepo.bookingRequest(userToken, company_id, address, start_datetime, total, services);
        }
        return bookingServiceResponseMutableLiveData;
    }

}
