package com.example.appchat.interact;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class Common {

    public static UserService getUserService(){
        Gson gson = new GsonBuilder()
                .setLenient()
                .create();

        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(Constant.duphong)
                .addConverterFactory(GsonConverterFactory.create(gson))
                .build();

        UserService userService =
                retrofit.create(UserService.class);
        return userService;
    }
}
