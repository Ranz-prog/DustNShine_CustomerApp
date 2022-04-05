package com.example.dustnshine.adapter;

import android.content.Context;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CheckBox;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.recyclerview.widget.RecyclerView;

import com.example.dustnshine.models.CompanyAndServicesModel;
import com.example.dustnshine.models.ServicesModel;
import com.example.dustnshine.R;
import com.example.dustnshine.ui.QuantityListener;

import java.util.ArrayList;
import java.util.List;

public class ServicesAdapter extends RecyclerView.Adapter<ServicesAdapter.ViewHolder> {


    private CompanyAndServicesModel companyAndServicesModel;
    private Context context;
    private QuantityListener quantityListener;
    private ArrayList<Integer> servicesID = new ArrayList<>();
    private ArrayList<String> servicesName = new ArrayList<>();
    private ArrayList<Integer> servicesPrice = new ArrayList<>();
    private ArrayList<Integer> QuantityOfService = new ArrayList<>();

    public ServicesAdapter(CompanyAndServicesModel companyAndServicesModel, Context context, QuantityListener quantityListener) {
        this.companyAndServicesModel = companyAndServicesModel;
        this.context = context;
        this.quantityListener = quantityListener;
    }

    public void setData(CompanyAndServicesModel companyAndServicesModels) {
        this.companyAndServicesModel = companyAndServicesModels;
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
        final int[] counter = {0};

        holder.serviceTitle.setText(companyAndServicesModel.getServices().get(position).getName());
        holder.servicePrice.setText(String.valueOf(companyAndServicesModel.getServices().get(position).getPrice()) + "/" + companyAndServicesModel.getServices().get(position).getTime() + "hr");
        holder.serviceDetails.setText(companyAndServicesModel.getServices().get(position).getDescription());
        holder.quantity.setText(String.valueOf(counter[0]));


            if (Integer.valueOf(holder.quantity.getText().toString()).equals(0)) {
                holder.cbItem.setEnabled(false);
            } else {
                holder.cbItem.setEnabled(true);
            }

            holder.Plus.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    holder.Minus.setEnabled(true);
                    holder.cbItem.setEnabled(true);
                    counter[0]++;
                    holder.quantity.setText(String.valueOf(counter[0]));
                }
            });

            holder.Minus.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {

                    if (Integer.valueOf(holder.quantity.getText().toString()).equals(0)) {
                        holder.Minus.setEnabled(false);
                        holder.cbItem.setEnabled(false);
                    } else {
                        counter[0]--;
                        holder.quantity.setText(String.valueOf(counter[0]));

                        holder.quantity.setText(String.valueOf(counter[0]));
                        if (Integer.valueOf(holder.quantity.getText().toString()).equals(0)) {
                            holder.Minus.setEnabled(false);
                            holder.cbItem.setEnabled(false);
                        }

                    }
                }
            });

            holder.cbItem.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    if (holder.cbItem.isChecked()) {
                        holder.quantity.setText(String.valueOf(counter[0]));
                        holder.Plus.setEnabled(false);
                        holder.Minus.setEnabled(false);
                        holder.Plus.setVisibility(View.INVISIBLE);
                        holder.Minus.setVisibility(View.INVISIBLE);
                        servicesID.add(companyAndServicesModel.getServices().get(itemPosition).getId());
                        servicesName.add(companyAndServicesModel.getServices().get(itemPosition).getName());
                        servicesPrice.add(companyAndServicesModel.getServices().get(itemPosition).getPrice() * Integer.valueOf(holder.quantity.getText().toString()));
                        counter[0] = 0;

                    } else {
                        holder.cbItem.setEnabled(false);
                        holder.Plus.setEnabled(true);
                        holder.Minus.setEnabled(true);
                        holder.Plus.setVisibility(View.VISIBLE);
                        holder.Minus.setVisibility(View.VISIBLE);
                        int index = servicesID.indexOf(companyAndServicesModel.getServices().get(itemPosition).getId());
                        int index2 = servicesName.indexOf(companyAndServicesModel.getServices().get(itemPosition).getName());
                        int index3 = servicesPrice.indexOf(companyAndServicesModel.getServices().get(itemPosition).getPrice() * Integer.valueOf(holder.quantity.getText().toString()));
                        servicesID.remove(index);
                        servicesName.remove(index2);
                        servicesPrice.remove(index3);
                        counter[0] = 0;
                        holder.quantity.setText(String.valueOf(counter[0]));

                    }
                    quantityListener.onQuantityChange(servicesID, servicesName, servicesPrice, QuantityOfService);
                }

            });
    }


    @Override
    public int getItemCount() {
        return companyAndServicesModel.getServices().size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {

        private CheckBox cbItem;
        private TextView serviceTitle, servicePrice, serviceDetails, quantity;
        private ImageView Plus, Minus;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);

            cbItem = itemView.findViewById(R.id.cbItem);
            serviceTitle = itemView.findViewById(R.id.tvServiceName);
            serviceDetails = itemView.findViewById(R.id.tvDescription);
            servicePrice = itemView.findViewById(R.id.tvPriceHour);

            quantity = itemView.findViewById(R.id.countertxt);
            Plus = itemView.findViewById(R.id.plus);
            Minus = itemView.findViewById(R.id.minus);

        }
    }
}