package com.example.dustnshine.adapter;



import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.dustnshine.models.booking_model;
import com.example.dustnshine.R;

import java.util.List;

public class booking_adapter extends RecyclerView.Adapter<booking_adapter.ViewHolder>{

    List<booking_model> bookingModelList;

    public booking_adapter(List<booking_model> bookingModelList) {
        this.bookingModelList = bookingModelList;
    }

    @NonNull
    @Override
    public booking_adapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.content_booking, parent, false);
        booking_adapter.ViewHolder viewHolder = new booking_adapter.ViewHolder(view);

        return viewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull booking_adapter.ViewHolder holder, int position) {

        holder.customerImg.setImageResource(bookingModelList.get(position).getCustomerImg());
        holder.customerName.setText(bookingModelList.get(position).getCustomerName());
        holder.customerLoc.setText(bookingModelList.get(position).getCustomerLocation());
        holder.customerNum.setText(bookingModelList.get(position).getCustomerContact());


    }

    @Override
    public int getItemCount() {
        return bookingModelList.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder{

        private ImageView customerImg;
        private TextView customerName, customerLoc,customerNum;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);

            customerImg = itemView.findViewById(R.id.clientImage);
            customerName = itemView.findViewById(R.id.customerNameBooking);
            customerLoc = itemView.findViewById(R.id.customerLocationBooking);
            customerNum = itemView.findViewById(R.id.contactNumberBooking);

        }
    }
}