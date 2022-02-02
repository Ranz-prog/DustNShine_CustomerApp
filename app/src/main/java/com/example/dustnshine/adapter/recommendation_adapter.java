package com.example.dustnshine.adapter;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.dustnshine.models.recommendation_model;
import com.example.dustnshine.R;

import java.util.List;

public class recommendation_adapter extends RecyclerView.Adapter<recommendation_adapter.ViewHolder>{

    List<recommendation_model> recommendationModelList;
    private recommendation_adapter.OnClickMessageListener onClickMessageListener;

    public recommendation_adapter(List<recommendation_model> recommendationModelList, recommendation_adapter.OnClickMessageListener onClickMessageListener) {
        this.recommendationModelList = recommendationModelList;
        this.onClickMessageListener = onClickMessageListener;
    }

    @NonNull
    @Override
    public recommendation_adapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.content_recommendation, parent, false);
        recommendation_adapter.ViewHolder viewHolder = new recommendation_adapter.ViewHolder(view, onClickMessageListener);

        return viewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull recommendation_adapter.ViewHolder holder, int position) {
        holder.companyImage.setImageResource(recommendationModelList.get(position).getCompanyImg());
        holder.companyName.setText(recommendationModelList.get(position).getCompanyName());
        holder.companyLocation.setText(recommendationModelList.get(position).getCompanyLocation());
        holder.companyRating.setText(recommendationModelList.get(position).getCompanyRating());
    }

    @Override
    public int getItemCount() {
        return recommendationModelList.size();
    }

    public interface OnClickMessageListener {
        void onClickMessage(int adapterPosition);
    }

    public class ViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener{

        private ImageView companyImage;
        private TextView companyName,companyLocation,companyRating;

        recommendation_adapter.OnClickMessageListener onClickMessageListener;

        public ViewHolder(@NonNull View itemView, recommendation_adapter.OnClickMessageListener onClickMessageListener) {
            super(itemView);

            companyImage = itemView.findViewById(R.id.companyImg);
            companyName = itemView.findViewById(R.id.companyNameTV);
            companyLocation = itemView.findViewById(R.id.companyLocationTV);
            companyRating = itemView.findViewById(R.id.companyRatingTV);

            this.onClickMessageListener = (OnClickMessageListener) onClickMessageListener;

            itemView.setOnClickListener(this);

        }
        @Override
        public void onClick(View v) {
            onClickMessageListener.onClickMessage(getAdapterPosition());
        }
    }
}
