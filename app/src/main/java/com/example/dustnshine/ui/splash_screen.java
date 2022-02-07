package com.example.dustnshine.ui;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.view.WindowManager;

import androidx.appcompat.app.AppCompatActivity;

import com.example.dustnshine.R;
import com.example.dustnshine.ui.activities.ActivitySignIn;

public class splash_screen extends AppCompatActivity {

    // /private TextView dustandshine;/

    Handler handler = new Handler();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        this.getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,WindowManager.LayoutParams.FLAG_FULLSCREEN);

        setContentView(R.layout.splashscreen);


        handler.postDelayed(new Runnable() {
            @Override
            public void run() {
                Intent intent = new Intent(splash_screen.this, ActivitySignIn.class);
                startActivity(intent);
            }
        }, 5000);
    }
}