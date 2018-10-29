package com.wb.weibao.model;

import java.io.Serializable;

/**
 * 用于保存数据库 信息实体类
 */
public class LoginUserInfoBean implements Serializable {
    private int id;

    private String HeadPicUrl;
    private String UserName;
    private String PassWord;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getHeadPicUrl() {
        return HeadPicUrl;
    }

    public void setHeadPicUrl(String headPicUrl) {
        HeadPicUrl = headPicUrl;
    }

    public String getUserName() {
        return UserName;
    }

    public void setUserName(String userName) {
        UserName = userName;
    }

    public String getPassWord() {
        return PassWord;
    }

    public void setPassWord(String passWord) {
        PassWord = passWord;
    }

    @Override
    public String toString() {
        return "LoginUserInfoBean{" +
                "id=" + id +
                ", HeadPicUrl='" + HeadPicUrl + '\'' +
                ", UserName='" + UserName + '\'' +
                ", PassWord='" + PassWord + '\'' +
                '}';
    }
}
