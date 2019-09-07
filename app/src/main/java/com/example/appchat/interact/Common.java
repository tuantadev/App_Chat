package com.example.appchat.interact;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import java.util.concurrent.TimeUnit;

import okhttp3.OkHttpClient;
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
                .client( new OkHttpClient.Builder()
                        .connectTimeout(10, TimeUnit.MINUTES)
                        .writeTimeout(10, TimeUnit.MINUTES)
                        .readTimeout(30, TimeUnit.MINUTES)
                        .build())
                .build();

        UserService userService =
                retrofit.create(UserService.class);
        return userService;
    }

    public static String getLinkImage(String path){
        return Constant.duphong+"/getImage?fileName="+path;
    }
}
