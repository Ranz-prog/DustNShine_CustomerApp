package com.example.dustnshine.ui.activities;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.LinearLayout;

import com.example.dustnshine.R;

public class ActivityTimeAndDate extends AppCompatActivity {

    LinearLayout backButon;
    Button CheckOut;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_time_and_date);

        backButon = findViewById(R.id.btnReturnDate);
        CheckOut = findViewById(R.id.CheckOut);

        CheckOut.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(ActivityTimeAndDate.this, ActivityCheckOut.class);
                startActivity(intent);
            }
        });

        backButon.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
    }
}