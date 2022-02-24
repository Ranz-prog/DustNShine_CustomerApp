package com.example.dustnshine.ui.booking;

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

import com.example.dustnshine.R;
import com.example.dustnshine.adapter.BookingAdapter;
import com.example.dustnshine.models.BookingServiceData;
import com.example.dustnshine.storage.SharedPrefManager;
import com.example.dustnshine.ui.BookingHistoryActivity;
import com.example.dustnshine.ui.checkout.CheckOutActivity;

import java.util.List;


public class BookingFragment extends Fragment implements BookingAdapter.OnClickMessageListener {
    private RecyclerView bookingRecycler;
    private View view;
    private List<BookingServiceData> bookingServiceDataList;
    LinearLayout historyBtn;
    private BookingFragmentViewModel bookingFragmentViewModel;
    private BookingAdapter bookingAdapter;
    private String userToken;

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

        view = inflater.inflate(R.layout.fragment_booking, container, false);
        historyBtn = view.findViewById(R.id.bookingHistoryBtn);
        userToken = SharedPrefManager.getInstance(getContext()).getUserToken();
        bookingRecycler = view.findViewById(R.id.bookingList);
        bookingRecycler.setHasFixedSize(true);
        bookingRecycler.setLayoutManager(new LinearLayoutManager(getContext()));
        bookingAdapter = new BookingAdapter(bookingServiceDataList, getContext(), this);
        bookingFragmentViewModel = new ViewModelProvider(BookingFragment.this).get(BookingFragmentViewModel.class);

        getBookedService(userToken);

        historyBtn.setOnClickListener(new View.OnClickListener() {
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
                if (bookingServiceData != null) {
                    bookingServiceDataList = bookingServiceData;
                    bookingAdapter.setData(bookingServiceData);
                    bookingRecycler.setAdapter(bookingAdapter);
                } else {
                    Toast.makeText(getActivity(), "No Booked Service yet", Toast.LENGTH_SHORT).show();
                }
            }
        });
    }

    @Override
    public void onClickMessage(int adapterPosition) {
        Intent intent = new Intent(getActivity(), CheckOutActivity.class);
        startActivity(intent);
    }
}