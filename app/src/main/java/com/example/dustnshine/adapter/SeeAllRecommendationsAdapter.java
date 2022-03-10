package com.example.dustnshine.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.Filter;
import android.widget.Filterable;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.example.dustnshine.models.RecommendationModel;
import com.example.dustnshine.R;
import com.example.dustnshine.utils.AppConstants;

import java.util.ArrayList;
import java.util.List;

public class SeeAllRecommendationsAdapter extends RecyclerView.Adapter<SeeAllRecommendationsAdapter.ViewHolder> {

    private List<RecommendationModel> recommendationModelList;
    private List<RecommendationModel> recommendationModels;
    private Context context;
    private SeeAllRecommendationsAdapter.OnClickMessageListener onClickMessageListener;
    private AppConstants appConstants;

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
        Glide.with(context).load(appConstants.BASE_URL + recommendationModelList.get(position).getCompany_image()).into(holder.imgCompanyImage);
        holder.tvCompanyName.setText(recommendationModelList.get(position).getName());
        holder.tvCompanyLocation.setText(recommendationModelList.get(position).getAddress());
//        holder.companyRating.setText(recommendationModelList.get(position).getCompanyRating());
    }

    @Override
    public int getItemCount() {
        return recommendationModelList.size();
    }

    public interface OnClickMessageListener {
        void onClickMessage(int adapterPosition);
    }

    public class ViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {

        private ImageView imgCompanyImage;
        private TextView tvCompanyName, tvCompanyLocation, companyRating;

        SeeAllRecommendationsAdapter.OnClickMessageListener onClickMessageListener;

        public ViewHolder(@NonNull View itemView, SeeAllRecommendationsAdapter.OnClickMessageListener onClickMessageListener) {
            super(itemView);

            imgCompanyImage = itemView.findViewById(R.id.imgCompanyImage);
            tvCompanyName = itemView.findViewById(R.id.tvCompanyName);
            tvCompanyLocation = itemView.findViewById(R.id.tvCompanyAddress);
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

