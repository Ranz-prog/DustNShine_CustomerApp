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
import com.example.dustnshine.R;
import com.example.dustnshine.models.RecommendationModel;
import com.example.dustnshine.utils.AppConstants;

import java.util.List;

public class CompanyServicesAdapter extends RecyclerView.Adapter<CompanyServicesAdapter.ViewHolder> {

    List<RecommendationModel> companyList;
    private Context context;
    private CompanyServicesAdapter.OnClickMessageListener onClickMessageListener;

    public CompanyServicesAdapter(List<RecommendationModel> companyList, Context context, CompanyServicesAdapter.OnClickMessageListener onClickMessageListener) {
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
        CompanyServicesAdapter.ViewHolder viewHolder = new CompanyServicesAdapter.ViewHolder(view, onClickMessageListener);

        return viewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        Glide.with(context).load(AppConstants.BASE_URL + companyList.get(position).getCompany_image()).into(holder.imgCompanyImage);
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
        private ImageView imgCompanyImage;

        CompanyServicesAdapter.OnClickMessageListener onClickMessageListener;

        public ViewHolder(@NonNull View itemView, CompanyServicesAdapter.OnClickMessageListener onClickMessageListener) {
            super(itemView);

            imgCompanyImage = itemView.findViewById(R.id.imgCompanyImage);
            txtCompanyName = itemView.findViewById(R.id.tvCompanyName);
            txtCompanyAddress = itemView.findViewById(R.id.tvCompanyAddress);

            this.onClickMessageListener = (CompanyServicesAdapter.OnClickMessageListener) onClickMessageListener;

            itemView.setOnClickListener(this);

        }

        @Override
        public void onClick(View v) {
            onClickMessageListener.onClickMessage(getAdapterPosition());
        }
    }
}