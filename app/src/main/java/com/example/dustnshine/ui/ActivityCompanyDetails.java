package com.example.dustnshine.ui;


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

import com.example.dustnshine.response.BookingServiceResponse;
import com.example.dustnshine.models.ServicesModel;
import com.example.dustnshine.R;
import com.example.dustnshine.adapter.ServicesAdapter;
import com.example.dustnshine.storage.SharedPrefManager;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class ActivityCompanyDetails extends AppCompatActivity {

    private RecyclerView serviceRecycler;
    private List<ServicesModel> servicesModelList;
    private List<Map<Integer, Integer>> services;
    private Map<Integer, Integer> company;
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

        company = new HashMap<Integer, Integer>();
        company.put(0, 1);
        company.put(1, 1);
        services = new ArrayList<Map<Integer, Integer>>();
        services.add(company);

        checkOut.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                getBookingRequest(userToken, 1, "Dagupan City", "2022-05-05 00:00:00", 1000, services);
            }
        });

//        btnBack.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                finish();
//            }
//        });

    }

    public void getBookingRequest(String userToken, int company_id, String address, String start_datetime, int total, List<Map<Integer, Integer>> services){
        companyDetailsViewModel.getBookingServiceRequest(userToken, company_id, address, start_datetime, total, services).observe(ActivityCompanyDetails.this, new Observer<BookingServiceResponse>() {
            @Override
            public void onChanged(BookingServiceResponse bookingServiceResponse) {
                if(bookingServiceResponse == null){
                    Toast.makeText(ActivityCompanyDetails.this, bookingServiceResponse.getMessage(), Toast.LENGTH_SHORT).show();
                } else {
                    Toast.makeText(ActivityCompanyDetails.this, bookingServiceResponse.getMessage(), Toast.LENGTH_SHORT).show();
                }
            }
        });
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
