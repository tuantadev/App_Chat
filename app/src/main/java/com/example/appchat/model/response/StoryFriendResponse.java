package com.example.appchat.model.response;

import java.io.Serializable;
import java.util.Date;

public class StoryFriendResponse implements Serializable {
    private int id;
    private int friend_id;
    private String friend_avatar;
    private String friend_image;
    private String friend_nameofchat;
    private Date createdTime;

    public StoryFriendResponse() {
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getFriend_id() {
        return friend_id;
    }

    public void setFriend_id(int friend_id) {
        this.friend_id = friend_id;
    }

    public String getFriend_avatar() {
        return friend_avatar;
    }

    public void setFriend_avatar(String friend_avatar) {
        this.friend_avatar = friend_avatar;
    }

    public String getFriend_image() {
        return friend_image;
    }

    public void setFriend_image(String friend_image) {
        this.friend_image = friend_image;
    }

    public String getFriend_nameofchat() {
        return friend_nameofchat;
    }

    public void setFriend_nameofchat(String friend_nameofchat) {
        this.friend_nameofchat = friend_nameofchat;
    }

    public Date getCreatedTime() {
        return createdTime;
    }

    public void setCreatedTime(Date createdTime) {
        this.createdTime = createdTime;
    }
}
