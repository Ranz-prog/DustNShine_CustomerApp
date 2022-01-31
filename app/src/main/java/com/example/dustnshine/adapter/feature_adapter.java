package com.example.dustnshine.adapter;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.dustnshine.models.feature_model;
import com.example.dustnshine.R;

import java.util.List;

public class feature_adapter extends RecyclerView.Adapter<feature_adapter.ViewHolder>{

    List<feature_model> featureModelsList;

    public feature_adapter(List<feature_model> featureModelsList) {
        this.featureModelsList = featureModelsList;
    }

    @NonNull
    @Override
    public feature_adapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.content_features, parent, false);
        feature_adapter.ViewHolder viewHolder = new feature_adapter.ViewHolder(view);

        return viewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull feature_adapter.ViewHolder holder, int position) {

        holder.serviceImg.setImageResource(featureModelsList.get(position).getServiceImg());
        holder.serviceName.setText(featureModelsList.get(position).getServiceName());
        holder.serviceDetails.setText(featureModelsList.get(position).getServiceDetails());


    }

    @Override
    public int getItemCount() {
        return featureModelsList.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder{

        private ImageView serviceImg;
        private TextView serviceName, serviceDetails;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);

            serviceImg = itemView.findViewById(R.id.serviceImg);
            serviceName = itemView.findViewById(R.id.serviceName);
            serviceDetails = itemView.findViewById(R.id.serviceDetails);

        }
    }
}