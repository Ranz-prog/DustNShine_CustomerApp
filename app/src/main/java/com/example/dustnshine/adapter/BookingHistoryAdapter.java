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
import com.example.dustnshine.models.BookingHistoryModel;

import java.util.List;

public class BookingHistoryAdapter extends RecyclerView.Adapter<BookingHistoryAdapter.ViewHolder> {

    private List<BookingHistoryModel> bookingHistoryModelList;
    private Context context;
    private BookingHistoryAdapter.OnClickMessageListener onClickMessageListener;

    public BookingHistoryAdapter(List<BookingHistoryModel> bookingHistoryModelList, Context context, OnClickMessageListener onClickMessageListener) {
        this.bookingHistoryModelList = bookingHistoryModelList;
        this.context = context;
        this.onClickMessageListener = onClickMessageListener;
    }

    public void setData(List<BookingHistoryModel> bookingHistoryModelList) {
        this.bookingHistoryModelList = bookingHistoryModelList;
        notifyDataSetChanged();
    }

    @NonNull
    @Override
    public BookingHistoryAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.content_booking_history, parent, false);
        BookingHistoryAdapter.ViewHolder viewHolder = new BookingHistoryAdapter.ViewHolder(view, onClickMessageListener);

        return viewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull BookingHistoryAdapter.ViewHolder holder, int position) {
        holder.tvServicesName.setText(bookingHistoryModelList.get(position).getServices().toString().replaceAll("(^\\[|\\]$)", ""));
        holder.tvComments.setText(bookingHistoryModelList.get(position).getReviews().get(0).getComment());
        holder.tvTotalCost.setText(String.valueOf(bookingHistoryModelList.get(position).getTotal()));
        holder.tvDateAndTime.setText(String.valueOf(bookingHistoryModelList.get(position).getSched_datetime()));
    }

    @Override
    public int getItemCount() {
        return bookingHistoryModelList.size();
    }

    public interface OnClickMessageListener {
        void onClickMessage(int adapterPosition);
    }

    public static class ViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener{

        private TextView tvDateAndTime, tvServicesName,tvTotalCost, tvTotalPayment, tvComments;

        BookingHistoryAdapter.OnClickMessageListener onClickMessageListener;

        public ViewHolder(@NonNull View itemView, BookingHistoryAdapter.OnClickMessageListener onClickMessageListener) {
            super(itemView);

            tvServicesName = itemView.findViewById(R.id.tvServicesName);
            tvDateAndTime = itemView.findViewById(R.id.tvDateAndTime);
            tvTotalCost = itemView.findViewById(R.id.tvTotalCost);
            tvComments = itemView.findViewById(R.id.tvComments);
            tvTotalPayment = itemView.findViewById(R.id.tvTotalPayment);

            this.onClickMessageListener = (BookingHistoryAdapter.OnClickMessageListener) onClickMessageListener;

            itemView.setOnClickListener(this);

        }
        @Override
        public void onClick(View v) {
            onClickMessageListener.onClickMessage(getAdapterPosition());
        }
    }
}
