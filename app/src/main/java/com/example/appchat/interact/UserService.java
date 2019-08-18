package com.example.appchat.interact;

import com.example.appchat.model.response.BaseResponse;
import com.example.appchat.model.response.FriendResponse;
import com.example.appchat.model.UserProfile;
import com.example.appchat.model.request.LoginRequest;
import com.example.appchat.model.response.MessageChatResponse;

import java.util.List;

import okhttp3.MultipartBody;
import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.GET;
import retrofit2.http.Multipart;
import retrofit2.http.POST;
import retrofit2.http.Part;
import retrofit2.http.Query;

public interface UserService {

    @GET(value = "/users/getAllFriend")
    Call<BaseResponse<List<FriendResponse>>> getAllFriendOfUser(
            @Query("userId") int userId
    );
    @GET(value = "/users/getHistoryChat")
    Call<BaseResponse<List<MessageChatResponse>>> getHistoryChat(
            @Query("senderId") int senderId,
            @Query("receiverId") int receiverId
    );

    @POST("/users/login")
    Call<BaseResponse<UserProfile>> postInfoLogin(
            @Body LoginRequest loginRequest
    );

    @Multipart
    @POST("/postImage")
    Call<String> upload(
            @Part MultipartBody.Part image
    );
}
