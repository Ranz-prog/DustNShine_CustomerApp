package com.example.dustnshine.ui.activities;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.LinearLayout;

import com.example.dustnshine.R;
import com.example.dustnshine.adapter.RecommendationAdapter;
import com.example.dustnshine.models.RecommendationModel;

import java.util.ArrayList;
import java.util.List;

public class ActivitySeeAllRecommendations extends AppCompatActivity implements RecommendationAdapter.OnClickMessageListener{
    LinearLayout backBtn;

    private RecyclerView recommendationRecycler;
    private List<RecommendationModel> recommendationModelList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_see_all_recommendations);

        backBtn = findViewById(R.id.btnHome);

        recommendationRecycler = findViewById(R.id.seeAllList);

        recommendationRecycler.setHasFixedSize(true);

        LinearLayoutManager layoutRecommendations = new LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false);
        recommendationRecycler.setLayoutManager(layoutRecommendations);

        recommendationRecycler.setAdapter(new RecommendationAdapter(recommendationModel(),this));

        backBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });


        return;

    }

    private List<RecommendationModel> recommendationModel(){

        recommendationModelList = new ArrayList<>();

        recommendationModelList.add(new RecommendationModel(R.drawable.company1,
                "Clean Solutions","Dagupan City","5/5"));
        recommendationModelList.add(new RecommendationModel(R.drawable.company2,
                "Super Clean","Dagupan City","5/5"));
        recommendationModelList.add(new RecommendationModel(R.drawable.company1,
                "Clean Solutions","Dagupan City","5/5"));
        recommendationModelList.add(new RecommendationModel(R.drawable.company2,
                "Super Clean","Dagupan City","5/5"));

        return recommendationModelList;

    }

    @Override
    public void onClickMessage(int adapterPosition) {
        Intent intent = new Intent(this, ActivityCompanyDetails.class);
        startActivity(intent);
    }
}