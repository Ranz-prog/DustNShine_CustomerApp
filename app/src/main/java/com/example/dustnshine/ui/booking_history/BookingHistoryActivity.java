package com.example.dustnshine.ui.booking_history;

import android.os.Bundle;
import android.view.View;
import android.view.WindowManager;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.databinding.DataBindingUtil;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.dustnshine.R;
import com.example.dustnshine.adapter.BookingHistoryAdapter;
import com.example.dustnshine.databinding.ActivityBookingHistoryBinding;
import com.example.dustnshine.models.BookingHistoryModel;
import com.example.dustnshine.storage.SharedPrefManager;

import java.util.List;

public class BookingHistoryActivity extends AppCompatActivity implements BookingHistoryAdapter.OnClickMessageListener {

    private RecyclerView rvBookingHistory;
    private List<BookingHistoryModel> bookingHistoryModelList;
    private BookingHistoryAdapter bookingHistoryAdapter;
    private BookingHistoryViewModel bookingHistoryViewModel;
    private String userToken;
    private ActivityBookingHistoryBinding activityBookingHistoryBinding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        this.getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,WindowManager.LayoutParams.FLAG_FULLSCREEN);

        activityBookingHistoryBinding = DataBindingUtil.setContentView(this, R.layout.activity_booking_history);
        bookingHistoryViewModel = new ViewModelProvider(BookingHistoryActivity.this).get(BookingHistoryViewModel.class);
        rvBookingHistory = findViewById(R.id.rvBookingHistory);
        userToken = SharedPrefManager.getInstance(BookingHistoryActivity.this).getUserToken();
        rvBookingHistory.setHasFixedSize(true);
        rvBookingHistory.setLayoutManager(new LinearLayoutManager(BookingHistoryActivity.this));
        bookingHistoryAdapter = new BookingHistoryAdapter(bookingHistoryModelList, BookingHistoryActivity.this, this);

        getBookingHistory(userToken);

        activityBookingHistoryBinding.btnBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
    }

    private void getBookingHistory(String userToken) {
        bookingHistoryViewModel.getBookingHistory(userToken).observe(BookingHistoryActivity.this, new Observer<List<BookingHistoryModel>>() {
            @Override
            public void onChanged(List<BookingHistoryModel> bookingHistoryModels) {
                if (!bookingHistoryModels.isEmpty()) {
                    bookingHistoryModelList = bookingHistoryModels;
                    bookingHistoryAdapter.setData(bookingHistoryModelList);
                    activityBookingHistoryBinding.rvBookingHistory.setAdapter(bookingHistoryAdapter);
                    activityBookingHistoryBinding.imgNoTransactions.setVisibility(View.GONE);
                    activityBookingHistoryBinding.tvNoTransactions.setVisibility(View.GONE);
                } else {
                    activityBookingHistoryBinding.rvBookingHistory.setVisibility(View.GONE);
                    activityBookingHistoryBinding.imgNoTransactions.setVisibility(View.VISIBLE);
                    activityBookingHistoryBinding.tvNoTransactions.setVisibility(View.VISIBLE);
                }
            }
        });
    }

    @Override
    public void onClickMessage(int adapterPosition) {

    }

}
