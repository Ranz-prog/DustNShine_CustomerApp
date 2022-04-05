package com.example.dustnshine.ui.home;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;

import androidx.databinding.DataBindingUtil;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout;

import com.example.dustnshine.R;

import com.example.dustnshine.adapter.FeaturedServicesAdapter;
import com.example.dustnshine.databinding.FragmentHomeBinding;
import com.example.dustnshine.adapter.RecommendationAdapter;
import com.example.dustnshine.models.RecommendedCompaniesModel;
import com.example.dustnshine.models.ServicesModel;
import com.example.dustnshine.response.UserManagementResponse;
import com.example.dustnshine.storage.SharedPrefManager;
import com.example.dustnshine.ui.featured_services.FeaturedServicesActivity;
import com.example.dustnshine.ui.company_details.CompanyDetailsActivity;
import com.example.dustnshine.ui.manage_account.ManageAccountActivity;
import com.example.dustnshine.ui.notification.NotificationActivity;
import com.example.dustnshine.ui.recommendations.SeeAllRecommendationsActivity;

import java.util.List;

public class HomeFragment extends Fragment implements RecommendationAdapter.OnClickMessageListener, SwipeRefreshLayout.OnRefreshListener {

    private View view;
    private List<RecommendedCompaniesModel> recommendedCompaniesModelList;
    private HomeFragmentViewModel homeFragmentViewModel;
    private RecommendationAdapter recommendationAdapter;
    private String userToken;
    private FeaturedServicesAdapter featuredServicesAdapter;
    private List<ServicesModel> servicesModelList;
    private FragmentHomeBinding fragmentHomeBinding;
    private LinearLayoutManager layoutRecommendations;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

        fragmentHomeBinding = DataBindingUtil.inflate(inflater, R.layout.fragment_home, container, false);
        homeFragmentViewModel = new ViewModelProvider(HomeFragment.this).get(HomeFragmentViewModel.class);
        view = fragmentHomeBinding.getRoot();
        userToken = SharedPrefManager.getInstance(getContext()).getUserToken();
        recommendationAdapter = new RecommendationAdapter(recommendedCompaniesModelList, getContext(),this);
        featuredServicesAdapter = new FeaturedServicesAdapter(servicesModelList, getContext());
        fragmentHomeBinding.rvCompanyList.setHasFixedSize(true);
        layoutRecommendations = new LinearLayoutManager(requireContext(), LinearLayoutManager.HORIZONTAL, false);
        fragmentHomeBinding.rvCompanyList.setLayoutManager(layoutRecommendations);
        fragmentHomeBinding.refreshLayout.setOnRefreshListener(this);

        getRecommendedCompanyList(userToken);
        getFeaturedServices(userToken);
        getUserInformation(userToken);

        fragmentHomeBinding.searchBar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getContext(), SeeAllRecommendationsActivity.class);
                startActivity(intent);
            }
        });

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
                Intent intent = new Intent(getContext(), FeaturedServicesActivity.class);
                intent.putExtra("SERVICE_ID", servicesModelList.get(position).getId());
                intent.putExtra("SERVICE_NAME", servicesModelList.get(position).getName());
                startActivity(intent);
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
                } else if (userManagementResponse.getData().get(0).getAddress() == null ){
                    fragmentHomeBinding.tvCityMunicipality.setText("");
                    fragmentHomeBinding.tvAddress.setText("");
                } else {
                    fragmentHomeBinding.tvCityMunicipality.setText(userManagementResponse.getData().get(0).getAddress().getMunicipality());
                    fragmentHomeBinding.tvAddress.setText("#" + userManagementResponse.getData().get(0).getAddress().getHouse_number() + " " + userManagementResponse.getData().get(0).getAddress().getStreet() + ", "+ userManagementResponse.getData().get(0).getAddress().getBarangay() + ", " + userManagementResponse.getData().get(0).getAddress().getMunicipality());
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
                } else {

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

    @Override
    public void onRefresh() {
        getUserInformation(userToken);
        getRecommendedCompanyList(userToken);
        getFeaturedServices(userToken);
        fragmentHomeBinding.refreshLayout.setRefreshing(false);
    }
}
