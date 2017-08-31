package com.hk.utils.com.hk.entity;

/**
 * User: hk
 * Date: 2017/8/30 上午4:06
 * version: 1.0
 */
public class UserDetail {

    private String addr;
    private String phone;

    public UserDetail(String addr, String phone) {
        this.addr = addr;
        this.phone = phone;
    }

    public String getAddr() {
        return addr;
    }

    public void setAddr(String addr) {
        this.addr = addr;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }
}
