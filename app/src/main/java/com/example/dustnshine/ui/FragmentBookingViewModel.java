package com.example.dustnshine.ui;

import android.util.Log;

import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.example.dustnshine.api.RetrofitClient;
import com.example.dustnshine.models.BookingModel;
import com.example.dustnshine.models.BookingServiceData;
import com.example.dustnshine.response.BookedServiceResponse;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class FragmentBookingViewModel extends ViewModel {

    private MutableLiveData<List<BookingServiceData>> bookedServiceList;

    public FragmentBookingViewModel() {
        bookedServiceList = new MutableLiveData<>();
    }

    public MutableLiveData<List<BookingServiceData>> getBookedServiceList(){
        return bookedServiceList;
    }

    public void makeAPICall(String userToken){
        Call<BookedServiceResponse> bookedServiceResponseCall = RetrofitClient.getInstance().getApi().getBookedService("Bearer " + userToken);
        bookedServiceResponseCall.enqueue(new Callback<BookedServiceResponse>() {
            @Override
            public void onResponse(Call<BookedServiceResponse> call, Response<BookedServiceResponse> response) {
                bookedServiceList.postValue(response.body().getData());
                Log.d("TAG", response.body().getMessage());
            }

            @Override
            public void onFailure(Call<BookedServiceResponse> call, Throwable t) {
                Log.d("TAG", "ERROR");
            }
        });
    }

}
