package com.example.appchat.ui.main.chat;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.appchat.GlideApp;
import com.example.appchat.R;
import com.example.appchat.model.response.StoryFriendResponse;


public class StoryFriendAdapter extends RecyclerView.Adapter<StoryFriendAdapter.StoryMainHolder> {
    private IStoryMain iStoryMain;

    public StoryFriendAdapter(IStoryMain IStoryMain) {
        this.iStoryMain = IStoryMain;
    }
    @NonNull
    @Override
    public StoryMainHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new StoryMainHolder(LayoutInflater.from(parent.getContext()).inflate(R.layout.story_chat_item, parent, false));
    }

    @Override
    public void onBindViewHolder(@NonNull final StoryMainHolder holder, final int position) {
        StoryFriendResponse storyChatReponse = iStoryMain.getData(position);
        if (storyChatReponse.getFriend_avatar() == null) {
            GlideApp.with(holder.itemView)
                    .load(R.drawable.default_ava)
                    .into(holder.iv);
        } else {
            GlideApp.with(holder.iv)
                    .load(storyChatReponse.getFriend_avatar())
                    .error(R.drawable.default_ava)
                    .into(holder.iv);
        }
        holder.tv.setText(storyChatReponse.getFriend_nameofchat());
        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                   iStoryMain.onClick(holder.getAdapterPosition());
            }
        });
    }

    @Override
    public int getItemCount() {
        return iStoryMain.getCount();
    }

    public interface IStoryMain {
        int getCount();
        StoryFriendResponse getData(int positions);
        void onClick(int pos);
    }

    static class StoryMainHolder extends RecyclerView.ViewHolder {
        private ImageView iv;
        private TextView tv;

        public StoryMainHolder(@NonNull View itemView) {
            super(itemView);
            iv = itemView.findViewById(R.id.iv_avatar_story);
            tv = itemView.findViewById(R.id.tv_namestory);
        }
    }
}
