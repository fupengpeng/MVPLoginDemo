package com.fupengpeng.mvplogindemo;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextUtils;
import android.text.TextWatcher;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.RequestQueue;
import com.android.volley.toolbox.Volley;
import com.fupengpeng.mvplogindemo.entity.HttpInfo;
import com.fupengpeng.mvplogindemo.entity.UserInfo;
import com.fupengpeng.mvplogindemo.inter.OnLoginListener;
import com.fupengpeng.mvplogindemo.mvp.model.LoginModel;
import com.fupengpeng.mvplogindemo.mvp.view.LoginView;
import com.fupengpeng.mvplogindemo.utils.HttpUtils;

public class LoginActivity extends AppCompatActivity implements View.OnClickListener,LoginView{
    public static final String TAG = "LoginActivity";

    private EditText editText001;
    private EditText editText002;
    private Button buttonLogin;
    private TextView textViewRegister;
    private ProgressBar progressBar;

    private String tel;
    private String password;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        init();
    }

    private void init() {
        editText001 = (EditText) findViewById(R.id.et_tel);
        editText002 = (EditText) findViewById(R.id.et_password);
        buttonLogin = (Button) findViewById(R.id.btn_login);
        textViewRegister = (TextView) findViewById(R.id.tv_register);
        progressBar  = (ProgressBar) findViewById(R.id.pb_login);

        editText001.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {

            }

            @Override
            public void afterTextChanged(Editable s) {
                tel = editText001.getText().toString();
                Log.e(TAG, "afterTextChanged: ---001---"+tel );
            }
        });
        editText002.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {

            }

            @Override
            public void afterTextChanged(Editable s) {
                password = editText002.getText().toString();
                Log.e(TAG, "afterTextChanged: ---002---"+password );
                //判断用户名和密码是否为空
                boolean canLogin = !(TextUtils.isEmpty(tel) || TextUtils.isEmpty(password));
                buttonLogin.setEnabled(canLogin);
            }
        });
        buttonLogin.setOnClickListener(this);
        textViewRegister.setOnClickListener(this);

    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.btn_login:
                // TODO: 2017/5/23 0023   根据输入的用户名和密码进行网络请求，并获取请求成功或者失败的展示
                //登录成功跳转至主页面，登录失败提示登录失败原因
                Log.e(TAG, "onClick: "+tel+"---"+"登录去"+"---"+password );


                Toast.makeText(LoginActivity.this,tel+"---"+"登录去"+"---"+password,Toast.LENGTH_LONG).show();
                break;
            case R.id.tv_register:
                toRegisterActivity();
                Log.e(TAG, "onClick: "+"注册去");
                Toast.makeText(LoginActivity.this,"注册去",Toast.LENGTH_LONG).show();
                break;

        }
    }

    @Override
    public String getTel() {
        return this.tel;
    }

    @Override
    public String getPassword() {
        return this.password;
    }

    @Override
    public void showProgressBar() {
        progressBar.setVisibility(View.VISIBLE);
    }

    @Override
    public void hideProgressBar() {
        progressBar.setVisibility(View.GONE);
    }

    @Override
    public void toHomeActivity(UserInfo userInfo) {
        Intent intent = new Intent(LoginActivity.this,HomeActivity.class);
        startActivity(intent);
    }

    @Override
    public void showFailedError() {
        Toast.makeText(LoginActivity.this,"账号或者密码错误，请重新输入！",Toast.LENGTH_LONG).show();
        clearUserName();
        clearPassword();
    }

    @Override
    public void clearUserName() {
        editText001.setText("");
    }

    @Override
    public void clearPassword() {
        editText002.setText("");
    }

    @Override
    public void toRegisterActivity() {
        Intent intent = new Intent(LoginActivity.this,RegisterActivity.class);
        startActivity(intent);
    }
}
