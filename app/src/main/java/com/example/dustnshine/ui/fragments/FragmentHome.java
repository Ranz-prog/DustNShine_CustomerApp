package com.example.dustnshine.ui.fragments;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import com.example.dustnshine.R;


import com.example.dustnshine.api.RetrofitClient;
import com.example.dustnshine.models.CompanyResponse;
import com.example.dustnshine.models.RecommendationModel;
import com.example.dustnshine.adapter.RecommendationAdapter;
import com.example.dustnshine.models.FeatureModel;
import com.example.dustnshine.models.ServiceResponse;
import com.example.dustnshine.models.ServicesModel;
import com.example.dustnshine.ui.activities.ActivitySeeAllRecommendations;
import com.example.dustnshine.ui.activities.ActivityCompanyDetails;
import com.example.dustnshine.ui.activities.ActivityManageAccount;
import com.example.dustnshine.ui.activities.ActivityNotification;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;


public class FragmentHome extends Fragment implements RecommendationAdapter.OnClickMessageListener{

    ImageView manage;
    LinearLayout notifBtn;
    View view;
    TextView seeAll;

    RecommendationAdapter recommendationAdapter;

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

        recommendationAdapter = new RecommendationAdapter(this);
        recommendationRecycler = view.findViewById(R.id.companiesList);

        seeAll = view.findViewById(R.id.viewAll);


        recommendationRecycler.setHasFixedSize(true);

        LinearLayoutManager layoutRecommendations = new LinearLayoutManager(requireContext(), LinearLayoutManager.HORIZONTAL, false);

        recommendationRecycler.setLayoutManager(layoutRecommendations);


        getAllCompanies();

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

    private void getAllCompanies(){

        Call<CompanyResponse> companyList = RetrofitClient.getInstance().getApi().getAllCompanies();

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
                    Toast.makeText(getContext(), "Failed", Toast.LENGTH_SHORT).show();
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
        Intent intent = new Intent(getActivity(), ActivityCompanyDetails.class);
        startActivity(intent);
    }
}
