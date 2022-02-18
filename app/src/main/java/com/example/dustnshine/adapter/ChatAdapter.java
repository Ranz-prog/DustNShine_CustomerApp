package com.example.dustnshine.adapter;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.dustnshine.models.ChatModel;
import com.example.dustnshine.R;

import java.util.List;

import de.hdodenhof.circleimageview.CircleImageView;

public class ChatAdapter extends RecyclerView.Adapter<ChatAdapter.ViewHolder> {

    List<ChatModel> chatModelList;
    private OnClickMessageListener onClickMessageListener;

<<<<<<< HEAD:app/src/main/java/com/example/dustnshine/adapter/chat_adapter.java
    public chat_adapter(List<ChatModel> chatModelList, OnClickMessageListener onClickMessageListener) {
=======
    public ChatAdapter(List<ChatModel> chatModelList, OnClickMessageListener onClickMessageListener) {
>>>>>>> branch_jericho:app/src/main/java/com/example/dustnshine/adapter/ChatAdapter.java
        this.chatModelList = chatModelList;
        this.onClickMessageListener = onClickMessageListener;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.content_message, parent, false);
        ViewHolder viewHolder = new ViewHolder(view, onClickMessageListener);


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

    public interface OnClickMessageListener {
        void onClickMessage(int adapterPosition);
    }

    public class ViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener{

        private CircleImageView chat_clientImageView;
        private TextView chat_clientName, chat_clientMessage, chat_timeReceived;

        OnClickMessageListener onClickMessageListener;

        public ViewHolder(@NonNull View itemView,OnClickMessageListener onClickMessageListener) {
            super(itemView);

            chat_clientImageView = itemView.findViewById(R.id.chat_clientImage);
            chat_clientName = itemView.findViewById(R.id.chat_clientName_TV);
            chat_clientMessage = itemView.findViewById(R.id.chat_clientMessage_TV);

            this.onClickMessageListener = onClickMessageListener;

            itemView.setOnClickListener(this);
        }

        @Override
        public void onClick(View v) {
            onClickMessageListener.onClickMessage(getAdapterPosition());
        }

        
    }

}




