package com.example.dustnshine.ui.feedback;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.RatingBar;
import android.widget.Toast;

import com.example.dustnshine.MainActivity;
import com.example.dustnshine.R;
import com.example.dustnshine.response.ReviewResponse;
import com.example.dustnshine.storage.SharedPrefManager;

public class FeedbackActivity extends AppCompatActivity {

    private RatingBar ratingBar;
    private EditText etFeedback;
    private FeedbackActivityViewModel feedbackActivityViewModel;
    private static String userToken;
    private Button btnReview;
    private double rating;
    private ImageView btnBack;
    private Intent intent;
    private static int bookingID;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_feedback);

        ratingBar = findViewById(R.id.ratingBar);
        etFeedback = findViewById(R.id.etFeedback);
        btnReview = findViewById(R.id.btnReview);
        feedbackActivityViewModel = new ViewModelProvider(FeedbackActivity.this).get(FeedbackActivityViewModel.class);
        userToken = SharedPrefManager.getInstance(FeedbackActivity.this).getUserToken();
        btnBack = findViewById(R.id.btnBack);
        intent = getIntent();

        bookingID = intent.getIntExtra("BOOKING_ID", 0);

        btnBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });

        btnReview.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                rating = ratingBar.getRating();
                if(etFeedback.getText().toString().isEmpty()){
                    Toast.makeText(FeedbackActivity.this, "Please give your feedback", Toast.LENGTH_SHORT).show();
                } else if(rating == 0){
                    Toast.makeText(FeedbackActivity.this, "Please give your rating", Toast.LENGTH_SHORT).show();
                } else {
                    putReviewRequest(userToken, bookingID, etFeedback.getText().toString(), rating);
                    Intent intent = new Intent(getApplication(), MainActivity.class);
                    startActivity(intent);
                }
            }
        });

    }

    public void putReviewRequest(String userToken, int booking_id, String comment, double rating){
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