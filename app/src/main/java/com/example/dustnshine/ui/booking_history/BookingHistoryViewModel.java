package com.example.dustnshine.ui.booking_history;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.example.dustnshine.models.BookingHistoryModel;
import com.example.dustnshine.models.CompanyAndServicesModel;
import com.example.dustnshine.service.BookingAPIService;

import java.util.List;

public class BookingHistoryViewModel extends ViewModel {
    private BookingAPIService bookingAPIService;
    private MutableLiveData<List<BookingHistoryModel>> modelMutableLiveData;
    private MutableLiveData<CompanyAndServicesModel> companyAndServicesModelMutableLiveData;

    public BookingHistoryViewModel() {
        bookingAPIService = new BookingAPIService();
    }

    public LiveData<List<BookingHistoryModel>> getBookingHistory(String userToken){
        if (modelMutableLiveData == null) {
            modelMutableLiveData = bookingAPIService.getBookingHistory(userToken);
        }
        return modelMutableLiveData;
    }

    public LiveData<CompanyAndServicesModel> getSpecificCompany( int companyId,String userToken){
        if (companyAndServicesModelMutableLiveData == null) {
            companyAndServicesModelMutableLiveData = bookingAPIService.getSpecificCompany(companyId ,userToken);
        }
        return companyAndServicesModelMutableLiveData;
    }

}
