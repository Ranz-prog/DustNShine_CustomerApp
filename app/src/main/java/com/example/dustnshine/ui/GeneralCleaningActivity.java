package com.example.dustnshine.ui;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.Toast;

import com.example.dustnshine.R;
import com.example.dustnshine.adapter.GeneralCleaningAdapter;
import com.example.dustnshine.adapter.SeeAllRecommendationsAdapter;
import com.example.dustnshine.api.RetrofitClient;
import com.example.dustnshine.models.RecommendationModel;
import com.example.dustnshine.response.FilteredServiceResponse;
import com.example.dustnshine.response.SearchCompanyResponse;
import com.example.dustnshine.storage.SharedPrefManager;
import com.example.dustnshine.ui.checkout.CheckOutActivity;
import com.example.dustnshine.ui.recommendations.SeeAllRecommendationsActivity;
import com.example.dustnshine.ui.recommendations.SeeAllRecommendationsViewModel;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class GeneralCleaningActivity extends AppCompatActivity implements GeneralCleaningAdapter.OnClickMessageListener {

    private RecyclerView generalCleaningRecycler;
    private GeneralCleaningAdapter generalCleaningAdapter;
    private static List<RecommendationModel> companyList;
    private LinearLayoutManager linearLayoutManager;
    private static String userToken;
    private GeneralCleaningViewModel generalCleaningViewModel;
    ImageView btnBack;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_general_cleaning);
        generalCleaningViewModel = new ViewModelProvider(GeneralCleaningActivity.this).get(GeneralCleaningViewModel.class);

        generalCleaningRecycler = findViewById(R.id.generalCleaningList);
        generalCleaningRecycler.setHasFixedSize(true);
        generalCleaningRecycler.setLayoutManager(new LinearLayoutManager(this));
        userToken = SharedPrefManager.getInstance(GeneralCleaningActivity.this).getUserToken();
        generalCleaningAdapter = new GeneralCleaningAdapter(companyList, GeneralCleaningActivity.this, GeneralCleaningActivity.this);

        generalCleaningRecycler.setHasFixedSize(true);
        linearLayoutManager = new LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false);
        generalCleaningRecycler.setLayoutManager(linearLayoutManager);

        getFilteredService(2, userToken);

        btnBack = findViewById(R.id.backGeneral);

        btnBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
    }

    private void getFilteredService(int service, String userToken) {

        generalCleaningViewModel.getFilteredService(service, userToken).observe(GeneralCleaningActivity.this, new Observer<List<RecommendationModel>>() {
            @Override
            public void onChanged(List<RecommendationModel> recommendationModels) {
                if(recommendationModels != null){
                    companyList = recommendationModels;
                    generalCleaningAdapter.setData(recommendationModels);
                    generalCleaningRecycler.setAdapter(generalCleaningAdapter);
                } else {
                    Toast.makeText(GeneralCleaningActivity.this, "No Company Found", Toast.LENGTH_SHORT).show();
                }
            }
        });

//        Call<FilteredServiceResponse> serviceResponseCall = RetrofitClient.getInstance().getApi().getFilteredService(service, "Bearer " + userToken);
//        serviceResponseCall.enqueue(new Callback<FilteredServiceResponse>() {
//            @Override
//            public void onResponse(Call<FilteredServiceResponse> call, Response<FilteredServiceResponse> response) {
//                if (response.code() == 200) {
//                    companyList = response.body().getData();
//                    generalCleaningAdapter.setData(companyList);
//                    generalCleaningRecycler.setAdapter(generalCleaningAdapter);
//                } else {
//                    Toast.makeText(GeneralCleaningActivity.this, "Failed", Toast.LENGTH_SHORT).show();
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