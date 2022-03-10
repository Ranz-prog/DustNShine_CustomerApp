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
import com.example.dustnshine.models.NotificationModel;

import java.util.List;

public class NotificationAdapter extends RecyclerView.Adapter<NotificationAdapter.ViewHolder> {

    private List<NotificationModel> notificationModelList;
    private Context context;
    private NotificationAdapter.OnClickMessageListener onClickMessageListener;

    public NotificationAdapter(List<NotificationModel> notificationModelList, Context context, OnClickMessageListener onClickMessageListener) {
        this.notificationModelList = notificationModelList;
        this.context = context;
        this.onClickMessageListener = onClickMessageListener;
    }

    public void setData(List<NotificationModel> notificationModelList) {
        this.notificationModelList = notificationModelList;
        notifyDataSetChanged();
    }

    @NonNull
    @Override
    public NotificationAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.content_done_services, parent, false);
        NotificationAdapter.ViewHolder viewHolder = new NotificationAdapter.ViewHolder(view, onClickMessageListener);

        return viewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull NotificationAdapter.ViewHolder holder, int position) {
        holder.tvServicesName.setText(notificationModelList.get(position).getServices().toString());
        holder.tvWorkerName.setText(notificationModelList.get(position).getWorkers().get(0).getFirst_name() + " " + notificationModelList.get(position).getWorkers().get(0).getLast_name());
    }

    @Override
    public int getItemCount() {
        return notificationModelList.size();
    }

    public interface OnClickMessageListener {
        void onClickMessage(int adapterPosition);
    }

    public static class ViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener{

        private TextView tvServicesName, tvWorkerName;

        NotificationAdapter.OnClickMessageListener onClickMessageListener;

        public ViewHolder(@NonNull View itemView, NotificationAdapter.OnClickMessageListener onClickMessageListener) {
            super(itemView);

            tvServicesName = itemView.findViewById(R.id.tvServicesName);
            tvWorkerName = itemView.findViewById(R.id.tvWorkerName);

            this.onClickMessageListener = (NotificationAdapter.OnClickMessageListener) onClickMessageListener;

            itemView.setOnClickListener(this);

        }
        @Override
        public void onClick(View v) {
            onClickMessageListener.onClickMessage(getAdapterPosition());
        }
    }
}
