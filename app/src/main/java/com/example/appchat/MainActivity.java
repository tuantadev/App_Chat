package com.example.appchat;

import android.os.Bundle;
import androidx.appcompat.app.AppCompatActivity;

import com.example.appchat.ui.main.ChatFrag;
import com.example.appchat.ui.begin.LoginFrag;

public class MainActivity extends AppCompatActivity {

    public static final String NICKNAME = "usernickname";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        openLogin();
    }

    public void openLogin(){
        getSupportFragmentManager()
                .beginTransaction()
                .replace(R.id.content,new LoginFrag(),LoginFrag.class.getName())
                .commit();
    }

    public void openMainChat(){
        getSupportFragmentManager()
                .beginTransaction()
                .replace(R.id.content,new ChatFrag(),ChatFrag.class.getName())
                .commit();
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        System.out.println("Destroyed");
    }
}
