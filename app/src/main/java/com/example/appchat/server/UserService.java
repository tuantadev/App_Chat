package com.example.appchat.server;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;

public interface UserService {
    @GET(value = "/getAllFriendUser")
    Call<List<FriendResponse>> getAllFriendUser();

    @GET(value = "/getAllInfoUser")
    Call<List<UserProfile>> getAllInfoUser(
            @Query("id") int id
    );
}
