package com.fupengpeng.mvplogindemo.login.model;

import android.util.Log;

import com.fupengpeng.mvplogindemo.entity.HttpInfo;
import com.fupengpeng.mvplogindemo.login.entity.LoginInfo;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;

import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.FormBody;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.RequestBody;
import okhttp3.Response;

/**
 * Created by fupengpeng on 2017/5/23 0023.
 *     登录的model
 */
public class LoginModel implements LoginModelInterface {
    //登录成功返回的消息实体
    private LoginInfo loginInfo;
    //实现LoginModelInterface接口后，重写login方法
    @Override
    public void login(final String tel, final String password, final OnLoginListener listener) {
        //子线程耗时操作
        new Thread() {
            @Override
            public void run() {
                //创建okHttpClient对象
                OkHttpClient okHttpClient = new OkHttpClient();
                //创建Post请求用RequestBody
                RequestBody requestBody = new FormBody.Builder()
                        .add("tel", tel)
                        .add("password",password)
                        .build();
                //创建一个Request
                final Request request = new Request.Builder()
                        .url(HttpInfo.URL_POST_LOGINL_LOGIN)
                        .post(requestBody)
                        .build();
                //new call
                Call call = okHttpClient.newCall(request);
                //请求加入调度
                call.enqueue(new Callback() {
                    @Override
                    public void onFailure(Call call, IOException e) {
                        Log.e("zss", "登录失败");
                        //登录失败调用失败的方法
                        listener.loginFailed();
                    }
                    @Override
                    public void onResponse(Call call, Response response) throws IOException {
                        Log.i("zss", "网络请求成功，返回请求数据---- + response.body().string()");
                        loginInfo = new LoginInfo();
                        //解析登录成功返回的json字符串，得到消息实体
                        try {
                            JSONObject jsonObject = new JSONObject(response.body().string());
                            loginInfo.setInfo(jsonObject.getString("info"));
                            loginInfo.setCode(jsonObject.getInt("code"));
                            loginInfo.setData(jsonObject.getString("Data"));
                        } catch (JSONException e) {
                            e.printStackTrace();
                        }
                        if (loginInfo.getCode() == 0){
                            //登录成功调用成功的方法
                            listener.loginSuccess(loginInfo);
                        }else {
                            //登录失败调用成功的方法
                            listener.loginFailed();
                        }
                    }
                });
            }
        }.start();
    }
}
