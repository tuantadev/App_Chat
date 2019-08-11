package com.example.appchat.ui.main;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;


import com.bumptech.glide.Glide;
import com.example.appchat.interact.Common;
import com.example.appchat.interact.CommonData;
import com.example.appchat.R;
import com.example.appchat.model.response.BaseResponse;
import com.example.appchat.model.response.FriendResponse;
import com.example.appchat.interact.UserService;
import com.example.appchat.ui.chat.Chat;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class ChatMainFragment extends Fragment implements FriendAdapter.IFriend, View.OnClickListener {

    private RecyclerView rcFriend;
    private List<FriendResponse> friendResponses;
    private FriendAdapter adapter;
    private UserService userService;
    private ImageView avatar;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.frag_main, container, false);
        avatar = view.findViewById(R.id.avatar_main);
        getAllFriend();
        Glide.with(this)
                .load(CommonData.getInstance().getUserProfile().getAvatar())
                .centerCrop()
                .placeholder(R.drawable.default_ava)
                .into(avatar)
        ;
        rcFriend = view.findViewById(R.id.rc_friend);
        rcFriend.setLayoutManager(new LinearLayoutManager(getContext()));
        adapter = new FriendAdapter(this);
        rcFriend.setAdapter(adapter);

        return view;
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        view.findViewById(R.id.avatar_main).setOnClickListener(this);
        view.findViewById(R.id.search_click).setOnClickListener(this);
    }

    private void getAllFriend() {
        userService = Common.getUserService();
        userService.getAllFriendOfUser(
                CommonData.getInstance().getUserProfile().getId())
                .enqueue(new Callback<BaseResponse<List<FriendResponse>>>() {
                    @Override
                    public void onResponse(Call<BaseResponse<List<FriendResponse>>> call, Response<BaseResponse<List<FriendResponse>>> response) {
                        friendResponses = response.body().getData();
                        adapter.notifyDataSetChanged();
                    }

                    @Override
                    public void onFailure(Call<BaseResponse<List<FriendResponse>>> call, Throwable t) {
                        t.printStackTrace();
                    }
                });
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.search_click:
                break;
            default:
                break;
        }
    }

    @Override
    public int getCount() {
        if (friendResponses == null) {
            return 0;
        }
        return friendResponses.size();
    }

    @Override
    public FriendResponse getData(int pos) {
        return friendResponses.get(pos);
    }

    @Override
    public void onClick(int pos) {
        openChat(pos);
    }

    private void openChat(int pos){
        Intent intent = new Intent();
        intent.setClass(getContext(), Chat.class);
//        intent.putExtra("name_chat", friendResponses.get(pos).getFriendNameofchat());
//        intent.putExtra("avatar", friendResponses.get(pos).getFriendAvatar());
//        intent.putExtra("id", friendResponses.get(pos).getFriendId());
        startActivity(intent);
    }
}
