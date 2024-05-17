package com.edu.smartpersionsys.controller;


import com.edu.smartpersionsys.mapper.UserMapper;
import com.edu.smartpersionsys.pojo.User;
import com.edu.smartpersionsys.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

import java.util.List;

@Controller
public class UserController {

    @Autowired
    private UserService userService;

    @GetMapping (value = "/welcome" )
    public String welcome(){
        System.out.println("welcome");
        return "success ";
    }

    //简单的判断 登录成功
    @GetMapping ("/login")
    public String login(Model model){
        return "login";
    }


    //简单的判断 登录成功
    @PostMapping("/tologin")
    public String login(@Validated User user , Model model){
        System.out.println("userName = " + user.getUserName());
        System.out.println(user.getUserPassword());

        //判断用户是否登录成功
        boolean flag = userService.login(user);
        model.addAttribute("msg","登录成功！");
        if (flag) return "bootstrap-taber";
        else{
            model.addAttribute("msg","登录失败！");
            return "login";
        }


    }

    @Autowired
    private UserMapper userMapper;
    @GetMapping("select")
    public String select(){
        //判断用户是否登录成功
        List<User> userList = userMapper.allSelect();
        for (User user : userList) {
            System.out.println(user);
        }
        return "success";
    }

    @GetMapping("old")
    public String olderManage(Model model){
        return "olderManage";
    }
    @GetMapping("schedule")
    public String schedule(Model model){
        return "schedule";
    }

}
