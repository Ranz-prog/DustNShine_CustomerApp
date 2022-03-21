package com.example.dustnshine.ui.general_cleaning;

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
import com.example.dustnshine.adapter.GeneralCleaningAdapter;
import com.example.dustnshine.databinding.ActivityGeneralCleaningBinding;
import com.example.dustnshine.models.RecommendationModel;
import com.example.dustnshine.storage.SharedPrefManager;

import java.util.List;

public class GeneralCleaningActivity extends AppCompatActivity implements GeneralCleaningAdapter.OnClickMessageListener {

    private GeneralCleaningAdapter generalCleaningAdapter;
    private static List<RecommendationModel> companyList;
    private LinearLayoutManager linearLayoutManager;
    private static String userToken;
    private GeneralCleaningViewModel generalCleaningViewModel;
    private ActivityGeneralCleaningBinding activityGeneralCleaningBinding;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        activityGeneralCleaningBinding = DataBindingUtil.setContentView(this, R.layout.activity_general_cleaning);
        generalCleaningViewModel = new ViewModelProvider(GeneralCleaningActivity.this).get(GeneralCleaningViewModel.class);
        userToken = SharedPrefManager.getInstance(GeneralCleaningActivity.this).getUserToken();
        generalCleaningAdapter = new GeneralCleaningAdapter(companyList, GeneralCleaningActivity.this, GeneralCleaningActivity.this);
        activityGeneralCleaningBinding.rvGeneralCleaning.setHasFixedSize(true);
        linearLayoutManager = new LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false);
        activityGeneralCleaningBinding.rvGeneralCleaning.setLayoutManager(linearLayoutManager);

        getFilteredService(2, userToken);

        activityGeneralCleaningBinding.btnBack.setOnClickListener(new View.OnClickListener() {
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
                if(!recommendationModels.isEmpty()){
                    companyList = recommendationModels;
                    generalCleaningAdapter.setData(recommendationModels);
                    activityGeneralCleaningBinding.rvGeneralCleaning.setAdapter(generalCleaningAdapter);
                    activityGeneralCleaningBinding.imgNoData.setVisibility(View.GONE);
                    activityGeneralCleaningBinding.tvNoData.setVisibility(View.GONE);
                } else {
                    activityGeneralCleaningBinding.rvGeneralCleaning.setVisibility(View.GONE);
                    activityGeneralCleaningBinding.imgNoData.setVisibility(View.VISIBLE);
                    activityGeneralCleaningBinding.tvNoData.setVisibility(View.VISIBLE);
                }
            }
        });

    }

    @Override
    public void onClickMessage(int adapterPosition) {

    }
}