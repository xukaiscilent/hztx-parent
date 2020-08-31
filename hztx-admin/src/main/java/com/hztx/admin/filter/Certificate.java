package com.hztx.admin.filter;

import java.util.Set;

/**
 * @auther xukai
 * @date 2020/3/20.
 */
public class Certificate {


    private String loginName;

    private String token;

    private String phone;


    Set<Object> permissions;

    public Certificate() {
    }

    public Certificate(String loginName) {
        this.loginName = loginName;
    }

    public Certificate(String loginName, String token, String phone, Set<Object> permissions) {
        this.loginName = loginName;
        this.token = token;
        this.phone = phone;
        this.permissions = permissions;
    }

    public Certificate(String token, String phone) {
        this.token = token;
        this.phone = phone;
    }

    public Set<Object> getPermissions() {
        return permissions;
    }

    public void setPermissions(Set<Object> permissions) {
        this.permissions = permissions;
    }

    public String getLoginName() {
        return loginName;
    }

    public void setLoginName(String loginName) {
        this.loginName = loginName;
    }

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }
}
