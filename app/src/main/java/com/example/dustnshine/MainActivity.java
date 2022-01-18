package com.example.dustnshine;

import java.io.*;
import androidx.appcompat.app.AppCompatActivity;
import androidx.annotation.NonNull;
import com.example.dustnshine.ui.FragmentFavorites;
import com.example.dustnshine.ui.FragmentHome;
import com.example.dustnshine.ui.FragmentMessage;
import com.google.android.material.bottomnavigation.BottomNavigationView;
import android.os.Bundle;
import android.view.MenuItem;

public class MainActivity extends AppCompatActivity implements BottomNavigationView.OnNavigationItemSelectedListener {

    BottomNavigationView bottomNavigationView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        bottomNavigationView = findViewById(R.id.bottomNavigationView);

        bottomNavigationView.setOnItemSelectedListener(this);
        bottomNavigationView.setSelectedItemId(R.id.homeFragment);

    }
    FragmentHome fragmentHome = new FragmentHome();
    FragmentMessage fragmentMessage = new FragmentMessage();
    FragmentFavorites fragmentFavorites = new FragmentFavorites();

    @Override
    public boolean onNavigationItemSelected(@NonNull MenuItem item) {

        switch (item.getItemId()) {
            case R.id.home:
                getSupportFragmentManager().beginTransaction().replace(R.id.container, fragmentHome).commit();
                return true;

            case R.id.message:
                getSupportFragmentManager().beginTransaction().replace(R.id.container, fragmentMessage).commit();
                return true;

            case R.id.favorite:
                getSupportFragmentManager().beginTransaction().replace(R.id.container, fragmentFavorites).commit();
                return true;
        }
        return false;
    }
}