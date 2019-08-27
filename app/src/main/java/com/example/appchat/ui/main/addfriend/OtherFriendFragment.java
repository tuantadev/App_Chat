package com.example.appchat.ui.main.addfriend;

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
import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class OtherFriendFragment extends Fragment implements AddFriendAdapter.IAddFriend {

    private RecyclerView rc;
    private AddFriendAdapter addFriendAdapter;
    private List<FriendToAdd> friendToAddList;
    private UserService userService;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.frag_add_friends,container,false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        rc = view.findViewById(R.id.rc_friends);
        rc.setLayoutManager(new LinearLayoutManager(getContext()));
        addFriendAdapter = new AddFriendAdapter(this);
        rc.setAdapter(addFriendAdapter);
        getFindFriend();
    }

    private void getFindFriend() {
        userService = Common.getUserService();
        if (  CommonData.getInstance().getUserProfile() == null ){
            return;
        }
        userService.getAllNotFriends(CommonData.getInstance().getUserProfile().getId()).enqueue(new Callback<List<FriendToAdd>>() {
            @Override
            public void onResponse(Call<List<FriendToAdd>> call, Response<List<FriendToAdd>> response) {

            }

            @Override
            public void onFailure(Call<List<FriendToAdd>> call, Throwable t) {

            }
        });
    }

    @Override
    public int getCount() {
        if (friendToAddList == null) return 0;
        return friendToAddList.size();
    }

    @Override
    public FriendToAdd getData(int pos) {
        return friendToAddList.get(pos);
    }

    @Override
    public void onClickItem(int position) {

    }
}
