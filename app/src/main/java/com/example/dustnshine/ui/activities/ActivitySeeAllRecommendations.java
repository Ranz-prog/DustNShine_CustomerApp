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
<<<<<<< HEAD

import com.example.dustnshine.adapter.RecommendationAdapter;
import com.example.dustnshine.models.RecommendationModel;
import com.example.dustnshine.ui.activities.ActivityManageAccount;
import com.example.dustnshine.ui.activities.ActivityNotification;
=======
import com.example.dustnshine.adapter.RecommendationAdapter;
import com.example.dustnshine.api.RetrofitClient;
import com.example.dustnshine.models.CompanyResponse;
import com.example.dustnshine.models.RecommendationModel;
>>>>>>> branch_jericho

import java.util.ArrayList;
import java.util.List;

<<<<<<< HEAD
=======
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

>>>>>>> branch_jericho
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

        recommendationAdapter = new RecommendationAdapter(this);
        recommendationRecycler = findViewById(R.id.seeAllList);

        recommendationRecycler.setHasFixedSize(true);

        LinearLayoutManager layoutRecommendations = new LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false);
        recommendationRecycler.setLayoutManager(layoutRecommendations);

<<<<<<< HEAD
        recommendationRecycler.setAdapter(new RecommendationAdapter(this));
=======
        getAllCompanies();
>>>>>>> branch_jericho

        backBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });


        return;

    }
    private void getAllCompanies(){

        Call<CompanyResponse> companyList = RetrofitClient.getInstance().getApi().getAllCompanies();

        companyList.enqueue(new Callback<CompanyResponse>() {
            @Override
            public void onResponse(Call<CompanyResponse> call, Response<CompanyResponse> response) {

<<<<<<< HEAD
    private List<RecommendationModel> recommendationModel(){
=======
                if(response.isSuccessful()){
>>>>>>> branch_jericho

                    Log.e("sucess",response.body().toString());
                    List<RecommendationModel> recommendationResponses = response.body().getData();
                    recommendationAdapter.setData(recommendationResponses);
                    recommendationRecycler.setAdapter(recommendationAdapter);

<<<<<<< HEAD
        recommendationModelList.add(new RecommendationModel(R.drawable.company1,
                "Clean Solutions","Dagupan City","5/5"));
        recommendationModelList.add(new RecommendationModel(R.drawable.company2,
                "Super Clean","Dagupan City","5/5"));
        recommendationModelList.add(new RecommendationModel(R.drawable.company1,
                "Clean Solutions","Dagupan City","5/5"));
        recommendationModelList.add(new RecommendationModel(R.drawable.company2,
                "Super Clean","Dagupan City","5/5"));
=======
                }
                else{
                    Toast.makeText(ActivitySeeAllRecommendations.this, "Failed", Toast.LENGTH_SHORT).show();
                }
>>>>>>> branch_jericho

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