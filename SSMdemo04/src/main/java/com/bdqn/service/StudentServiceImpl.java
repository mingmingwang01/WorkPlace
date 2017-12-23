package com.bdqn.service;

import com.bdqn.dao.StudentMapper;
import com.bdqn.dao.StudentNumMapper;
import com.bdqn.entity.Grade;
import com.bdqn.entity.Student;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

/**
 * Created by Administrator on 2017/12/13/013.
 */
@Service
public class StudentServiceImpl implements StudentService {
    @Resource
    private StudentMapper studentMapper;
    @Resource
    private StudentNumMapper studentNumMapper;

    //1.分页查询所有学生信息
    @Override
    public PageInfo<Student> queryAll(Integer pageNum,Integer pageSize) {
        PageHelper.startPage(pageNum, pageSize);
        List<Student> list=studentMapper.queryAll();
        PageInfo<Student> pageInfo = new PageInfo<>(list);
        return pageInfo;
    }

    //2.批量删除学生
    @Override
    public Integer deleteStudentByIds(List<Integer> list) {
        return studentMapper.deleteStudentByIds(list);
    }

    //3.添加学生信息  添加学生同时生成学生编号
    @Override
    public Integer addStudent(Student student) {
        //1.生成学生编号
        //1.1. 修改学员所在班的最大学员编号
        Grade grade=student.getGrade();
        Integer gradeId=grade.getId();
        studentNumMapper.updateMaxNumByGradeId(gradeId);
        String gradeName=grade.getGradeName();
        //1.2. 查询学员所在班的最大学员编号
        Integer maxNum=studentNumMapper.queryMaxNumByGradeId(gradeId);
        //1.3. 生成学员编号
        String studentNum= gradeName+maxNum;
        if(15-studentNum.length()>0){
            for(int i=1;i<=(15-studentNum.length());i++){
                gradeName=gradeName+0;
            }
            studentNum=gradeName+maxNum;
        }
        //4. 添加学员信息数据
        student.setStudentNum(studentNum);
        return studentMapper.addStudent(student);
    }

    //4.通过id查询学生信息
    public Student queryStudentById(Integer id){
        return studentMapper.queryStudentById(id);
    }

    //5.修改学生
    @Override
    public Integer updateStudent(Student student) {
        return studentMapper.updateStudent(student);
    }
}
