package com.example.dustnshine.ui.fragments;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import com.example.dustnshine.R;


import com.example.dustnshine.models.RecommendationModel;
import com.example.dustnshine.adapter.RecommendationAdapter;
import com.example.dustnshine.models.FeatureModel;
import com.example.dustnshine.ui.activities.ActivitySeeAllRecommendations;
import com.example.dustnshine.ui.activities.ActivityCompanyDetails;
import com.example.dustnshine.ui.activities.ActivityManageAccount;
import com.example.dustnshine.ui.activities.ActivityNotification;

import java.util.ArrayList;
import java.util.List;


public class FragmentHome extends Fragment implements RecommendationAdapter.OnClickMessageListener{

    ImageView manage;
    LinearLayout notifBtn;
    View view;
    TextView seeAll;

    private RecyclerView recommendationRecycler,featureRecycler;
    private List<FeatureModel> featureModelList;
    private List<RecommendationModel> recommendationModelList;

    public FragmentHome(){

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

        view = inflater.inflate(R.layout.fragment_home, container, false);

        manage = view.findViewById(R.id.manageAccButton);
        notifBtn = view.findViewById(R.id.notificationBtn);
        recommendationRecycler = view.findViewById(R.id.companiesList);

        seeAll = view.findViewById(R.id.viewAll);


        recommendationRecycler.setHasFixedSize(true);

        LinearLayoutManager layoutRecommendations = new LinearLayoutManager(requireContext(), LinearLayoutManager.HORIZONTAL, false);
        LinearLayoutManager layoutFeature = new LinearLayoutManager(requireContext(), LinearLayoutManager.HORIZONTAL, false);
        recommendationRecycler.setLayoutManager(layoutRecommendations);

        recommendationRecycler.setAdapter(new RecommendationAdapter(recommendationModel(),this));

        seeAll.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getContext(), ActivitySeeAllRecommendations.class);
                startActivity(intent);
            }
        });

        manage.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent intent = new Intent(getContext(), ActivityManageAccount.class);
                startActivity(intent);
            }
        });

        notifBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getContext(), ActivityNotification.class);
                startActivity(intent);
            }
        });

        return view;

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
        Intent intent = new Intent(getActivity(), ActivityCompanyDetails.class);
        startActivity(intent);
    }
}
