package com.example.dustnshine.ui.booking;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.databinding.DataBindingUtil;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout;

import com.example.dustnshine.R;
import com.example.dustnshine.adapter.BookingAdapter;
import com.example.dustnshine.databinding.FragmentBookingBinding;
import com.example.dustnshine.models.BookingServiceData;
import com.example.dustnshine.storage.SharedPrefManager;
import com.example.dustnshine.ui.booking_history.BookingHistoryActivity;
import com.example.dustnshine.ui.checkout.CheckOutActivity;

import java.util.List;


public class BookingFragment extends Fragment implements BookingAdapter.OnClickMessageListener, SwipeRefreshLayout.OnRefreshListener {
    private RecyclerView rvBooking;
    private View view;
    private List<BookingServiceData> bookingServiceDataList;
    private BookingFragmentViewModel bookingFragmentViewModel;
    private BookingAdapter bookingAdapter;
    private String userToken;
    private FragmentBookingBinding fragmentBookingBinding;

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

        fragmentBookingBinding = DataBindingUtil.inflate(inflater, R.layout.fragment_booking, container, false);
        bookingFragmentViewModel = new ViewModelProvider(BookingFragment.this).get(BookingFragmentViewModel.class);
        view = fragmentBookingBinding.getRoot();
        userToken = SharedPrefManager.getInstance(getContext()).getUserToken();
        fragmentBookingBinding.rvBooking.setHasFixedSize(true);
        fragmentBookingBinding.rvBooking.setLayoutManager(new LinearLayoutManager(getContext()));
        bookingAdapter = new BookingAdapter(bookingServiceDataList, getContext(), this);
        fragmentBookingBinding.refreshLayout.setOnRefreshListener(this);
        getBookedService(userToken);

        fragmentBookingBinding.btnBookingHistory.setOnClickListener(new View.OnClickListener() {
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
                    fragmentBookingBinding.rvBooking.setAdapter(bookingAdapter);
                    fragmentBookingBinding.imgNoTransactions.setVisibility(View.GONE);
                    fragmentBookingBinding.tvNoTransactions.setVisibility(View.GONE);
                } else {
                    fragmentBookingBinding.rvBooking.setVisibility(View.GONE);
                    fragmentBookingBinding.imgNoTransactions.setVisibility(View.VISIBLE);
                    fragmentBookingBinding.tvNoTransactions.setVisibility(View.VISIBLE);
                }
            }
        });
    }

    @Override
    public void onClickMessage(int adapterPosition) {

    }

    @Override
    public void onRefresh() {
        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                getBookedService(userToken);
            }
        }, 2000);
        fragmentBookingBinding.refreshLayout.setRefreshing(false);
    }
}