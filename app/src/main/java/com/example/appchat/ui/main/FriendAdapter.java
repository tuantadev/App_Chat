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

    private IFriend inter;

    public FriendAdapter(IFriend inter){
        this.inter = inter;
    }

    @NonNull
    @Override
    public HolderFriend onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        return new HolderFriend(
                LayoutInflater.from(viewGroup.getContext())
                        .inflate(R.layout.frag_friend_chat, viewGroup, false)
        );
    }

    @Override
    public void onBindViewHolder(@NonNull final HolderFriend holderFriend, int i) {
        System.out.println("setUp");
        FriendResponse data = inter.getItem(i);

        Glide.with(holderFriend.ivAvatar)
                .load(data.getFriendAvatar())
                .into(holderFriend.ivAvatar);

        holderFriend.tvUsername.setText(
                data.getFriendNameofchat()
        );
        holderFriend.itemView.setOnClickListener(
                new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                inter.onClickItem(holderFriend.getAdapterPosition());
            }
        });
    }

    @Override
    public int getItemCount() {
        return inter.getCount();
    }

    public interface IFriend{
        int getCount();
        FriendResponse getItem(int position);
        void onClickItem(int position);
    }

    static class HolderFriend extends RecyclerView.ViewHolder{
        private ImageView ivAvatar;
        private TextView  tvUsername;
        public HolderFriend(@NonNull View itemView) {
            super(itemView);
            ivAvatar = itemView.findViewById(R.id.iv_avatar);
            tvUsername = itemView.findViewById(R.id.tv_username);
        }
    }
}
