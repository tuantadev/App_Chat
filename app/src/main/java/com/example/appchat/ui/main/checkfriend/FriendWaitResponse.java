package com.example.appchat.ui.main.checkfriend;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import com.example.appchat.R;
import com.example.appchat.interact.Common;
import com.example.appchat.interact.CommonData;
import com.example.appchat.interact.UserService;
import com.example.appchat.model.FriendToAdd;
import com.example.appchat.model.response.AddFriendResponse;
import com.example.appchat.model.response.BaseResponse;
import com.example.appchat.ui.main.addfriend.AddFriendAdapter;

import java.util.List;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class FriendWaitResponse extends Fragment implements AdapterFriendWaitResponse.IFriendWait {

    private List<FriendToAdd> friendToAddList;
    private RecyclerView rc;
    private UserService userService;
    private AdapterFriendWaitResponse adapter;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.friend_wait_response_frag,container,false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        rc = view.findViewById(R.id.rc_friends_wait);
        rc.setLayoutManager(new LinearLayoutManager(getContext()));
        adapter = new AdapterFriendWaitResponse(this);
        rc.setAdapter(adapter);
        getAllFriendWaitAccepct();
    }

    private void getAllFriendWaitAccepct(){
        userService = Common.getUserService();
        if (  CommonData.getInstance().getUserProfile() == null ){
            return;
        }
        userService.getAllFriendWaitResponse(CommonData.getInstance().getUserProfile().getId())
                .enqueue(new Callback<List<FriendToAdd>>() {
                    @Override
                    public void onResponse(Call<List<FriendToAdd>> call, Response<List<FriendToAdd>> response) {
                        friendToAddList = response.body();
                        adapter.notifyDataSetChanged();
                    }

                    @Override
                    public void onFailure(Call<List<FriendToAdd>> call, Throwable t) {
                        t.printStackTrace();
                    }
                });
    }

    @Override
    public int getCount() {
        if (friendToAddList == null){
            return 0;
        }
        return friendToAddList.size();
    }

    @Override
    public FriendToAdd getData(int pos) {
        return friendToAddList.get(pos);
    }

    @Override
    public void onClickAccept(int pos) {
        accepted(pos);
    }

    @Override
    public void onClickDecline(int pos) {
        decline(pos);
    }

    private void accepted(int pos){
        AddFriendResponse addFriendResponse = new AddFriendResponse();
        addFriendResponse.setReceiver_id(CommonData.getInstance().getUserProfile().getId());
        addFriendResponse.setSender_id(friendToAddList.get(pos).getId());
        userService.accepted(addFriendResponse).enqueue(new Callback<BaseResponse>() {
            @Override
            public void onResponse(Call<BaseResponse> call, Response<BaseResponse> response) {
                System.out.println(response.message());
                getAllFriendWaitAccepct();
            }

            @Override
            public void onFailure(Call<BaseResponse> call, Throwable t) {
                t.printStackTrace();
            }
        });
    }

    private void decline(int pos){
        AddFriendResponse addFriendResponse = new AddFriendResponse();
        addFriendResponse.setReceiver_id(CommonData.getInstance().getUserProfile().getId());
        addFriendResponse.setSender_id(friendToAddList.get(pos).getId());
        userService.decline(addFriendResponse).enqueue(new Callback<BaseResponse>() {
            @Override
            public void onResponse(Call<BaseResponse> call, Response<BaseResponse> response) {
                System.out.println(response.message());
                getAllFriendWaitAccepct();
            }

            @Override
            public void onFailure(Call<BaseResponse> call, Throwable t) {

            }
        });
    }
}
