package com.fupengpeng.mvplogindemo.mvp.model;


import android.content.Context;

import com.fupengpeng.mvplogindemo.entity.UserInfo;
import com.fupengpeng.mvplogindemo.mvp.view.LoginView;

/**
 * Created by fupengpeng on 2017/5/23 0023.
 */

public interface LoginModelInterface {

    /**
     * 登录
     */
//    void setOnLoginListener(OnLoginListener listener);

    /**
     * 登录的监听
     */
    interface OnLoginListener{
        void loginSuccess(UserInfo userInfo);

        void loginFailed();
    }

    void login(String tel, String password, Class<? extends LoginView> context, OnLoginListener listener);
}
