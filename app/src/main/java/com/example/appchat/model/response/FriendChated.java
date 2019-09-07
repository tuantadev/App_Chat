package com.example.appchat.model.response;

import java.io.Serializable;

public class FriendChated implements Serializable {
    private int friend_id;
    private String friend_nameofchat;
    private String friend_username;
    private String friend_avatar;
    private String phone;

    public int getFriend_id() {
        return friend_id;
    }

    public void setFriend_id(int friend_id) {
        this.friend_id = friend_id;
    }

    public String getFriend_nameofchat() {
        return friend_nameofchat;
    }

    public void setFriend_nameofchat(String friend_nameofchat) {
        this.friend_nameofchat = friend_nameofchat;
    }

    public String getFriend_username() {
        return friend_username;
    }

    public void setFriend_username(String friend_username) {
        this.friend_username = friend_username;
    }

    public String getFriend_avatar() {
        return friend_avatar;
    }

    public void setFriend_avatar(String friend_avatar) {
        this.friend_avatar = friend_avatar;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }
}
