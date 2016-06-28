package com.zhidisoft.sqldemo2.bean;

/**
 * Created by Administrator on 2016/6/18.
 */
public class User {
    private int id;
    private String username;
    private String pwd;

    public User(int id,String username, String pwd) {
        this.id=id;
        this.username = username;
        this.pwd = pwd;
    }

    public User() {

    }

    public User(String username, String pwd) {

        this.username = username;
        this.pwd = pwd;
    }

    public void setId(int id) {

        this.id = id;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public void setPwd(String pwd) {
        this.pwd = pwd;
    }

    public int getId() {

        return id;
    }

    public String getUsername() {
        return username;
    }

    public String getPwd() {
        return pwd;
    }
}
