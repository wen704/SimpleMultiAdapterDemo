package com.gaofu.adapterdemo.bean;

import java.io.Serializable;

/**
 * @author Gaofu
 * Time 2019-09-21 13:48
 */
public class UserInfo implements Serializable {

    private String account;
    private String password;
    // 布局类型
    private int    type;

    public UserInfo() {
    }

    public UserInfo(String account, String password) {
        this.account = account;
        this.password = password;
    }

    public String getAccount() {
        return account;
    }

    public void setAccount(String account) {
        this.account = account;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public int getType() {
        return type;
    }

    public void setType(int type) {
        this.type = type;
    }

    @Override
    public String toString() {
        return "UserInfo{" +
                "account='" + account + '\'' +
                ", password='" + password + '\'' +
                ", type=" + type +
                '}';
    }

}
