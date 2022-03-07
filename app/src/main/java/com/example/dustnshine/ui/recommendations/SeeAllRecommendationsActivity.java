package com.example.dustnshine.ui.recommendations;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.Toast;

import com.example.dustnshine.R;
import com.example.dustnshine.adapter.SeeAllRecommendationsAdapter;
import com.example.dustnshine.api.RetrofitClient;
import com.example.dustnshine.databinding.ActivitySeeAllRecommendationsBinding;
import com.example.dustnshine.models.RecommendationModel;
import com.example.dustnshine.response.CompanyResponse;
import com.example.dustnshine.response.SearchCompanyResponse;
import com.example.dustnshine.storage.SharedPrefManager;
import com.example.dustnshine.ui.company_details.CompanyDetailsActivity;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class SeeAllRecommendationsActivity extends AppCompatActivity implements SeeAllRecommendationsAdapter.OnClickMessageListener{

    private LinearLayout  btnSearch;
    private ImageView btnBack;
    private RecyclerView rvRecommendations;
    private List<RecommendationModel> recommendationModelList;
    private SeeAllRecommendationsAdapter seeAllRecommendationsAdapter;
    private EditText searchView;
    private ActivitySeeAllRecommendationsBinding activitySeeAllRecommendationsBinding;
    private SeeAllRecommendationsViewModel seeAllRecommendationsViewModel;
    private LinearLayoutManager layoutRecommendations;
    private static String userToken;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

//        activitySeeAllRecommendationsBinding = DataBindingUtil.setContentView(this, R.layout.activity_see_all_recommendations);
        seeAllRecommendationsViewModel = new ViewModelProvider(SeeAllRecommendationsActivity.this).get(SeeAllRecommendationsViewModel.class);
        setContentView(R.layout.activity_see_all_recommendations);

        searchView = findViewById(R.id.edtSearchCompany);
        btnBack = findViewById(R.id.backSAR);
        btnSearch = findViewById(R.id.btnSearch);
        rvRecommendations = findViewById(R.id.rvRecommendations);
        userToken = SharedPrefManager.getInstance(SeeAllRecommendationsActivity.this).getUserToken();
        seeAllRecommendationsAdapter = new SeeAllRecommendationsAdapter(recommendationModelList, SeeAllRecommendationsActivity.this, SeeAllRecommendationsActivity.this);

        rvRecommendations.setHasFixedSize(true);
        layoutRecommendations = new LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false);
        rvRecommendations.setLayoutManager(layoutRecommendations);

        getCompanies();

        btnBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });

        btnSearch.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String query = searchView.getText().toString();
                getSearchCompany(query);
            }
        });

    }

//    private void getSearchCompany(String companyName, String userToken){
//
//        seeAllRecommendationsViewModel.getSearchedCompany(companyName, userToken).observe(this, new Observer<List<RecommendationModel>>() {
//            @Override
//            public void onChanged(List<RecommendationModel> recommendationModels) {
//                if(recommendationModels != null){
//                    recommendationModelList = recommendationModels;
//                    seeAllRecommendationsAdapter.setData(recommendationModels);
//                    recommendationRecycler.setAdapter(seeAllRecommendationsAdapter);
//                } else {
//                    Toast.makeText(ActivitySeeAllRecommendations.this, "No Company Found", Toast.LENGTH_SHORT).show();
//                }
//            }
//        });
//
//    }

    private void getCompanies(){
        if (searchView.getText().toString().isEmpty()){
            Call<CompanyResponse> companyResponseCall = RetrofitClient.getInstance().getApi().getCompanies("Bearer " + userToken);
            companyResponseCall.enqueue(new Callback<CompanyResponse>() {
                @Override
                public void onResponse(Call<CompanyResponse> call, Response<CompanyResponse> response) {
                    recommendationModelList = response.body().getData();
                    seeAllRecommendationsAdapter.setData(recommendationModelList);
                    rvRecommendations.setAdapter(seeAllRecommendationsAdapter);
                }

                @Override
                public void onFailure(Call<CompanyResponse> call, Throwable t) {

                }
            });
        }
    }

    private void getSearchCompany(String companyName){

        Call<SearchCompanyResponse> searchCompanyResponseCall = RetrofitClient.getInstance().getApi().getSearchedCompany(companyName, "Bearer " + userToken);
        searchCompanyResponseCall.enqueue(new Callback<SearchCompanyResponse>() {
            @Override
            public void onResponse(Call<SearchCompanyResponse> call, Response<SearchCompanyResponse> response) {
                if(response.code() == 200){
                    recommendationModelList = response.body().getData();
                    seeAllRecommendationsAdapter.setData(recommendationModelList);
                    rvRecommendations.setAdapter(seeAllRecommendationsAdapter);
                } else {
                    Toast.makeText(SeeAllRecommendationsActivity.this, "Failed", Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onFailure(Call<SearchCompanyResponse> call, Throwable t) {
                Log.e("TAG",t.getLocalizedMessage());

            }
        });

    }

    @Override
    public void onClickMessage(int adapterPosition) {
        Intent intent = new Intent(this, CompanyDetailsActivity.class);
        startActivity(intent);
    }
}