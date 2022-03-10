package com.example.dustnshine.ui.notification;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
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
import com.example.dustnshine.adapter.NotificationAdapter;
import com.example.dustnshine.models.BookingHistoryModel;
import com.example.dustnshine.models.NotificationModel;
import com.example.dustnshine.storage.SharedPrefManager;
import com.example.dustnshine.ui.feedback.FeedbackActivity;

import java.util.List;

public class NotificationActivity extends AppCompatActivity implements NotificationAdapter.OnClickMessageListener {

    private ImageView returnHome;
    private RecyclerView rvNotification;
    private List<NotificationModel> notificationModels;
    private NotificationAdapter notificationAdapter;
    private NotificationActivityViewModel notificationActivityViewModel;
    private String userToken;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        this.getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,WindowManager.LayoutParams.FLAG_FULLSCREEN);

        setContentView(R.layout.activity_notification);

        rvNotification = findViewById(R.id.rvNotification);
        rvNotification.setHasFixedSize(true);
        rvNotification.setLayoutManager(new LinearLayoutManager(NotificationActivity.this));
        notificationAdapter = new NotificationAdapter(notificationModels, NotificationActivity.this, this);
        notificationActivityViewModel = new ViewModelProvider(NotificationActivity.this).get(NotificationActivityViewModel.class);
        userToken = SharedPrefManager.getInstance(NotificationActivity.this).getUserToken();
        returnHome = findViewById(R.id.backNotification);

        getDoneServices(userToken);

        returnHome.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });

    }

    private void getDoneServices(String userToken) {
        notificationActivityViewModel.getDoneServices(userToken).observe(NotificationActivity.this, new Observer<List<NotificationModel>>() {
            @Override
            public void onChanged(List<NotificationModel> notificationModelList) {
                if (notificationModelList != null) {
                    notificationModels = notificationModelList;
                    notificationAdapter.setData(notificationModelList);
                    rvNotification.setAdapter(notificationAdapter);
                } else {
                    Toast.makeText(NotificationActivity.this, "No Transactions yet", Toast.LENGTH_SHORT).show();
                }
            }
        });
    }

    @Override
    public void onClickMessage(int adapterPosition) {
        Intent intent = new Intent(NotificationActivity.this, FeedbackActivity.class);
        intent.putExtra("BOOKING_ID", notificationModels.get(adapterPosition).getId());
        Log.d("BOOKING ID", String.valueOf(notificationModels.get(adapterPosition).getId()));
        startActivity(intent);
    }
}
