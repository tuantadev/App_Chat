package com.example.appchat.ui.setting.user;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.bumptech.glide.Glide;
import com.example.appchat.R;
import com.example.appchat.interact.Common;
import com.example.appchat.interact.CommonData;
import com.example.appchat.interact.UserService;
import com.example.appchat.model.UserProfile;
import com.example.appchat.model.request.UpdateAvatar;
import com.example.appchat.ui.gallery.ImageGalleryActivity;
import com.example.appchat.ui.logout.ActivityLogout;

import java.io.File;

import okhttp3.MediaType;
import okhttp3.MultipartBody;
import okhttp3.RequestBody;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;


public class ManagerUserProfileFrag extends AppCompatActivity implements View.OnClickListener {

    private TextView nickname;
    private ImageView ava;
    private TextView phonenumber;
    private UserService userService;
    private LinearLayout logout;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.frag_manager_user_profile);
        findViewById(R.id.done).setOnClickListener(this);
        nickname = findViewById(R.id.nickname_in_setting);
        phonenumber = findViewById(R.id.phone_setting);
        phonenumber.setText(CommonData.getInstance().getUserProfile().getPhonenumber());
        nickname.setText(CommonData.getInstance().getUserProfile().getNameofchat());
        ava = findViewById(R.id.setting_ava);
        ava.setOnClickListener(this);
        ((TextView)findViewById(R.id.username_setting)).setText(CommonData.getInstance().getUserProfile().getUsername());
        Glide.with(ava)
                .load(CommonData.getInstance().getUserProfile().getAvatar())
                .error(R.drawable.default_ava)
                .into(ava);

        userService = Common.getUserService();
        findViewById(R.id.logout).setOnClickListener(this);

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
            case R.id.phone_setting:
                break;
            case R.id.logout:
                Intent intent1 = new Intent();
                intent1.setClass(this, ActivityLogout.class);
                startActivity(intent1);
                break;
                default: break;
        }
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        if (resultCode ==  RESULT_OK){
            String path = data.getStringExtra("PATH");
            sendImage(path);
        }
    }

    private void sendImage(String path) {
        File file = new File(path);
        final RequestBody requestFile =
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
                        UpdateAvatar updateAvatar = new UpdateAvatar();
                        updateAvatar.setPath(Common.getLinkImage(response.body()));
                        updateAvatar.setId(CommonData.getInstance().getUserProfile().getId());
                        userService.changeAvartar(updateAvatar).enqueue(new Callback<UserProfile>() {
                            @Override
                            public void onResponse(Call<UserProfile> call, Response<UserProfile> response) {
                                System.out.println(response.body().getAvatar());
                                CommonData.getInstance().setUserProfile(response.body());
                                Glide.with(ava)
                                        .load(CommonData.getInstance().getUserProfile().getAvatar())
                                        .into(ava);
                            }

                            @Override
                            public void onFailure(Call<UserProfile> call, Throwable t) {

                            }
                        });
                    }

                    @Override
                    public void onFailure(Call<String> call, Throwable t) {

                    }
                });
    }

}
