package com.example.dustnshine;

import java.io.*;
import androidx.appcompat.app.AppCompatActivity;
import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;

import com.example.dustnshine.ui.FragmentFavorites;
import com.example.dustnshine.ui.FragmentHome;
import com.example.dustnshine.ui.FragmentMessage;

import com.google.android.material.bottomnavigation.BottomNavigationView;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.WindowManager;

public class MainActivity extends AppCompatActivity {

    BottomNavigationView bottomNavigationView;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        this.getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,WindowManager.LayoutParams.FLAG_FULLSCREEN);
        getSupportActionBar().hide();
        setContentView(R.layout.activity_main);

        bottomNavigationView = findViewById(R.id.bottomNavigationView);


        bottomNavigationView.setOnNavigationItemSelectedListener(navListener);

        getSupportFragmentManager().beginTransaction().replace(
                R.id.flFragment, new FragmentHome()).commit();
    }
    private BottomNavigationView.OnNavigationItemSelectedListener navListener
            = new BottomNavigationView.OnNavigationItemSelectedListener() {
        @Override
        public boolean onNavigationItemSelected(@NonNull MenuItem item) {
            Fragment selectedFragment = null;

            int itemId = item.getItemId();
            if (itemId == R.id.home) {
                selectedFragment = new FragmentHome();
            } else if (itemId == R.id.message) {
                selectedFragment = new FragmentMessage();
            } else if (itemId == R.id.favorite) {
                selectedFragment = new FragmentFavorites();
            }
            getSupportFragmentManager().beginTransaction().replace(R.id.flFragment,
                    selectedFragment).commit();

            return true;
        }
    };
}