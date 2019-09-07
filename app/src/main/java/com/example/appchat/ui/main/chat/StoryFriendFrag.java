//package com.example.appchat.ui.main.chat;
//
//import android.content.Intent;
//import android.os.Bundle;
//import android.view.LayoutInflater;
//import android.view.View;
//import android.view.ViewGroup;
//
//import androidx.annotation.NonNull;
//import androidx.annotation.Nullable;
//import androidx.fragment.app.Fragment;
//import androidx.recyclerview.widget.LinearLayoutManager;
//import androidx.recyclerview.widget.RecyclerView;
//
//import com.example.appchat.R;
//import com.example.appchat.interact.Common;
//import com.example.appchat.interact.CommonData;
//import com.example.appchat.interact.UserService;
//import com.example.appchat.model.response.BaseResponse;
//import com.example.appchat.model.response.StoryFriendResponse;
//import com.example.appchat.ui.chat.Chat;
//
//import java.util.List;
//
//import retrofit2.Call;
//import retrofit2.Callback;
//import retrofit2.Response;
//
//public class StoryFriendFrag extends Fragment implements StoryFriendAdapter.IStoryMain {
//    private RecyclerView rc_storyFriend;
//    private List<StoryFriendResponse> storyFriendResponses;
//    private UserService userService;
//    private StoryFriendAdapter storyFriendAdapter;
//
//    @Nullable
//    @Override
//    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
//        return inflater.inflate(R.layout.frag_story_friend,container,false);
//    }
//
//    @Override
//    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
//        super.onViewCreated(view, savedInstanceState);
//        rc_storyFriend =view.findViewById(R.id.rc_story_friend);
//        rc_storyFriend.setLayoutManager(new LinearLayoutManager(getContext(),RecyclerView.HORIZONTAL,false));
//        storyFriendAdapter = new StoryFriendAdapter(this);
//        rc_storyFriend.setAdapter(storyFriendAdapter);
//        getStoryFriend();
//    }
//    private void getStoryFriend(){
//        userService = Common.getUserService();
//        userService.getAllFriendStory(CommonData.getInstance().getUserProfile().getId()).enqueue(new Callback<BaseResponse<List<StoryFriendResponse>>>() {
//            @Override
//            public void onResponse(Call<BaseResponse<List<StoryFriendResponse>>> call, Response<BaseResponse<List<StoryFriendResponse>>> response) {
//                storyFriendResponses = response.body().getData();
//              storyFriendAdapter.notifyDataSetChanged();
//            }
//            @Override
//            public void onFailure(Call<BaseResponse<List<StoryFriendResponse>>> call, Throwable t) {
//                t.printStackTrace();
//            }
//        });
//    }
//    @Override
//    public int getCount() {
//        if(storyFriendResponses == null){
//            return 0;
//        }else {
//            return storyFriendResponses.size();
//        }
//    }
//
//    @Override
//    public StoryFriendResponse getData(int positions) {
//        return storyFriendResponses.get(positions);
//    }
//
//    @Override
//    public void onClick(int pos) {
//        Intent intent = new Intent();
//        intent.setClass(getContext(), Chat.class);
//        intent.putExtra("CHATFRIEND",storyFriendResponses.get(pos));
//        startActivity(intent);
//    }
//}
