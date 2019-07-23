package com.example.appchat.ui.frag;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;


import com.example.appchat.Common;
import com.example.appchat.interact.UserService;
import com.example.appchat.ui.adapter.ChatMainFragment;
import com.example.appchat.R;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class LoginFrag extends Fragment implements View.OnClickListener {

//    private Button keep_sign_in;
    private TextView forget;
    private TextView username_login,password_login;
    private UserService userService;
    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = View.inflate(getContext(), R.layout.frag_login,container);
        userService = Common.getUserService();
        return view;
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        view.findViewById(R.id.login_frag_login).setOnClickListener(this);
        view.findViewById(R.id.register_of_frag_login).setOnClickListener(this);
        view.findViewById(R.id.forget).setOnClickListener(this);
        username_login = view.findViewById(R.id.username_login);
        password_login = view.findViewById(R.id.password_login);
    }

    public boolean emailValidator(String email)
    {
        Pattern pattern;
        Matcher matcher;
        final String EMAIL_PATTERN = "^[_A-Za-z0-9-]+(\\.[_A-Za-z0-9-]+)*@[A-Za-z0-9]+(\\.[A-Za-z0-9]+)*(\\.[A-Za-z]{2,})$";
        pattern = Pattern.compile(EMAIL_PATTERN);
        matcher = pattern.matcher(email);
        return matcher.matches();
    }
    public boolean phonenumberValidator(String phone){
        Pattern pattern;
        Matcher matcher;
        final String MOBILE_PATTERN = "[0-9]{10}";
        pattern = Pattern.compile(MOBILE_PATTERN);
        matcher = pattern.matcher(phone);
        return matcher.matches();
    }
    @Override
    public void onClick(View view) {
        switch (view.getId()){
            case R.id.login_frag_login:
                if (username_login.getText().toString().trim() == null
                        || password_login.getText().toString().trim() == null
                        || !emailValidator(username_login.getText().toString())
                        ||!phonenumberValidator(username_login.getText().toString())){
                    Toast.makeText(getContext(),"username or password invalid"
                            ,Toast.LENGTH_LONG)
                            .show();
                }else {
                    Toast.makeText(getContext(),"login success"
                            ,Toast.LENGTH_LONG).show();
                    getFragmentManager().beginTransaction().replace(R.id.login_frag_login
                            ,new ChatMainFragment()
                            ,ChatMainFragment.class.getName())
                            .commit();
                }
                break;
            case R.id.register_of_frag_login:
                break;
            case R.id.keep_sign_in:
                break;
            case R.id.forget:
                break;
        }
    }
}
