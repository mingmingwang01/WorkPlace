package com.bdqn.controller;

import com.bdqn.entity.TUser;
import com.bdqn.service.TUserService;
import com.bdqn.util.Constants;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import javax.annotation.Resource;
import javax.servlet.http.HttpSession;

/**
 * Created by Administrator on 2017/12/6/006.
 */
@Controller
@RequestMapping("user")  //@RequestMapping注解指定请求的url
public class TUserController {
    @Resource
    private TUserService tUserService;
    //1.用户登录
    //@RequestMapping：定义请求url到处理器功能方法的映射
    @RequestMapping(value = "login",method = RequestMethod.POST)
    public String login(TUser tUser, HttpSession session, Model model) {
        //调用service方法，进行用户匹配
        TUser loginUser = tUserService.login(tUser);
        if (null != loginUser) {   //登录成功
            session.setAttribute(Constants.LOGIN_USER, loginUser); //放入session
            return "welcome";                                      //页面跳转（welcome.jsp）
            //response.sendRedirect("jsp/welcome.jsp");
        } else {
            //页面跳转（login.jsp）带出提示信息--转发   添加model
            model.addAttribute("message", "用户名密码错误!");
            return "index";
        }
    }
    //2.用户注销
    @RequestMapping("logout")
    public String logout(HttpSession session) {
        //session.removeAttribute("loginUser");
        session.removeAttribute(Constants.LOGIN_USER);
        System.out.println("退出");
        return "index";
    }
}
