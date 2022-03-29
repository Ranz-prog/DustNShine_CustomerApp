package com.example.dustnshine.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.dustnshine.models.BookingModel;
import com.example.dustnshine.R;
import com.example.dustnshine.models.BookingServiceData;
import com.example.dustnshine.models.CompanyAndServicesModel;
import com.example.dustnshine.models.RecommendationModel;

import java.util.List;

public class BookingAdapter extends RecyclerView.Adapter<BookingAdapter.ViewHolder>{

    private List<BookingServiceData> bookingServiceData;
    private Context context;
    private BookingAdapter.OnClickMessageListener onClickMessageListener;

    public BookingAdapter(List<BookingServiceData> bookingServiceData, Context context, OnClickMessageListener onClickMessageListener) {
        this.bookingServiceData = bookingServiceData;

        this.context = context;
        this.onClickMessageListener = onClickMessageListener;
    }

    public void setData(List<BookingServiceData> bookingServiceData) {
        this.bookingServiceData = bookingServiceData;

        notifyDataSetChanged();
    }

    @NonNull
    @Override
    public BookingAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.content_booking, parent, false);
        BookingAdapter.ViewHolder viewHolder = new BookingAdapter.ViewHolder(view, onClickMessageListener);

        return viewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull BookingAdapter.ViewHolder holder, int position) {

        holder.customerName.setText(bookingServiceData.get(position).getServices().toString().replaceAll("(^\\[|\\]$)", ""));
        holder.customerLoc.setText(bookingServiceData.get(position).getSched_datetime());
        holder.customerNum.setText("Php "+String.valueOf(bookingServiceData.get(position).getTotal()));

    }

    @Override
    public int getItemCount() {
        return bookingServiceData.size();
    }

    public interface OnClickMessageListener {
        void onClickMessage(int adapterPosition);
    }

    public static class ViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener{

        private ImageView customerImg;
        private TextView customerName, customerLoc,customerNum;

        BookingAdapter.OnClickMessageListener onClickMessageListener;

        public ViewHolder(@NonNull View itemView, BookingAdapter.OnClickMessageListener onClickMessageListener) {
            super(itemView);

            customerImg = itemView.findViewById(R.id.clientImage);
            customerName = itemView.findViewById(R.id.customerNameBooking);
            customerLoc = itemView.findViewById(R.id.customerLocationBooking);
            customerNum = itemView.findViewById(R.id.contactNumberBooking);

            this.onClickMessageListener = (BookingAdapter.OnClickMessageListener) onClickMessageListener;

            itemView.setOnClickListener(this);

        }
        @Override
        public void onClick(View v) {
            onClickMessageListener.onClickMessage(getAdapterPosition());
        }
    }

}