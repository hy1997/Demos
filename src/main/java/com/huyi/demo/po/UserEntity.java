package com.huyi.demo.po;

import java.io.Serializable;

public class UserEntity implements Serializable {
    private String uid;
    private String openid;
    private String name;
    private String image; //头像
    private String username;
    private String passwore;


    public String getUid() {
        return uid;
    }

    public void setUid(String uid) {
        this.uid = uid;
    }

    public String getOpenid() {
        return openid;
    }

    public void setOpenid(String openid) {
        this.openid = openid;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPasswore() {
        return passwore;
    }

    public void setPasswore(String passwore) {
        this.passwore = passwore;
    }
}
