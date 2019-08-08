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
import com.example.appchat.model.response.FriendResponse;
import com.example.appchat.interact.UserService;
import com.example.appchat.ui.chat.Chat;

import java.io.Serializable;
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
        View view = inflater.inflate(R.layout.frag_main,container,false);
        avatar = view.findViewById(R.id.avatar_main);
        Glide.with(this)
                .load(CommonData.getInstance().getUserProfile().getAvatar())
                .centerCrop()
                .placeholder(R.drawable.default_ava)
                .into(avatar)
        ;
        return view;
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        rcFriend = view.findViewById(R.id.rc_friend);
        rcFriend.setLayoutManager(new LinearLayoutManager(getContext()));
        adapter = new FriendAdapter(this);
        rcFriend.setAdapter(adapter);
        getAllFriend();
        view.findViewById(R.id.avatar_main).setOnClickListener(this);
        view.findViewById(R.id.search_click).setOnClickListener(this);
    }

    private void getAllFriend(){
        userService = Common.getUserService();
        userService.getAllFriendOfUser(
                CommonData.getInstance().getUserProfile().getId())
                .enqueue(new Callback<List<FriendResponse>>() {
            @Override
            public void onResponse(Call<List<FriendResponse>> call, Response<List<FriendResponse>> response) {
                friendResponses = response.body();
                adapter.notifyDataSetChanged();
            }

            @Override
            public void onFailure(Call<List<FriendResponse>> call, Throwable t) {
                t.printStackTrace();
            }
        });
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()){
            case R.id.search_click:
                break;
                default:
                    break;
        }
    }

    @Override
    public int getCount() {
        if (friendResponses == null ){
            return 0;
        }
        return friendResponses.size();
    }

    @Override
    public FriendResponse getItem(int pos) {
        return friendResponses.get(pos);
    }

    @Override
    public void onClickItem(int pos) {
        Intent intent = new Intent();
        intent.setClass(getContext(), Chat.class);
        intent.putExtra("FRIEND",
                (Serializable) friendResponses.get(pos));
        startActivity(intent);
    }

}
