package com.bdqn.dao;

/**
 * Created by Administrator on 2017/12/16/016.
 */
    /*
    1.学生编号生成
    1.1. 创建班级的时候student_num表增加一条记录
    */
public interface StudentNumMapper {
    //1.添加学生编号
    public Integer addStudentNum(Integer gradeId);
    //2.修改学员所在班的最大学员编号
    public Integer updateMaxNumByGradeId(Integer gradeId);
    //3.查询学员所在班的最大学员编号
    public Integer queryMaxNumByGradeId(Integer gradeId);
}
