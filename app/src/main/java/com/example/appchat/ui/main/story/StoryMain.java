package com.example.appchat.ui.main.story;

import android.widget.ImageView;

public class StoryMain {
    private int sender_id,receive_id;
    private ImageView mAvatar;
    private String mContentStory;
    private String mNameofChat;

    public StoryMain() {
    }

    public int getSender_id() {
        return sender_id;
    }

    public void setSender_id(int sender_id) {
        this.sender_id = sender_id;
    }

    public int getReceive_id() {
        return receive_id;
    }

    public void setReceive_id(int receive_id) {
        this.receive_id = receive_id;
    }

    public ImageView getmAvatar() {
        return mAvatar;
    }

    public void setmAvatar(ImageView mAvatar) {
        this.mAvatar = mAvatar;
    }

    public String getmContentStory() {
        return mContentStory;
    }

    public void setmContentStory(String mContentStory) {
        this.mContentStory = mContentStory;
    }

    public String getmNameofChat() {
        return mNameofChat;
    }

    public void setmNameofChat(String mNameofChat) {
        this.mNameofChat = mNameofChat;
    }
}
