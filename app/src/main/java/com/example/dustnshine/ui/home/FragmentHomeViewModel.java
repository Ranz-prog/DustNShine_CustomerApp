package com.example.dustnshine.ui.home;

import androidx.lifecycle.ViewModel;

public class FragmentHomeViewModel extends ViewModel {

//    List<RecommendationModel> recommendationModelList;
//    public FragmentHomeViewModel() {
//        companyList = new MutableLiveData<>();
//    }
//    public MutableLiveData<List<RecommendationModel>> getCompanyListObserver(){
//        return companyList;
//    }
//
//    public void getCompanyRequest(){
//        Call<List<CompanyResponse>> companyResponseCall = RetrofitClient.getInstance().getApi().getCompanies();
//        companyResponseCall.enqueue(new Callback<List<CompanyResponse>>() {
//            @Override
//            public void onResponse(Call<List<CompanyResponse>> call, Response<List<CompanyResponse>> response) {
//                companyList.postValue(response.body());
//                Log.d("TAG", "Data found");
//            }
//
//            @Override
//            public void onFailure(Call<List<CompanyResponse>> call, Throwable t) {
//                Log.d("TAG", "No data found");
//            }
//        });
//    }
}
