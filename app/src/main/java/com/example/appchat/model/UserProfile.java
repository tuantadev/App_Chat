package com.example.appchat.model;

import java.io.Serializable;
import java.util.Date;

public class UserProfile implements Serializable {
    private int id;
    private String username;
    private String password;
    private String avatar;
    private String nameofchat;
    private String createdTime;
    private String phonenumber;

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

    public String getNameofchat() {
        return nameofchat;
    }

    public void setNameofchat(String nameofchat) {
        this.nameofchat = nameofchat;
    }

    public String getCreatedTime() {
        return createdTime;
    }

    public void setCreatedTime(String createdTime) {
        this.createdTime = createdTime;
    }

    public String getPhonenumber() {
        return phonenumber;
    }

    public void setPhonenumber(String phonenumber) {
        this.phonenumber = phonenumber;
    }
}
