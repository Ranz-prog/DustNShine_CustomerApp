package com.example.dustnshine.ui;

import android.content.Intent;
import android.media.Image;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;

import androidx.appcompat.app.AlertDialog;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import com.example.dustnshine.R;

import com.example.dustnshine.Models.recommendation_model;
import com.example.dustnshine.adapter.chat_adapter;
import com.example.dustnshine.adapter.recommendation_adapter;
import com.example.dustnshine.Models.feature_model;
import com.example.dustnshine.adapter.feature_adapter;

import java.util.ArrayList;
import java.util.List;


public class FragmentHome extends Fragment implements recommendation_adapter.OnClickMessageListener{

    ImageView manage;
    LinearLayout notifBtn;
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
        notifBtn = view.findViewById(R.id.notificationBtn);
        recommendationRecycler = view.findViewById(R.id.companiesList);


        recommendationRecycler.setHasFixedSize(true);

        LinearLayoutManager layoutRecommendations = new LinearLayoutManager(requireContext(), LinearLayoutManager.HORIZONTAL, false);
        LinearLayoutManager layoutFeature = new LinearLayoutManager(requireContext(), LinearLayoutManager.HORIZONTAL, false);
        recommendationRecycler.setLayoutManager(layoutRecommendations);

        recommendationRecycler.setAdapter(new recommendation_adapter(recommendationModel(),this));



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
        Intent intent = new Intent(getActivity(), ActivityCompanyDetails.class);
        startActivity(intent);
    }
}
