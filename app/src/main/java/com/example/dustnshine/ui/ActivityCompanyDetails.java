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
import androidx.lifecycle.MutableLiveData;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;


import com.example.dustnshine.api.RetrofitClient;
import com.example.dustnshine.models.ServiceResponse;
import com.example.dustnshine.models.ServicesModel;
import com.example.dustnshine.models.services_model;
import com.example.dustnshine.R;
import com.example.dustnshine.adapter.ServicesAdapter;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class ActivityCompanyDetails extends AppCompatActivity {

    private RecyclerView serviceRecycler;
    private List<services_model> servicesModelList;

    LinearLayout btnBack;
    // private List<ServiceResponse> servicesModelList;
    ServicesAdapter servicesAdapter;
    //List<ServicesModel> serviceResponse;
    MutableLiveData<List<ServicesModel>> serviceMutableData;

    Button checkOut;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_company_details);

        checkOut = findViewById(R.id.checkOutBtn);
        servicesAdapter = new ServicesAdapter();

//        btnBack = findViewById(R.id.ReturnBtnOnFavorite);
        serviceRecycler = findViewById(R.id.serviceList);

        serviceRecycler.setHasFixedSize(true);
        serviceRecycler.setLayoutManager(new LinearLayoutManager(this));

        getAllServiceDetails();

//        btnBack.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                finish();
//            }
//        });

//        checkOut.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                Intent intent = new Intent(ActivityCompanyDetails.this, ActivityTimeAndDate.class);
//                startActivity(intent);
//            }
//        });

    }

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
                    List<ServicesModel> serviceResponses = response.body().getServices();
                    servicesAdapter.setData(serviceResponses);
                    serviceRecycler.setAdapter(servicesAdapter);

                    Toast.makeText(ActivityCompanyDetails.this, "Success", Toast.LENGTH_LONG).show();

                }
                else{
                    Toast.makeText(ActivityCompanyDetails.this, "Failed", Toast.LENGTH_SHORT).show();
                }


            }

            @Override
            public void onFailure(Call<ServiceResponse> call, Throwable t) {
                Log.e("Juan",t.getLocalizedMessage());
            }
        });
    }

    @Override
    public void onBackPressed()
    {
        finish();
    }
}
