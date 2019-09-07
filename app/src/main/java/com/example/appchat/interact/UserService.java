package com.example.appchat.interact;

import com.example.appchat.model.FriendToAdd;
import com.example.appchat.model.LastMess;
import com.example.appchat.model.request.AddFriendRequest;
import com.example.appchat.model.request.UpdateAvatar;
import com.example.appchat.model.request.RegisterRequest;
import com.example.appchat.model.response.AddFriendResponse;
import com.example.appchat.model.response.BaseResponse;
import com.example.appchat.model.response.FriendChated;
import com.example.appchat.model.response.FriendResponse;
import com.example.appchat.model.UserProfile;
import com.example.appchat.model.request.LoginRequest;
import com.example.appchat.model.response.MessageChatResponse;
import com.example.appchat.model.response.StoryFriendResponse;

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
    Call<BaseResponse<List<FriendChated>>> getAllFriendOfUser(
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

    @POST("/users/register")
    Call<BaseResponse> postInfoRegister(
            @Body RegisterRequest registerRequest
    );

    @Multipart
    @POST("/postImage")
    Call<String> upload(
            @Part MultipartBody.Part image
    );

    @POST("/users/changeAvatar")
    Call<UserProfile> changeAvartar(
            @Body UpdateAvatar updateAvatar
    );

    @GET(value = "/users/getAllNotFriend")
    Call<List<FriendToAdd>> getAllNotFriends(
            @Query("userId") int userId
    );

    @POST(value = "/users/getAllLastMess")
    Call<List<MessageChatResponse>> getAllLastMess(
            @Body List<LastMess> lastMesses
    );
    @GET(value = "users/getAllFriendStory")
    Call<BaseResponse<List<StoryFriendResponse>>> getAllFriendStory(
            @Query("userId") int userId
    );
    @GET(value = "/users/getFriendSendedMess")
    Call<List<FriendChated>> getFriendSendedMess(
            @Query("userId") int userId
    );

    @POST(value = "/users/requestAddFriend")
    Call<BaseResponse> requestAddFriend(
            @Body AddFriendRequest addFriendRequest
    );

    @GET(value = "/users/getAllFriendWaitResponse")
    Call<List<FriendToAdd>> getAllFriendWaitResponse(
            @Query("userId") int userId
    );

    @POST(value = "/users/accepted")
    Call<BaseResponse> accepted(
            @Body AddFriendResponse addFriendResponse
    );

    @POST(value = "/users/decline")
    Call<BaseResponse> decline(
            @Body AddFriendResponse addFriendResponse
    );

    @GET(value = "/users/allImg")
    Call<List<MessageChatResponse>> getImg(
            @Query("type") String type,
            @Query("senderId") int  senderId,
            @Query("receiverId") int receiverId

    );

    @POST(value = "/users/removeFriend")
    Call<BaseResponse> removeFriend(
            @Query("sender_id") int sender_id,
            @Query("receiver_id") int receiver_id
    );

    @POST(value = "/users/saveMess")
    Call<BaseResponse> save(
            @Body MessageChatResponse messageChatResponse
    );
}
