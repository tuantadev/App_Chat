package com.example.appchat.ui.main.chat;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;
import com.bumptech.glide.Glide;
import com.example.appchat.R;
import com.example.appchat.model.response.FriendChated;
import com.example.appchat.model.response.FriendResponse;
import com.example.appchat.model.response.MessageChatResponse;

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
        FriendChated data = inter.getItem(i);
//        MessageChatResponse mess = inter.getMes(i);
        Glide.with(holderFriend.ivAvatar)
                .load(data.getFriend_avatar())
                .into(holderFriend.ivAvatar);

        holderFriend.tvUsername.setText(
                data.getFriend_nameofchat()
        );
//        if (mess == null)
//        {
//            return;
//        }else
//            if (mess.getType().equals("IMG")){
//            holderFriend.lastMess.setText("");
//        }else
//            if (mess.getContent().length()>30){
//            holderFriend.lastMess.setText(mess.getContent().substring(0,30)+"...");
//        }else holderFriend.lastMess.setText(mess.getContent());

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
        FriendChated getItem(int position);
        void onClickItem(int position);
//        MessageChatResponse getMes(int pos);
    }

    static class HolderFriend extends RecyclerView.ViewHolder{
        private ImageView ivAvatar;
        private TextView  tvUsername;
        private TextView lastMess;
        public HolderFriend(@NonNull View itemView) {
            super(itemView);
            ivAvatar = itemView.findViewById(R.id.iv_avatar);
            tvUsername = itemView.findViewById(R.id.tv_username);
            lastMess = itemView.findViewById(R.id.last_mess);
        }
    }
}
