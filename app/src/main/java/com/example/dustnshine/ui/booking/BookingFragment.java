package com.example.dustnshine.ui.booking;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.dustnshine.R;
import com.example.dustnshine.adapter.BookingAdapter;
import com.example.dustnshine.models.BookingServiceData;
import com.example.dustnshine.storage.SharedPrefManager;
import com.example.dustnshine.ui.booking_history.BookingHistoryActivity;
import com.example.dustnshine.ui.checkout.CheckOutActivity;

import java.util.List;


public class BookingFragment extends Fragment implements BookingAdapter.OnClickMessageListener {
    private RecyclerView rvBooking;
    private View view;
    private List<BookingServiceData> bookingServiceDataList;
    private LinearLayout btnBookingHistory;
    private BookingFragmentViewModel bookingFragmentViewModel;
    private BookingAdapter bookingAdapter;
    private String userToken;
    private TextView tvNoTransactions;
    private ImageView imgNoTransactions;

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

        view = inflater.inflate(R.layout.fragment_booking, container, false);
        btnBookingHistory = view.findViewById(R.id.btnBookingHistory);
        userToken = SharedPrefManager.getInstance(getContext()).getUserToken();
        rvBooking = view.findViewById(R.id.rvBooking);
        tvNoTransactions = view.findViewById(R.id.tvNoTransactions);
        imgNoTransactions = view.findViewById(R.id.imgNoTransactions);
        rvBooking.setHasFixedSize(true);
        rvBooking.setLayoutManager(new LinearLayoutManager(getContext()));
        bookingAdapter = new BookingAdapter(bookingServiceDataList, getContext(), this);
        bookingFragmentViewModel = new ViewModelProvider(BookingFragment.this).get(BookingFragmentViewModel.class);

        getBookedService(userToken);

        btnBookingHistory.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getContext(), BookingHistoryActivity.class);
                startActivity(intent);
            }
        });
        return view;
    }

    private void getBookedService(String userToken) {
        bookingFragmentViewModel.getBookedServices(userToken).observe(getActivity(), new Observer<List<BookingServiceData>>() {
            @Override
            public void onChanged(List<BookingServiceData> bookingServiceData) {
                if (!bookingServiceData.isEmpty()) {
                    bookingServiceDataList = bookingServiceData;
                    bookingAdapter.setData(bookingServiceData);
                    rvBooking.setAdapter(bookingAdapter);
                    imgNoTransactions.setVisibility(View.GONE);
                    tvNoTransactions.setVisibility(View.GONE);
                } else {
                    rvBooking.setVisibility(View.GONE);
                    imgNoTransactions.setVisibility(View.VISIBLE);
                    tvNoTransactions.setVisibility(View.VISIBLE);
                }
            }
        });
    }

    @Override
    public void onClickMessage(int adapterPosition) {
        Intent intent = new Intent(getActivity(), CheckOutActivity.class);
        startActivity(intent);
    }

//    public void loadData(){
//        if (!bookingServiceDataList.isEmpty()){
//            rvBooking.setVisibility(view.VISIBLE);
//            tvNoTransactions.setVisibility(view.GONE);
//            imgNoTransactions.setVisibility(view.GONE);
//        } else {
//            rvBooking.setVisibility(view.GONE);
//            tvNoTransactions.setVisibility(view.VISIBLE);
//            imgNoTransactions.setVisibility(view.VISIBLE);
//        }
//    }
}