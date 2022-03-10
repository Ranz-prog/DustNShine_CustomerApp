package com.example.dustnshine.ui.booking_history;

import android.os.Bundle;
import android.view.View;
import android.view.WindowManager;
import android.widget.ImageView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.dustnshine.R;
import com.example.dustnshine.adapter.BookingHistoryAdapter;
import com.example.dustnshine.models.BookingHistoryModel;
import com.example.dustnshine.storage.SharedPrefManager;

import java.util.List;

public class BookingHistoryActivity extends AppCompatActivity implements BookingHistoryAdapter.OnClickMessageListener {

    private ImageView btnBack;
    private RecyclerView rvBookingHistory;
    private List<BookingHistoryModel> bookingHistoryModelList;
    private BookingHistoryAdapter bookingHistoryAdapter;
    private BookingHistoryViewModel bookingHistoryViewModel;
    private String userToken;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        this.getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,WindowManager.LayoutParams.FLAG_FULLSCREEN);

        setContentView(R.layout.activity_booking_history);

        btnBack = findViewById(R.id.backBookingHistory);
        rvBookingHistory = findViewById(R.id.rvBookingHistory);
        userToken = SharedPrefManager.getInstance(BookingHistoryActivity.this).getUserToken();
        rvBookingHistory.setHasFixedSize(true);
        rvBookingHistory.setLayoutManager(new LinearLayoutManager(BookingHistoryActivity.this));
        bookingHistoryAdapter = new BookingHistoryAdapter(bookingHistoryModelList, BookingHistoryActivity.this, this);
        bookingHistoryViewModel = new ViewModelProvider(BookingHistoryActivity.this).get(BookingHistoryViewModel.class);

        getBookingHistory(userToken);

        btnBack.setOnClickListener(new View.OnClickListener() {
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
                if (bookingHistoryModels != null) {
                    bookingHistoryModelList = bookingHistoryModels;
                    bookingHistoryAdapter.setData(bookingHistoryModelList);
                    rvBookingHistory.setAdapter(bookingHistoryAdapter);
                } else {
                    Toast.makeText(BookingHistoryActivity.this, "No Transactions yet", Toast.LENGTH_SHORT).show();
                }
            }
        });
    }

    @Override
    public void onClickMessage(int adapterPosition) {

    }
}
