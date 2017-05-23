package com.fupengpeng.mvplogindemo.mvp.model;

import com.android.volley.RequestQueue;
import com.android.volley.toolbox.Volley;
import com.fupengpeng.mvplogindemo.entity.HttpInfo;
import com.fupengpeng.mvplogindemo.mvp.view.LoginView;
import com.fupengpeng.mvplogindemo.utils.HttpUtils;

/**
 * Created by fupengpeng on 2017/5/23 0023.
 */

public class LoginModel implements LoginModelInterface {
    @Override
    public void login(String tel, String password, Class<? extends LoginView> context, OnLoginListener listener) {
        RequestQueue mRequestQueue = Volley.newRequestQueue(context);
        HttpUtils.LoginPOST(
                HttpInfo.URL_POST_PROBLEMLIST,
                tel,
                password,
                (com.fupengpeng.mvplogindemo.inter.OnLoginListener) context, mRequestQueue);
    }


//        RequestQueue mRequestQueue = Volley.newRequestQueue(this);
//        HttpUtils.LoginPOST(
//                HttpInfo.URL_POST_PROBLEMLIST,
//                tel,
//                password,
//                this, mRequestQueue);
//
//        listener.networkRequest(tel,password);


}
