package com.example.appchat.interact;

import com.example.appchat.model.BaseResponse;
import com.example.appchat.model.FriendResponse;
import com.example.appchat.model.UserProfile;
import com.example.appchat.model.request.LoginRequest;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.GET;
import retrofit2.http.Query;

public interface UserService {
    @GET(value = "/getAllFriendUser")
    Call<List<FriendResponse>> getAllFriendUser();

    @GET(value = "/getAllInfoUser")
    Call<List<UserProfile>> getAllInfoUser(
            @Query("id") int id
    );
    @GET(value = "/login")
    Call<BaseResponse<UserProfile>> getInfoLogin(
            @Body LoginRequest loginRequest
    );
}
