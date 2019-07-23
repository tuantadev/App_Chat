package com.example.appchat.Frag;

import android.os.Bundle;
import android.text.Editable;
import android.text.TextUtils;
import android.text.TextWatcher;
import android.util.Log;
import android.util.Patterns;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.widget.AppCompatEditText;
import androidx.fragment.app.Fragment;


import com.example.appchat.R;

import java.nio.charset.Charset;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class LoginFrag extends Fragment implements View.OnClickListener, TextWatcher {
    private Button login, register, keep_sign_in;
    private TextView forget, txtcheckValidation;
    private AppCompatEditText txtusername_login, txtpassword_login;
    private String strPwdLogin, strUserLogin;
    private View view;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.frag_login, container, false);
        init();
        return view;
    }

    private void init() {
        view.findViewById(R.id.login_frag_login).setOnClickListener(this);
        view.findViewById(R.id.register_of_frag_login).setOnClickListener(this);
        keep_sign_in = view.findViewById(R.id.keep_sign_in);
        keep_sign_in.setOnClickListener(this);
        view.findViewById(R.id.forget).setOnClickListener(this);
        txtusername_login = view.findViewById(R.id.username_login);
        txtpassword_login = view.findViewById(R.id.password_login);
        txtcheckValidation = view.findViewById(R.id.txtcheckValidate);
        txtusername_login.addTextChangedListener(this);
        strPwdLogin = txtpassword_login.getText().toString().trim();
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.login_frag_login:
                checkLoginSuccess();
                break;
            case R.id.register_of_frag_login:
                break;
            case R.id.keep_sign_in:
                break;
            case R.id.forget:
                break;
        }
    }

    @Override
    public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

    }

    @Override
    public void onTextChanged(CharSequence s, int i, int i1, int i2) {
        checkValidationUsername(s);

    }

    @Override
    public void afterTextChanged(Editable editable) {

    }

    private void checkLoginSuccess() {
//        if (!validPhone && validEmail) {
//            checkValidation.setText("The phone number you entered " +
//                    "doesn't appear to belong to an account" +
//                    ".Please check your phone number and try again");
//        }
//        if (!validEmail && validPhone) {
//            checkValidation.setText("The email you enterd " +
//                    "doesn't appear to belong to an account" +
//                    ".Please check your email address and try again");
//        }
//        if (!validpwd) {
//            checkValidation.setText("The password you enterd is incorrect" +
//                    ". Please try again");
//        } else {
//            getFragmentManager().beginTransaction().replace(R.id.contentLogin
//                    , new ChatMainFragment()
//                    , ChatMainFragment.class.getName())
//                    .commit();
//        }
//

    }

    private void checkValidationUsername(CharSequence s) {
        txtcheckValidation.setText("");
        strUserLogin = txtusername_login.getText().toString().trim();
        boolean isValidateEmail = Patterns.EMAIL_ADDRESS.matcher(strUserLogin).matches();
        boolean isValidatePhone = Patterns.PHONE.matcher(strUserLogin).matches()
                && s.length() > 9 && s.length() < 11;
        if (!isValidateEmail && !isValidatePhone) {
            txtcheckValidation.setText("InValid Email or Phone no ");
        }
    }
}
















