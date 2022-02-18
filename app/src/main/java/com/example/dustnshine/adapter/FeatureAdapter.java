package com.example.dustnshine.adapter;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.dustnshine.models.FeatureModel;
import com.example.dustnshine.R;

import java.util.List;

public class FeatureAdapter extends RecyclerView.Adapter<FeatureAdapter.ViewHolder>{

    List<FeatureModel> featureModelsList;

<<<<<<< HEAD:app/src/main/java/com/example/dustnshine/adapter/feature_adapter.java
    public feature_adapter(List<FeatureModel> featureModelsList) {
=======
    public FeatureAdapter(List<FeatureModel> featureModelsList) {
>>>>>>> branch_jericho:app/src/main/java/com/example/dustnshine/adapter/FeatureAdapter.java
        this.featureModelsList = featureModelsList;
    }

    @NonNull
    @Override
    public FeatureAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.content_features, parent, false);
        FeatureAdapter.ViewHolder viewHolder = new FeatureAdapter.ViewHolder(view);

        return viewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull FeatureAdapter.ViewHolder holder, int position) {

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