package com.example.dustnshine.ui;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RatingBar;
import android.widget.Toast;

import com.example.dustnshine.R;
import com.example.dustnshine.response.ReviewResponse;
import com.example.dustnshine.storage.SharedPrefManager;

public class FeedbackActivity extends AppCompatActivity {

    private RatingBar ratingBar;
    private EditText etFeedback;
    private FeedbackActivityViewModel feedbackActivityViewModel;
    private static String userToken;
    private Button btnReview;
    private int rating;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_feedback);

        ratingBar = findViewById(R.id.ratingBar);
        etFeedback = findViewById(R.id.etFeedback);
        btnReview = findViewById(R.id.btnReview);
        feedbackActivityViewModel = new ViewModelProvider(FeedbackActivity.this).get(FeedbackActivityViewModel.class);
        userToken = SharedPrefManager.getInstance(FeedbackActivity.this).getUserToken();

        btnReview.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                rating = (int) ratingBar.getRating();
                putReviewRequest(userToken, 204, etFeedback.getText().toString(), rating);
                Intent intent = new Intent(FeedbackActivity.this, NotificationActivity.class);
                startActivity(intent);
            }
        });

    }

    public void putReviewRequest(String userToken, int booking_id, String comment, int rating){
        feedbackActivityViewModel.putReview(userToken, booking_id, comment, rating).observe(FeedbackActivity.this, new Observer<ReviewResponse>() {
            @Override
            public void onChanged(ReviewResponse reviewResponse) {
                if (reviewResponse == null){
                    Toast.makeText(FeedbackActivity.this, "Failed", Toast.LENGTH_SHORT).show();
                } else {
                    Toast.makeText(FeedbackActivity.this, "Success", Toast.LENGTH_SHORT).show();
                }
            }
        });
    }
}