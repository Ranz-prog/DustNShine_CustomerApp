package com.example.dustnshine.ui;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.dustnshine.api.RetrofitClient;
import com.example.dustnshine.models.BookingModel;
import com.example.dustnshine.R;
import com.example.dustnshine.adapter.BookingAdapter;
import com.example.dustnshine.models.BookingServiceData;
import com.example.dustnshine.models.RecommendationModel;
import com.example.dustnshine.response.BookedServiceResponse;
import com.example.dustnshine.storage.SharedPrefManager;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class FragmentBooking extends Fragment implements BookingAdapter.OnClickMessageListener{
    private RecyclerView bookingRecycler;
    private View view;
    private List<BookingServiceData> bookingServiceDataList;
    LinearLayout historyBtn;
    private FragmentBookingViewModel fragmentBookingViewModel;
    private BookingAdapter bookingAdapter;
    private String userToken;

    public FragmentBooking(){
    }

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {


        view = inflater.inflate(R.layout.fragment_booking,container,false);

        historyBtn = view.findViewById(R.id.bookingHistoryBtn);

        userToken = SharedPrefManager.getInstance(getContext()).getUserToken();
        bookingRecycler = view.findViewById(R.id.bookingList);
        bookingRecycler.setHasFixedSize(true);
        bookingRecycler.setLayoutManager(new LinearLayoutManager(getContext()));
        bookingAdapter = new BookingAdapter(bookingServiceDataList, getContext(),this);


        fragmentBookingViewModel = new ViewModelProvider(FragmentBooking.this).get(FragmentBookingViewModel.class);
        fragmentBookingViewModel.getBookedServiceList().observe(getActivity(), new Observer<List<BookingServiceData>>() {
            @Override
            public void onChanged(List<BookingServiceData> bookingServiceData) {
                if(bookingServiceData != null){
                    bookingServiceDataList = bookingServiceData;
                    bookingAdapter.setData(bookingServiceData);
                    bookingRecycler.setAdapter(bookingAdapter);
                }
            }
        });

        fragmentBookingViewModel.makeAPICall(userToken);


        historyBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getContext(), ActivityBookingHistory.class);
                startActivity(intent);

                
            }
        });

        return view;

    }

//    private void getBookedService(){
//        String userToken = SharedPrefManager.getInstance(getContext()).getUserToken();
//        Call<BookedServiceResponse> bookedServiceResponseCall = RetrofitClient.getInstance().getApi().getBookedService("Bearer " + userToken);
//        bookedServiceResponseCall.enqueue(new Callback<BookedServiceResponse>() {
//            @Override
//            public void onResponse(Call<BookedServiceResponse> call, Response<BookedServiceResponse> response) {
//                if(response.isSuccessful()){
//                    List<BookingServiceData> bookingServiceData = response.body().getData();
//                    bookingAdapter.setData(bookingServiceData);
//                    bookingRecycler.setAdapter(bookingAdapter);
//                    Toast.makeText(getContext(), "Success", Toast.LENGTH_LONG).show();
//                }
//                else{
//                    Toast.makeText(getContext(), "Failed", Toast.LENGTH_SHORT).show();
//                }
//            }
//
//            @Override
//            public void onFailure(Call<BookedServiceResponse> call, Throwable t) {
//
//            }
//        });
//    }

    @Override
    public void onClickMessage(int adapterPosition) {
        Intent intent = new Intent(getActivity(), ActivityCheckOut.class);
        startActivity(intent);
    }
}