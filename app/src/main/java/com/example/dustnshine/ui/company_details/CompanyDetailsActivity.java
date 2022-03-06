package com.example.dustnshine.ui.company_details;


import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.Toast;

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
import com.example.dustnshine.ui.QuantityListener;
import com.example.dustnshine.ui.TimeAndDateActivity;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class CompanyDetailsActivity extends AppCompatActivity implements QuantityListener {

    private RecyclerView rvServices;
    private List<ServicesModel> servicesModelList;
    private ImageView btnBack;
    private ServicesAdapter servicesAdapter;
    private CompanyDetailsViewModel companyDetailsViewModel;
    private String userToken;
    private ActivityCompanyDetailsBinding activityCompanyDetailsBinding;
    private String companyName, companyAddress;
    private int companyID;
    private Intent intent;
    private static ArrayList<Integer> servicesIdList;
    private static ArrayList<String> servicesNameList;
    private static ArrayList<Integer> servicesPriceList;
    private static String notes;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        activityCompanyDetailsBinding = DataBindingUtil.setContentView(this, R.layout.activity_company_details);
        companyDetailsViewModel = new ViewModelProvider(CompanyDetailsActivity.this).get(CompanyDetailsViewModel.class);
        userToken = SharedPrefManager.getInstance(CompanyDetailsActivity.this).getUserToken();
        intent = getIntent();
        btnBack = findViewById(R.id.backCompanyDetails);
        rvServices = findViewById(R.id.rvServices);
        servicesAdapter = new ServicesAdapter(servicesModelList, this, this);

        companyID = intent.getIntExtra("COMPANY_ID", 0);
        companyName = intent.getStringExtra("COMPANY_NAME");
        companyAddress = intent.getStringExtra("COMPANY_ADDRESS");

        rvServices.setHasFixedSize(true);
        rvServices.setLayoutManager(new LinearLayoutManager(this));
        getServices(userToken);

        activityCompanyDetailsBinding.tvCompanyName.setText(companyName);
        activityCompanyDetailsBinding.tvCompanyAddress.setText(companyAddress);

        activityCompanyDetailsBinding.btnNext.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (servicesNameList == null) {
                    Toast.makeText(CompanyDetailsActivity.this, "Please select services", Toast.LENGTH_SHORT).show();
                } else if(notes == null){
                    notes = activityCompanyDetailsBinding.etNotes.getText().toString();
                    Toast.makeText(CompanyDetailsActivity.this, "Notes is empty", Toast.LENGTH_SHORT).show();
                }
                else {
                    Log.d("Kinuha mong Service", servicesNameList.toString());
                    notes = activityCompanyDetailsBinding.etNotes.getText().toString();
                    Intent intent = new Intent(CompanyDetailsActivity.this, TimeAndDateActivity.class);
                    intent.putExtra("COMPANY_ID", companyID);
                    intent.putExtra("COMPANY_NAME", companyName);
                    intent.putExtra("COMPANY_ADDRESS", companyAddress);
                    intent.putIntegerArrayListExtra("SERVICES_ID_LIST", servicesIdList);
                    intent.putStringArrayListExtra("SERVICES_NAME_LIST", servicesNameList);
                    intent.putIntegerArrayListExtra("SERVICES_PRICE_LIST", servicesPriceList);
                    intent.putExtra("NOTES", notes);
//                    servicesNameList.clear();
//                    servicesIdList.clear();
                    Intent restart = getIntent();
                    finish();
                    startActivity(restart);
                    startActivity(intent);
                }
            }
        });

        btnBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();

            }
        });

    }

    public void getServices(String userToken) {
        companyDetailsViewModel.getServicesList(userToken).observe(CompanyDetailsActivity.this, new Observer<List<ServicesModel>>() {
            @Override
            public void onChanged(List<ServicesModel> servicesModels) {
                if (servicesModels != null) {
                    servicesModelList = servicesModels;
                    servicesAdapter.setData(servicesModels);
                    rvServices.setAdapter(servicesAdapter);
                    Log.d("TAG", "Success");
                } else {
                    Log.d("TAG", "Failure");
                }
            }
        });
    }

    @Override
    public void onBackPressed() {
        finish();
    }


    @Override
    public void onQuantityChange(ArrayList<Integer> servicesID, ArrayList<String> servicesName, ArrayList<Integer> servicesPrice) {
        if (servicesID == null || servicesName == null) {
            Log.d("Null na sya", "wala sya laman kaya oki lang");
        } else {
            servicesIdList = servicesID;
            servicesNameList = servicesName;
            servicesPriceList = servicesPrice;
            Log.d("Para d nakaka lito", "Palitan ko yung message daw");
        }
    }
}
