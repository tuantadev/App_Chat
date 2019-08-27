package com.example.appchat.model.request;

public class RegisterRequest {

    private String username;
    private String password;
    private String name_of_chat;

    public String getName_of_chat() {
        return name_of_chat;
    }

    public void setName_of_chat(String name_of_chat) {
        this.name_of_chat = name_of_chat;
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
}
