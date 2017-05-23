package com.fupengpeng.mvplogindemo.login.view;

import com.fupengpeng.mvplogindemo.login.entity.LoginInfo;

/**
 * Created by fupengpeng on 2017/5/23 0023.
 *     登录页面的view接口
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
    void toHomeActivity(LoginInfo loginInfo);
    void showFailedError();

    /**
     * 登录失败后清除EditText的内容
     */
    void clearUserName();
    void clearPassword();

    //点击注册textview后跳转至注册页面
    void toRegisterActivity();

}
