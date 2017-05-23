package com.fupengpeng.mvplogindemo.login.model;


import com.fupengpeng.mvplogindemo.login.entity.LoginInfo;

/**
 * Created by fupengpeng on 2017/5/23 0023.
 *     登录的model接口
 */
public interface LoginModelInterface {

    /**
     * 登录
     */
    void login(String tel, String password,  OnLoginListener listener);

    /**
     * 登录的监听
     */
    interface OnLoginListener{
        //登录成功
        void loginSuccess(LoginInfo loginInfo);
        //登录失败
        void loginFailed();
    }

}
