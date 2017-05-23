package com.fupengpeng.mvplogindemo.login.view;

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

import com.fupengpeng.mvplogindemo.home.HomeActivity;
import com.fupengpeng.mvplogindemo.R;
import com.fupengpeng.mvplogindemo.register.RegisterActivity;
import com.fupengpeng.mvplogindemo.login.entity.LoginInfo;
import com.fupengpeng.mvplogindemo.login.presenter.LoginPresenter;

/**
 * 登录主页面
 */
public class LoginActivity extends AppCompatActivity implements View.OnClickListener,LoginView{
    public static final String TAG = "LoginActivity";
    /**
     * 登录页面的控件声明
     */
    private EditText editTextTel;
    private EditText editTextPassword;
    private Button buttonLogin;
    private TextView textViewRegister;
    private ProgressBar progressBar;

    /**
     * 登录时输入的用户账号和密码
     */
    private String tel;
    private String password;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
//        EventBus.getDefault().register(this);//订阅
        init();
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
//        EventBus.getDefault().unregister(this);//解除订阅
    }

    /**
     * 登录页面的控件获取，监听事件
     */
    private void init() {
        editTextTel = (EditText) findViewById(R.id.et_tel);
        editTextPassword = (EditText) findViewById(R.id.et_password);
        buttonLogin = (Button) findViewById(R.id.btn_login);
        textViewRegister = (TextView) findViewById(R.id.tv_register);
        progressBar  = (ProgressBar) findViewById(R.id.pb_login);

        //用户账号输入监听，获取输入内容
        editTextTel.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {

            }

            @Override
            public void afterTextChanged(Editable s) {
                tel = editTextTel.getText().toString();
                Log.e(TAG, "afterTextChanged: ---001---"+tel );
            }
        });
        //用户密码输入监听，获取输入内容
        editTextPassword.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {

            }

            @Override
            public void afterTextChanged(Editable s) {
                password = editTextPassword.getText().toString();
                Log.e(TAG, "afterTextChanged: ---002---"+password );
                //判断用户名和密码是否为空
                boolean canLogin = !(TextUtils.isEmpty(tel) || TextUtils.isEmpty(password));
                buttonLogin.setEnabled(canLogin);
            }
        });
        //登录和快速注册的监听
        buttonLogin.setOnClickListener(this);
        textViewRegister.setOnClickListener(this);
    }
    // 登录和快速注册的点击事件
    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.btn_login:
                //根据输入的用户名和密码进行网络请求，并获取请求成功或者失败的展示
                //登录成功跳转至主页面，登录失败提示登录失败原因
                //实例化LoginPresenter对象，调用login方法是现代登录
                new LoginPresenter(this).login();
//                Toast.makeText(LoginActivity.this,tel+"---"+"登录去"+"---"+password,Toast.LENGTH_LONG).show();
                break;
            case R.id.tv_register:
                //调用toRegisterActivity方法跳转至注册页面
                toRegisterActivity();
                Log.e(TAG, "onClick: "+"注册去");
//                Toast.makeText(LoginActivity.this,"注册去",Toast.LENGTH_LONG).show();
                break;

        }
    }


    /**
     * 实现View接口方法
     * @return
     */
    //获取用户账号
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
    public void toHomeActivity(LoginInfo loginInfo) {
        Intent intent = new Intent(LoginActivity.this,HomeActivity.class);
        startActivity(intent);
    }

    @Override
    public void showFailedError() {
//        Toast.makeText(LoginActivity.this,"账号或者密码错误，请重新输入！",Toast.LENGTH_LONG).show();
        clearUserName();
        clearPassword();
    }

    @Override
    public void clearUserName() {
        editTextTel.setText("");
    }

    @Override
    public void clearPassword() {
        editTextPassword.setText("");
    }

    @Override
    public void toRegisterActivity() {
        Intent intent = new Intent(LoginActivity.this,RegisterActivity.class);
        startActivity(intent);
    }

}
