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

import com.example.dustnshine.models.ServicesModel;
import com.example.dustnshine.R;
import com.example.dustnshine.ui.QuantityListener;

import java.util.ArrayList;
import java.util.List;

public class ServicesAdapter extends RecyclerView.Adapter<ServicesAdapter.ViewHolder> {


    private List<ServicesModel> servicesModelList;
    private Context context;
    QuantityListener quantityListener;
    private ArrayList<Integer> servicesID = new ArrayList<>();
    private ArrayList<String> servicesName = new ArrayList<>();
    private ArrayList<Integer> servicesPrice = new ArrayList<>();
    private ArrayList<Integer> QuantityOfService = new ArrayList<>();
    int counter = 0;

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
        holder.serviceDetails1.setText(String.valueOf(servicesModelList.get(position).getPrice()) + "/" + servicesModelList.get(position).getTime() + "hr");
        holder.Quantity.setText(String.valueOf(counter));

        if(Integer.valueOf(holder.Quantity.getText().toString()).equals(0)){
            holder.cbItem.setEnabled(false);
        }else{
            holder.cbItem.setEnabled(true);
        }

        holder.Plus.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                holder.Minus.setEnabled(true);
                holder.cbItem.setEnabled(true);
                counter++;
                holder.Quantity.setText(String.valueOf(counter));
            }
        });

        holder.Minus.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(Integer.valueOf(holder.Quantity.getText().toString()).equals(0)){
                    Log.d("d", holder.Quantity.getText().toString());
                    holder.Minus.setEnabled(false);
                    holder.cbItem.setEnabled(false);
                } else {
                    counter--;
                    holder.Quantity.setText(String.valueOf(counter));
                    if(Integer.valueOf(holder.Quantity.getText().toString()).equals(0)){
                        Log.d("d", holder.Quantity.getText().toString());
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

                    holder.Quantity.setText(String.valueOf(counter));

                    holder.Plus.setEnabled(false);
                    holder.Minus.setEnabled(false);
                    holder.Plus.setVisibility(View.INVISIBLE);
                    holder.Minus.setVisibility(View.INVISIBLE);

                    servicesID.add(servicesModelList.get(itemPosition).getId());
                    servicesName.add(servicesModelList.get(itemPosition).getName());
                    servicesPrice.add(servicesModelList.get(itemPosition).getPrice() * Integer.valueOf(holder.Quantity.getText().toString()));

                    counter = 0;

                } else {
                    holder.cbItem.setEnabled(false);
                    holder.Plus.setEnabled(true);
                    holder.Minus.setEnabled(true);
                    holder.Plus.setVisibility(View.VISIBLE);
                    holder.Minus.setVisibility(View.VISIBLE);
                    int index = servicesID.indexOf(servicesModelList.get(itemPosition).getId());
                    int index2 = servicesName.indexOf(servicesModelList.get(itemPosition).getName());
                    int index3 = servicesPrice.indexOf(servicesModelList.get(itemPosition).getPrice() * Integer.valueOf(holder.Quantity.getText().toString()));
                    servicesID.remove(index);
                    servicesName.remove(index2);
                    servicesPrice.remove(index3);
                    // QuantityOfService.remove(index4);

                    counter = 0;
                    holder.Quantity.setText(String.valueOf(counter));
                }


                Log.d("Nagana", servicesID.toString());
                Log.d("Nagana", servicesName.toString());
                Log.d("Nagana", servicesPrice.toString());
                Log.d("Nagana", QuantityOfService.toString());

                quantityListener.onQuantityChange(servicesID, servicesName, servicesPrice,QuantityOfService);

            }

        });
    }


    @Override
    public int getItemCount() {
        return servicesModelList.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {

        private CheckBox cbItem;
        private TextView serviceTitle, servicePrice, serviceDetails1, serviceDetails2,Quantity;
        private RelativeLayout describe;
        private ImageView Plus, Minus;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);

            cbItem = itemView.findViewById(R.id.cbItem);
            serviceTitle = itemView.findViewById(R.id.serviceTitleTv);
            servicePrice = itemView.findViewById(R.id.servicePriceTv);
            serviceDetails1 = itemView.findViewById(R.id.tvDescription);
//            serviceDetails2 = itemView.findViewById(R.id.description2Tv);

            Quantity = itemView.findViewById(R.id.countertxt);
            Plus = itemView.findViewById(R.id.plus);
            Minus = itemView.findViewById(R.id.minus);

        }
    }
}