package com.bdqn.service;

import com.bdqn.entity.Grade;
import com.github.pagehelper.PageInfo;

import java.util.List;

/**
 * Created by Administrator on 2017/12/9/009.
 */

public interface GradeService {
    //1.分页查询所有班级
    public PageInfo<Grade> queryAll(Integer pageNum, Integer pageSize);
    //2.根据gradeId查询班级
    public Grade queryGradeById(Integer id);
    //3.单条删除班级信息
    public Integer deleteGradeById(Integer id);
    //4.批量删除
    public Integer deleteGradeByIds(List<Integer> list);
    //5.添加班级信息
    public Integer addGrade(Grade grade);
    //6.修改班级信息
    public Integer updateGrade(Grade grade);
    //7.获取全部班级
    public List<Grade> queryAll();
}