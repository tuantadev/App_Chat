package com.example.appchat.ui.main.chat;

import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentPagerAdapter;

import com.example.appchat.ui.main.addfriend.OtherFriendFragment;
import com.example.appchat.ui.main.allfriend.AllFriendFrag;
import com.example.appchat.ui.main.checkfriend.FriendWaitResponse;

public class ChatMainAdapter extends FragmentPagerAdapter {

    public ChatMainAdapter(FragmentManager fm) {
        super(fm);
    }

    @Override
    public Fragment getItem(int position) {
        if (position == 0){
            return new FriendFrag();
        }
        if (position == 1){
            return new OtherFriendFragment();
        }
        if (position == 2){
            return new FriendWaitResponse();
        }
        return new AllFriendFrag();
    }

    @Override
    public int getCount() {
        return 4;
    }

    @Nullable
    @Override
    public CharSequence getPageTitle(int position) {
        if (position == 0){
            return "Friend";
        }
        if (position == 1){
            return "Other Friend";
        }
        if (position == 2){
            return "Accept make Friend";
        }
        return "All Friend";
    }

}
