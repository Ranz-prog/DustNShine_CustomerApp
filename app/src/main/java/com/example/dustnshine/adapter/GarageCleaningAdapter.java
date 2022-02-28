package com.example.dustnshine.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.dustnshine.R;
import com.example.dustnshine.models.RecommendationModel;

import java.util.List;

public class GarageCleaningAdapter extends RecyclerView.Adapter<GarageCleaningAdapter.ViewHolder> {

    List<RecommendationModel> companyList;
    private Context context;
    private GeneralCleaningAdapter.OnClickMessageListener onClickMessageListener;

    public GarageCleaningAdapter(List<RecommendationModel> companyList, Context context, GeneralCleaningAdapter.OnClickMessageListener onClickMessageListener) {
        this.companyList = companyList;
        this.context = context;
        this.onClickMessageListener = onClickMessageListener;
    }

    public void setData(List<RecommendationModel> companyList) {
        this.companyList = companyList;
        notifyDataSetChanged();
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.content_company, parent, false);
        GarageCleaningAdapter.ViewHolder viewHolder = new GarageCleaningAdapter.ViewHolder(view, onClickMessageListener);

        return viewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        holder.txtCompanyName.setText(companyList.get(position).getName());
        holder.txtCompanyAddress.setText(companyList.get(position).getAddress());
    }

    @Override
    public int getItemCount() {
        return companyList.size();
    }

    public interface OnClickMessageListener {
        void onClickMessage(int adapterPosition);
    }

    public class ViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener{

        private TextView txtCompanyName, txtCompanyAddress;

        GeneralCleaningAdapter.OnClickMessageListener onClickMessageListener;

        public ViewHolder(@NonNull View itemView, GeneralCleaningAdapter.OnClickMessageListener onClickMessageListener) {
            super(itemView);

            txtCompanyName = itemView.findViewById(R.id.txtCompanyName);
            txtCompanyAddress = itemView.findViewById(R.id.txtCompanyAddress);

            this.onClickMessageListener = (GeneralCleaningAdapter.OnClickMessageListener) onClickMessageListener;

            itemView.setOnClickListener(this);

        }
        @Override
        public void onClick(View v) {
            onClickMessageListener.onClickMessage(getAdapterPosition());
        }
    }
}
