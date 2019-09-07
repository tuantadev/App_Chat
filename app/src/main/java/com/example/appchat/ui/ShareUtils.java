package com.example.appchat.ui;

import android.content.Context;
import android.content.SharedPreferences;

import com.example.appchat.model.UserProfile;
import com.google.gson.Gson;

public class ShareUtils {
    public static void saveUserProfile(Context context, UserProfile userProfile) {
        SharedPreferences sh = context.getSharedPreferences("SHARE", Context.MODE_PRIVATE);
        sh.edit().putString("USER",
                new Gson().toJson(userProfile))
                .apply();
    }

    public static UserProfile getUserProfile(Context context) {
        String userString = context.getSharedPreferences("SHARE", Context.MODE_PRIVATE)
                .getString("USER", null);
        if (userString != null) {
            return new Gson().fromJson(userString, UserProfile.class);
        }
        return null;
    }

    public static void clearProfile(Context context) {
        context.getSharedPreferences("SHARE", Context.MODE_PRIVATE)
                .edit().putString("USER",
                null)
                .apply();
    }
}
