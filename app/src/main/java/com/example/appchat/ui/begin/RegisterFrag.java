package com.example.appchat.ui.begin;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.example.appchat.MainActivity;
import com.example.appchat.R;
import com.example.appchat.interact.Common;
import com.example.appchat.interact.UserService;
import com.example.appchat.model.request.RegisterRequest;
import com.example.appchat.model.response.BaseResponse;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;


public class RegisterFrag extends Fragment implements View.OnClickListener {

    private EditText username_regist,pass_regist,re_pass_regist,name_of_chat;
    private UserService userService;
    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.frag_register,container,false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        view.findViewById(R.id.register).setOnClickListener(this);
        username_regist = view.findViewById(R.id.username_register);
        pass_regist = view.findViewById(R.id.password_register);
        re_pass_regist = view.findViewById(R.id.re_password_register);
        name_of_chat = view.findViewById(R.id.name_of_chat_register);
        userService = Common.getUserService();
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()){
            case R.id.register:
                if (pass_regist.getText().toString().equals(re_pass_regist.getText().toString())){
                    final RegisterRequest registerRequest = new RegisterRequest();
                    registerRequest.setUsername(username_regist.getText().toString());
                    registerRequest.setPassword(pass_regist.getText().toString());
                    registerRequest.setName_of_chat(name_of_chat.getText().toString());
                    userService.postInfoRegister(registerRequest).enqueue(new Callback<BaseResponse>() {
                        @Override
                        public void onResponse(Call<BaseResponse> call, Response<BaseResponse> response) {
                            if (response.body().getStatus()!=1){
                                Toast.makeText(getContext(), response.body().getMessage(), Toast.LENGTH_SHORT).show();
                            }else{
                                Toast.makeText(getContext(), response.body().getMessage(), Toast.LENGTH_SHORT).show();
                                ((MainActivity)getActivity()).openLogin();
                            }
                        }
                        @Override
                        public void onFailure(Call<BaseResponse> call, Throwable t) {
                            System.out.println("connect is fail");
                        }
                    });

                }else {
                    Toast.makeText(getContext(), "password is incorrect", Toast.LENGTH_SHORT).show();
                }
                break;
        }

    }
}
