package com.bdqn.service;

import com.bdqn.entity.Student;
import com.github.pagehelper.PageInfo;

import java.util.List;

/**
 * Created by Administrator on 2017/12/13/013.
 */
public interface StudentService {
    //1.分页查询所有学生信息
    public PageInfo<Student> queryAll(Integer pageNum, Integer pageSize);
    //2.批量删除学生
    public Integer deleteStudentByIds(List<Integer> list);
    //3.添加学生信息
    public Integer addStudent(Student student);
    //4.通过id查询学生信息
    public Student queryStudentById(Integer id);
    //5.修改学生
    public Integer updateStudent(Student student);
}
