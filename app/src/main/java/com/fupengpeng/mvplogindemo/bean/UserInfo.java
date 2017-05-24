package com.fupengpeng.mvplogindemo.bean;

import com.orm.SugarRecord;

import java.io.Serializable;

/**
 * Created by fupengpeng on 2017/5/24 0024.
 */

public class UserInfo extends SugarRecord implements Serializable{
    private String uid;
    private String tel;
    private String password;
    public UserInfo (){}

    public UserInfo(String uid, String tel, String password) {
        this.uid = uid;
        this.tel = tel;
        this.password = password;
    }

    @Override
    public String toString() {
        return "UserInfo{" +
                "uid='" + uid + '\'' +
                ", tel='" + tel + '\'' +
                ", password='" + password + '\'' +
                '}';
    }

    public String getUid() {
        return uid;
    }

    public void setUid(String uid) {
        this.uid = uid;
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
