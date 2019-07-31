package com.example.appchat.ui.chat;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.appchat.interact.CommonData;
import com.example.appchat.R;
import com.example.appchat.model.response.MessageChatResponse;

public class AdapterChat extends RecyclerView.Adapter<RecyclerView.ViewHolder> {

    private IChat inter;
    public AdapterChat(IChat inter){
        this.inter = inter;
    }

    @Override
    public int getItemViewType(int position) {
        if (inter.getData(position).getSenderId()== CommonData.getInstance().getUserProfile().getId()) return 1;
        else return 0;
    }

    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        if (viewType == 1) return new SendViewHolder(
                LayoutInflater.from(parent.getContext())
                        .inflate(R.layout.user_chat,parent,false));
        return new ReceiveViewHolder(
                LayoutInflater.from(parent.getContext())
                        .inflate(R.layout.friend_chat,parent,false));
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder viewHolder, int position) {
        MessageChatResponse messageChatResponse = inter.getData(position);

        if (getItemViewType(position) == 1){
            SendViewHolder holder = (SendViewHolder) viewHolder;
            holder.tv.setText(messageChatResponse.getContent());
        }else {
            ReceiveViewHolder holder = (ReceiveViewHolder) viewHolder;
            holder.tv.setText(messageChatResponse.getContent());
        }
    }

    public interface IChat{
        int getCount();
        MessageChatResponse getData(int pos);
    }
    @Override
    public int getItemCount() {
        return inter.getCount();
    }

    static class SendViewHolder extends RecyclerView.ViewHolder {
        private ImageView im;
        private TextView tv;
        public SendViewHolder(@NonNull View itemView) {
            super(itemView);
            im = itemView.findViewById(R.id.avatar_chat);
            tv = itemView.findViewById(R.id.tv_chat);
        }
    }

    static class ReceiveViewHolder extends RecyclerView.ViewHolder {
        private ImageView im;
        private TextView tv;
        public ReceiveViewHolder(@NonNull View itemView) {
            super(itemView);
            im = itemView.findViewById(R.id.avatar_chat);
            tv = itemView.findViewById(R.id.tv_chat);
        }
    }
}
