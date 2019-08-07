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

    @GET(value = "/getAllFriend")
    Call<List<FriendResponse>> getAllFriendOfUser(
            @Query("id") int id
    );

    @POST(value = "/login")
    Call<BaseResponse<UserProfile>> postInfoLogin(
            @Body LoginRequest loginRequest
    );
}
