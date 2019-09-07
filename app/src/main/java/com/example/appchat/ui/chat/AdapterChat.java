package com.example.appchat.ui.chat;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.example.appchat.GlideApp;
import com.example.appchat.interact.Common;
import com.example.appchat.interact.CommonData;
import com.example.appchat.R;
import com.example.appchat.model.response.MessageChatResponse;

public class AdapterChat extends RecyclerView.Adapter<RecyclerView.ViewHolder> {
    private static final int SEND_TEXT = 0;
    private static final int RECEIVE_TEXT = 1;
    private static final int SEND_IMG = 2;
    private static final int RECEIVE_IMG = 3;
    private IChat inter;

    public AdapterChat(IChat inter) {
        this.inter = inter;
    }

    @Override
    public int getItemViewType(int position) {
        String type = inter.getData(position).getType();
        if (inter.getData(position).getSenderId()
                == CommonData.getInstance().getUserProfile().getId()) {
            if (type == null || type.equals(MessageChatResponse.TYPE_TEXT)) {
                return SEND_TEXT;
            }
            return SEND_IMG;
        }
        if (type == null || type.equals(MessageChatResponse.TYPE_TEXT)) {
            return RECEIVE_TEXT;
        }
        return RECEIVE_IMG;
    }

    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        switch (viewType) {
            case SEND_TEXT:
                return new SendViewHolder(
                        LayoutInflater.from(parent.getContext())
                                .inflate(R.layout.user_chat, parent, false));
            case SEND_IMG:
                return new SendImageViewHolder(
                        LayoutInflater.from(parent.getContext())
                                .inflate(R.layout.item_send_image, parent, false));
            case RECEIVE_TEXT:
                return new ReceiveViewHolder(
                        LayoutInflater.from(parent.getContext())
                                .inflate(R.layout.friend_chat, parent, false));
            default:
                return new ReceiveImageViewHolder(
                        LayoutInflater.from(parent.getContext())
                                .inflate(R.layout.item_receive_image, parent, false));
        }
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder viewHolder, int position) {
        MessageChatResponse messageChatResponse = inter.getData(position);

        switch (getItemViewType(position)) {
            case SEND_TEXT:
                SendViewHolder holder = (SendViewHolder) viewHolder;
                holder.tv.setText(messageChatResponse.getContent());

                break;
            case RECEIVE_TEXT:
                ReceiveViewHolder holderText = (ReceiveViewHolder) viewHolder;
                holderText.tv.setText(messageChatResponse.getContent());
                Glide.with(holderText.im)
                        .load(inter.img())
                        .into(holderText.im);
                break;
            case SEND_IMG:
                final SendImageViewHolder sendImage = (SendImageViewHolder) viewHolder;
                GlideApp.with(sendImage.im)
                        .load(Common.getLinkImage(messageChatResponse.getContent()))
                        .error(R.drawable.default_ava)
                        .placeholder(R.drawable.default_ava)
                        .into(sendImage.im);
                sendImage.im.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        inter.itemCLick(sendImage.getAdapterPosition());
                    }
                });

                break;
            case RECEIVE_IMG:
                final ReceiveImageViewHolder reImage = (ReceiveImageViewHolder) viewHolder;
                GlideApp.with(reImage.im)
                        .load(Common.getLinkImage(messageChatResponse.getContent()))
                        .error(R.drawable.default_ava)
                        .placeholder(R.drawable.default_ava)
                        .into(reImage.im);
                reImage.im.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        inter.itemCLick(reImage.getAdapterPosition());
                    }
                });
                Glide.with(reImage.avatar)
                        .load(inter.img())
                        .into(reImage.avatar);
                break;
            default:
                break;
        }
    }

    public interface IChat {
        int getCount();
        void itemCLick(int pos);
        MessageChatResponse getData(int pos);
        String img();
    }

    @Override
    public int getItemCount() {
        return inter.getCount();
    }

    static class SendViewHolder extends RecyclerView.ViewHolder {
        private TextView tv;

        public SendViewHolder(@NonNull View itemView) {
            super(itemView);
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

    static class SendImageViewHolder extends RecyclerView.ViewHolder {
        private ImageView im;

        public SendImageViewHolder(@NonNull View itemView) {
            super(itemView);
            im = itemView.findViewById(R.id.iv_img);
        }
    }

    static class ReceiveImageViewHolder extends RecyclerView.ViewHolder {
        private ImageView im, avatar;

        public ReceiveImageViewHolder(@NonNull View itemView) {
            super(itemView);
            im = itemView.findViewById(R.id.iv_img);
            avatar = itemView.findViewById(R.id.iv_avatar);
        }
    }
}
