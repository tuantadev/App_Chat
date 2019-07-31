package com.example.appchat.model.response;

public class FriendResponse {
    private int id;
    private int friendId;

    public int getFriendId() {
        return friendId;
    }

    public void setFriendId(int friendId) {
        this.friendId = friendId;
    }

    private String friendUsername;
    private String nickname;
    private String friendAvatar;

    public String getNickname() {
        return nickname;
    }
    public void setNickname(String nickname) {
        this.nickname = nickname;
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
