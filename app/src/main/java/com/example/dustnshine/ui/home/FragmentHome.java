package com.example.dustnshine.ui.home;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import com.example.dustnshine.R;


import com.example.dustnshine.api.RetrofitClient;
import com.example.dustnshine.response.CompanyResponse;
import com.example.dustnshine.models.RecommendationModel;
import com.example.dustnshine.adapter.RecommendationAdapter;
import com.example.dustnshine.models.FeatureModel;
import com.example.dustnshine.storage.SharedPrefManager;
import com.example.dustnshine.ui.ActivityCompanyDetails;
import com.example.dustnshine.ui.ActivityManageAccount;
import com.example.dustnshine.ui.ActivityNotification;
import com.example.dustnshine.ui.ActivitySeeAllRecommendations;
//import com.example.dustnshine.ui.activities.ActivityManageAccount;
//import com.example.dustnshine.ui.activities.ActivityNotification;

import java.util.List;
import java.util.Observable;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;


public class FragmentHome extends Fragment implements RecommendationAdapter.OnClickMessageListener{

    ImageView manage;
    LinearLayout notifBtn;
    View view;
    private TextView viewAll;

    private RecyclerView recommendationRecycler, featureRecycler;
    private List<FeatureModel> featureModelList;
    private List<RecommendationModel> recommendationModelList;
    private FragmentHomeViewModel fragmentHomeViewModel;
    private RecommendationAdapter recommendationAdapter;
    private String userToken;

    public FragmentHome(){

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

        view = inflater.inflate(R.layout.fragment_home, container, false);

        manage = view.findViewById(R.id.manageAccButton);
        notifBtn = view.findViewById(R.id.notificationBtn);
        viewAll = view.findViewById(R.id.viewAll);
        recommendationRecycler = view.findViewById(R.id.companiesList);
        recommendationAdapter = new RecommendationAdapter(recommendationModelList, getContext(),this);
        userToken = SharedPrefManager.getInstance(getContext()).getUserToken();

        recommendationRecycler.setHasFixedSize(true);
        LinearLayoutManager layoutRecommendations = new LinearLayoutManager(requireContext(), LinearLayoutManager.HORIZONTAL, false);
        recommendationRecycler.setLayoutManager(layoutRecommendations);

        fragmentHomeViewModel = new ViewModelProvider(FragmentHome.this).get(FragmentHomeViewModel.class);
        getCompanyList(userToken);

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

        viewAll.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getContext(), ActivitySeeAllRecommendations.class);
                startActivity(intent);
            }
        });

        return view;

    }

    public void getCompanyList(String userToken){
        fragmentHomeViewModel.getCompaniesList(userToken).observe(getActivity(), new Observer<List<RecommendationModel>>() {
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
        Intent intent = new Intent(getActivity(), ActivityCompanyDetails.class);
        startActivity(intent);
    }
}
