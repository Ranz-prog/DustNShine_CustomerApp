package com.example.dustnshine.ui;


import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.dustnshine.MainActivity;
import com.example.dustnshine.response.BookingServiceResponse;
import com.example.dustnshine.models.ServicesModel;
import com.example.dustnshine.R;
import com.example.dustnshine.adapter.ServicesAdapter;
import com.example.dustnshine.storage.SharedPrefManager;
import com.example.dustnshine.ui.checkout.ActivityCheckOut;
import com.example.dustnshine.ui.signin.ActivitySignIn;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class ActivityCompanyDetails extends AppCompatActivity {

    private RecyclerView serviceRecycler;
    private List<ServicesModel> servicesModelList;
    LinearLayout btnBack;
    private ServicesAdapter servicesAdapter;
    private CompanyDetailsViewModel companyDetailsViewModel;
    private String userToken;
    Button checkOut;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_company_details);
        userToken = SharedPrefManager.getInstance(ActivityCompanyDetails.this).getUserToken();
        checkOut = findViewById(R.id.checkOutBtn);
        serviceRecycler = findViewById(R.id.serviceList);
        servicesAdapter = new ServicesAdapter(servicesModelList, this);
//        btnBack = findViewById(R.id.ReturnBtnOnFavorite);

        serviceRecycler.setHasFixedSize(true);
        serviceRecycler.setLayoutManager(new LinearLayoutManager(this));

        companyDetailsViewModel = new ViewModelProvider(ActivityCompanyDetails.this).get(CompanyDetailsViewModel.class);
        getServices(userToken);

        checkOut.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(ActivityCompanyDetails.this, ActivityCheckOut.class);
                startActivity(intent);
            }
        });

//        btnBack.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                finish();
//            }
//        });

    }


    public void getServices(String userToken){
        companyDetailsViewModel.getServicesList(userToken).observe(ActivityCompanyDetails.this, new Observer<List<ServicesModel>>() {
            @Override
            public void onChanged(List<ServicesModel> servicesModels) {
                if(servicesModels != null){
                    servicesModelList = servicesModels;
                    servicesAdapter.setData(servicesModels);
                    serviceRecycler.setAdapter(servicesAdapter);
                    Log.d("TAG", "Success");
                } else {
                    Log.d("TAG", "Failure");
                }
            }
        });
    }

    @Override
    public void onBackPressed()
    {
        finish();
    }
}
