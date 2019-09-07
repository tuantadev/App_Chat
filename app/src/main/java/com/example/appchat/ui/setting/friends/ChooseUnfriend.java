package com.example.appchat.ui.setting.friends;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.example.appchat.MainActivity;
import com.example.appchat.R;
import com.example.appchat.interact.Common;
import com.example.appchat.interact.CommonData;
import com.example.appchat.interact.UserService;
import com.example.appchat.model.response.BaseResponse;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class ChooseUnfriend extends Fragment implements View.OnClickListener {

    private UserService userService;
    private Remove inter;

    public ChooseUnfriend(Remove inter) {
        this.inter = inter;
    }

    public ChooseUnfriend() {

    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.choose_unfriend,container,false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        view.findViewById(R.id.back_set).setOnClickListener(this);
        view.findViewById(R.id.accept).setOnClickListener(this);
        userService = Common.getUserService();
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()){
            case R.id.back_set:
                getFragmentManager().beginTransaction()
                        .remove(getFragmentManager().findFragmentByTag(ChooseUnfriend.class.getName()))
                        .commit();
                break;
            case R.id.accept:
                if (userService == null){
                    return;
                }
                int receiverid = inter.sendID();
                userService.removeFriend(CommonData.getInstance().getUserProfile().getId(),receiverid)
                        .enqueue(new Callback<BaseResponse>() {
                            @Override
                            public void onResponse(Call<BaseResponse> call, Response<BaseResponse> response) {
                                System.out.println(response.body().getMessage());
                                Intent intent = new Intent();
                                intent.setClass(getContext(), MainActivity.class);
                                startActivity(intent);
                                getActivity().finish();
                            }

                            @Override
                            public void onFailure(Call<BaseResponse> call, Throwable t) {

                            }
                        });
                break;
        }
    }

    public interface Remove{
        int sendID();
    }

}
