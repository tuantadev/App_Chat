package com.example.appchat.ui.chat;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import com.example.appchat.GlideApp;
import com.example.appchat.R;
import com.example.appchat.interact.Common;

public class ShowImageFrag extends AppCompatActivity implements View.OnClickListener {

    private ImageView im;
    private Button back;
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.show_image_onmess);
        Intent intent = getIntent();
        im = findViewById(R.id.show_img_mess);
        System.out.println("frag"+Common.getLinkImage(intent.getStringExtra("PATH")));
        findViewById(R.id.btn_back_chat).setOnClickListener(this);
        GlideApp.with(this)
                .load(Common.getLinkImage(intent.getStringExtra("PATH")))
                .into(im);
    }

    @Override
    public void onClick(View view) {
        finish();
    }
}
