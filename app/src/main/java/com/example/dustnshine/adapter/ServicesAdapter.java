package com.example.dustnshine.adapter;

import android.annotation.SuppressLint;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CheckBox;
import android.widget.RelativeLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.dustnshine.models.ServicesModel;
import com.example.dustnshine.models.services_model;
import com.example.dustnshine.R;
import com.example.dustnshine.ui.QuantityListener;
import com.google.android.material.textfield.TextInputLayout;

import java.util.ArrayList;
import java.util.List;

public class ServicesAdapter extends RecyclerView.Adapter<ServicesAdapter.ViewHolder> {

    private List<ServicesModel> servicesModelList;
    private Context context;
    QuantityListener quantityListener;
    private ArrayList<Integer> servicesID = new ArrayList<>();
    private ArrayList<String> servicesName = new ArrayList<>();

    public ServicesAdapter(List<ServicesModel> servicesModelList, Context context, QuantityListener quantityListener) {
        this.servicesModelList = servicesModelList;
        this.context = context;
        this.quantityListener = quantityListener;
    }

    public void setData(List<ServicesModel> servicesModelList) {
        this.servicesModelList = servicesModelList;
        notifyDataSetChanged();
    }

    @NonNull
    @Override
    public ServicesAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.content_extra_services, parent, false);
        ServicesAdapter.ViewHolder viewHolder = new ServicesAdapter.ViewHolder(view);

        context = parent.getContext();

        return viewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull ServicesAdapter.ViewHolder holder, int position) {
        int itemPosition = position;
        holder.serviceTitle.setText(servicesModelList.get(position).getName());
        holder.servicePrice.setText(servicesModelList.get(position).getCreated_at());
        holder.serviceDetails1.setText(servicesModelList.get(position).getDescription());
        holder.serviceDetails2.setText(servicesModelList.get(position).getCreated_at());

        RelativeLayout.LayoutParams params = (RelativeLayout.LayoutParams) holder.describe.getLayoutParams();
        params.height = 0;
        params.width = 0;
        holder.describe.setLayoutParams(params);

        holder.describe.setVisibility(View.INVISIBLE);

        holder.cbItem.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (holder.cbItem.isChecked()) {
                    RelativeLayout.LayoutParams params = (RelativeLayout.LayoutParams) holder.describe.getLayoutParams();
                    params.height = RelativeLayout.LayoutParams.WRAP_CONTENT;
                    params.width = RelativeLayout.LayoutParams.MATCH_PARENT;
                    holder.describe.setLayoutParams(params);
                    holder.describe.setVisibility(View.VISIBLE);
                    servicesID.add(servicesModelList.get(itemPosition).getId());
                    servicesName.add(servicesModelList.get(itemPosition).getName());
                } else {
                    RelativeLayout.LayoutParams params = (RelativeLayout.LayoutParams) holder.describe.getLayoutParams();
                    params.height = 0;
                    params.width = 0;
                    holder.describe.setLayoutParams(params);
                    holder.describe.setVisibility(View.INVISIBLE);
                    servicesID.remove(servicesModelList.get(itemPosition));
                    servicesName.remove(servicesModelList.get(itemPosition));
                }
                quantityListener.onQuantityChange(servicesID, servicesName);
            }
        });
    }

    @Override
    public int getItemCount() {
        return servicesModelList.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {

        private CheckBox cbItem;
        private TextView serviceTitle, servicePrice, serviceDetails1, serviceDetails2;
        private RelativeLayout describe;


        public ViewHolder(@NonNull View itemView) {
            super(itemView);

            cbItem = itemView.findViewById(R.id.cbItem);
            serviceTitle = itemView.findViewById(R.id.serviceTitleTv);
            servicePrice = itemView.findViewById(R.id.servicePriceTv);
            serviceDetails1 = itemView.findViewById(R.id.description1Tv);
            serviceDetails2 = itemView.findViewById(R.id.description2Tv);

            describe = itemView.findViewById(R.id.describeNeeds);

        }
    }
}