package com.fupengpeng.mvplogindemo.login.model;

import android.util.Log;

import com.alibaba.fastjson.JSON;
import com.fupengpeng.mvplogindemo.base.WaitDialog;
import com.fupengpeng.mvplogindemo.bean.UserInfo;
import com.fupengpeng.mvplogindemo.entity.HttpInfo;
import com.fupengpeng.mvplogindemo.login.entity.LoginInfo;
import com.yolanda.nohttp.Headers;
import com.yolanda.nohttp.NoHttp;
import com.yolanda.nohttp.rest.OnResponseListener;
import com.yolanda.nohttp.rest.Request;
import com.yolanda.nohttp.rest.RequestQueue;
import com.yolanda.nohttp.rest.Response;

import java.io.IOException;
import java.util.Locale;


/**
 * Created by fupengpeng on 2017/5/23 0023.
 *     登录的model
 */
public class LoginModel implements LoginModelInterface {
    public static final String TAG = "LoginModel";
    //请求队列
    private RequestQueue requestQueue;

    //用来标志请求的what
    private static final int NOHTTP_WHAT = 0x001;

    //登录成功返回的消息实体
    private LoginInfo loginInfo;

    //实现LoginModelInterface接口后，重写login方法
    @Override
    public void login(final String tel, final String password, final OnLoginListener listener) {
        //创建请求队列
        requestQueue = NoHttp.newRequestQueue();
        //创建String请求
        final Request<String> request = NoHttp.createStringRequest(HttpInfo.URL_POST_LOGINL_LOGIN);
        //添加请求参数
        request.add("tel", tel);// String类型
        request.add("password", password);

        //将request加入requestQueue
        requestQueue.add(NOHTTP_WHAT, request, new OnResponseListener<String>() {
            @Override
            public void onStart(int i) {
                // 请求开始，这里可以显示一个dialog
                Log.e(TAG, "onStart: "+request );
            }

            @Override
            public void onSucceed(int i, Response<String> response) {
                Log.e("zss", "网络请求成功，返回请求数据---- + response.body().string()");

                // 根据what判断是哪个请求的返回，这样就可以用一个OnResponseListener来接受多个请求的结果。
                if (i == NOHTTP_WHAT) {
                    // 服务器响应码。
                    int responseCode = response.getHeaders().getResponseCode();
                    // 这里一定要判断状态码，经测试，404错误时也走这个回调方法
                    if (responseCode == 200) {
                        // 响应结果。
                        String result = response.get();
                        Log.e(TAG, "onSucceed: "+result );
                        //显示结果
                        loginInfo = new LoginInfo();
                        //解析登录成功返回的json字符串，得到消息实体
                        loginInfo =  JSON.parseObject(result,LoginInfo.class);
                        if (loginInfo.getCode() == 0){
                            //登录成功调用成功的方法
                            Log.e(TAG, "onSucceed: "+"00001" );
                            listener.loginSuccess(loginInfo);
                            UserInfo userInfo = new UserInfo();
                            userInfo.setTel(tel);
                            userInfo.setPassword(password);
                            userInfo.setUid(loginInfo.getInfo());
                            userInfo.save();
                        }else {
                            //登录失败调用成功的方法
                            Log.e(TAG, "onSucceed: "+"000002" );
                            listener.loginFailed();
                        }

                        // 拿到请求时设置的tag。
                        Object tag = response.getTag();

                        // 响应头
                        Headers headers = response.getHeaders();

                        String headResult = "响应码：%1$d\n花费时间：%2$d毫秒。";
                        headResult = String.format(Locale.getDefault(), headResult,
                                headers.getResponseCode(), response.getNetworkMillis());
                    }
                }
            }

            @Override
            public void onFailed(int i, Response<String> response) {
                Log.e("zss", "网络请求失败");
                //登录失败调用失败的方法
                listener.loginFailed();
            }

            @Override
            public void onFinish(int i) {
                // 请求结束，这里关闭dialog
                Log.e(TAG, "onFinish: "+i );
            }
        });
    }
}
