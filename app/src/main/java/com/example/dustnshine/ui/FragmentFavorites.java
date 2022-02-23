package com.example.dustnshine.ui;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;


import com.example.dustnshine.models.FavoriteModel;
import com.example.dustnshine.R;

import com.example.dustnshine.adapter.FavoriteAdapter;
import com.example.dustnshine.ui.company_details.ActivityCompanyDetails;

import java.util.ArrayList;
import java.util.List;
public class FragmentFavorites extends Fragment implements FavoriteAdapter.OnClickMessageListener{

    private RecyclerView favoriteRecycler;
    private View view;
    private List<FavoriteModel> favoriteModelList;


    public FragmentFavorites(){

    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {


        view = inflater.inflate(R.layout.fragment_favorites,container,false);

        favoriteRecycler = view.findViewById(R.id.favoriteCompanyList);
        favoriteRecycler.setHasFixedSize(true);
        favoriteRecycler.setLayoutManager(new LinearLayoutManager(getContext()));



        favoriteRecycler.setAdapter(new FavoriteAdapter(favoriteModels(),this));



        return view;
    }

    private List<FavoriteModel> favoriteModels(){

        favoriteModelList = new ArrayList<>();

        favoriteModelList.add(new FavoriteModel(R.drawable.company1,
                "Clean Solutions","Dagupan City","5/5"));
        favoriteModelList.add(new FavoriteModel(R.drawable.company2,
                "Super Clean","Dagupan City","5/5"));

        return favoriteModelList;

    }
    @Override
    public void onClickMessage(int adapterPosition) {
        Intent intent = new Intent(getActivity(), ActivityCompanyDetails.class);
        startActivity(intent);
    }
}
