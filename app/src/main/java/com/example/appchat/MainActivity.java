package com.example.appchat;

import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import com.example.appchat.ui.adapter.ChatMainFragment;
import com.example.appchat.ui.frag.LoginFrag;

public class MainActivity extends AppCompatActivity {

    private Toolbar toolbar;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        openLogin();
    }

    private void openLogin(){
        getSupportFragmentManager().beginTransaction().add(R.id.content,new LoginFrag(),LoginFrag.class.getName());
    }

    private void openChat(){
        getSupportFragmentManager().beginTransaction().add(R.id.content,new ChatMainFragment(),LoginFrag.class.getName());

    }
}
