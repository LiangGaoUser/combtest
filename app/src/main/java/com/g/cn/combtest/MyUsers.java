package com.g.cn.combtest;

import cn.bmob.v3.Bmob;
import cn.bmob.v3.BmobObject;

public class MyUsers extends BmobObject {

    private String username;
    private String password;


    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
}
