package com.example.appchat.ui.setting.friends;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;

import com.bumptech.glide.Glide;
import com.example.appchat.R;
import com.example.appchat.model.response.FriendResponse;


public class SettingFriendActivity extends AppCompatActivity implements View.OnClickListener {

    private FriendResponse friendResponse;
    private ImageView avatar;
    private TextView name;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.frag_setting_friend_chat);
        friendResponse =
                (FriendResponse) getIntent()
                        .getSerializableExtra(
                                "detail");
        name =findViewById(R.id.title);
        name.setText(friendResponse.getFriendNameofchat());
        avatar = findViewById(R.id.toolbar_image);
        Glide.with(this)
                .load(friendResponse.getFriendAvatar())
                .error(R.drawable.default_ava)
                .into(avatar);
        findViewById(R.id.backButton).setOnClickListener(this);
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()){
            case R.id.backButton:
                finish();
                break;
                default:
                    break;
        }
    }
}
