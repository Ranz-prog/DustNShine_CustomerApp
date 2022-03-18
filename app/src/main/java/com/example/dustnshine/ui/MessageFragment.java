package com.example.dustnshine.ui;

import android.content.Context;
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

import com.cometchat.pro.core.CometChat;
import com.cometchat.pro.core.GroupsRequest;
import com.cometchat.pro.exceptions.CometChatException;
import com.cometchat.pro.models.Group;
import com.example.dustnshine.models.ChatModel;
import com.example.dustnshine.R;
import com.example.dustnshine.adapter.ChatAdapter;

import java.util.ArrayList;
import java.util.List;

public class MessageFragment extends Fragment {

    private RecyclerView chatRecycler;
    private View view;
    private List<ChatModel> chatModelList;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {


        view = inflater.inflate(R.layout.fragment_message,container,false);

        chatRecycler = view.findViewById(R.id.chat_RecyclerView);
//        chatRecycler.setHasFixedSize(true);
//        chatRecycler.setLayoutManager(new LinearLayoutManager(getContext()));

        getGroupedList();

       // chatRecycler.setAdapter(new ChatAdapter(chatModels(),this));

//        RecyclerView.ItemDecoration divider = new DividerItemDecoration(getContext(), DividerItemDecoration.VERTICAL);
//        chatRecycler.addItemDecoration(divider);

        return view;
    }

    private void getGroupedList() {
        GroupsRequest groupsRequest = new GroupsRequest.GroupsRequestBuilder().build();

        groupsRequest.fetchNext(new CometChat.CallbackListener<List<Group>>() {
            @Override
            public void onSuccess(List <Group> list) {
                updateUI(list);
            }
            @Override
            public void onError(CometChatException e) {

            }
        });

    }

    private void updateUI(List<Group> list) {
        chatRecycler.setLayoutManager(new LinearLayoutManager(getContext()));
        ChatAdapter chatAdapter = new ChatAdapter(list, getContext());

        chatRecycler.setAdapter(chatAdapter);
    }

//    private List<ChatModel> chatModels(){
//
//        chatModelList = new ArrayList<>();
//
//        chatModelList.add(new ChatModel(R.drawable.user,
//                "Juan Dela Cruz", "Lorem ipsum, lorem ipsum","11:59"));
//        chatModelList.add(new ChatModel(R.drawable.user,
//                "Ivan Dasigan", "Lorem ipsum, lorem ipsum","11:59"));
//
//        return chatModelList;
//
//
//
//    }

//    @Override
//    public void onClickMessage(int adapterPosition) {
//        Intent intent = new Intent(getActivity(), MessageBox.class);
//        startActivity(intent);
//    }
}
