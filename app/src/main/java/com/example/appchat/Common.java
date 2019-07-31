package com.example.appchat;

import com.example.appchat.interact.Constant;
import com.example.appchat.interact.UserService;

import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class Common {

    public static UserService getUserService(){

        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(Constant.URL_BASE)
                .addConverterFactory(GsonConverterFactory.create())
                .build();
        UserService userService =
                retrofit.create(UserService.class);
        return userService;
    }

}
