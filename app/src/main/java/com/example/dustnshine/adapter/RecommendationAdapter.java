package com.example.dustnshine.adapter;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.dustnshine.models.RecommendationModel;
import com.example.dustnshine.R;
import com.example.dustnshine.models.ServicesModel;

import java.util.List;

public class RecommendationAdapter extends RecyclerView.Adapter<RecommendationAdapter.ViewHolder>{

<<<<<<< HEAD:app/src/main/java/com/example/dustnshine/adapter/RecommendationAdapter.java
    private List<RecommendationModel> recommendationModelList;
=======
    List<RecommendationModel> recommendationModelList;
>>>>>>> branch_jericho:app/src/main/java/com/example/dustnshine/adapter/recommendation_adapter.java
    private RecommendationAdapter.OnClickMessageListener onClickMessageListener;

    public RecommendationAdapter( RecommendationAdapter.OnClickMessageListener onClickMessageListener) {
        this.onClickMessageListener = onClickMessageListener;
    }

    public void setData(List<RecommendationModel> recommendationModelList) {
        this.recommendationModelList = recommendationModelList;
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
<<<<<<< HEAD:app/src/main/java/com/example/dustnshine/adapter/RecommendationAdapter.java
//        holder.companyImage.setImageResource();
        holder.companyName.setText(recommendationModelList.get(position).getName());
        holder.companyLocation.setText(recommendationModelList.get(position).getAddress());
//        holder.companyRating.setText(recommendationModelList.get(position).getCompanyRating());
=======
       // holder.companyImage.setImageResource(recommendationModelList.get(position).getCompanyImg());
        holder.companyName.setText(recommendationModelList.get(position).getName());
        holder.companyLocation.setText(recommendationModelList.get(position).getAddress());
>>>>>>> branch_jericho:app/src/main/java/com/example/dustnshine/adapter/recommendation_adapter.java
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

        RecommendationAdapter.OnClickMessageListener onClickMessageListener;

        public ViewHolder(@NonNull View itemView, RecommendationAdapter.OnClickMessageListener onClickMessageListener) {
            super(itemView);

<<<<<<< HEAD:app/src/main/java/com/example/dustnshine/adapter/RecommendationAdapter.java
//            companyImage = itemView.findViewById(R.id.companyImg);
            companyName = itemView.findViewById(R.id.companyNameTV);
            companyLocation = itemView.findViewById(R.id.companyLocationTV);
//            companyRating = itemView.findViewById(R.id.companyRatingTV);
=======
           // companyImage = itemView.findViewById(R.id.companyImg);
            companyName = itemView.findViewById(R.id.companyNameTV);
            companyLocation = itemView.findViewById(R.id.companyLocationTV);
            //companyRating = itemView.findViewById(R.id.companyRatingTV);
>>>>>>> branch_jericho:app/src/main/java/com/example/dustnshine/adapter/recommendation_adapter.java

            this.onClickMessageListener = (OnClickMessageListener) onClickMessageListener;

            itemView.setOnClickListener(this);

        }
        @Override
        public void onClick(View v) {
            onClickMessageListener.onClickMessage(getAdapterPosition());
        }
    }
}
