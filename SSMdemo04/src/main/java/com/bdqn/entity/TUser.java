package com.bdqn.entity;

/**
 * Created by Administrator on 2017/12/6/006.
 */
public class TUser {
    /**
     * id int(11) NOT NULL      用户ID
     user_name varchar(20) NULL 用户名
     password varchar(20) NULL 密码
     nick_name varchar(20) NULL 昵称
     */
    private Integer id;
    private String userName,password,nickName;

    @Override
    public String toString() {
        return "TUser{" +
                "id=" + id +
                ", userName='" + userName + '\'' +
                ", password='" + password + '\'' +
                ", nickName='" + nickName + '\'' +
                '}';
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getNickName() {
        return nickName;
    }

    public void setNickName(String nickName) {
        this.nickName = nickName;
    }
}
