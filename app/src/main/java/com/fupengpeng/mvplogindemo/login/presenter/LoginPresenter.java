package com.fupengpeng.mvplogindemo.login.presenter;

import android.os.Handler;

import com.fupengpeng.mvplogindemo.login.entity.LoginInfo;
import com.fupengpeng.mvplogindemo.login.model.LoginModel;
import com.fupengpeng.mvplogindemo.login.model.LoginModelInterface;
import com.fupengpeng.mvplogindemo.login.view.LoginView;

/**
 * Created by fupengpeng on 2017/5/23 0023.
 *     登录的Presenter
 */

public class LoginPresenter  {

    //实例化View接口对象
    LoginView loginView;

    //实例化Model对象
    LoginModelInterface loginModelInterface = new LoginModel();

    //接收View传递过来的网络请求参数
    private String tel;
    private String password;

    private Handler mHandler = new Handler();

    //通过构造方法实例化loginView
    public LoginPresenter(LoginView loginView) {
        this.loginView = loginView;
    }

    /**
     * 登录方法
     */
    public void login(){
        //显示进度条
        loginView.showProgressBar();

        //接收View传递过来的网络请求参数
        tel = loginView.getTel();
        password = loginView.getPassword();

        //让model load data
        if (loginModelInterface != null){
            //使用Model对象调用login方法进行网络请求，传递进来通过View获取到的参数。  实例化OnLoginListener接口，实现里面的方法
            loginModelInterface.login(tel, password,  new LoginModelInterface.OnLoginListener() {

                @Override
                public void loginSuccess(final LoginInfo loginInfo) {
                    //登录成功，隐藏progressbar，跳转至HomeActivity页面
                    loginView.toHomeActivity(loginInfo);
                    loginView.hideProgressBar();
                }

                @Override
                public void loginFailed() {
                    //需要在UI线程执行
                    mHandler.post(new Runnable() {
                        @Override
                        public void run() {
                            //登录失败，展示失败消息提示重新输入，隐藏progressbar，清除输入内容
                            loginView.showFailedError();
                            loginView.hideProgressBar();
                            loginView.clearUserName();
                            loginView.clearPassword();
                        }
                    });

                }

            });

        }

    }

}
