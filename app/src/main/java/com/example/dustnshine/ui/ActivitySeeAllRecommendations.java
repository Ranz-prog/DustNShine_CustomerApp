package com.example.dustnshine.ui;

import static android.view.ViewGroup.LayoutParams.MATCH_PARENT;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.SearchView;
import android.widget.Toast;

import com.example.dustnshine.R;
import com.example.dustnshine.adapter.RecommendationAdapter;
import com.example.dustnshine.adapter.SeeAllRecommendationsAdapter;
import com.example.dustnshine.api.RetrofitClient;
import com.example.dustnshine.models.RecommendationModel;
import com.example.dustnshine.models.SearchCompanyModel;
import com.example.dustnshine.response.CompanyResponse;
import com.example.dustnshine.response.SearchCompanyResponse;
import com.example.dustnshine.storage.SharedPrefManager;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class ActivitySeeAllRecommendations extends AppCompatActivity implements SeeAllRecommendationsAdapter.OnClickMessageListener{

    private LinearLayout backBtn, btnSearch;
    private RecyclerView recommendationRecycler;
    private List<RecommendationModel> recommendationModelList;
    private RecommendationAdapter recommendationAdapter;
    private SeeAllRecommendationsAdapter seeAllRecommendationsAdapter;
    private String userToken;
    private EditText searchView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_see_all_recommendations);

        backBtn = findViewById(R.id.btnHome);
        btnSearch = findViewById(R.id.btnSearch);
        searchView = findViewById(R.id.favCompanyTV);
        userToken = SharedPrefManager.getInstance(ActivitySeeAllRecommendations.this).getUserToken();
        recommendationRecycler = findViewById(R.id.seeAllList);
        seeAllRecommendationsAdapter = new SeeAllRecommendationsAdapter(recommendationModelList, ActivitySeeAllRecommendations.this, this);

        recommendationRecycler.setHasFixedSize(true);
        LinearLayoutManager layoutRecommendations = new LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false);
        recommendationRecycler.setLayoutManager(layoutRecommendations);

        backBtn.setOnClickListener(new View.OnClickListener() {
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

        return;

    }

    private void getSearchCompany(String companyName){

        Call<SearchCompanyResponse> searchCompanyResponseCall = RetrofitClient.getInstance().getApi().getSearchedCompany(companyName, "Bearer " + userToken);
        searchCompanyResponseCall.enqueue(new Callback<SearchCompanyResponse>() {
            @Override
            public void onResponse(Call<SearchCompanyResponse> call, Response<SearchCompanyResponse> response) {
                if(response.code() == 200){
                    List<RecommendationModel> recommendationResponses = response.body().getData();
                    seeAllRecommendationsAdapter.setData(recommendationResponses);
                    recommendationRecycler.setAdapter(seeAllRecommendationsAdapter);
                } else {
                    Toast.makeText(ActivitySeeAllRecommendations.this, "Failed", Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onFailure(Call<SearchCompanyResponse> call, Throwable t) {
                Log.e("Juan",t.getLocalizedMessage());

            }
        });

//        Call<CompanyResponse> companyList = RetrofitClient.getInstance().getApi().getCompanies("Bearer " + userToken);
//
//        companyList.enqueue(new Callback<CompanyResponse>() {
//            @Override
//            public void onResponse(Call<CompanyResponse> call, Response<CompanyResponse> response) {
//
//                if(response.isSuccessful()){
//                    Log.e("sucess",response.body().toString());
//                    List<RecommendationModel> recommendationResponses = response.body().getData();
//                    recommendationAdapter.setData(recommendationResponses);
//                    recommendationRecycler.setAdapter(recommendationAdapter);
//                }
//                else{
//                    Toast.makeText(ActivitySeeAllRecommendations.this, "Failed", Toast.LENGTH_SHORT).show();
//                }
//            }
//
//            @Override
//            public void onFailure(Call<CompanyResponse> call, Throwable t) {
//                Log.e("Juan",t.getLocalizedMessage());
//            }
//        });

    }

    @Override
    public void onClickMessage(int adapterPosition) {
        Intent intent = new Intent(this, ActivityCompanyDetails.class);
        startActivity(intent);
    }
}