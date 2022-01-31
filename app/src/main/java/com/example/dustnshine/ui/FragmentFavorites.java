package com.example.dustnshine.ui;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;


import com.example.dustnshine.models.favorite_model;
import com.example.dustnshine.R;

import com.example.dustnshine.adapter.favorite_adapter;

import java.util.ArrayList;
import java.util.List;
public class FragmentFavorites extends Fragment {

    private RecyclerView favoriteRecycler;
    private View view;
    private List<favorite_model> favoriteModelList;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {


        view = inflater.inflate(R.layout.fragment_favorites,container,false);

        favoriteRecycler = view.findViewById(R.id.favoriteCompanyList);
        favoriteRecycler.setHasFixedSize(true);
        favoriteRecycler.setLayoutManager(new LinearLayoutManager(getContext()));



        favoriteRecycler.setAdapter(new favorite_adapter(favoriteModels()));



        return view;
    }

    private List<favorite_model> favoriteModels(){

        favoriteModelList = new ArrayList<>();

        favoriteModelList.add(new favorite_model(R.drawable.company1,
                "Clean Solutions","Dagupan City","5/5"));
        favoriteModelList.add(new favorite_model(R.drawable.company2,
                "Super Clean","Dagupan City","5/5"));
        favoriteModelList.add(new favorite_model(R.drawable.company1,
                "Clean Solutions","Dagupan City","5/5"));
        favoriteModelList.add(new favorite_model(R.drawable.company2,
                "Super Clean","Dagupan City","5/5"));
        favoriteModelList.add(new favorite_model(R.drawable.company1,
                "Clean Solutions","Dagupan City","5/5"));
        favoriteModelList.add(new favorite_model(R.drawable.company2,
                "Super Clean","Dagupan City","5/5"));

        return favoriteModelList;



    }
}
