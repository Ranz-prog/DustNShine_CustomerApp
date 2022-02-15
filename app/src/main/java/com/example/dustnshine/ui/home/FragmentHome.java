package com.example.dustnshine.ui.home;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;

import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import com.example.dustnshine.R;


import com.example.dustnshine.models.CompanyResponse;
import com.example.dustnshine.models.RecommendationModel;
import com.example.dustnshine.adapter.RecommendationAdapter;
import com.example.dustnshine.models.FeatureModel;
import com.example.dustnshine.ui.ActivityCompanyDetails;
import com.example.dustnshine.ui.ActivityManageAccount;
import com.example.dustnshine.ui.ActivityNotification;

import java.util.ArrayList;
import java.util.List;


public class FragmentHome extends Fragment implements RecommendationAdapter.OnClickMessageListener{

    ImageView manage;
    LinearLayout notifBtn;
    View view;

    private RecyclerView recommendationRecycler, featureRecycler;
    private List<FeatureModel> featureModelList;
    private List<RecommendationModel> recommendationModelList;
    private List<CompanyResponse> companyResponses;
    private FragmentHomeViewModel fragmentHomeViewModel;
    private RecommendationAdapter recommendationAdapter;

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
        recommendationRecycler.setAdapter(new RecommendationAdapter(recommendationModel(), this));

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
