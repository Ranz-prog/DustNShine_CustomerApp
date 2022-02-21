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


import com.example.dustnshine.api.RetrofitClient;
import com.example.dustnshine.response.BookingServiceResponse;
import com.example.dustnshine.response.ServiceResponse;
import com.example.dustnshine.models.ServicesModel;
import com.example.dustnshine.models.services_model;
import com.example.dustnshine.R;
import com.example.dustnshine.adapter.ServicesAdapter;
import com.example.dustnshine.storage.SharedPrefManager;
import com.example.dustnshine.ui.home.FragmentHome;
import com.example.dustnshine.ui.home.FragmentHomeViewModel;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

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
        companyDetailsViewModel.getServiceList().observe(ActivityCompanyDetails.this, new Observer<List<ServicesModel>>() {
            @Override
            public void onChanged(List<ServicesModel> servicesModels) {
                if(servicesModels != null){
                    servicesModelList = servicesModels;
                    servicesAdapter.setData(servicesModels);
                    serviceRecycler.setAdapter(servicesAdapter);
                }
            }
        });

        companyDetailsViewModel.makeAPICall(userToken);

        company = new HashMap<Integer, Integer>();
        company.put(0, 1);
        company.put(1, 1);
        services = new ArrayList<Map<Integer, Integer>>();
        services.add(company);

//        btnBack.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                finish();
//            }
//        });

        checkOut.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Call<BookingServiceResponse> bookingServiceResponseCall = RetrofitClient.getInstance().getApi().bookService("Bearer " + userToken, 1, "San Fabian", "2022-05-05 00:00:00", 1000, services);
                bookingServiceResponseCall.enqueue(new Callback<BookingServiceResponse>() {
                    @Override
                    public void onResponse(Call<BookingServiceResponse> call, Response<BookingServiceResponse> response) {
                        if(response.code() == 200){
                            Toast.makeText(ActivityCompanyDetails.this, "Booked", Toast.LENGTH_LONG).show();
                        } else if(response.code() == 422){
                            Toast.makeText(ActivityCompanyDetails.this, "The given data is invalid", Toast.LENGTH_LONG).show();
                        } else {
                            Toast.makeText(ActivityCompanyDetails.this, "Success", Toast.LENGTH_LONG).show();
                        }
                    }
                    @Override
                    public void onFailure(Call<BookingServiceResponse> call, Throwable t) {

                    }
                });
            }
        });

    }

    @Override
    public void onBackPressed()
    {
        finish();
    }
}
