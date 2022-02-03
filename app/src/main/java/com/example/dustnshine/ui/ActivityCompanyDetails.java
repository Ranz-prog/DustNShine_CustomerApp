package com.example.dustnshine.ui;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;


import com.example.dustnshine.models.services_model;
import com.example.dustnshine.R;
import com.example.dustnshine.adapter.services_adapter;

import java.util.ArrayList;
import java.util.List;

public class ActivityCompanyDetails extends AppCompatActivity {

    private RecyclerView serviceRecycler;
    private List<services_model> servicesModelList;

    Button checkOut;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_company_details);

        checkOut = findViewById(R.id.checkOutBtn);

        serviceRecycler = findViewById(R.id.serviceList);
        serviceRecycler.setHasFixedSize(true);
        serviceRecycler.setLayoutManager(new LinearLayoutManager(this));

        serviceRecycler.setAdapter(new services_adapter(servicesModels()));

        checkOut.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(ActivityCompanyDetails.this, ActivityCheckOut.class);
                startActivity(intent);
            }
        });

        return;


    }

    private List<services_model> servicesModels(){

        servicesModelList = new ArrayList<>();

        servicesModelList.add(new services_model(
                "General","1000/ hr","Cleaning of whole house","All materials included"));
        servicesModelList.add(new services_model(
                "Dishwashing","90/ 30 mins","Washing of dishes and kitchenware","kindly provide sponge and dishwashing liquid"));
        servicesModelList.add(new services_model(
                "Electricfan Cleaning","99 per unit(s)","Food for air Quality","Cleaning of blades and cage"));


        return servicesModelList;



    }
}
