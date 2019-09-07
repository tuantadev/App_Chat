package com.example.appchat.interact;

import com.example.appchat.model.UserProfile;

public class CommonData {

    private UserProfile userProfile;
    private static CommonData instance;
    public static CommonData getInstance(){

        if (instance == null ){
            instance = new CommonData();
        }
        return instance;
    }

    public CommonData() {

    }

    public UserProfile getUserProfile() {
        return userProfile;
    }

    public void setUserProfile(UserProfile userProfile) {
        this.userProfile = userProfile;
    }
}
