package com.example.appchat.ui.main.chat;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import com.example.appchat.interact.Common;
import com.example.appchat.interact.CommonData;
import com.example.appchat.R;
import com.example.appchat.model.LastMess;
import com.example.appchat.model.response.FriendChated;
import com.example.appchat.model.response.FriendResponse;
import com.example.appchat.interact.UserService;
import com.example.appchat.model.response.MessageChatResponse;
import com.example.appchat.ui.chat.Chat;

import java.util.ArrayList;
import java.util.List;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class FriendFrag extends Fragment implements FriendAdapter.IFriend {

    private RecyclerView rcFriend;
    private List<FriendChated> friendChateds;
    private FriendAdapter adapter;
    private  UserService userService;
    private List<MessageChatResponse> messageChatResponses;
    private List<LastMess> lastMess;
    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.frag_friends, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        rcFriend = view.findViewById(R.id.rc_friend);
        rcFriend.setLayoutManager(new LinearLayoutManager(getContext()));
        adapter = new FriendAdapter(this);
        rcFriend.setAdapter(adapter);
        getAllFriendChat();
    }

    private void getAllFriendChat(){
        userService = Common.getUserService();
        if (  CommonData.getInstance().getUserProfile() == null ){
            return;
        }
        userService.getFriendSendedMess(CommonData.getInstance().getUserProfile().getId())
                .enqueue(new Callback<List<FriendChated>>() {
                    @Override
                    public void onResponse(Call<List<FriendChated>> call, Response<List<FriendChated>> response) {
                        friendChateds = response.body();
                        adapter.notifyDataSetChanged();
                        getAllLastMess();
                    }

                    @Override
                    public void onFailure(Call<List<FriendChated>> call, Throwable t) {
                        t.printStackTrace();
                    }
                });

    }

    private void getAllLastMess(){
        lastMess = new ArrayList<>();
        if (friendChateds == null){
            return;
        }
        for (int i = 0; i < friendChateds.size(); i++) {
            lastMess.add(new LastMess(CommonData.getInstance().getUserProfile().getId(),friendChateds.get(i).getFriend_id()));
        }
        userService.getAllLastMess(lastMess).enqueue(new Callback<List<MessageChatResponse>>() {
            @Override
            public void onResponse(Call<List<MessageChatResponse>> call, Response<List<MessageChatResponse>> response) {
                messageChatResponses = response.body();
                adapter.notifyDataSetChanged();
            }
            @Override
            public void onFailure(Call<List<MessageChatResponse>> call, Throwable t) {
                t.printStackTrace();
            }
        });
    }

    @Override
    public int getCount() {
        if (friendChateds == null ){
            return 0;
        }
        return friendChateds.size();
    }

    @Override
    public FriendChated getItem(int position) {
        return friendChateds.get(position);
    }

    @Override
    public void onClickItem(int position) {
        Intent intent = new Intent();
        intent.setClass(getContext(),
                Chat.class);
        intent.putExtra("FRIEND",
                 friendChateds.get(position));
        startActivity(intent);
    }

//    @Override
//    public MessageChatResponse getMes(int pos) {
//        return messageChatResponses.get(pos);
//    }

}
