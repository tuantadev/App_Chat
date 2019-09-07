package com.example.appchat.ui.main.allfriend;

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

public class AdapterAllFriend extends RecyclerView.Adapter<AdapterAllFriend.Holder> {

    private IAllFriend inter;

    public AdapterAllFriend(IAllFriend inter) {
        this.inter = inter;
    }

    @NonNull
    @Override
    public Holder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new Holder(
                LayoutInflater.from(parent.getContext())
                        .inflate(R.layout.adapter_all_friends,parent,false)
        );
    }

    @Override
    public void onBindViewHolder(@NonNull final Holder holder, int position) {
        FriendChated friendChated = inter.getData(position);

        Glide.with(holder.avatar)
                .load(friendChated.getFriend_avatar())
                .into(holder.avatar);

        holder.name.setText(friendChated.getFriend_nameofchat());

        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                inter.onClick(holder.getAdapterPosition());
            }
        });
    }

    @Override
    public int getItemCount() {
        return inter.getCount();
    }

    public interface IAllFriend{
        int getCount();
        FriendChated getData(int pos);
        void onClick(int pos);
    }

    static class Holder extends RecyclerView.ViewHolder {
        private TextView name;
        private ImageView avatar;
        public Holder(@NonNull View itemView) {
            super(itemView);
            name = itemView.findViewById(R.id.username_all_friends);
            avatar = itemView.findViewById(R.id.ava_all_friends);
        }
    }
}
