package com.fupengpeng.mvplogindemo.mvp.presenter;

import android.app.Activity;
import android.util.Log;

import com.android.volley.RequestQueue;
import com.android.volley.toolbox.Volley;
import com.fupengpeng.mvplogindemo.entity.HttpInfo;
import com.fupengpeng.mvplogindemo.entity.UserInfo;
import com.fupengpeng.mvplogindemo.inter.OnLoginListener;
import com.fupengpeng.mvplogindemo.mvp.model.LoginModel;
import com.fupengpeng.mvplogindemo.mvp.model.LoginModelInterface;
import com.fupengpeng.mvplogindemo.mvp.view.LoginView;
import com.fupengpeng.mvplogindemo.utils.HttpUtils;

import static com.fupengpeng.mvplogindemo.LoginActivity.TAG;

/**
 * Created by fupengpeng on 2017/5/23 0023.
 */

public class LoginPresenter  {

    LoginView loginView;
    LoginModelInterface loginModelInterface = new LoginModel();

    private String tel;
    private String password;
    private Class<? extends LoginView> loginActivity;

    //通过构造方法实例化mIGirlView
    public LoginPresenter(LoginView loginView) {
        this.loginView = loginView;
    }

    /**
     * bind view and model
     *   view调用GirlPresenterVl的fetch方法就可以从model中获取到数据
     */
    public void login(){
        //显示进度条
        loginView.showProgressBar();
        tel = loginView.getTel();
        password = loginView.getPassword();
        loginActivity = loginView.getClass();

        //让model load data
        if (loginModelInterface != null){
            loginModelInterface.login(tel, password, loginActivity, new LoginModelInterface.OnLoginListener() {
                @Override
                public void loginSuccess(UserInfo userInfo) {

                }

                @Override
                public void loginFailed() {

                }
            });
        }
    }




}
