package com.example.dustnshine.ui;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import com.example.dustnshine.R;

import com.example.dustnshine.Models.recommendation_model;
import com.example.dustnshine.adapter.recommendation_adapter;
import com.example.dustnshine.Models.feature_model;
import com.example.dustnshine.adapter.feature_adapter;

import java.util.ArrayList;
import java.util.List;

public class FragmentHome extends Fragment {
    Button manage;
    View view;

    private RecyclerView recommendationRecycler,featureRecycler;
    private List<feature_model> featureModelList;
    private List<recommendation_model> recommendationModelList;

    public FragmentHome(){

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

        view = inflater.inflate(R.layout.fragment_home, container, false);

        manage = view.findViewById(R.id.manageAccButton);
        recommendationRecycler = view.findViewById(R.id.companiesList);
        featureRecycler = view.findViewById(R.id.featuredList);

        recommendationRecycler.setHasFixedSize(true);
        featureRecycler.setHasFixedSize(true);

        LinearLayoutManager layoutRecommendations = new LinearLayoutManager(requireContext(), LinearLayoutManager.HORIZONTAL, false);
        LinearLayoutManager layoutFeature = new LinearLayoutManager(requireContext(), LinearLayoutManager.HORIZONTAL, false);
        recommendationRecycler.setLayoutManager(layoutRecommendations);
        recommendationRecycler.setAdapter(new recommendation_adapter(recommendationModel()));

        featureRecycler.setLayoutManager(layoutFeature);
        featureRecycler.setAdapter(new feature_adapter(featureModel()));


        manage.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getContext(), ActivityManageAccount.class);
                startActivity(intent);
            }
        });

        return view;

    }

    private List<recommendation_model> recommendationModel(){

        recommendationModelList = new ArrayList<>();

        recommendationModelList.add(new recommendation_model(R.drawable.company1,
                "Clean Solutions"));
        recommendationModelList.add(new recommendation_model(R.drawable.company2,
                "Super Clean"));
        recommendationModelList.add(new recommendation_model(R.drawable.company1,
                "Clean Solutions"));
        recommendationModelList.add(new recommendation_model(R.drawable.company2,
                "Super Clean"));

        return recommendationModelList;

    }


    private List<feature_model> featureModel(){

        featureModelList = new ArrayList<>();

        featureModelList.add(new feature_model(R.drawable.home_cleaning,
                "House Cleaning","cleaning"));
        featureModelList.add(new feature_model(R.drawable.garden_cleaning,
                "Gardening","pick up weeds"));
        featureModelList.add(new feature_model(R.drawable.garage_cleaning,
                "Garage Cleaning","cleaning"));
        featureModelList.add(new feature_model(R.drawable.repair,
                "Appliance repair","repair"));


        return featureModelList;

    }


}
