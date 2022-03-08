package com.example.dustnshine.ui.checkout;

import android.util.ArrayMap;
import android.view.View;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.example.dustnshine.repository.BookingAPIRepo;
import com.example.dustnshine.repository.UserAPIRepo;
import com.example.dustnshine.response.BookingServiceResponse;
import com.example.dustnshine.response.UserManagementResponse;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class CheckOutViewModel extends ViewModel {

    private BookingAPIRepo bookingAPIRepo;
    private UserAPIRepo userAPIRepo;
    private MutableLiveData<BookingServiceResponse> bookingServiceResponseMutableLiveData;
    private MutableLiveData<UserManagementResponse> userManagementResponseMutableLiveData;

    public CheckOutViewModel() {
        bookingAPIRepo = new BookingAPIRepo();
        userAPIRepo = new UserAPIRepo();
    }

    public LiveData<BookingServiceResponse> getBookingServiceRequest(String userToken, int company_id, String address, String start_datetime, int total, List<Map<Integer, Integer>> services, String notes){
        if (bookingServiceResponseMutableLiveData == null) {
            bookingServiceResponseMutableLiveData = bookingAPIRepo.bookingRequest(userToken, company_id, address, start_datetime, total, services, notes);
        }
        return bookingServiceResponseMutableLiveData;
    }

    public LiveData<UserManagementResponse> getUserInformationRequest(String userToken){
        if (userManagementResponseMutableLiveData == null) {
            userManagementResponseMutableLiveData = userAPIRepo.getUserInformation(userToken);
        }
        return userManagementResponseMutableLiveData;
    }

}
