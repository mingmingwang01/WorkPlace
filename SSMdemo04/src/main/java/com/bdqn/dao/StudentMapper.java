package com.bdqn.dao;

import com.bdqn.entity.Student;

import java.util.List;

public interface StudentMapper {
    //1.查询所有学生信息
    public List<Student> queryAll();
    //2.批量删除学生
    public Integer deleteStudentByIds(List<Integer> list);
    //3.添加学生信息
    public Integer addStudent(Student student);
    //4.通过id查询学生信息
    public Student queryStudentById(Integer id);
    //5.修改学生
    public Integer updateStudent(Student student);

}
