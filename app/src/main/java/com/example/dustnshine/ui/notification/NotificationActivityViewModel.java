package com.example.dustnshine.ui.notification;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.example.dustnshine.models.NotificationModel;
import com.example.dustnshine.service.BookingAPIService;

import java.util.List;

public class NotificationActivityViewModel extends ViewModel {
    private BookingAPIService bookingAPIService;
    private MutableLiveData<List<NotificationModel>> modelMutableLiveData;

    public NotificationActivityViewModel() {
        bookingAPIService = new BookingAPIService();
    }

    public LiveData<List<NotificationModel>> getDoneServices(String userToken){
        if (modelMutableLiveData == null) {
            modelMutableLiveData = bookingAPIService.getDoneServices(userToken);
        }
        return modelMutableLiveData;
    }
}
