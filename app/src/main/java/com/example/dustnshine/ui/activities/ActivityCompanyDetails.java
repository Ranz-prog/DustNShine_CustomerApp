package com.example.dustnshine.ui;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.MutableLiveData;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;


import com.example.dustnshine.api.RetrofitClient;
<<<<<<< HEAD
import com.example.dustnshine.response.BookingServiceResponse;
import com.example.dustnshine.response.ServiceResponse;
import com.example.dustnshine.models.ServicesModel;
import com.example.dustnshine.models.services_model;
import com.example.dustnshine.R;
import com.example.dustnshine.adapter.ServicesAdapter;
import com.example.dustnshine.storage.SharedPrefManager;

import java.util.ArrayList;
import java.util.HashMap;
=======
import com.example.dustnshine.models.ServiceResponse;
import com.example.dustnshine.R;
import com.example.dustnshine.adapter.ServicesAdapter;
import com.example.dustnshine.models.ServicesModel;

>>>>>>> branch_jericho
import java.util.List;
import java.util.Map;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class ActivityCompanyDetails extends AppCompatActivity {

<<<<<<< HEAD
    private RecyclerView serviceRecycler;
    private List<services_model> servicesModelList;
    private List<Map<Integer, Integer>> services;
    private Map<Integer, Integer> company;
    LinearLayout btnBack;
    ServicesAdapter servicesAdapter;
    private String userToken;
=======
    LinearLayout btnBack;
    private RecyclerView serviceRecycler;
   // private List<ServiceResponse> servicesModelList;
    ServicesAdapter servicesAdapter;
    //List<ServicesModel> serviceResponse;
    MutableLiveData<List<ServicesModel>> serviceMutableData;

>>>>>>> branch_jericho
    Button checkOut;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_company_details);
        userToken = SharedPrefManager.getInstance(ActivityCompanyDetails.this).getUserToken();
        checkOut = findViewById(R.id.checkOutBtn);
        servicesAdapter = new ServicesAdapter();

<<<<<<< HEAD
//        btnBack = findViewById(R.id.ReturnBtnOnFavorite);
=======
        btnBack = findViewById(R.id.ReturnBtnOnFavorite);
>>>>>>> branch_jericho
        serviceRecycler = findViewById(R.id.serviceList);

        serviceRecycler.setHasFixedSize(true);
        serviceRecycler.setLayoutManager(new LinearLayoutManager(this));
<<<<<<< HEAD
        getAllServiceDetails();
        company = new HashMap<Integer, Integer>();
        company.put(0, 1);
        company.put(1, 1);
        services = new ArrayList<Map<Integer, Integer>>();
        services.add(company);
        Log.d("LOG", services.toString());

//        btnBack.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                finish();
//            }
//        });
=======

        getAllServiceDetails();

        btnBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
>>>>>>> branch_jericho

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
<<<<<<< HEAD


    private void getAllServiceDetails(){
        Call<ServiceResponse> serviceList = RetrofitClient.getInstance().getApi().getAllServiceDetails("Bearer " + userToken);
        serviceList.enqueue(new Callback<ServiceResponse>() {
            @Override
            public void onResponse(Call<ServiceResponse> call, Response<ServiceResponse> response) {
                if(response.isSuccessful()){
                    List<ServicesModel> serviceResponses = response.body().getData();
                    servicesAdapter.setData(serviceResponses);
                    serviceRecycler.setAdapter(servicesAdapter);
                    Toast.makeText(ActivityCompanyDetails.this, "Success", Toast.LENGTH_LONG).show();
                }
                else{
                    Toast.makeText(ActivityCompanyDetails.this, "Failed", Toast.LENGTH_SHORT).show();
                }
=======

//    public MutableLiveData<List<ServicesModel>> getServiceMutableData(){
//        return serviceMutableData;
//    }

    private void getAllServiceDetails(){

        Call<ServiceResponse> serviceList = RetrofitClient.getInstance().getApi().getAllServiceDetails();

        serviceList.enqueue(new Callback<ServiceResponse>() {
            @Override
            public void onResponse(Call<ServiceResponse> call, Response<ServiceResponse> response) {


                if(response.isSuccessful()){

                    Log.e("sucess",response.body().toString());
                    List<ServicesModel> serviceResponses = response.body().getData();
                    servicesAdapter.setData(serviceResponses);
                    serviceRecycler.setAdapter(servicesAdapter);

                }
                else{
                    Toast.makeText(ActivityCompanyDetails.this, "Failed", Toast.LENGTH_SHORT).show();
                }


>>>>>>> branch_jericho
            }

            @Override
            public void onFailure(Call<ServiceResponse> call, Throwable t) {
<<<<<<< HEAD
                Log.e("Failure",t.getLocalizedMessage());
=======
                Log.e("Juan",t.getLocalizedMessage());
>>>>>>> branch_jericho
            }
        });
    }

    @Override
    public void onBackPressed()
    {
        finish();
    }
}
