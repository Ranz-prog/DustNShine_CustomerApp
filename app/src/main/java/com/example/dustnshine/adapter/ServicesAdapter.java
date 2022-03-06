package com.example.dustnshine.adapter;

import android.annotation.SuppressLint;
import android.content.Context;
import android.util.Log;
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
    private ArrayList<Integer> servicesPrice = new ArrayList<>();

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
        holder.servicePrice.setText(servicesModelList.get(position).getDescription());
        holder.serviceDetails1.setText("P" + " " + String.valueOf(servicesModelList.get(position).getPrice()) + "/" + servicesModelList.get(position).getTime());
        holder.serviceDetails2.setText(servicesModelList.get(position).getCreated_at());

        holder.cbItem.setChecked(false);
        holder.cbItem.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (holder.cbItem.isChecked()) {
                    servicesID.add(servicesModelList.get(itemPosition).getId());
                    servicesName.add(servicesModelList.get(itemPosition).getName());
                    servicesPrice.add(servicesModelList.get(itemPosition).getPrice());
                    Log.d("Nagana", servicesID.toString());
                    Log.d("Nagana", servicesName.toString());
                    Log.d("Nagana", servicesPrice.toString());
                } else {
                    int index = servicesID.indexOf(servicesModelList.get(itemPosition).getId());
                    int index2 = servicesName.indexOf(servicesModelList.get(itemPosition).getName());
                    int index3 = servicesPrice.indexOf(servicesModelList.get(itemPosition).getPrice());
                    servicesID.remove(index);
                    servicesName.remove(index2);
                    servicesPrice.remove(index3);
                    Log.d("D nagana", servicesID.toString());
                    Log.d("D nagana", servicesName.toString());
                    Log.d("D nagana", servicesPrice.toString());
                }
                quantityListener.onQuantityChange(servicesID, servicesName, servicesPrice);
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

        }
    }
}