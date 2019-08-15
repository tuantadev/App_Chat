package com.example.appchat.ui.main;

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
    public void onBindViewHolder(@NonNull final HolderFriend holder, int position) {
        FriendResponse data = iFriend.getData(position);
        Glide.with(holder.itemView.getContext())
                .load(data.getFriendAvatar())
//                .placeholder(R.drawable.default_ava)
////                .error(R.drawable.default_ava)
                .into(holder.ivAvatar);
        holder.nickName.setText(
                data.getFriendNameofchat()
        );
        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                iFriend.onClick(holder.getAdapterPosition());
            }
        });
    }

    @Override
    public int getItemCount() {
        return iFriend.getCount();
    }

    public interface IFriend{
        int getCount();
        FriendResponse getData(int pos);
        void onClick(int pos);
    }

    static class HolderFriend extends RecyclerView.ViewHolder {
        private ImageView ivAvatar;
        private TextView nickName;

        public HolderFriend(@NonNull View itemView) {
            super(itemView);
            ivAvatar = itemView.findViewById(R.id.iv_avatar);
            nickName = itemView.findViewById(R.id.tv_username);
        }
    }
}
