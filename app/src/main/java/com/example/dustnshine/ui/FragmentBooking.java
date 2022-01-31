package com.example.dustnshine.ui;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.dustnshine.models.booking_model;
import com.example.dustnshine.R;
import com.example.dustnshine.adapter.booking_adapter;

import java.util.ArrayList;
import java.util.List;

public class FragmentBooking extends Fragment {
    private RecyclerView bookingRecycler;
    private View view;
    private List<booking_model> bookingModelList;
    Button historyBtn;

    public FragmentBooking(){
    }

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {


        view = inflater.inflate(R.layout.fragment_booking,container,false);

        historyBtn = view.findViewById(R.id.bookingHistoryBtn);

        bookingRecycler = view.findViewById(R.id.bookingList);
        bookingRecycler.setHasFixedSize(true);
        bookingRecycler.setLayoutManager(new LinearLayoutManager(getContext()));

        bookingRecycler.setAdapter(new booking_adapter(bookingModels()));

        historyBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getContext(), ActivityBookingHistory.class);
                startActivity(intent);

                
            }
        });

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