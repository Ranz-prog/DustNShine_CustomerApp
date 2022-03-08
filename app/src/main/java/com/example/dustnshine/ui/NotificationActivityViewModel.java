package com.example.dustnshine.ui;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.example.dustnshine.models.BookingHistoryModel;
import com.example.dustnshine.repository.BookingAPIRepo;

import java.util.List;

public class NotificationActivityViewModel extends ViewModel {
    private BookingAPIRepo bookingAPIRepo;
    private MutableLiveData<List<BookingHistoryModel>> modelMutableLiveData;

    public NotificationActivityViewModel() {
        bookingAPIRepo = new BookingAPIRepo();
    }

    public LiveData<List<BookingHistoryModel>> getBookingHistory(String userToken){
        if (modelMutableLiveData == null) {
            modelMutableLiveData = bookingAPIRepo.getBookingHistory(userToken);
        }
        return modelMutableLiveData;
    }
}
