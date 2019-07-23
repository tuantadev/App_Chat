package com.example.appchat;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.fragment.app.FragmentTransaction;

import android.os.Bundle;
import android.view.View;
import android.widget.EditText;

import com.example.appchat.Frag.FragExtend;
import com.example.appchat.Frag.FragPhotoImage;

public class MainActivity extends AppCompatActivity {
    private EditText editText;
    private Toolbar toolbar;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        FragPhotoImage fragPhotoImage = new FragPhotoImage();
        final FragmentTransaction fragmentTransaction = getSupportFragmentManager().beginTransaction();
        fragmentTransaction.add(R.id.change_frag_collapse,fragPhotoImage,FragPhotoImage.class.getName());
        fragmentTransaction.commit();
        editText = findViewById(R.id.edit_text_chat);
        editText.setOnFocusChangeListener(new View.OnFocusChangeListener() {
            @Override
            public void onFocusChange(View view, boolean hasClick) {
                FragExtend fragExtend = new FragExtend();
                getSupportFragmentManager()
                        .beginTransaction()
                        .replace(R.id.change_frag_collapse,fragExtend)
                        .addToBackStack(null).commit();
            }
        });
    }
}
