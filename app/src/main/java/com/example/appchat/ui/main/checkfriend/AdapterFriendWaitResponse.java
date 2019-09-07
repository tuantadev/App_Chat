package com.example.appchat.ui.main.checkfriend;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.example.appchat.R;
import com.example.appchat.model.FriendToAdd;

public class AdapterFriendWaitResponse extends RecyclerView.Adapter<AdapterFriendWaitResponse.HolderFriend> {

    private IFriendWait iFriendWait;

    public AdapterFriendWaitResponse(IFriendWait iFriendWait) {
        this.iFriendWait = iFriendWait;
    }

    @NonNull
    @Override
    public HolderFriend onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new HolderFriend(
                LayoutInflater.from(parent.getContext())
                        .inflate(R.layout.adapter_friend_wait_response,parent,false));
    }

    @Override
    public void onBindViewHolder(@NonNull final HolderFriend holder, int position) {
        FriendToAdd friends = iFriendWait.getData(position);
        Glide.with(holder.avatar)
                .load(friends.getAvatar())
                .into(holder.avatar);

        holder.username.setText(friends.getUsername());
        holder.addFriend.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                iFriendWait.onClickAccept(holder.getAdapterPosition());
            }
        });
        holder.decline.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                iFriendWait.onClickDecline(holder.getAdapterPosition());
            }
        });

    }

    @Override
    public int getItemCount() {
        return iFriendWait.getCount();
    }

    public interface IFriendWait{
        int getCount();
        FriendToAdd getData(int pos);
        void onClickAccept(int pos);
        void onClickDecline(int pos);
    }
    static class HolderFriend extends RecyclerView.ViewHolder{
        private ImageView avatar;
        private TextView username;
        private ImageView addFriend;
        private ImageView decline;
        public HolderFriend(@NonNull View itemView) {
            super(itemView);
            username = itemView.findViewById(R.id.name_of_friends_wait);
            avatar = itemView.findViewById(R.id.avatar_friends_wait);
            addFriend = itemView.findViewById(R.id.btn_accept_friends_wait);
            decline = itemView.findViewById(R.id.btn_decline);
        }
    }
}
