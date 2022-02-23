package com.example.dustnshine.ui.company_details;


import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.LinearLayout;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.databinding.DataBindingUtil;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.dustnshine.databinding.ActivityCompanyDetailsBinding;
import com.example.dustnshine.models.ServicesModel;
import com.example.dustnshine.R;
import com.example.dustnshine.adapter.ServicesAdapter;
import com.example.dustnshine.storage.SharedPrefManager;
import com.example.dustnshine.ui.checkout.CheckOutActivity;

import java.util.List;

public class CompanyDetailsActivity extends AppCompatActivity {

    private RecyclerView serviceRecycler;
    private List<ServicesModel> servicesModelList;
    LinearLayout btnBack;
    private ServicesAdapter servicesAdapter;
    private CompanyDetailsViewModel companyDetailsViewModel;
    private String userToken;
    private ActivityCompanyDetailsBinding activityCompanyDetailsBinding;
    private String companyName, companyAddress;
    private int companyID;
    private Intent intent;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        activityCompanyDetailsBinding = DataBindingUtil.setContentView(this, R.layout.activity_company_details);
        companyDetailsViewModel = new ViewModelProvider(CompanyDetailsActivity.this).get(CompanyDetailsViewModel.class);
        userToken = SharedPrefManager.getInstance(CompanyDetailsActivity.this).getUserToken();
        intent = getIntent();
        btnBack = findViewById(R.id.btnBack);
        serviceRecycler = findViewById(R.id.serviceList);
        servicesAdapter = new ServicesAdapter(servicesModelList, this);

        companyID = intent.getIntExtra("COMPANY_ID", 0);
        companyName = intent.getStringExtra("COMPANY_NAME");
        companyAddress = intent.getStringExtra("COMPANY_ADDRESS");

        serviceRecycler.setHasFixedSize(true);
        serviceRecycler.setLayoutManager(new LinearLayoutManager(this));

        getServices(userToken);

        activityCompanyDetailsBinding.txtCompanyName.setText(companyName);
        activityCompanyDetailsBinding.txtCompanyAddress.setText(companyAddress);

        activityCompanyDetailsBinding.btnNext.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(CompanyDetailsActivity.this, CheckOutActivity.class);
                intent.putExtra("COMPANY_ID", companyID);
                intent.putExtra("COMPANY_NAME", companyName);
                intent.putExtra("COMPANY_ADDRESS", companyAddress);
                startActivity(intent);
            }
        });

        btnBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });

    }


    public void getServices(String userToken){
        companyDetailsViewModel.getServicesList(userToken).observe(CompanyDetailsActivity.this, new Observer<List<ServicesModel>>() {
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
