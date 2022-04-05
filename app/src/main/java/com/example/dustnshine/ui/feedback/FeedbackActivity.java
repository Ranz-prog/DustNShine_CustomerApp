package com.example.dustnshine.ui.feedback;

import androidx.appcompat.app.AppCompatActivity;
import androidx.databinding.DataBindingUtil;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;

import com.example.dustnshine.MainActivity;
import com.example.dustnshine.R;
import com.example.dustnshine.databinding.ActivityFeedbackBinding;
import com.example.dustnshine.response.ReviewResponse;
import com.example.dustnshine.storage.SharedPrefManager;
import com.example.dustnshine.ui.company_details.CompanyDetailsActivity;
import com.example.dustnshine.ui.notification.NotificationActivity;
import com.example.dustnshine.utils.AppConstants;

public class FeedbackActivity extends AppCompatActivity {

    private FeedbackActivityViewModel feedbackActivityViewModel;
    private static String userToken;
    private double rating;
    private ImageView btnBack;
    private Intent intent;
    private static int bookingID;
    private ActivityFeedbackBinding activityFeedbackBinding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        activityFeedbackBinding = DataBindingUtil.setContentView(this, R.layout.activity_feedback);
        feedbackActivityViewModel = new ViewModelProvider(FeedbackActivity.this).get(FeedbackActivityViewModel.class);
        userToken = SharedPrefManager.getInstance(FeedbackActivity.this).getUserToken();
        intent = getIntent();

        bookingID = intent.getIntExtra("BOOKING_ID", 0);

        activityFeedbackBinding.btnBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getApplicationContext(), MainActivity.class);
                startActivity(intent);
            }
        });

        activityFeedbackBinding.btnReview.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                rating = activityFeedbackBinding.ratingBar.getRating();
                Log.d("Feedback", activityFeedbackBinding.etFeedback.getText().toString());
                Log.d("rating", String.valueOf(rating));
                if(activityFeedbackBinding.etFeedback.getText().toString().isEmpty()){
                    activityFeedbackBinding.etFeedback.setError("Please give your review");
                    activityFeedbackBinding.etFeedback.requestFocus();
                } else if(rating == 0){
                    AppConstants.alertMessage(0, R.drawable.ic_error_2, "Failed", "Please give your rating", FeedbackActivity.this, MainActivity.class, "VISIBLE");
                } else {
                    putReviewRequest(userToken, bookingID, activityFeedbackBinding.etFeedback.getText().toString(), rating);
                }
            }
        });

    }

    public void putReviewRequest(String userToken, int booking_id, String comment, double rating){
        feedbackActivityViewModel.putReview(userToken, booking_id, comment, rating).observe(FeedbackActivity.this, new Observer<ReviewResponse>() {
            @Override
            public void onChanged(ReviewResponse reviewResponse) {
                if (reviewResponse == null){
                    AppConstants.alertMessage(1, R.drawable.ic_error_2, "Failed!", "Try Again", FeedbackActivity.this, NotificationActivity.class, "GONE");
                } else {
                    AppConstants.alertMessage(1, R.drawable.check, "Success!", "Thank you, your response will help our company improved!", FeedbackActivity.this, NotificationActivity.class, "VISIBLE");
                }
            }
        });
    }
}