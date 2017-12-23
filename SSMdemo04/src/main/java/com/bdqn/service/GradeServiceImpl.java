package com.bdqn.service;

import com.bdqn.dao.GradeMapper;
import com.bdqn.dao.StudentNumMapper;
import com.bdqn.entity.Grade;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

/**
 * Created by Administrator on 2017/12/9/009.
 */
@Service
public class GradeServiceImpl implements GradeService {
    @Resource
    private GradeMapper gradeMapper;
    @Resource
    private StudentNumMapper studentNumMapper;

    //1.分页查询所有班级
    @Override
    public PageInfo<Grade> queryAll(Integer pageNum,Integer pageSize) {
        PageHelper.startPage(pageNum,pageSize);
        List<Grade> list=gradeMapper.queryAll();
        PageInfo<Grade> pageInfo = new PageInfo<>(list);
        return pageInfo;
    }
    //2.根据gradeId查询班级
    @Override
    public Grade queryGradeById(Integer id) {
        return gradeMapper.queryGradeById(id);
    }
    //3.单条删除班级信息
    @Override
    public Integer deleteGradeById(Integer id) {
        return gradeMapper.deleteGradeById(id);
    }
    //4.批量删除
    @Override
    public Integer deleteGradeByIds(List<Integer> list) {
        return gradeMapper.deleteGradeByIds(list);
    }

    //5.添加班级信息  创建班级的时候增加一条student_num表记录
    @Override
    public Integer addGrade(Grade grade) {
        gradeMapper.addGrade(grade);

        return studentNumMapper.addStudentNum(grade.getId());
    }

    //6.修改班级信息
    public Integer updateGrade(Grade grade) {
        return gradeMapper.updateGrade(grade);
    }
    //7.获取全部班级
    @Override
    public List<Grade> queryAll() {
        return gradeMapper.queryAll();
    }

}
