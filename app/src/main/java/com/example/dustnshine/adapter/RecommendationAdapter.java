package com.example.dustnshine.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.dustnshine.models.CompanyResponse;
import com.example.dustnshine.models.RecommendationModel;
import com.example.dustnshine.R;

import java.util.List;

public class RecommendationAdapter extends RecyclerView.Adapter<RecommendationAdapter.ViewHolder>{

    List<RecommendationModel> recommendationModelList;
    private RecommendationAdapter.OnClickMessageListener onClickMessageListener;

    public RecommendationAdapter(List<RecommendationModel> recommendationModelList, RecommendationAdapter.OnClickMessageListener onClickMessageListener) {
        this.recommendationModelList = recommendationModelList;
        this.onClickMessageListener = onClickMessageListener;
    }

    @NonNull
    @Override
    public RecommendationAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.content_recommendation, parent, false);
        RecommendationAdapter.ViewHolder viewHolder = new RecommendationAdapter.ViewHolder(view, onClickMessageListener);

        return viewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull RecommendationAdapter.ViewHolder holder, int position) {
//        holder.companyImage.setImageResource(recommendationModelList.get(position).getCompanyImg());
//        holder.companyName.setText(companyList.get(position).getRecommendationModel().getCompanyName());
//        holder.companyLocation.setText(companyList.get(position).getRecommendationModel().getCompanyLocation());
//        holder.companyRating.setText(recommendationModelList.get(position).getCompanyRating());
    }

    @Override
    public int getItemCount() {
        return 2;
    }

    public interface OnClickMessageListener {
        void onClickMessage(int adapterPosition);
    }

    public class ViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener{

        private ImageView companyImage;
        private TextView companyName,companyLocation,companyRating;

        RecommendationAdapter.OnClickMessageListener onClickMessageListener;

        public ViewHolder(@NonNull View itemView, RecommendationAdapter.OnClickMessageListener onClickMessageListener) {
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
