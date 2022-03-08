package com.example.dustnshine.ui;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.WindowManager;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.dustnshine.R;
import com.example.dustnshine.adapter.BookingHistoryAdapter;
import com.example.dustnshine.adapter.NotificationAdapter;
import com.example.dustnshine.models.BookingHistoryModel;
import com.example.dustnshine.storage.SharedPrefManager;

import java.util.List;

public class NotificationActivity extends AppCompatActivity implements NotificationAdapter.OnClickMessageListener {

    private ImageView returnHome;
    private TextView tvConfirmation;
    private RecyclerView rvNotification;
    private List<BookingHistoryModel> bookingHistoryModelList;
    private NotificationAdapter notificationAdapter;
    private NotificationActivityViewModel notificationActivityViewModel;
    private String userToken;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        this.getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,WindowManager.LayoutParams.FLAG_FULLSCREEN);

        setContentView(R.layout.activity_notification);

//        rvNotification = findViewById(R.id.rvNotification);
//        rvNotification.setHasFixedSize(true);
//        rvNotification.setLayoutManager(new LinearLayoutManager(NotificationActivity.this));
//        notificationAdapter = new NotificationAdapter(bookingHistoryModelList, NotificationActivity.this, this);
//        notificationActivityViewModel = new ViewModelProvider(NotificationActivity.this).get(NotificationActivityViewModel.class);
//        userToken = SharedPrefManager.getInstance(NotificationActivity.this).getUserToken();

        returnHome = findViewById(R.id.backNotification);
        tvConfirmation = findViewById(R.id.tvConfirmation);

//        getBookingHistory(userToken);
        returnHome.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });

        tvConfirmation.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(NotificationActivity.this, FeedbackActivity.class);
                startActivity(intent);
            }
        });

    }

    private void getBookingHistory(String userToken) {
        notificationActivityViewModel.getBookingHistory(userToken).observe(NotificationActivity.this, new Observer<List<BookingHistoryModel>>() {
            @Override
            public void onChanged(List<BookingHistoryModel> bookingHistoryModels) {
                if (bookingHistoryModels != null) {
                    bookingHistoryModelList = bookingHistoryModels;
                    notificationAdapter.setData(bookingHistoryModelList);
                    rvNotification.setAdapter(notificationAdapter);
                } else {
                    Toast.makeText(NotificationActivity.this, "No Transactions yet", Toast.LENGTH_SHORT).show();
                }
            }
        });
    }

    @Override
    public void onClickMessage(int adapterPosition) {

    }
}
