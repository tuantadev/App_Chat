package com.example.appchat.ui.logout;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.bumptech.glide.Glide;
import com.example.appchat.MainActivity;
import com.example.appchat.R;
import com.example.appchat.interact.CommonData;
import com.example.appchat.socket.SocketManager;
import com.example.appchat.ui.ShareUtils;

public class ActivityLogout extends AppCompatActivity implements View.OnClickListener {

    private TextView name;
    private ImageView avatar;
    private ImageView back;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.switch_logout);
        avatar = findViewById(R.id.ava_in_switch);
        name = findViewById(R.id.name_in_switch);
        name.setText(CommonData.getInstance().getUserProfile().getNameofchat());
        Glide.with(this)
                .load(CommonData.getInstance().getUserProfile().getAvatar())
                .into(avatar);
        findViewById(R.id.back).setOnClickListener(this);
        findViewById(R.id.logout_login).setOnClickListener(this);

    }

    @Override
    public void onClick(View view) {
        switch (view.getId()){
            case R.id.back:
                finish();
                break;
            case R.id.logout_login:
                ShareUtils.clearProfile(this);
                Intent intent = new Intent();
                intent.setClass(this, MainActivity.class);
                startActivity(intent);
                finish();
                break;
        }
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        SocketManager.getInstance().disconnect();
    }
}
