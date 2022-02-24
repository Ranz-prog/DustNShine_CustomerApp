package com.example.dustnshine.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.dustnshine.models.RecommendationModel;
import com.example.dustnshine.R;

import java.util.List;

public class SeeAllRecommendationsAdapter extends RecyclerView.Adapter<SeeAllRecommendationsAdapter.ViewHolder>{

    private List<RecommendationModel> recommendationModelList;
    private Context context;
    private SeeAllRecommendationsAdapter.OnClickMessageListener onClickMessageListener;

    public SeeAllRecommendationsAdapter(List<RecommendationModel> recommendationModelList, Context context, OnClickMessageListener onClickMessageListener) {
        this.recommendationModelList = recommendationModelList;
        this.context = context;
        this.onClickMessageListener = onClickMessageListener;
    }

    public void setData(List<RecommendationModel> recommendationModelList) {
        this.recommendationModelList = recommendationModelList;
        notifyDataSetChanged();
    }

    @NonNull
    @Override
    public SeeAllRecommendationsAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.content_see_all_recommendations, parent, false);
        SeeAllRecommendationsAdapter.ViewHolder viewHolder = new SeeAllRecommendationsAdapter.ViewHolder(view, onClickMessageListener);

        return viewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull SeeAllRecommendationsAdapter.ViewHolder holder, int position) {
//        holder.companyImage.setImageResource();
        holder.companyName.setText(recommendationModelList.get(position).getName());
        holder.companyLocation.setText(recommendationModelList.get(position).getAddress());
//        holder.companyRating.setText(recommendationModelList.get(position).getCompanyRating());
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

        SeeAllRecommendationsAdapter.OnClickMessageListener onClickMessageListener;

        public ViewHolder(@NonNull View itemView, SeeAllRecommendationsAdapter.OnClickMessageListener onClickMessageListener) {
            super(itemView);

//            companyImage = itemView.findViewById(R.id.companyImg);
            companyName = itemView.findViewById(R.id.companyNameTVSAR);
            companyLocation = itemView.findViewById(R.id.companyLocationTVSAR);
//            companyRating = itemView.findViewById(R.id.companyRatingTV);

            this.onClickMessageListener = (OnClickMessageListener) onClickMessageListener;

            itemView.setOnClickListener(this);

        }
        @Override
        public void onClick(View v) {
            onClickMessageListener.onClickMessage(getAdapterPosition());
        }
    }
}