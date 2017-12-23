package com.bdqn.service;

import com.bdqn.entity.TUser;
import com.github.pagehelper.PageInfo;

/**
 * Created by Administrator on 2017/12/6/006.
 */
public interface TUserService {
    /**
     * 用户登录
     * @return TUser
     */
    public TUser login(TUser tUser);

    //2.查询所有用户信息
    public PageInfo<TUser> queryAll(Integer pageNum, Integer pageSize);
}
