package com.example.appchat;

import android.os.Bundle;
import androidx.appcompat.app.AppCompatActivity;
import com.example.appchat.ui.main.ChatMainFragment;
import com.example.appchat.ui.begin.LoginFrag;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        openLogin();
    }

    public void openLogin(){
        getSupportFragmentManager()
                .beginTransaction()
                .add(R.id.content,new LoginFrag(),LoginFrag.class.getName())
                .commit();
    }

    public void openChat(){
        getSupportFragmentManager()
                .beginTransaction()
                .replace(R.id.content,new ChatMainFragment(),LoginFrag.class.getName())
                .commit();
    }
}
