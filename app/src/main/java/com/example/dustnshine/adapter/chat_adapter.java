package com.example.dustnshine.adapter;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.dustnshine.Models.chat_model;
import com.example.dustnshine.R;

import java.util.List;

import de.hdodenhof.circleimageview.CircleImageView;

public class chat_adapter extends RecyclerView.Adapter<chat_adapter.ViewHolder> {

    List<chat_model> chatModelList;

    public chat_adapter(List<chat_model> chatModelList) {
        this.chatModelList = chatModelList;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.content_message, parent, false);
        ViewHolder viewHolder = new ViewHolder(view);

        return viewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {

        holder.chat_clientImageView.setImageResource(chatModelList.get(position).getImage());
        holder.chat_clientName.setText(chatModelList.get(position).getClientname());
        holder.chat_clientMessage.setText(chatModelList.get(position).getClientMesage());

    }

    @Override
    public int getItemCount() {
        return chatModelList.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder{

        private CircleImageView chat_clientImageView;
        private TextView chat_clientName, chat_clientMessage, chat_timeReceived;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);

            chat_clientImageView = itemView.findViewById(R.id.chat_clientImage);
            chat_clientName = itemView.findViewById(R.id.chat_clientName_TV);
            chat_clientMessage = itemView.findViewById(R.id.chat_clientMessage_TV);
        }
    }
}