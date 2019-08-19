package com.example.appchat.ui.chat;

import android.content.Intent;
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
import com.example.appchat.interact.Common;
import com.example.appchat.interact.CommonData;
import com.example.appchat.interact.UserService;
import com.example.appchat.model.response.BaseResponse;
import com.example.appchat.model.response.FriendResponse;
import com.example.appchat.model.response.MessageChatResponse;
import com.example.appchat.socket.ReceiverMess;
import com.example.appchat.socket.SocketManager;
import com.example.appchat.ui.gallery.ImageGalleryActivity;
import com.google.gson.Gson;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

import okhttp3.MediaType;
import okhttp3.MultipartBody;
import okhttp3.RequestBody;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class Chat extends AppCompatActivity implements AdapterChat.IChat, View.OnClickListener, ReceiverMess {

    private RecyclerView rc;
    private AdapterChat adapter;
    private EditText editText;
    private List<MessageChatResponse> messages;
    private FriendResponse friendResponse;
    private UserService userService;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.frag_chat_mess);
        userService = Common.getUserService();
        rc = findViewById(R.id.show_chat);
        messages = new ArrayList<>();
        editText = findViewById(R.id.edit_text_chat);
        rc.setLayoutManager(new LinearLayoutManager(this));
        adapter = new AdapterChat(this);
        rc.setAdapter(adapter);
        friendResponse =
                (FriendResponse) getIntent()
                        .getSerializableExtra(
                                "FRIEND");
        findViewById(R.id.back_main).setOnClickListener(this);
        findViewById(R.id.send_mess).setOnClickListener(this);
        init();
        SocketManager.getInstance().register(this);
        findViewById(R.id.take_photo_in_mess).setOnClickListener(this);
    }

    private void init() {
        Glide.with(this)
                .load(CommonData.getInstance().getUserProfile().getAvatar())
                .error(R.drawable.default_ava)
                .placeholder(R.drawable.default_ava)
                .into((ImageView)
                        findViewById(R.id.click_setting_fri_chat));
        ((TextView) findViewById(R.id.name_or_nickname)).setText(CommonData.getInstance()
                .getUserProfile()
                .getName_of_chat());

        if (friendResponse.getFriendAvatar() == null) {
            Glide.with(this)
                    .load(friendResponse.getFriendAvatar())
                    .error(R.drawable.default_ava)
                    .placeholder(R.drawable.default_ava)
                    .into((ImageView)
                            findViewById(R.id.iv_avatar));
        }
        ((TextView) findViewById(R.id.name_or_nickname)).setText(friendResponse.getFriendNameofchat());

        userService.getHistoryChat(CommonData.getInstance().getUserProfile().getId(),
                friendResponse.getFriendId())
                .enqueue(new Callback<BaseResponse<List<MessageChatResponse>>>() {
                    @Override
                    public void onResponse(Call<BaseResponse<List<MessageChatResponse>>> call, Response<BaseResponse<List<MessageChatResponse>>> response) {
                        messages = response.body().getData();
                        adapter.notifyDataSetChanged();
                    }

                    @Override
                    public void onFailure(Call<BaseResponse<List<MessageChatResponse>>> call, Throwable t) {

                    }
                });
    }

    @Override
    public int getCount() {
        if (messages == null) {
            return 0;
        }
        return messages.size();
    }

    @Override
    public void itemCLick(int pos) {
        Intent intent = new Intent();
        intent.putExtra("PATH",messages.get(pos).getContent());
        System.out.println(messages.get(pos).getContent());
        intent.setClass(this,ShowImageFrag.class);
        startActivity(intent);
    }


    @Override
    public MessageChatResponse getData(int pos) {
        return messages.get(pos);
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.send_mess:
                sendMess();
                break;
            case R.id.back_main:
                onBackPressed();
                break;
            case R.id.take_photo_in_mess:
                Intent intent = new Intent();
                intent.setClass(this, ImageGalleryActivity.class);
                startActivityForResult(intent, 100);
                break;
            default:
                break;
        }
    }

    private void sendImage(String path) {
        File file = new File(path);
        RequestBody requestFile =
                RequestBody.create(
                        MediaType.parse("image/*"),
                        file
                );
        MultipartBody.Part body =
                MultipartBody.Part.createFormData("image", file.getName(), requestFile);
        userService.upload(body)
                .enqueue(new Callback<String>() {
                    @Override
                    public void onResponse(Call<String> call, Response<String> response) {
                        System.out.println("image: " + response.body());
                        MessageChatResponse mes = new MessageChatResponse();
                        mes.setReceiverId(friendResponse.getFriendId());
                        mes.setSenderId(CommonData.getInstance().getUserProfile().getId());
                        mes.setType(MessageChatResponse.TYPE_IMG);
                        mes.setContent(response.body());
                        messages.add(mes);
                        adapter.notifyItemInserted(messages.size()-1);
                        rc.smoothScrollToPosition(messages.size()-1);

                        SocketManager.getInstance().sendMessage(
                                new Gson().toJson(mes)
                        );
                    }

                    @Override
                    public void onFailure(Call<String> call, Throwable t) {

                    }
                });
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        switch (requestCode) {
            case 100:
                if (resultCode ==  RESULT_OK){
                    String path = data.getStringExtra("PATH");
                    sendImage(path);
                }
                break;
            default:
                break;
        }
    }

    private void sendMess() {
        MessageChatResponse message = new MessageChatResponse();
        message.setReceiverId(friendResponse.getFriendId());
        message.setSenderId(
                CommonData.getInstance().getUserProfile().getId()
        );
        message.setContent(editText.getText().toString());
        messages.add(message);
        adapter.notifyItemInserted(messages.size() - 1);
        rc.smoothScrollToPosition(messages.size() - 1);

        SocketManager.getInstance().sendMessage(
                new Gson().toJson(message)
        );
        editText.setText("");
    }

    @Override
    public void receieve(final MessageChatResponse response) {
        if (response.getSenderId() != friendResponse.getFriendId()) return;
        runOnUiThread(new Runnable() {
            @Override
            public void run() {
                int send = response.getReceiverId();
                response.setReceiverId(CommonData.getInstance().getUserProfile().getId());
                response.setSenderId(friendResponse.getFriendId());
                messages.add(response);
                adapter.notifyItemInserted(messages.size() - 1);
                rc.scrollToPosition(messages.size()-1);
            }
        });

    }

    @Override
    protected void onDestroy() {
        SocketManager.getInstance().unregister(this);
        super.onDestroy();
    }
}
