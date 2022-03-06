package com.example.dustnshine.ui.checkout;

import android.util.ArrayMap;
import android.view.View;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.example.dustnshine.repository.BookingAPIRepo;
import com.example.dustnshine.response.BookingServiceResponse;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class CheckOutViewModel extends ViewModel {

    private BookingAPIRepo bookingAPIRepo;
    private MutableLiveData<BookingServiceResponse> bookingServiceResponseMutableLiveData;

    public CheckOutViewModel() {
        bookingAPIRepo = new BookingAPIRepo();
    }

    public LiveData<BookingServiceResponse> getBookingServiceRequest(String userToken, int company_id, String address, String start_datetime, int total, List<Map<Integer, Integer>> services, String notes){
        if (bookingServiceResponseMutableLiveData == null) {
            bookingServiceResponseMutableLiveData = bookingAPIRepo.bookingRequest(userToken, company_id, address, start_datetime, total, services, notes);
        }
        return bookingServiceResponseMutableLiveData;
    }
}
