package com.example.dustnshine.adapter;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.dustnshine.R;
import com.example.dustnshine.models.BookingHistoryModel;

import java.util.List;

public class NotificationAdapter extends RecyclerView.Adapter<NotificationAdapter.ViewHolder> {

    private List<BookingHistoryModel> bookingHistoryModelList;
    private Context context;
    private NotificationAdapter.OnClickMessageListener onClickMessageListener;

    public NotificationAdapter(List<BookingHistoryModel> bookingHistoryModelList, Context context, OnClickMessageListener onClickMessageListener) {
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
    public NotificationAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return null;
    }

    @Override
    public void onBindViewHolder(@NonNull NotificationAdapter.ViewHolder holder, int position) {

    }

    @Override
    public int getItemCount() {
        return bookingHistoryModelList.size();
    }

    public interface OnClickMessageListener {
        void onClickMessage(int adapterPosition);
    }

    public static class ViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener{

        private ImageView customerImg;
        private TextView customerName, customerLoc,customerNum;

        NotificationAdapter.OnClickMessageListener onClickMessageListener;

        public ViewHolder(@NonNull View itemView, BookingHistoryAdapter.OnClickMessageListener onClickMessageListener) {
            super(itemView);

            customerImg = itemView.findViewById(R.id.clientImage);
            customerName = itemView.findViewById(R.id.customerNameBooking);
            customerLoc = itemView.findViewById(R.id.customerLocationBooking);
            customerNum = itemView.findViewById(R.id.contactNumberBooking);

            this.onClickMessageListener = (NotificationAdapter.OnClickMessageListener) onClickMessageListener;

            itemView.setOnClickListener(this);

        }
        @Override
        public void onClick(View v) {
            onClickMessageListener.onClickMessage(getAdapterPosition());
        }
    }
}
