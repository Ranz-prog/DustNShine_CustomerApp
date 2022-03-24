package com.example.dustnshine.ui.home;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.GridView;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.databinding.DataBindingUtil;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import com.example.dustnshine.R;


import com.example.dustnshine.adapter.FeaturedServicesAdapter;
import com.example.dustnshine.databinding.FragmentBookingBinding;
import com.example.dustnshine.databinding.FragmentHomeBinding;
import com.example.dustnshine.models.AddressModel;
import com.example.dustnshine.models.RecommendationModel;
import com.example.dustnshine.adapter.RecommendationAdapter;
import com.example.dustnshine.models.FeatureModel;
import com.example.dustnshine.models.RecommendedCompaniesModel;
import com.example.dustnshine.models.ServicesModel;
import com.example.dustnshine.response.UserManagementResponse;
import com.example.dustnshine.storage.SharedPrefManager;
import com.example.dustnshine.ui.feedback.FeedbackActivity;
import com.example.dustnshine.ui.garage_cleaning.GarageCleaningActivity;
import com.example.dustnshine.ui.general_cleaning.GeneralCleaningActivity;
import com.example.dustnshine.ui.company_details.CompanyDetailsActivity;
import com.example.dustnshine.ui.manage_account.ManageAccountActivity;
import com.example.dustnshine.ui.notification.NotificationActivity;
import com.example.dustnshine.ui.recommendations.SeeAllRecommendationsActivity;
//import com.example.dustnshine.ui.activities.ActivityManageAccount;
//import com.example.dustnshine.ui.activities.ActivityNotification;

import java.util.List;


public class HomeFragment extends Fragment implements RecommendationAdapter.OnClickMessageListener{

    private View view;
    private List<RecommendedCompaniesModel> recommendedCompaniesModelList;
    private HomeFragmentViewModel homeFragmentViewModel;
    private RecommendationAdapter recommendationAdapter;
    private String userToken;
    private FeaturedServicesAdapter featuredServicesAdapter;
    private List<ServicesModel> servicesModelList;
    private FragmentHomeBinding fragmentHomeBinding;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

        fragmentHomeBinding = DataBindingUtil.inflate(inflater, R.layout.fragment_home, container, false);
        homeFragmentViewModel = new ViewModelProvider(HomeFragment.this).get(HomeFragmentViewModel.class);
        view = fragmentHomeBinding.getRoot();
        recommendationAdapter = new RecommendationAdapter(recommendedCompaniesModelList, getContext(),this);
        featuredServicesAdapter = new FeaturedServicesAdapter(servicesModelList, getContext());
        userToken = SharedPrefManager.getInstance(getContext()).getUserToken();
        getUserInformation(userToken);

        fragmentHomeBinding.rvCompanyList.setHasFixedSize(true);
        LinearLayoutManager layoutRecommendations = new LinearLayoutManager(requireContext(), LinearLayoutManager.HORIZONTAL, false);
        fragmentHomeBinding.rvCompanyList.setLayoutManager(layoutRecommendations);

        getRecommendedCompanyList(userToken);
        getFeaturedServices(userToken);

        fragmentHomeBinding.btnManageAccount.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getContext(), ManageAccountActivity.class);
                startActivity(intent);
            }
        });

        fragmentHomeBinding.btnNotification.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getContext(), NotificationActivity.class);
                startActivity(intent);
            }
        });

        fragmentHomeBinding.btnShowAll.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getContext(), SeeAllRecommendationsActivity.class);
                startActivity(intent);
            }
        });

        fragmentHomeBinding.gvFeaturedServices.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int position, long l) {
                if (position == 0){
                    Intent intent = new Intent(getContext(), GeneralCleaningActivity.class);
                    startActivity(intent);
                } else if (position == 2){
                    Intent intent = new Intent(getContext(), GarageCleaningActivity.class);
                    startActivity(intent);
                }
            }
        });

        return view;

    }

    private void getRecommendedCompanyList(String userToken){
        homeFragmentViewModel.getRecommendedCompaniesList(userToken).observe(getActivity(), new Observer<List<RecommendedCompaniesModel>>() {
            @Override
            public void onChanged(List<RecommendedCompaniesModel> recommendationModels) {
                if (recommendationModels != null) {
                    recommendedCompaniesModelList = recommendationModels;
                    recommendationAdapter.setData(recommendationModels);
                    fragmentHomeBinding.rvCompanyList.setAdapter(recommendationAdapter);
                }
            }
        });
    }

    private void getUserInformation(String userToken){
        homeFragmentViewModel.getUserInformationRequest(userToken).observe(getActivity(), new Observer<UserManagementResponse>() {
            @Override
            public void onChanged(UserManagementResponse userManagementResponse) {
                if (userManagementResponse == null){
                } else if (userManagementResponse.getData().get(0).getAddress().isEmpty() || userManagementResponse.getData().get(0).getAddress() == null ){
                    fragmentHomeBinding.tvCityMunicipality.setText("");
                    fragmentHomeBinding.tvAddress.setText("");
                } else {
                    fragmentHomeBinding.tvCityMunicipality.setText(userManagementResponse.getData().get(0).getAddress().get(0).getMunicipality());
                    fragmentHomeBinding.tvAddress.setText(userManagementResponse.getData().get(0).getAddress().get(0).getHouse_number() + " " + userManagementResponse.getData().get(0).getAddress().get(0).getStreet() + " "+ userManagementResponse.getData().get(0).getAddress().get(0).getBarangay() + " " + userManagementResponse.getData().get(0).getAddress().get(0).getMunicipality());
                }
            }
        });
    }

    private void getFeaturedServices(String userToken) {
        homeFragmentViewModel.getServicesList(userToken).observe(getActivity(), new Observer<List<ServicesModel>>() {
            @Override
            public void onChanged(List<ServicesModel> servicesModels) {
                if (servicesModels != null) {
                    servicesModelList = servicesModels;
                    featuredServicesAdapter.setData(servicesModels);
                    fragmentHomeBinding.gvFeaturedServices.setAdapter(featuredServicesAdapter);
                    Log.d("TAG", "Success");
                } else {
                    Log.d("TAG", "Failure");
                }
            }
        });
    }

    @Override
    public void onClickMessage(int adapterPosition) {
        Intent intent = new Intent(getActivity(), CompanyDetailsActivity.class);
        intent.putExtra("COMPANY_ID", recommendedCompaniesModelList.get(adapterPosition).getCompany().getId());
        intent.putExtra("COMPANY_NAME", recommendedCompaniesModelList.get(adapterPosition).getCompany().getName());
        intent.putExtra("COMPANY_ADDRESS", recommendedCompaniesModelList.get(adapterPosition).getCompany().getAddress());
        intent.putExtra("COMPANY_IMAGE", recommendedCompaniesModelList.get(adapterPosition).getCompany().getCompany_image());
        intent.putExtra("COMPANY_RATING", recommendedCompaniesModelList.get(adapterPosition).getRating());
        intent.putExtra("COMPANY_EMAIL", recommendedCompaniesModelList.get(adapterPosition).getCompany().getEmail());
        intent.putExtra("COMPANY_MOBILE", recommendedCompaniesModelList.get(adapterPosition).getCompany().getMobile_number());
        intent.putExtra("COMPANY_TELEPHONE", recommendedCompaniesModelList.get(adapterPosition).getCompany().getTel_number());
        startActivity(intent);
    }
}
