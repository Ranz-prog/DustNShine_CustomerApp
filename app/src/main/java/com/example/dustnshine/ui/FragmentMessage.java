package com.example.dustnshine.ui;

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

import com.example.dustnshine.Models.chat_model;
import com.example.dustnshine.R;
import com.example.dustnshine.adapter.chat_adapter;

import java.util.ArrayList;
import java.util.List;

public class FragmentMessage extends Fragment {

    private RecyclerView chatRecycler;
    private View view;
    private List<chat_model> chatModelList;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {


        view = inflater.inflate(R.layout.fragment_message,container,false);

        chatRecycler = view.findViewById(R.id.chat_RecyclerView);
        chatRecycler.setHasFixedSize(true);
        chatRecycler.setLayoutManager(new LinearLayoutManager(getContext()));



        chatRecycler.setAdapter(new chat_adapter(chatModels()));

        RecyclerView.ItemDecoration divider = new DividerItemDecoration(getContext(), DividerItemDecoration.VERTICAL);
        chatRecycler.addItemDecoration(divider);

        return view;
    }

    private List<chat_model> chatModels(){

        chatModelList = new ArrayList<>();

        chatModelList.add(new chat_model(R.drawable.user,
                "Juan Dela Cruz", "Lorem ipsum, lorem ipsum","11:59"));
        chatModelList.add(new chat_model(R.drawable.user,
                "Ivan Dasigan", "Lorem ipsum, lorem ipsum","11:59"));

        return chatModelList;



    }
}
