package com.example.appchat.ui.main.chat;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.viewpager.widget.ViewPager;

import com.bumptech.glide.Glide;
import com.example.appchat.GlideApp;
import com.example.appchat.R;
import com.example.appchat.interact.CommonData;
import com.example.appchat.model.response.StoryFriendResponse;
import com.example.appchat.ui.chat.Chat;
import com.example.appchat.ui.setting.user.ManagerUserProfileFrag;
import com.google.android.material.tabs.TabLayout;

import java.util.List;

public class ChatFrag extends Fragment implements StoryFriendFrag.IStoryFriend,View.OnClickListener {
    private ViewPager vp;
    private ImageView ava;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.frag_main, container, false);
    }

    @Override
    public void onViewCreated(@NonNull final View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        vp = view.findViewById(R.id.vp);
        TabLayout tab = view.findViewById(R.id.tab);
        tab.setupWithViewPager(vp);

        ava = view.findViewById(R.id.avatar_main);
        Glide.with(ava)
                .load(CommonData.getInstance().getUserProfile().getAvatar())
                .error(R.drawable.default_ava)
                .into(ava);
        vp.setAdapter(new ChatMainAdapter(getChildFragmentManager()));

    }

    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.avatar_main:
                openSettingAcc();
                break;
        }
    }

    private void openSettingAcc() {
        Intent intent = new Intent();
        intent.setClass(getContext(), ManagerUserProfileFrag.class);
        startActivity(intent);
    }

    @Override
    public void onResume() {
        super.onResume();
        Glide.with(ava)
                .load(CommonData.getInstance().getUserProfile().getAvatar())
                .error(R.drawable.default_ava)
                .into(ava);
    }

    @Override
    public StoryFriendFrag getData() {
        return new StoryFriendFrag();
    }
//    @Override
//    public void onClick(int pos) {
//        Intent intent = new Intent();
//        intent.setClass(getContext(), Chat.class);
//        intent.putExtra("CHAT", storyFriendResponses.get(pos));
//        startActivity(intent);
//    }
}
