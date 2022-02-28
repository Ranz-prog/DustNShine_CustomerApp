package com.example.dustnshine.ui.activities;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.Toast;

import com.example.dustnshine.R;
import com.example.dustnshine.adapter.RecommendationAdapter;
import com.example.dustnshine.api.RetrofitClient;
import com.example.dustnshine.models.RecommendationModel;
import com.example.dustnshine.response.CompanyResponse;
import com.example.dustnshine.storage.SharedPrefManager;
import com.example.dustnshine.ui.ActivityCompanyDetails;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class ActivitySeeAllRecommendations extends AppCompatActivity implements RecommendationAdapter.OnClickMessageListener{
    LinearLayout backBtn;
    RecommendationAdapter recommendationAdapter;

    private RecyclerView recommendationRecycler;
    private List<RecommendationModel> recommendationModelList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_see_all_recommendations);

        backBtn = findViewById(R.id.btnHome);

//        recommendationAdapter = new RecommendationAdapter(this);
        recommendationRecycler = findViewById(R.id.seeAllList);

        recommendationRecycler.setHasFixedSize(true);

        LinearLayoutManager layoutRecommendations = new LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false);
        recommendationRecycler.setLayoutManager(layoutRecommendations);

//        getAllCompanies();

        backBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });


        return;

    }
    private void getAllCompanies(){

        String userToken = SharedPrefManager.getInstance(ActivitySeeAllRecommendations.this).getUserToken();
        Call<CompanyResponse> companyList = RetrofitClient.getInstance().getApi().getCompanies("Bearer" + userToken);

        companyList.enqueue(new Callback<CompanyResponse>() {
            @Override
            public void onResponse(Call<CompanyResponse> call, Response<CompanyResponse> response) {

                if(response.isSuccessful()){

                    Log.e("sucess",response.body().toString());
                    List<RecommendationModel> recommendationResponses = response.body().getData();
                    recommendationAdapter.setData(recommendationResponses);
                    recommendationRecycler.setAdapter(recommendationAdapter);

                }
                else{
                    Toast.makeText(ActivitySeeAllRecommendations.this, "Failed", Toast.LENGTH_SHORT).show();
                }

            }

            @Override
            public void onFailure(Call<CompanyResponse> call, Throwable t) {
                Log.e("Juan",t.getLocalizedMessage());
            }
        });

    }

    @Override
    public void onClickMessage(int adapterPosition) {
        Intent intent = new Intent(this, ActivityCompanyDetails.class);
        startActivity(intent);
    }
}