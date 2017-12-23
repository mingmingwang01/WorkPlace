package com.bdqn.controller;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.bdqn.entity.Grade;
import com.bdqn.entity.Student;
import com.bdqn.service.GradeService;
import com.bdqn.service.StudentService;
import com.bdqn.util.Message;
import com.github.pagehelper.PageInfo;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by Administrator on 2017/12/9/009.
 */
@Controller
@RequestMapping("student")   //@RequestMapping注解指定请求的url
public class StudentController {
    @Resource
    private StudentService studentService;
    @Resource
    private GradeService gradeService;

    //1.查询所有学生信息
    @RequestMapping("toStudent")
    public String toStudent(Integer pageNum,Integer pageSize,Model model) {
        PageInfo<Student> pageInfo = studentService.queryAll(pageNum, pageSize);
        model.addAttribute("pageInfo", pageInfo);
        return "student/student";
    }

    //2.批量删除学生
    @ResponseBody
    @RequestMapping(value = "deleteStudentList",method = RequestMethod.POST,
            produces ={"application/json;charset=UTF-8"} )
    public String deleteStudentList(String ids) {
        String[] idsArray = ids.split(",");
        List<Integer> idList = new ArrayList<>();
        for (String s : idsArray) {
            idList.add(Integer.parseInt(s));
        }
        int n = studentService.deleteStudentByIds(idList);
        if (n > 0) {
            return JSON.toJSONString(Message.success());
        }
        return JSON.toJSONString(Message.error());
    }

    //3.显示所有班级
    @ResponseBody
    @RequestMapping(value = "queryAllGrade",method = RequestMethod.GET,
            produces = {"application/json;charset=UTF-8"})
    public String queryAllGrade() {
        List<Grade> list=gradeService.queryAll();
        return JSONArray.toJSONString(list);
    }

    //4.添加学生
    @ResponseBody
    @RequestMapping(value = "addStudent",method = RequestMethod.POST,
            produces = {"application/json;charset=UTF-8"})
    public String addStudent(Student student) {
        int n = studentService.addStudent(student);
        if (n > 0) {
            return JSON.toJSONString(Message.success());
        }
        return JSON.toJSONString(Message.error());
    }

    //5.通过id查询学生
    @ResponseBody
    @RequestMapping(value = "queryStudentById",method = RequestMethod.GET,
            produces = {"application/json;charset=UTF-8"})
    public String queryStudentById(Integer studentId) {
        Student student = studentService.queryStudentById(studentId);

        return JSON.toJSONString(student);
    }

    //6.修改学生
    @ResponseBody
    @RequestMapping(value = "updateStudent",method = RequestMethod.POST,
            produces = {"application/json;charset=UTF-8"})
    public String updateStudent(Student student) {
        int n = studentService.updateStudent(student);
        if (n > 0) {
            return JSON.toJSONString(Message.success());
        }
        return JSON.toJSONString(Message.error());
    }

}