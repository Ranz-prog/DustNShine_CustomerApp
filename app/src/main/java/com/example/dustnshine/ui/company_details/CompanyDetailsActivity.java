package com.example.dustnshine.ui.company_details;


import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.databinding.DataBindingUtil;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.LinearLayoutManager;

import com.bumptech.glide.Glide;
import com.example.dustnshine.MainActivity;
import com.example.dustnshine.databinding.ActivityCompanyDetailsBinding;
import com.example.dustnshine.models.CompanyAndServicesModel;
import com.example.dustnshine.R;
import com.example.dustnshine.adapter.ServicesAdapter;
import com.example.dustnshine.storage.SharedPrefManager;
import com.example.dustnshine.ui.QuantityListener;
import com.example.dustnshine.ui.TimeAndDateActivity;
import com.example.dustnshine.utils.AppConstants;

import java.util.ArrayList;

public class CompanyDetailsActivity extends AppCompatActivity implements QuantityListener {

    private CompanyAndServicesModel companyAndServicesModels;
    private ServicesAdapter servicesAdapter;
    private CompanyDetailsViewModel companyDetailsViewModel;
    private String userToken;
    private ActivityCompanyDetailsBinding activityCompanyDetailsBinding;
    private static String companyName, companyAddress, companyImage, companyEmail, companyMobile, companyTelephone;
    private static int companyID;
    private static double companyRating;
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
        servicesAdapter = new ServicesAdapter(companyAndServicesModels, this, this);

        companyID = intent.getIntExtra("COMPANY_ID", 0);
        companyName = intent.getStringExtra("COMPANY_NAME");
        companyAddress = intent.getStringExtra("COMPANY_ADDRESS");
        companyImage = intent.getStringExtra("COMPANY_IMAGE");
        companyRating = intent.getDoubleExtra("COMPANY_RATING", 0.0);
        companyEmail = intent.getStringExtra("COMPANY_EMAIL");
        companyMobile = intent.getStringExtra("COMPANY_MOBILE");
        companyTelephone = intent.getStringExtra("COMPANY_TELEPHONE");

        activityCompanyDetailsBinding.rvServices.setHasFixedSize(true);
        activityCompanyDetailsBinding.rvServices.setLayoutManager(new LinearLayoutManager(this));
        getServices(companyID, userToken);

        Glide.with(CompanyDetailsActivity.this).load(AppConstants.BASE_URL + companyImage).into(activityCompanyDetailsBinding.imgCompanyImage);
        activityCompanyDetailsBinding.tvCompanyName.setText(companyName);
        activityCompanyDetailsBinding.tvCompanyAddress.setText(companyAddress);
        activityCompanyDetailsBinding.tvEmail.setText(companyEmail);
        activityCompanyDetailsBinding.tvMobile.setText(companyMobile);
        activityCompanyDetailsBinding.tvTelephone.setText(companyTelephone);
        activityCompanyDetailsBinding.tvCompanyRating.setText(String.valueOf(companyRating));

        activityCompanyDetailsBinding.btnNext.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View view) {
                notes = activityCompanyDetailsBinding.etNotes.getText().toString();
                if (servicesNameList == null || servicesNameList.size() == 0) {
                    AppConstants.alertMessage(0, R.drawable.ic_error_2, "Ooops!", "No service selected", CompanyDetailsActivity.this, CompanyDetailsActivity.class, "VISIBLE");
                } else {
                    Intent intent = new Intent(CompanyDetailsActivity.this, TimeAndDateActivity.class);
                    intent.putExtra("COMPANY_ID", companyID);
                    intent.putExtra("COMPANY_NAME", companyName);
                    intent.putExtra("COMPANY_ADDRESS", companyAddress);
                    intent.putIntegerArrayListExtra("SERVICES_ID_LIST", servicesIdList);
                    intent.putStringArrayListExtra("SERVICES_NAME_LIST", servicesNameList);
                    intent.putIntegerArrayListExtra("SERVICES_PRICE_LIST", servicesPriceList);
                    intent.putExtra("NOTES", notes);
                    startActivity(intent);
                }
            }
        });


        activityCompanyDetailsBinding.btnBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(CompanyDetailsActivity.this, MainActivity.class);
                startActivity(intent);
            }
        });
    }

    public void getServices(int company_id, String userToken) {
        companyDetailsViewModel.getSpecificCompany(company_id, userToken).observe(CompanyDetailsActivity.this, new Observer<CompanyAndServicesModel>() {
            @Override
            public void onChanged(CompanyAndServicesModel companyAndServicesModel) {
                if (!companyAndServicesModel.getServices().isEmpty()) {
                    companyAndServicesModels = companyAndServicesModel;
                    servicesAdapter.setData(companyAndServicesModel);
                    activityCompanyDetailsBinding.rvServices.setAdapter(servicesAdapter);
                    activityCompanyDetailsBinding.imgNoData.setVisibility(View.GONE);
                    activityCompanyDetailsBinding.tvNoData.setVisibility(View.GONE);
                } else {
                    activityCompanyDetailsBinding.rvServices.setVisibility(View.GONE);
                    activityCompanyDetailsBinding.tvServices.setVisibility(View.GONE);
                    activityCompanyDetailsBinding.tvExtraServices.setVisibility(View.GONE);
                    activityCompanyDetailsBinding.imgNoData.setVisibility(View.VISIBLE);
                    activityCompanyDetailsBinding.tvNoData.setVisibility(View.VISIBLE);
                }
            }
        });
    }

    @Override
    public void onBackPressed() {
        Intent intent = new Intent(CompanyDetailsActivity.this, MainActivity.class);
        startActivity(intent);
    }

    @Override
    public void onQuantityChange(ArrayList<Integer> servicesID, ArrayList<String> servicesName, ArrayList<Integer> servicesPrice, ArrayList<Integer> quantityOfService) {

        if (servicesID.toString() == "" || servicesName.toString() == "" || servicesID.contains(" ") || quantityOfService.contains(" ")) {
        } else {
            servicesIdList = servicesID;
            servicesNameList = servicesName;
            servicesPriceList = servicesPrice;
            quantityOfService = quantityOfService;
        }

    }


}
