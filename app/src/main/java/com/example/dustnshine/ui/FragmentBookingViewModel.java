package com.example.dustnshine.ui;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.example.dustnshine.models.BookingServiceData;
import com.example.dustnshine.repository.BookingAPIRepo;

import java.util.List;

public class FragmentBookingViewModel extends ViewModel {

    private MutableLiveData<List<BookingServiceData>> bookedServiceList;
    private BookingAPIRepo bookingAPIRepo;

    public FragmentBookingViewModel() {
        bookingAPIRepo = new BookingAPIRepo();
    }

    public LiveData<List<BookingServiceData>> getBookedServices(String userToken){
        if (bookedServiceList == null) {
            bookedServiceList = bookingAPIRepo.getBookedServices(userToken);
        }
        return bookedServiceList;
    }

}
