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

import com.example.dustnshine.Models.booking_model;
import com.example.dustnshine.R;
import com.example.dustnshine.adapter.booking_adapter;

import java.util.ArrayList;
import java.util.List;

public class FragmentBooking extends Fragment {
    private RecyclerView bookingRecycler;
    private View view;
    private List<booking_model> bookingModelList;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {


        view = inflater.inflate(R.layout.fragment_booking,container,false);

        bookingRecycler = view.findViewById(R.id.bookingList);
        bookingRecycler.setHasFixedSize(true);
        bookingRecycler.setLayoutManager(new LinearLayoutManager(getContext()));



        bookingRecycler.setAdapter(new booking_adapter(bookingModels()));

        RecyclerView.ItemDecoration divider = new DividerItemDecoration(getContext(), DividerItemDecoration.VERTICAL);
        bookingRecycler.addItemDecoration(divider);

        return view;
    }

    private List<booking_model> bookingModels(){

        bookingModelList = new ArrayList<>();

        bookingModelList.add(new booking_model(R.drawable.user,
                "Juan Dela Cruz", "Lorem ipsum, lorem","09465886972"));
        bookingModelList.add(new booking_model(R.drawable.user,
                "Ivan Dasigan", "Lorem ipsum, lorem","09568556314"));

        return bookingModelList;



    }
}