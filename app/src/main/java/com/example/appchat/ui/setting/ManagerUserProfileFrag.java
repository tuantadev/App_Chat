package com.example.appchat.ui.setting;

import android.content.Intent;
import android.os.Bundle;
import android.os.PersistableBundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;

import com.bumptech.glide.Glide;
import com.example.appchat.GlideApp;
import com.example.appchat.R;
import com.example.appchat.interact.CommonData;
import com.example.appchat.ui.gallery.ImageGalleryActivity;


public class ManagerUserProfileFrag extends AppCompatActivity implements View.OnClickListener {

    private TextView nickname,done;
    private ImageView ava;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.frag_manager_user_profile);
        findViewById(R.id.done).setOnClickListener(this);
//        Intent intent = getIntent();
        nickname = findViewById(R.id.nickname_in_setting);
        nickname.setText(CommonData.getInstance().getUserProfile().getNameofchat());
        ava = findViewById(R.id.setting_ava);
        ava.setOnClickListener(this);
        ((TextView)findViewById(R.id.username_setting)).setText(CommonData.getInstance().getUserProfile().getUsername());
        Glide.with(ava)
                .load(CommonData.getInstance().getUserProfile().getAvatar())
                .into(ava);
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()){
            case R.id.setting_ava:
                Intent intent = new Intent();
                intent.setClass(this, ImageGalleryActivity.class);
                startActivityForResult(intent, 7);
                break;
            case R.id.done:
                finish();
                break;
        }
    }


}
