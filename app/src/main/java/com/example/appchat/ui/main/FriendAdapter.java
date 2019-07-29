package com.example.appchat.ui.chat;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.example.appchat.R;
import com.example.appchat.model.response.FriendResponse;


public class FriendAdapter extends RecyclerView.Adapter<FriendAdapter.HolderFriend> {

    private IFriend iFriend;
    public FriendAdapter(IFriend iFriend){
        this.iFriend = iFriend;
    }

    @NonNull
    @Override
    public HolderFriend onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new HolderFriend(LayoutInflater.from(parent.getContext()).
                inflate(R.layout.frag_friend_chat,parent,false));
    }

    @Override
    public void onBindViewHolder(@NonNull HolderFriend holder, int position) {
        FriendResponse data = iFriend.getItem(position);
        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

            }
        });
        Glide.with(holder.ivAvatar)
                .load(data.getFriendAvatar())
                .into(holder.ivAvatar);
        holder.tvUsername.setText(
                data.getFriendUsername()
        );
        holder.tvName.setText(
                data.getNickname()
        );
    }

    @Override
    public int getItemCount() {
        return iFriend.getCount();
    }

    public interface IFriend{
        int getCount();
        FriendResponse getItem(int pos);
    }

    static class HolderFriend extends RecyclerView.ViewHolder {
        private ImageView ivAvatar;
        private TextView tvName, tvUsername;
        public HolderFriend(@NonNull View itemView) {
            super(itemView);
            ivAvatar = itemView.findViewById(R.id.iv_avatar);
            tvName = itemView.findViewById(R.id.tv_name);
            tvUsername = itemView.findViewById(R.id.tv_username);
        }
    }
}
