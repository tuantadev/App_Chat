package com.example.appchat.ui.main.addfriend;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.example.appchat.R;
import com.example.appchat.model.FriendToAdd;
import com.example.appchat.ui.main.chat.FriendAdapter;

public class AddFriendAdapter extends RecyclerView.Adapter<AddFriendAdapter.HoldeFriend> {

    public IAddFriend inter;

    public AddFriendAdapter(IAddFriend inter) {
        this.inter = inter;
    }

    @NonNull
    @Override
    public HoldeFriend onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new HoldeFriend(
                LayoutInflater.from(parent.getContext())
                        .inflate(R.layout.frag_friend_to_add,parent,false));
    }

    @Override
    public void onBindViewHolder(@NonNull final HoldeFriend holder, int position) {
        FriendToAdd friend = inter.getData(position);
        Glide.with(holder.avatar)
                .load(friend.getAvatar())
                .into(holder.avatar);

        holder.username.setText(
                friend.getUsername()
        );
        holder.addFriend.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                inter.onClickItem(holder.getAdapterPosition());
            }
        });
    }

    @Override
    public int getItemCount() {
        return inter.getCount();
    }

    public interface IAddFriend{
        int getCount();
        FriendToAdd getData(int pos);
        void onClickItem(int position);
    }

    static class HoldeFriend extends RecyclerView.ViewHolder {
        private ImageView avatar;
        private TextView username;
        private ImageView addFriend;
        public HoldeFriend(@NonNull View itemView) {
            super(itemView);
            avatar = itemView.findViewById(R.id.avatar_another);
            username = itemView.findViewById(R.id.name_of_another);
            addFriend = itemView.findViewById(R.id.btn_add_friend);
        }
    }
}
