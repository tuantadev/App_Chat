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
import com.example.appchat.model.response.BaseResponse;
import com.example.appchat.model.response.FriendResponse;
import com.example.appchat.interact.UserService;
import com.example.appchat.model.response.MessageChatResponse;
import com.example.appchat.ui.chat.Chat;
import com.example.appchat.ui.main.chat.FriendAdapter;

import java.util.ArrayList;
import java.util.List;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class FriendFrag extends Fragment implements FriendAdapter.IFriend {

    private RecyclerView rcFriend;
    private List<FriendResponse> friendResponses;
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
        getAllFriend();
    }

    private void getAllFriend(){
        userService = Common.getUserService();
        if (  CommonData.getInstance().getUserProfile() == null ){
            return;
        }
        userService.getAllFriendOfUser(
                CommonData.getInstance().getUserProfile().getId())
                .enqueue(new Callback<BaseResponse<List<FriendResponse>>>() {
                    @Override
                    public void onResponse(Call<BaseResponse<List<FriendResponse>>> call, Response<BaseResponse<List<FriendResponse>>> response) {
                        friendResponses = response.body().getData();
//                        getAllLastMess();
                        adapter.notifyDataSetChanged();
                    }

                    @Override
                    public void onFailure(Call<BaseResponse<List<FriendResponse>>> call, Throwable t) {
                        t.printStackTrace();
                    }
                });
    }

    private void getAllLastMess(){
        lastMess = new ArrayList<>();
        for (int i = 0; i < friendResponses.size(); i++) {
            lastMess.add(new LastMess(CommonData.getInstance().getUserProfile().getId(),friendResponses.get(i).getFriendId()));
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
        if (friendResponses == null ){
            return 0;
        }
        return friendResponses.size();
    }

    @Override
    public FriendResponse getItem(int position) {
        return friendResponses.get(position);
    }

    @Override
    public void onClickItem(int position) {
        Intent intent = new Intent();
        intent.setClass(getContext(),
                Chat.class);
        intent.putExtra("FRIEND",
                friendResponses.get(position));
        startActivity(intent);
    }

//    @Override
//    public MessageChatResponse getMes(int pos) {
//        return messageChatResponses.get(pos);
//    }


}
