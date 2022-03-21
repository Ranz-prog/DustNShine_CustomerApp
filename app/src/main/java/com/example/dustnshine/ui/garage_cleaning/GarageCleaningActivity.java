package com.example.dustnshine.ui.garage_cleaning;

import androidx.appcompat.app.AppCompatActivity;
import androidx.databinding.DataBindingUtil;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.Toast;

import com.example.dustnshine.R;
import com.example.dustnshine.adapter.GarageCleaningAdapter;
import com.example.dustnshine.adapter.GeneralCleaningAdapter;
import com.example.dustnshine.databinding.ActivityGarageCleaningBinding;
import com.example.dustnshine.models.RecommendationModel;
import com.example.dustnshine.storage.SharedPrefManager;

import java.util.List;

public class GarageCleaningActivity extends AppCompatActivity implements GeneralCleaningAdapter.OnClickMessageListener {

    private RecyclerView rvGarageCleaning;
    private GarageCleaningAdapter garageCleaningAdapter;
    private static List<RecommendationModel> companyList;
    private LinearLayoutManager linearLayoutManager;
    private static String userToken;
    private GarageCleaningViewModel garageCleaningViewModel;
    private ImageView btnBack;
    private ActivityGarageCleaningBinding activityGarageCleaningBinding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        garageCleaningViewModel = new ViewModelProvider(GarageCleaningActivity.this).get(GarageCleaningViewModel.class);
        activityGarageCleaningBinding = DataBindingUtil.setContentView(this, R.layout.activity_garage_cleaning);
        userToken = SharedPrefManager.getInstance(GarageCleaningActivity.this).getUserToken();
        garageCleaningAdapter = new GarageCleaningAdapter(companyList, GarageCleaningActivity.this, GarageCleaningActivity.this);
        linearLayoutManager = new LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false);
        activityGarageCleaningBinding.rvGarageCleaning.setLayoutManager(linearLayoutManager);

        getFilteredService(1, userToken);


        activityGarageCleaningBinding.btnBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });

    }

    private void getFilteredService(int service, String userToken) {

        garageCleaningViewModel.getFilteredService(service, userToken).observe(GarageCleaningActivity.this, new Observer<List<RecommendationModel>>() {
            @Override
            public void onChanged(List<RecommendationModel> recommendationModels) {
                if(!recommendationModels.isEmpty()){
                    companyList = recommendationModels;
                    garageCleaningAdapter.setData(recommendationModels);
                    activityGarageCleaningBinding.rvGarageCleaning.setAdapter(garageCleaningAdapter);
                    activityGarageCleaningBinding.imgNoData.setVisibility(View.GONE);
                    activityGarageCleaningBinding.tvNoData.setVisibility(View.GONE);
                } else {
                    activityGarageCleaningBinding.rvGarageCleaning.setVisibility(View.GONE);
                    activityGarageCleaningBinding.imgNoData.setVisibility(View.VISIBLE);
                    activityGarageCleaningBinding.tvNoData.setVisibility(View.VISIBLE);
                }
            }
        });
    }

    @Override
    public void onClickMessage(int adapterPosition) {

    }
}