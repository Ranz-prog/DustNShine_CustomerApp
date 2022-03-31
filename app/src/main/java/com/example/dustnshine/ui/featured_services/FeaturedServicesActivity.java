package com.example.dustnshine.ui.featured_services;

import androidx.appcompat.app.AppCompatActivity;
import androidx.databinding.DataBindingUtil;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.LinearLayoutManager;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import com.example.dustnshine.R;
import com.example.dustnshine.adapter.CompanyServicesAdapter;
import com.example.dustnshine.adapter.GeneralCleaningAdapter;
import com.example.dustnshine.databinding.ActivityFeaturedServicesBinding;
import com.example.dustnshine.models.RecommendationModel;
import com.example.dustnshine.storage.SharedPrefManager;
import com.example.dustnshine.ui.company_details.CompanyDetailsActivity;

import java.util.List;

public class FeaturedServicesActivity extends AppCompatActivity implements GeneralCleaningAdapter.OnClickMessageListener {

    private CompanyServicesAdapter companyServicesAdapter;
    private static List<RecommendationModel> companyList;
    private LinearLayoutManager linearLayoutManager;
    private static String userToken;
    private FeaturedServicesViewModel featuredServicesViewModel;
    private ActivityFeaturedServicesBinding activityFeaturedServicesBinding;
    private Intent intent;
    private static String serviceName;
    private static int serviceId;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        intent = getIntent();
        serviceId = intent.getIntExtra("SERVICE_ID", 0);
        serviceName = intent.getStringExtra("SERVICE_NAME");
        featuredServicesViewModel = new ViewModelProvider(FeaturedServicesActivity.this).get(FeaturedServicesViewModel.class);
        activityFeaturedServicesBinding = DataBindingUtil.setContentView(this, R.layout.activity_featured_services);
        userToken = SharedPrefManager.getInstance(FeaturedServicesActivity.this).getUserToken();
        companyServicesAdapter = new CompanyServicesAdapter(companyList, FeaturedServicesActivity.this, FeaturedServicesActivity.this);
        linearLayoutManager = new LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false);
        activityFeaturedServicesBinding.rvGarageCleaning.setLayoutManager(linearLayoutManager);
        activityFeaturedServicesBinding.tvServiceName.setText(serviceName);

        getFilteredService(serviceId, userToken);

        activityFeaturedServicesBinding.btnBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });

    }

    private void getFilteredService(int service, String userToken) {

        featuredServicesViewModel.getFilteredService(service, userToken).observe(FeaturedServicesActivity.this, new Observer<List<RecommendationModel>>() {
            @Override
            public void onChanged(List<RecommendationModel> recommendationModels) {
                if(!recommendationModels.isEmpty()){
                    companyList = recommendationModels;
                    companyServicesAdapter.setData(recommendationModels);
                    activityFeaturedServicesBinding.rvGarageCleaning.setAdapter(companyServicesAdapter);
                    activityFeaturedServicesBinding.imgNoData.setVisibility(View.GONE);
                    activityFeaturedServicesBinding.tvNoData.setVisibility(View.GONE);
                } else {
                    activityFeaturedServicesBinding.rvGarageCleaning.setVisibility(View.GONE);
                    activityFeaturedServicesBinding.imgNoData.setVisibility(View.VISIBLE);
                    activityFeaturedServicesBinding.tvNoData.setVisibility(View.VISIBLE);
                }
            }
        });
    }

    @Override
    public void onClickMessage(int adapterPosition) {
        Intent intent = new Intent(getApplicationContext(), CompanyDetailsActivity.class);
        intent.putExtra("COMPANY_ID", companyList.get(adapterPosition).getId());
        intent.putExtra("COMPANY_NAME", companyList.get(adapterPosition).getName());
        intent.putExtra("COMPANY_ADDRESS", companyList.get(adapterPosition).getAddress());
        intent.putExtra("COMPANY_IMAGE", companyList.get(adapterPosition).getCompany_image());
        intent.putExtra("COMPANY_EMAIL", companyList.get(adapterPosition).getEmail());
        intent.putExtra("COMPANY_MOBILE", companyList.get(adapterPosition).getMobile_number());
        intent.putExtra("COMPANY_TELEPHONE", companyList.get(adapterPosition).getTel_number());
        startActivity(intent);
    }
}