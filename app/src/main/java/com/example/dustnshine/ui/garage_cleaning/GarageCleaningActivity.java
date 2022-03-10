package com.example.dustnshine.ui.garage_cleaning;

import androidx.appcompat.app.AppCompatActivity;
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
import com.example.dustnshine.models.RecommendationModel;
import com.example.dustnshine.storage.SharedPrefManager;

import java.util.List;

public class GarageCleaningActivity extends AppCompatActivity implements GeneralCleaningAdapter.OnClickMessageListener {

    private RecyclerView garageCleaningRecycler;
    private GarageCleaningAdapter garageCleaningAdapter;
    private static List<RecommendationModel> companyList;
    private LinearLayoutManager linearLayoutManager;
    private static String userToken;
    private GarageCleaningViewModel garageCleaningViewModel;
    ImageView btnBack;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_garage_cleaning);
        garageCleaningViewModel = new ViewModelProvider(GarageCleaningActivity.this).get(GarageCleaningViewModel.class);

        garageCleaningRecycler = findViewById(R.id.garageCleaningList);
        garageCleaningRecycler.setHasFixedSize(true);
        garageCleaningRecycler.setLayoutManager(new LinearLayoutManager(this));
        userToken = SharedPrefManager.getInstance(GarageCleaningActivity.this).getUserToken();
        garageCleaningAdapter = new GarageCleaningAdapter(companyList, GarageCleaningActivity.this, GarageCleaningActivity.this);

        garageCleaningRecycler.setHasFixedSize(true);
        linearLayoutManager = new LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false);
        garageCleaningRecycler.setLayoutManager(linearLayoutManager);

        getFilteredService(1, userToken);

        btnBack = findViewById(R.id.backGarage);

        btnBack.setOnClickListener(new View.OnClickListener() {
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
                if(recommendationModels != null){
                    companyList = recommendationModels;
                    garageCleaningAdapter.setData(recommendationModels);
                    garageCleaningRecycler.setAdapter(garageCleaningAdapter);
                } else {
                    Toast.makeText(GarageCleaningActivity.this, "No Company Found", Toast.LENGTH_SHORT).show();
                }
            }
        });

//        Call<FilteredServiceResponse> serviceResponseCall = RetrofitClient.getInstance().getApi().getFilteredService(service, "Bearer " + userToken);
//        serviceResponseCall.enqueue(new Callback<FilteredServiceResponse>() {
//            @Override
//            public void onResponse(Call<FilteredServiceResponse> call, Response<FilteredServiceResponse> response) {
//                if (response.code() == 200) {
//                    companyList = response.body().getData();
//                    garageCleaningAdapter.setData(companyList);
//                    garageCleaningRecycler.setAdapter(garageCleaningAdapter);
//                } else {
//                    Toast.makeText(GarageCleaningActivity.this, "Failed", Toast.LENGTH_SHORT).show();
//                }
//            }
//
//            @Override
//            public void onFailure(Call<FilteredServiceResponse> call, Throwable t) {
//                Log.d("TAG", "Failure to connect");
//            }
//        });
    }

    @Override
    public void onClickMessage(int adapterPosition) {

    }
}