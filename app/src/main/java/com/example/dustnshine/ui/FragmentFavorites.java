package com.example.dustnshine.ui;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.DividerItemDecoration;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;


import com.example.dustnshine.Models.favorite_model;
import com.example.dustnshine.Models.recommendation_model;
import com.example.dustnshine.R;

import com.example.dustnshine.adapter.favorite_adapter;
import com.example.dustnshine.adapter.recommendation_adapter;

import java.util.ArrayList;
import java.util.List;
public class FragmentFavorites extends Fragment implements favorite_adapter.OnClickMessageListener{

    private RecyclerView favoriteRecycler;
    private View view;
    private List<favorite_model> favoriteModelList;


    public FragmentFavorites(){

    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {


        view = inflater.inflate(R.layout.fragment_favorites,container,false);

        favoriteRecycler = view.findViewById(R.id.favoriteCompanyList);
        favoriteRecycler.setHasFixedSize(true);
        favoriteRecycler.setLayoutManager(new LinearLayoutManager(getContext()));



        favoriteRecycler.setAdapter(new favorite_adapter(favoriteModels(),this));



        return view;
    }

    private List<favorite_model> favoriteModels(){

        favoriteModelList = new ArrayList<>();

        favoriteModelList.add(new favorite_model(R.drawable.company1,
                "Clean Solutions","Dagupan City","5/5"));
        favoriteModelList.add(new favorite_model(R.drawable.company2,
                "Super Clean","Dagupan City","5/5"));

        return favoriteModelList;

    }
    @Override
    public void onClickMessage(int adapterPosition) {
        Intent intent = new Intent(getActivity(), ActivityCompanyDetails.class);
        startActivity(intent);
    }
}
