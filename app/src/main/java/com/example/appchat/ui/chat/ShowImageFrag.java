package com.example.appchat.ui.chat;

import android.content.Intent;
import android.database.Cursor;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.provider.MediaStore;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.example.appchat.GlideApp;
import com.example.appchat.R;
import com.example.appchat.interact.Common;
import com.example.appchat.model.ImageData;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

public class ShowImageFrag extends AppCompatActivity implements View.OnClickListener {

    private ImageView im;
    private String path;
    private  File file;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.show_image_onmess);
        Intent intent = getIntent();
        im = findViewById(R.id.show_img_mess);
        System.out.println(Common.getLinkImage(intent.getStringExtra("PATH")));
        findViewById(R.id.btn_back_chat).setOnClickListener(this);
        path = getPath();
        if (path != null){
            file = new File(path);
            Bitmap myBitmap = BitmapFactory.decodeFile(file.getAbsolutePath());
            im.setImageBitmap(myBitmap);

        }else GlideApp.with(this)
                .load(Common.getLinkImage(intent.getStringExtra("PATH")))
                .into(im);
    }

    @Override
    public void onClick(View view) {
        finish();
    }

    private String getPath(){
        String path = null;
        Cursor c = getContentResolver().query(
                MediaStore.Images.Media.EXTERNAL_CONTENT_URI,
                new String[]{MediaStore.Images.Media.DATA},
                null,
                null,
                MediaStore.Images.Media.DATE_ADDED + " DESC"
        );
        List<ImageData> imageDatas = new ArrayList<>();
        if (c != null) {
            int indexData = c.getColumnIndex(MediaStore.Images.Media.DATA);
            c.moveToFirst();
            while (!c.isAfterLast()) {
                String link = c.getString(indexData);
                imageDatas.add(new ImageData(link));
                c.moveToNext();
            }
            c.close();
        }
        String from = getIntent().getStringExtra("PATH").substring(14);
        for(ImageData i:imageDatas){
            if (from.equals(i.getPath().substring(32))){
                path = i.getPath();
            }
        }
        return path;
    }
}
