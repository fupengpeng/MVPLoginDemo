package com.fupengpeng.mvplogindemo.login.entity;

/**
 * Created by fupengpeng on 2017/5/23 0023.
 */

public class LoginInfo {
    private int code;
    private String info;
    private String Data;
    public LoginInfo(){}

    @Override
    public String toString() {
        return "LoginInfo{" +
                "code=" + code +
                ", info='" + info + '\'' +
                ", Data='" + Data + '\'' +
                '}';
    }

    public LoginInfo(int code, String info, String data) {
        this.code = code;
        this.info = info;
        Data = data;
    }

    public int getCode() {
        return code;
    }

    public void setCode(int code) {
        this.code = code;
    }

    public String getInfo() {
        return info;
    }

    public void setInfo(String info) {
        this.info = info;
    }

    public String getData() {
        return Data;
    }

    public void setData(String data) {
        Data = data;
    }
}
