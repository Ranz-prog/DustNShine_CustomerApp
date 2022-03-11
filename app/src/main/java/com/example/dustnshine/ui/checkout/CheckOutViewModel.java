package com.example.dustnshine.ui.checkout;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.example.dustnshine.service.BookingAPIService;
import com.example.dustnshine.service.UserAPIService;
import com.example.dustnshine.response.BookingServiceResponse;
import com.example.dustnshine.response.UserManagementResponse;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class CheckOutViewModel extends ViewModel {

    private BookingAPIService bookingAPIService;
    private UserAPIService userAPIService;
    private MutableLiveData<BookingServiceResponse> bookingServiceResponseMutableLiveData;
    private MutableLiveData<UserManagementResponse> userManagementResponseMutableLiveData;

    public CheckOutViewModel() {
        bookingAPIService = new BookingAPIService();
        userAPIService = new UserAPIService();
    }

    public LiveData<BookingServiceResponse> getBookingServiceRequest(String userToken, int company_id, String address, String start_datetime, int total, ArrayList<Integer> services, String notes){
        if (bookingServiceResponseMutableLiveData == null) {
            bookingServiceResponseMutableLiveData = bookingAPIService.bookingRequest(userToken, company_id, address, start_datetime, total, services, notes);
        }
        return bookingServiceResponseMutableLiveData;
    }

    public LiveData<UserManagementResponse> getUserInformationRequest(String userToken){
        if (userManagementResponseMutableLiveData == null) {
            userManagementResponseMutableLiveData = userAPIService.getUserInformation(userToken);
        }
        return userManagementResponseMutableLiveData;
    }

}
