package com.example.dustnshine.ui.notification;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.example.dustnshine.models.NotificationModel;
import com.example.dustnshine.service.BookingAPIRepo;

import java.util.List;

public class NotificationActivityViewModel extends ViewModel {
    private BookingAPIRepo bookingAPIRepo;
    private MutableLiveData<List<NotificationModel>> modelMutableLiveData;

    public NotificationActivityViewModel() {
        bookingAPIRepo = new BookingAPIRepo();
    }

    public LiveData<List<NotificationModel>> getDoneServices(String userToken){
        if (modelMutableLiveData == null) {
            modelMutableLiveData = bookingAPIRepo.getDoneServices(userToken);
        }
        return modelMutableLiveData;
    }
}
