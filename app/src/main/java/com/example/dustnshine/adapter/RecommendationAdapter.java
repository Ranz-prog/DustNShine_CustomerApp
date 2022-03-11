package com.example.dustnshine.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.example.dustnshine.models.RecommendationModel;
import com.example.dustnshine.R;
import com.example.dustnshine.models.RecommendedCompaniesModel;
import com.example.dustnshine.utils.AppConstants;

import java.util.List;

public class RecommendationAdapter extends RecyclerView.Adapter<RecommendationAdapter.ViewHolder>{

    private List<RecommendedCompaniesModel> recommendedCompaniesModelList;
    private Context context;
    private RecommendationAdapter.OnClickMessageListener onClickMessageListener;
    private AppConstants appConstants;

    public RecommendationAdapter(List<RecommendedCompaniesModel> recommendedCompaniesModelList, Context context, OnClickMessageListener onClickMessageListener) {
        this.recommendedCompaniesModelList = recommendedCompaniesModelList;
        this.context = context;
        this.onClickMessageListener = onClickMessageListener;
    }

    public void setData(List<RecommendedCompaniesModel> recommendedCompaniesModelList) {
        this.recommendedCompaniesModelList = recommendedCompaniesModelList;
        notifyDataSetChanged();
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
        Glide.with(context).load(appConstants.BASE_URL + recommendedCompaniesModelList.get(position).getCompany().getCompany_image()).into(holder.imgCompanyImage);
        holder.tvCompanyName.setText(recommendedCompaniesModelList.get(position).getCompany().getName());
        holder.tvCompanyLocation.setText(recommendedCompaniesModelList.get(position).getCompany().getAddress());
        holder.tvCompanyRating.setText(String.valueOf(recommendedCompaniesModelList.get(position).getRating()));
    }

    @Override
    public int getItemCount() {
        return recommendedCompaniesModelList.size();
    }

    public interface OnClickMessageListener {
        void onClickMessage(int adapterPosition);
    }

    public class ViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener{

        private ImageView imgCompanyImage;
        private TextView tvCompanyName, tvCompanyLocation, tvCompanyRating;

        RecommendationAdapter.OnClickMessageListener onClickMessageListener;

        public ViewHolder(@NonNull View itemView, RecommendationAdapter.OnClickMessageListener onClickMessageListener) {
            super(itemView);

            imgCompanyImage = itemView.findViewById(R.id.imgCompanyImage);
            tvCompanyName = itemView.findViewById(R.id.companyNameTV);
            tvCompanyLocation = itemView.findViewById(R.id.companyLocationTV);
            tvCompanyRating = itemView.findViewById(R.id.tvCompanyRating);

            this.onClickMessageListener = (OnClickMessageListener) onClickMessageListener;

            itemView.setOnClickListener(this);

        }
        @Override
        public void onClick(View v) {
            onClickMessageListener.onClickMessage(getAdapterPosition());
        }
    }
}
