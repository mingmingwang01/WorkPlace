package com.bdqn.dao;

import com.bdqn.entity.TUser;

import java.util.List;

/**
 * Created by Administrator on 2017/12/6/006.
 */
public interface TUserMapper {
    //1.用户登录
    public TUser login(TUser tUser);
    //2.查询所有用户信息  并进行分页查询
    public List<TUser> queryAll();
}
