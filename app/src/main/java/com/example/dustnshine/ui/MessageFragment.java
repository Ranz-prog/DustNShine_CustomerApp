package com.example.dustnshine.ui;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.DividerItemDecoration;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.dustnshine.models.ChatModel;
import com.example.dustnshine.R;
import com.example.dustnshine.adapter.ChatAdapter;

import java.util.ArrayList;
import java.util.List;

public class MessageFragment extends Fragment implements ChatAdapter.OnClickMessageListener{

    private RecyclerView chatRecycler;
    private View view;
    private List<ChatModel> chatModelList;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {


        view = inflater.inflate(R.layout.fragment_message,container,false);

        chatRecycler = view.findViewById(R.id.chat_RecyclerView);
        chatRecycler.setHasFixedSize(true);
        chatRecycler.setLayoutManager(new LinearLayoutManager(getContext()));



        chatRecycler.setAdapter(new ChatAdapter(chatModels(),this));

        RecyclerView.ItemDecoration divider = new DividerItemDecoration(getContext(), DividerItemDecoration.VERTICAL);
        chatRecycler.addItemDecoration(divider);

        return view;
    }

    private List<ChatModel> chatModels(){

        chatModelList = new ArrayList<>();

        chatModelList.add(new ChatModel(R.drawable.user,
                "Juan Dela Cruz", "Lorem ipsum, lorem ipsum","11:59"));
        chatModelList.add(new ChatModel(R.drawable.user,
                "Ivan Dasigan", "Lorem ipsum, lorem ipsum","11:59"));

        return chatModelList;



    }

    @Override
    public void onClickMessage(int adapterPosition) {
        Intent intent = new Intent(getActivity(), MessageBox.class);
        startActivity(intent);
    }
}