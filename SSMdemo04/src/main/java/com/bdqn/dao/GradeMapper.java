package com.bdqn.dao;

import com.bdqn.entity.Grade;

import java.util.List;

/**
 * Created by Administrator on 2017/12/9/009.
 */
public interface GradeMapper {

    //1.查询年级内所有学生信息
    public List<Grade> queryAll();
    //2.根据gradeId查询班级
    public Grade queryGradeById(Integer id);
    //3.单条删除班级
    public Integer deleteGradeById(Integer id);
    //4.批量删除
    public Integer deleteGradeByIds(List<Integer> list);
    //5.添加班级信息
    public Integer addGrade(Grade grade);
    //6.修改班级信息
    public Integer updateGrade(Grade grade);
}

