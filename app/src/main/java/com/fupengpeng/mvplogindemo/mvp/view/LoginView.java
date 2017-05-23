package com.fupengpeng.mvplogindemo.mvp.view;

import com.fupengpeng.mvplogindemo.entity.UserInfo;

/**
 * Created by fupengpeng on 2017/5/23 0023.
 */

public interface LoginView {

    /**
     * 获取输入的用户名和密码
     * @return
     */
    String getTel();
    String getPassword();

    /**
     * 登录时展示的progressbar
     */
    void showProgressBar();
    void hideProgressBar();

    /**
     * 登录成功跳转主页面，登录失败提示
     */
    void toHomeActivity(UserInfo userInfo);
    void showFailedError();
    /**
     * 登录失败后清除EditText的内容
     */
    void clearUserName();
    void clearPassword();

    void toRegisterActivity();

}
