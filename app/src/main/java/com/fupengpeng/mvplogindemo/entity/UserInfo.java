package com.fupengpeng.mvplogindemo.entity;

/**
 * Created by fupengpeng on 2017/5/23 0023.
 */

public class UserInfo {
    private String tel;
    private String password;

    @Override
    public String toString() {
        return "UserInfo{" +
                "tel='" + tel + '\'' +
                ", password='" + password + '\'' +
                '}';
    }

    public UserInfo(){}

    public UserInfo(String tel, String password) {
        this.tel = tel;
        this.password = password;
    }

    public String getTel() {
        return tel;
    }

    public void setTel(String tel) {
        this.tel = tel;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
}
