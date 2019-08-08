package com.example.appchat.model.response;

public class FriendResponse {

    private int id;
    private int friendId;
    private String friendNameofchat;
    private String friendUsername;
    private String friendAvatar;

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

    public String getFriendAvatar() {
        return friendAvatar;
    }

    public void setFriendAvatar(String friendAvatar) {
        this.friendAvatar = friendAvatar;
    }
}
