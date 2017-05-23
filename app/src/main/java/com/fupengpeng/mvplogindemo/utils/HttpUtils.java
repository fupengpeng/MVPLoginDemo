package com.fupengpeng.mvplogindemo.utils;

import android.util.Log;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.fupengpeng.mvplogindemo.inter.OnLoginListener;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by Administrator on 2017/4/22.
 */

public class HttpUtils {

    //回答问题
    public static void LoginPOST(String uri,
                                         final String tel,
                                         final String password,
                                         final OnLoginListener onLoginListener,
                                         RequestQueue requestQueue) {
        StringRequest request = new StringRequest(Request.Method.POST, uri, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                onLoginListener.login(response);
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Log.e("-----", error.getMessage());
            }
        }) {
            @Override
            protected Map<String, String> getParams() throws AuthFailureError {
                Map<String, String> map = new HashMap<>();
                map.put("tel", tel);
                map.put("password", password);
                return map;
            }
        };
        requestQueue.add(request);
    }




}
