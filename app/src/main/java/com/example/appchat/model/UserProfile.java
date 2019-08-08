package com.example.appchat.model;

import java.util.Date;

public class UserProfile {
    private int id;
    private String username;
    private String password;
    private String avatar;
    private String name_of_chat;
    private String createdTime;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getAvatar() {
        return avatar;
    }

    public void setAvatar(String avatar) {
        this.avatar = avatar;
    }

    public String getName_of_chat() {
        return name_of_chat;
    }

    public void setName_of_chat(String name_of_chat) {
        this.name_of_chat = name_of_chat;
    }

    public String getCreatedTime() {
        return createdTime;
    }

    public void setCreatedTime(String createdTime) {
        this.createdTime = createdTime;
    }
}
