package com.example.dustnshine.ui.activities;

import static android.view.ViewGroup.LayoutParams.MATCH_PARENT;
import static android.view.ViewGroup.LayoutParams.WRAP_CONTENT;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.ViewGroup;
import android.widget.FrameLayout;
import android.widget.LinearLayout;

import com.example.dustnshine.R;
import com.example.dustnshine.adapter.recommendation_adapter;
import com.example.dustnshine.models.feature_model;
import com.example.dustnshine.models.recommendation_model;
import com.example.dustnshine.ui.activities.ActivityCompanyDetails;
import com.example.dustnshine.ui.activities.ActivityManageAccount;
import com.example.dustnshine.ui.activities.ActivityNotification;

import java.util.ArrayList;
import java.util.List;

public class ActivitySeeAllRecommendations extends AppCompatActivity implements recommendation_adapter.OnClickMessageListener{
    LinearLayout backBtn;

    private RecyclerView recommendationRecycler;
    private List<recommendation_model> recommendationModelList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_see_all_recommendations);

        backBtn = findViewById(R.id.btnHome);

        recommendationRecycler = findViewById(R.id.seeAllList);

        recommendationRecycler.setHasFixedSize(true);

        LinearLayoutManager layoutRecommendations = new LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false);
        recommendationRecycler.setLayoutManager(layoutRecommendations);

        recommendationRecycler.setAdapter(new recommendation_adapter(recommendationModel(),this));

        backBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });


        return;

    }

    private List<recommendation_model> recommendationModel(){

        recommendationModelList = new ArrayList<>();

        recommendationModelList.add(new recommendation_model(R.drawable.company1,
                "Clean Solutions","Dagupan City","5/5"));
        recommendationModelList.add(new recommendation_model(R.drawable.company2,
                "Super Clean","Dagupan City","5/5"));
        recommendationModelList.add(new recommendation_model(R.drawable.company1,
                "Clean Solutions","Dagupan City","5/5"));
        recommendationModelList.add(new recommendation_model(R.drawable.company2,
                "Super Clean","Dagupan City","5/5"));

        return recommendationModelList;

    }

    @Override
    public void onClickMessage(int adapterPosition) {
        Intent intent = new Intent(this, ActivityCompanyDetails.class);
        startActivity(intent);
    }
}