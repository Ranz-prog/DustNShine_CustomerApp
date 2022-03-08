package com.example.dustnshine.ui;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.example.dustnshine.repository.BookingAPIRepo;
import com.example.dustnshine.response.ReviewResponse;

public class FeedbackActivityViewModel extends ViewModel {
    private BookingAPIRepo bookingAPIRepo;
    private MutableLiveData<ReviewResponse> responseMutableLiveData;

    public FeedbackActivityViewModel() {
        bookingAPIRepo = new BookingAPIRepo();
    }

    public LiveData<ReviewResponse> putReview(String userToken, int booking_id, String comment, int rating){
        if (responseMutableLiveData == null) {
            responseMutableLiveData = bookingAPIRepo.putReview(userToken, booking_id, comment, rating);
        }
        return responseMutableLiveData;
    }
}
