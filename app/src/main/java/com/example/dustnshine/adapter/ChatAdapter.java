package com.example.dustnshine.adapter;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.AsyncTask;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.cometchat.pro.models.Group;
import com.example.dustnshine.models.ChatModel;
import com.example.dustnshine.R;
import com.example.dustnshine.ui.MessageBox;

import java.io.IOException;
import java.io.InputStream;
import java.util.List;

import de.hdodenhof.circleimageview.CircleImageView;

public class ChatAdapter extends RecyclerView.Adapter<ChatAdapter.ViewHolder> {

    List<Group> chatModelList;
    //private OnClickMessageListener onClickMessageListener;
    Context context;
    String groupImg;
    public ChatAdapter(List<Group> chatModelList, Context context) {
        this.chatModelList = chatModelList;
        this.context = context;
        //this.onClickMessageListener = onClickMessageListener;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.content_message_linear, parent, false);
        ViewHolder viewHolder = new ViewHolder(view);


        return viewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {

//        holder.chat_clientImageView.setImageResource(chatModelList.get(position).getImage());
//        holder.chat_clientName.setText(chatModelList.get(position).getClientname());
//        holder.chat_clientMessage.setText(chatModelList.get(position).getClientMesage());

        holder.bind(chatModelList.get(position));

    }

    @Override
    public int getItemCount() {
        return chatModelList.size();
    }

    public interface OnClickMessageListener {
        void onClickMessage(int adapterPosition);
    }

    public class ViewHolder extends RecyclerView.ViewHolder{

        private CircleImageView chat_clientImageView;
        private TextView chat_clientName, chat_clientMessage, chat_timeReceived;
        private LinearLayout containerLayout;

        //OnClickMessageListener onClickMessageListener;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);

            chat_clientImageView = itemView.findViewById(R.id.chat_clientImageLinear);
            chat_clientName = itemView.findViewById(R.id.chat_clientName_TVLinear);
            containerLayout = itemView.findViewById(R.id.containerLayout);

//            chat_clientImageView = itemView.findViewById(R.id.chat_clientImage);
//            chat_clientName = itemView.findViewById(R.id.chat_clientName_TV);
//            chat_clientMessage = itemView.findViewById(R.id.chat_clientMessage_TV);
//            chat_RecyclerView = itemView.findViewById(R.id.chat_RecyclerView);

            //this.onClickMessageListener = onClickMessageListener;

            //itemView.setOnClickListener(this);
        }

//        @Override
//        public void onClick(View v) {
//            onClickMessageListener.onClickMessage(getAdapterPosition());
//        }


        public void bind(Group group) {
            chat_clientName.setText(group.getName());
            groupImg = group.getIcon();
            LoadImage loadImage = new LoadImage(chat_clientImageView);
            loadImage.execute(groupImg);
            containerLayout.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {

                    MessageBox.start(context, group.getGuid());
                }
            });

        }
        // Getting Image via url
        private class LoadImage extends AsyncTask<String, Void , Bitmap> {
            CircleImageView circleImageView;
            public LoadImage(CircleImageView chat_clientImageView){
                circleImageView = chat_clientImageView;

            }

            @Override
            protected Bitmap doInBackground(String... strings) {
                String urlLink = strings[0];
                Bitmap bitmap = null;
                try {
                    InputStream inputStream = new java.net.URL(groupImg).openStream();
                    bitmap = BitmapFactory.decodeStream(inputStream);
                } catch (IOException e) {
                    e.printStackTrace();
                }
                return bitmap;
            }

            @Override
            protected void onPostExecute(Bitmap bitmap) {
                chat_clientImageView.setImageBitmap(bitmap);
            }
        }// end of geeting image
    }



}




