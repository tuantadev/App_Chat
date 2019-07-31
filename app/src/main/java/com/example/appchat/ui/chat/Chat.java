package com.example.appchat.ui.chat;

import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.example.appchat.R;
import com.example.appchat.interact.CommonData;
import com.example.appchat.model.response.FriendResponse;
import com.example.appchat.model.response.MessageChatResponse;
import com.example.appchat.socket.ReceiverMess;
import com.example.appchat.socket.SocketManager;
import com.google.gson.Gson;

import java.util.ArrayList;
import java.util.List;

public class Chat extends AppCompatActivity implements AdapterChat.IChat, View.OnClickListener, ReceiverMess {

    private RecyclerView rc;
    private ImageView camera,callVideo,back,send;
    private AdapterChat adapter;
    private EditText editText;
    private List<MessageChatResponse> messages;
    private FriendResponse friendResponse;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.frag_chat_mess);
        rc = findViewById(R.id.rc_friend);
        messages = new ArrayList<>();
        editText = findViewById(R.id.edit_text_chat);
        rc.setLayoutManager(new LinearLayoutManager(this));
        adapter = new AdapterChat(this);
        rc.setAdapter(adapter);
        camera.setOnClickListener(this);
        back.setOnClickListener(this);
        callVideo.setOnClickListener(this);
        send.setOnClickListener(this);
        init();

        SocketManager.getInstance().register(this);
    }

    private void init() {
        if (friendResponse.getFriendAvatar()==null){
            Glide.with(this)
                    .load(friendResponse.getFriendAvatar())
                    .error(R.drawable.default_ava)
                    .placeholder(R.drawable.default_ava)
                    .into((ImageView) findViewById(R.id.iv_avatar));
        }
        ((TextView) findViewById(R.id.name_or_nickname)).setText(friendResponse.getNickname());
    }

    @Override
    public int getCount() {
        if (messages == null){
            return 0;
        }
        return messages.size();
    }


    @Override
    public MessageChatResponse getData(int pos) {
        return null;
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()){
            case R.id.send_mess:
                sendMess();
                break;
            case R.id.back_main:
                onBackPressed();
        }
    }

    private void sendMess() {
        MessageChatResponse message = new MessageChatResponse();
        message.setReceiverId(friendResponse.getId());
        message.setSenderId(
                CommonData.getInstance().getUserProfile().getId()
        );
        message.setContent(editText.getText().toString());
        messages.add(message);
        adapter.notifyItemInserted(messages.size() - 1);
        rc.scrollToPosition(messages.size());

        SocketManager.getInstance().sendMessage(
                new Gson().toJson(message)
        );
        editText.setText("");
    }

    @Override
    public void receieve(final MessageChatResponse response) {
        if (response.getSenderId() != friendResponse.getId()) return;
        runOnUiThread(new Runnable() {
            @Override
            public void run() {
                int send = response.getReceiverId();
                response.setReceiverId(CommonData.getInstance().getUserProfile().getId());
                response.setSenderId(friendResponse.getFriendId());
                messages.add(response);
                adapter.notifyItemInserted(messages.size() - 1);
                rc.scrollToPosition(messages.size());
            }
        });

    }

    @Override
    protected void onDestroy() {
        SocketManager.getInstance().unregister(this);
        super.onDestroy();
    }
}
