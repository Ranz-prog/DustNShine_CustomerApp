package com.example.dustnshine.adapter;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.dustnshine.models.BookingModel;
import com.example.dustnshine.R;

import java.util.List;

public class BookingAdapter extends RecyclerView.Adapter<BookingAdapter.ViewHolder>{

    List<BookingModel> bookingModelList;
    private BookingAdapter.OnClickMessageListener onClickMessageListener;

    public BookingAdapter(List<BookingModel> bookingModelList, BookingAdapter.OnClickMessageListener onClickMessageListener) {
        this.bookingModelList = bookingModelList;
        this.onClickMessageListener = onClickMessageListener;
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

        holder.customerImg.setImageResource(bookingModelList.get(position).getCustomerImg());
        holder.customerName.setText(bookingModelList.get(position).getCustomerName());
        holder.customerLoc.setText(bookingModelList.get(position).getCustomerLocation());
        holder.customerNum.setText(bookingModelList.get(position).getCustomerContact());


    }

    @Override
    public int getItemCount() {
        return bookingModelList.size();
    }

    public interface OnClickMessageListener {
        void onClickMessage(int adapterPosition);
    }

    public class ViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener{

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