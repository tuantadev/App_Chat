package com.example.appchat.ui.main.story;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.appchat.GlideApp;
import com.example.appchat.R;


public class StoryMainAdapter extends RecyclerView.Adapter<StoryMainAdapter.StoryMainHolder> {
    private IStoryMain iStoryMain;

    public StoryMainAdapter(IStoryMain iStoryMainAdapter) {
        this.iStoryMain = iStoryMainAdapter;
    }


    @NonNull
    @Override
    public StoryMainHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new StoryMainHolder(LayoutInflater.from(parent.getContext()).inflate(R.layout.story_chat_item, parent, false));
    }

    @Override
    public void onBindViewHolder(@NonNull final StoryMainHolder holder, final int position) {
        StoryMain storyMain = iStoryMain.getData(position);
        if (storyMain.getmAvatar() == null) {
            GlideApp.with(holder.itemView)
                    .load(R.drawable.default_ava)
                    .into(holder.iv);
        } else {
            GlideApp.with(holder.iv)
                    .load(R.drawable.default_ava)
                    .error(R.drawable.default_ava)
                    .placeholder(R.drawable.default_ava)
                    .into(holder.iv);
        }
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
        StoryMain getData(int positions);
        void onClick(int pos);
    }

    static class StoryMainHolder extends RecyclerView.ViewHolder {
        private ImageView iv;

        public StoryMainHolder(@NonNull View itemView) {
            super(itemView);
            iv = itemView.findViewById(R.id.avatar_story);
        }
    }

}
