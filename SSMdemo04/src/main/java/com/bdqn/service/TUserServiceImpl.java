package com.bdqn.service;

import com.bdqn.dao.TUserMapper;
import com.bdqn.entity.TUser;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

/**
 * service层捕获异常，进行事务处理
 * 事务处理：调用不同dao的多个方法，必须使用同一个connection（connection作为参数传递）
 * 事务完成之后，需要在service层进行connection的关闭，在dao层关闭（PreparedStatement和ResultSet对象）
 * @author Administrator
 * Created by Administrator on 2017/12/6/006.
 */
@Service("tUserService")
public class TUserServiceImpl implements TUserService {
    @Resource
    private TUserMapper tUserMapper;

    @Override
    public TUser login(TUser tUser) {
        return tUserMapper.login(tUser);
    }

    /*
     在sql语句执行之前添加分页操作,用PageInfo封装结果
    */
    @Override
    public PageInfo<TUser> queryAll(Integer pageNum, Integer pageSize) {
        PageHelper.startPage(pageNum,pageSize);

        List<TUser>list= tUserMapper.queryAll();

        PageInfo<TUser>pageInfo=new PageInfo<>(list);

        return pageInfo;
    }
}
