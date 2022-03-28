package com.example.dustnshine.ui.notification;

import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.WindowManager;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.NotificationCompat;
import androidx.core.app.NotificationManagerCompat;
import androidx.databinding.DataBindingUtil;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.dustnshine.MainActivity;
import com.example.dustnshine.R;
import com.example.dustnshine.adapter.NotificationAdapter;
import com.example.dustnshine.databinding.ActivityNotificationBinding;
import com.example.dustnshine.models.NotificationModel;
import com.example.dustnshine.storage.SharedPrefManager;
import com.example.dustnshine.ui.company_details.CompanyDetailsActivity;
import com.example.dustnshine.ui.feedback.FeedbackActivity;

import java.util.List;

public class NotificationActivity extends AppCompatActivity implements NotificationAdapter.OnClickMessageListener {

    private List<NotificationModel> notificationModels;
    private NotificationAdapter notificationAdapter;
    private NotificationActivityViewModel notificationActivityViewModel;
    private String userToken;
    private ActivityNotificationBinding activityNotificationBinding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        this.getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,WindowManager.LayoutParams.FLAG_FULLSCREEN);

        activityNotificationBinding = DataBindingUtil.setContentView(this, R.layout.activity_notification);
        notificationActivityViewModel = new ViewModelProvider(NotificationActivity.this).get(NotificationActivityViewModel.class);
        activityNotificationBinding.rvNotification.setHasFixedSize(true);
        activityNotificationBinding.rvNotification.setLayoutManager(new LinearLayoutManager(NotificationActivity.this));
        notificationAdapter = new NotificationAdapter(notificationModels, NotificationActivity.this, this);
        userToken = SharedPrefManager.getInstance(NotificationActivity.this).getUserToken();

        getDoneServices(userToken);

        if(Build.VERSION.SDK_INT >= Build.VERSION_CODES.O){
            NotificationChannel channel = new NotificationChannel("My Notification", "My Notification", NotificationManager.IMPORTANCE_DEFAULT);
            NotificationManager manager = getSystemService(NotificationManager.class);
            manager.createNotificationChannel(channel);

        }

        activityNotificationBinding.btnBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(NotificationActivity.this, MainActivity.class);
                startActivity(intent);
            }
        });

    }

    private void getDoneServices(String userToken) {
        notificationActivityViewModel.getDoneServices(userToken).observe(NotificationActivity.this, new Observer<List<NotificationModel>>() {
            @Override
            public void onChanged(List<NotificationModel> notificationModelList) {
                if (!notificationModelList.isEmpty()) {
                    notificationModels = notificationModelList;
                    notificationAdapter.setData(notificationModelList);
                    activityNotificationBinding.rvNotification.setAdapter(notificationAdapter);
                    activityNotificationBinding.imgNoTransactions.setVisibility(View.GONE);
                    activityNotificationBinding.tvNoTransactions.setVisibility(View.GONE);
                } else {
                    activityNotificationBinding.rvNotification.setVisibility(View.GONE);
                    activityNotificationBinding.imgNoTransactions.setVisibility(View.VISIBLE);
                    activityNotificationBinding.tvNoTransactions.setVisibility(View.VISIBLE);
                }
            }
        });
    }

    @Override
    public void onBackPressed() {
        Intent intent = new Intent(NotificationActivity.this, MainActivity.class);
        startActivity(intent);
    }

    @Override
    public void onClickMessage(int adapterPosition) {
        Intent intent = new Intent(NotificationActivity.this, FeedbackActivity.class);
        intent.putExtra("BOOKING_ID", notificationModels.get(adapterPosition).getId());
        startActivity(intent);
    }
}
