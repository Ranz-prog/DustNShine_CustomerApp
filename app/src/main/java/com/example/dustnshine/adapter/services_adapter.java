package com.example.dustnshine.adapter;

import com.example.dustnshine.Models.services_model;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.dustnshine.models.services_model;
import com.example.dustnshine.R;

import java.util.List;

public class services_adapter extends RecyclerView.Adapter<services_adapter.ViewHolder>{

    List<services_model> servicesModelList;

    public services_adapter(List<services_model> servicesModelList) {
        this.servicesModelList = servicesModelList;
    }

    @NonNull
    @Override
    public services_adapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.content_extra_services, parent, false);
        services_adapter.ViewHolder viewHolder = new services_adapter.ViewHolder(view);

        return viewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull services_adapter.ViewHolder holder, int position) {


        holder.serviceTitle.setText(servicesModelList.get(position).getServiceTitle());
        holder.servicePrice.setText(servicesModelList.get(position).getServicePrice());
        holder.serviceDetails1.setText(servicesModelList.get(position).getServiceDes1());
        holder.serviceDetails2.setText(servicesModelList.get(position).getServiceDes2());


    }

    @Override
    public int getItemCount() {
        return servicesModelList.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder{


        private TextView serviceTitle,servicePrice, serviceDetails1,serviceDetails2;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);


            serviceTitle = itemView.findViewById(R.id.serviceTitleTv);
            servicePrice = itemView.findViewById(R.id.servicePriceTv);
            serviceDetails1 = itemView.findViewById(R.id.description1Tv);
            serviceDetails2 = itemView.findViewById(R.id.description2Tv);


        }
    }
}