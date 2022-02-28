package com.example.dustnshine.ui.home;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import com.example.dustnshine.R;


import com.example.dustnshine.models.RecommendationModel;
import com.example.dustnshine.adapter.RecommendationAdapter;
import com.example.dustnshine.models.FeatureModel;
import com.example.dustnshine.storage.SharedPrefManager;
import com.example.dustnshine.ui.GarageCleaningActivity;
import com.example.dustnshine.ui.GeneralCleaningActivity;
import com.example.dustnshine.ui.company_details.CompanyDetailsActivity;
import com.example.dustnshine.ui.manage_account.ManageAccountActivity;
import com.example.dustnshine.ui.NotificationActivity;
import com.example.dustnshine.ui.recommendations.SeeAllRecommendationsActivity;
//import com.example.dustnshine.ui.activities.ActivityManageAccount;
//import com.example.dustnshine.ui.activities.ActivityNotification;

import java.util.List;


public class HomeFragment extends Fragment implements RecommendationAdapter.OnClickMessageListener{

    ImageView manage;
    LinearLayout notifBtn, generalCleaning, garageCleaning;
    View view;
    private TextView viewAll;

    private RecyclerView recommendationRecycler, featureRecycler;
    private List<FeatureModel> featureModelList;
    private List<RecommendationModel> recommendationModelList;
    private HomeFragmentViewModel homeFragmentViewModel;
    private RecommendationAdapter recommendationAdapter;
    private String userToken;
    private CompanyDetailsActivity companyDetailsActivity;


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

        view = inflater.inflate(R.layout.fragment_home, container, false);

        manage = view.findViewById(R.id.manageAccButton);
        notifBtn = view.findViewById(R.id.notificationBtn);
        generalCleaning = view.findViewById(R.id.generalCleaning);
        garageCleaning = view.findViewById(R.id.garageCleaning);
        viewAll = view.findViewById(R.id.viewAll);
        recommendationRecycler = view.findViewById(R.id.companiesList);
        recommendationAdapter = new RecommendationAdapter(recommendationModelList, getContext(),this);
        userToken = SharedPrefManager.getInstance(getContext()).getUserToken();

        recommendationRecycler.setHasFixedSize(true);
        LinearLayoutManager layoutRecommendations = new LinearLayoutManager(requireContext(), LinearLayoutManager.HORIZONTAL, false);
        recommendationRecycler.setLayoutManager(layoutRecommendations);

        homeFragmentViewModel = new ViewModelProvider(HomeFragment.this).get(HomeFragmentViewModel.class);
        getCompanyList(userToken);

        manage.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getContext(), ManageAccountActivity.class);
                startActivity(intent);
            }
        });

        notifBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getContext(), NotificationActivity.class);
                startActivity(intent);
            }
        });

        viewAll.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getContext(), SeeAllRecommendationsActivity.class);
                startActivity(intent);
            }
        });

        generalCleaning.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getContext(), GeneralCleaningActivity.class);
                startActivity(intent);
            }
        });

        garageCleaning.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getContext(), GarageCleaningActivity.class);
                startActivity(intent);
            }
        });

        return view;

    }

    public void getCompanyList(String userToken){
        homeFragmentViewModel.getCompaniesList(userToken).observe(getActivity(), new Observer<List<RecommendationModel>>() {
            @Override
            public void onChanged(List<RecommendationModel> recommendationModels) {
                if (recommendationModels != null) {
                    recommendationModelList = recommendationModels;
                    recommendationAdapter.setData(recommendationModels);
                    recommendationRecycler.setAdapter(recommendationAdapter);
                }
            }
        });
    }

    @Override
    public void onClickMessage(int adapterPosition) {
        Intent intent = new Intent(getActivity(), CompanyDetailsActivity.class);
        intent.putExtra("COMPANY_ID", recommendationModelList.get(adapterPosition).getId());
        intent.putExtra("COMPANY_NAME", recommendationModelList.get(adapterPosition).getName());
        intent.putExtra("COMPANY_ADDRESS", recommendationModelList.get(adapterPosition).getAddress());
        startActivity(intent);
    }
}
