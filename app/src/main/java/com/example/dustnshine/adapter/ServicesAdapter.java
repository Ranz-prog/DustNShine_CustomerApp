package com.example.dustnshine.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

<<<<<<< HEAD:app/src/main/java/com/example/dustnshine/adapter/ServicesAdapter.java
import com.example.dustnshine.models.ServicesModel;
import com.example.dustnshine.models.services_model;
=======
import com.example.dustnshine.models.ServiceResponse;
import com.example.dustnshine.models.ServicesModel;
>>>>>>> branch_jericho:app/src/main/java/com/example/dustnshine/adapter/services_adapter.java
import com.example.dustnshine.R;

import java.util.List;

public class ServicesAdapter extends RecyclerView.Adapter<ServicesAdapter.ViewHolder>{

    private List<ServicesModel> servicesModelList;
    private Context context;

    public ServicesAdapter() {

    }

    public void setData(List<ServicesModel> servicesModelList) {
        this.servicesModelList = servicesModelList;
        notifyDataSetChanged();
    }

    @NonNull
    @Override
    public ServicesAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.content_extra_services, parent, false);
        ServicesAdapter.ViewHolder viewHolder = new ServicesAdapter.ViewHolder(view);

        context = parent.getContext();

        return viewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull ServicesAdapter.ViewHolder holder, int position) {
<<<<<<< HEAD:app/src/main/java/com/example/dustnshine/adapter/ServicesAdapter.java
=======


>>>>>>> branch_jericho:app/src/main/java/com/example/dustnshine/adapter/services_adapter.java
        holder.serviceTitle.setText(servicesModelList.get(position).getName());
        holder.servicePrice.setText(servicesModelList.get(position).getCreated_at());
        holder.serviceDetails1.setText(servicesModelList.get(position).getDescription());
        holder.serviceDetails2.setText(servicesModelList.get(position).getCreated_at());
<<<<<<< HEAD:app/src/main/java/com/example/dustnshine/adapter/ServicesAdapter.java
=======


>>>>>>> branch_jericho:app/src/main/java/com/example/dustnshine/adapter/services_adapter.java
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