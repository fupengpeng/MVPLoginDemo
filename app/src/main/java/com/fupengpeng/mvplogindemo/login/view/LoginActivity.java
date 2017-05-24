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
import android.widget.Toast;

import com.fupengpeng.mvplogindemo.base.WaitDialog;
import com.fupengpeng.mvplogindemo.home.HomeActivity;
import com.fupengpeng.mvplogindemo.R;
import com.fupengpeng.mvplogindemo.register.RegisterActivity;
import com.fupengpeng.mvplogindemo.login.entity.LoginInfo;
import com.fupengpeng.mvplogindemo.login.presenter.LoginPresenter;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import butterknife.OnTextChanged;
import butterknife.Unbinder;

/**
 * 登录主页面
 */
public class LoginActivity extends AppCompatActivity implements LoginView{
    public static final String TAG = "LoginActivity";
    /**
     * 登录页面的控件声明
     */
    @BindView(R.id.et_password)
    EditText editTextPassword;
    @BindView(R.id.et_tel)
    EditText editTextTel;
    @BindView(R.id.btn_login)
    Button buttonLogin;
    @BindView(R.id.tv_register)
    TextView textViewRegister;
    @BindView(R.id.pb_login)
    ProgressBar progressBar;

    //请求时的等待框
    private WaitDialog waitDialog;

    /**
     * 登录时输入的用户账号和密码
     */
    private String tel;
    private String password;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        ButterKnife.bind(this);
        //创建等待框
        waitDialog = new WaitDialog(this);
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
    }

    /**
     * editText监听事件
     */
    @OnTextChanged(value = R.id.et_tel,callback = OnTextChanged.Callback.AFTER_TEXT_CHANGED)
    void afterTextChangedTel(Editable s) {
        tel = editTextTel.getText().toString();
    }
    @OnTextChanged(value = R.id.et_password,callback = OnTextChanged.Callback.AFTER_TEXT_CHANGED)
    void afterTextChangedPassword(Editable s) {
        password = editTextPassword.getText().toString();
        Log.e(TAG, "afterTextChanged: ---002---"+password );
        //判断用户名和密码是否为空
        boolean canLogin = !(TextUtils.isEmpty(tel) || TextUtils.isEmpty(password));
        buttonLogin.setEnabled(canLogin);
    }

    // 登录和快速注册的点击事件
    @OnClick(R.id.btn_login)
    public void login(){
        //根据输入的用户名和密码进行网络请求，并获取请求成功或者失败的展示
        //登录成功跳转至主页面，登录失败提示登录失败原因
        //实例化LoginPresenter对象，调用login方法是现代登录
        new LoginPresenter(this).login();
    }
    @OnClick(R.id.tv_register)
    public void register(){
        //调用toRegisterActivity方法跳转至注册页面
        toRegisterActivity();
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
//        progressBar.setVisibility(View.VISIBLE);
        //请求开始，展示dialog
        if (waitDialog != null && !waitDialog.isShowing()){
            waitDialog.show();
        }

    }

    @Override
    public void hideProgressBar() {
//        progressBar.setVisibility(View.GONE);
        //请求结束，隐藏dialog
        if (waitDialog != null && waitDialog.isShowing()){
            waitDialog.dismiss();
        }

    }

    @Override
    public void toHomeActivity(LoginInfo loginInfo) {
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
