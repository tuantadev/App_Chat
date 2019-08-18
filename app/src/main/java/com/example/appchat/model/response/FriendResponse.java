package com.example.appchat.model.response;

import java.io.Serializable;

public class FriendResponse implements Serializable {

    private int id;
    private int friendId;
    private String friendNameofchat;
    private String friendUsername;
    private String friendAvatar;

    public String getFriendUsername() {
        return friendUsername;
    }

    public void setFriendUsername(String friendUsername) {
        this.friendUsername = friendUsername;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getFriendId() {
        return friendId;
    }

    public void setFriendId(int friendId) {
        this.friendId = friendId;
    }

    public String getFriendNameofchat() {
        return friendNameofchat;
    }

    public void setFriendNameofchat(String friendNameofchat) {
        this.friendNameofchat = friendNameofchat;
    }

    public String getFriendAvatar() {
        return friendAvatar;
    }

    public void setFriendAvatar(String friendAvatar) {
        this.friendAvatar = friendAvatar;
    }
}
