package com.example.appchat.interact;

import com.example.appchat.model.response.BaseResponse;
import com.example.appchat.model.response.FriendResponse;
import com.example.appchat.model.UserProfile;
import com.example.appchat.model.request.LoginRequest;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.Query;

public interface UserService {

    @GET(value = "/users/getAllFriend")
    Call<BaseResponse<List<FriendResponse>>> getAllFriendOfUser(
            @Query("userId") int userId
    );

    @POST("/users/login")
    Call<BaseResponse<UserProfile>> postInfoLogin(
            @Body LoginRequest loginRequest
    );
}
