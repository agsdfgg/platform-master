package com.mcwl.authentication.entity;

/**
 * @author Jerry
 * @date 2018/8/13
 * 描述：
 * 登录时发来的消息实体映射
 * @description
 */
public class User {

    private long id;

    private String username;

    private String auth;

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getAuth() {
        return auth;
    }

    public void setAuth(String auth) {
        this.auth = auth;
    }
}
