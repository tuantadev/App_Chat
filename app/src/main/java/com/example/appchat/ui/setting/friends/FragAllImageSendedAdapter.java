package com.example.appchat.ui.setting.friends;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.example.appchat.R;
import com.example.appchat.interact.Common;
import com.example.appchat.model.response.FriendChated;
import com.example.appchat.model.response.MessageChatResponse;


public class FragAllImageSendedAdapter extends RecyclerView.Adapter<FragAllImageSendedAdapter.Holder> {

    private IIMage iiMage;

    public FragAllImageSendedAdapter(IIMage iiMage) {
        this.iiMage = iiMage;
    }

    @NonNull
    @Override
    public Holder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new Holder(LayoutInflater.from(parent.getContext()).inflate(R.layout.item_squareimageview,parent,false));
    }

    @Override
    public void onBindViewHolder(@NonNull final Holder holder, int position) {
        MessageChatResponse messageChatResponse = iiMage.getData(position);
        Glide.with(holder.im)
                .load(Common.getLinkImage(messageChatResponse.getContent()))
                .error(R.drawable.default_ava)
                .into(holder.im);
        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                iiMage.onClick(holder.getAdapterPosition());
            }
        });
    }

    @Override
    public int getItemCount() {
        return iiMage.getCount();
    }

    public interface IIMage{
        int getCount();
        MessageChatResponse getData(int pos);
        void onClick(int pos);
    }

    static class Holder extends RecyclerView.ViewHolder {
        private ImageView im;
        public Holder(@NonNull View itemView) {
            super(itemView);
            im = itemView.findViewById(R.id.iv_img);
        }
    }
}
