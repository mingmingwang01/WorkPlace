package com.bdqn.controller;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.bdqn.entity.Grade;
import com.bdqn.service.GradeService;
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
@RequestMapping("grade")   //@RequestMapping注解指定请求的url
public class GradeController {

    @Resource
    private GradeService gradeService;

    //1.查询年级内所有学生信息
    @RequestMapping("toGrade")
    public String toGrade(Integer pageNum,Integer pageSize,Model model) {
        PageInfo<Grade> pageInfo=gradeService.queryAll(pageNum,pageSize);
        model.addAttribute("pageInfo",pageInfo);
        return "grade/grade";
    }

    //2.根据gradeId查询班级
    @ResponseBody
    @RequestMapping(value ="queryGradeById",method = RequestMethod.GET,
            produces = {"application/json;charset=UTF-8"})
        public String queryGradeById(Integer gradeId){
        Grade grade=gradeService.queryGradeById(gradeId);
        return JSON.toJSONString(grade);
    }

    //3.单条删除班级
    @ResponseBody
    @RequestMapping(value = "deleteGradeById",method = RequestMethod.POST,
            produces ={"application/json;charset=UTF-8"} )
        public String deleteGradeById(Integer gradeId) {
        int n=gradeService.deleteGradeById(gradeId);
        if(n>0){
            return JSON.toJSONString(Message.success());
        }
        return JSON.toJSONString(Message.error());
    }

    //4.批量删除
    @ResponseBody
    @RequestMapping(value = "deleteGradeByIds",method = RequestMethod.POST,
            produces = {"application/json;charset=UTF-8"})
    public String deleteGradeByIds(String gradeIds){
        String[] gradeIdArray=gradeIds.split(",");
        List<Integer> list = new ArrayList<Integer>();
        for (String s : gradeIdArray) {
            list.add(Integer.parseInt(s));
        }
        int n=gradeService.deleteGradeByIds(list);
        if(n>0){
            return JSON.toJSONString(Message.success());
        }
        return JSON.toJSONString(Message.error());
    }

    //5.添加班级信息
    @ResponseBody
    @RequestMapping(value = "addGrade",method = RequestMethod.POST,
            produces = {"application/json;charset=UTF-8"})
    public String addGrade(Grade grade) {
        int n = gradeService.addGrade(grade);
        if (n > 0) {
            return JSON.toJSONString(Message.success());
        }
        return JSON.toJSONString(Message.error());
    }

    //6.修改班级信息
    @ResponseBody
    @RequestMapping(value = "updateGrade",method = RequestMethod.POST,
            produces = {"application/json;charset=UTF-8"})
    public String updateGrade(Grade grade) {
        int n = gradeService.updateGrade(grade);
        if (n > 0) {
            return JSON.toJSONString(Message.success());
        }
        return JSON.toJSONString(Message.error());
    }

    //7.获取全部班级信息
    @ResponseBody
    @RequestMapping(value = "queryAllGrade",method = RequestMethod.GET,
            produces = {"application/json;charset=UTF-8"})
    public String queryAllGrade() {
        List<Grade> list=gradeService.queryAll();
        return JSONArray.toJSONString(list);
    }

}