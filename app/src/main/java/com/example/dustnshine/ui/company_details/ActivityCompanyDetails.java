package com.example.dustnshine.ui.company_details;


import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
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
import com.example.dustnshine.ui.checkout.ActivityCheckOut;

import java.util.List;

public class ActivityCompanyDetails extends AppCompatActivity {

    private RecyclerView serviceRecycler;
    private List<ServicesModel> servicesModelList;
    LinearLayout btnBack;
    private ServicesAdapter servicesAdapter;
    private CompanyDetailsViewModel companyDetailsViewModel;
    private String userToken;
    private ActivityCompanyDetailsBinding activityCompanyDetailsBinding;
    private Intent intent;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        activityCompanyDetailsBinding = DataBindingUtil.setContentView(this, R.layout.activity_company_details);
        companyDetailsViewModel = new ViewModelProvider(ActivityCompanyDetails.this).get(CompanyDetailsViewModel.class);
        userToken = SharedPrefManager.getInstance(ActivityCompanyDetails.this).getUserToken();
        intent = getIntent();

        serviceRecycler = findViewById(R.id.serviceList);
        servicesAdapter = new ServicesAdapter(servicesModelList, this);
//        btnBack = findViewById(R.id.btnBack);

        serviceRecycler.setHasFixedSize(true);
        serviceRecycler.setLayoutManager(new LinearLayoutManager(this));

        getServices(userToken);

//        activityCompanyDetailsBinding.txtCompanyName.setText(intent.getStringExtra("COMPANY_NAME"));
//        activityCompanyDetailsBinding.txtCompanyAddress.setText(intent.getStringExtra("COMPANY_ADDRESS"));

        activityCompanyDetailsBinding.btnNext.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(ActivityCompanyDetails.this, ActivityCheckOut.class);
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
