package com.edu.smartpersionsys.controller;


import com.edu.smartpersionsys.mapper.UserMapper;
import com.edu.smartpersionsys.pojo.User;
import com.edu.smartpersionsys.service.UserService;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
public class UserController {

    @Autowired
    private UserService userService;
    @Autowired
    private UserMapper userMapper;





    //注册
    @GetMapping("/reg")
    public String reg(){
        return "register";
    }
    //注册判断
    @PostMapping("/toReg")
    public String toReg(Model model, User user,String confirmPwd){
        System.out.println("userRole:"+user.getUserRole());
        System.out.println("confirmPwd:"+confirmPwd);
        //判断账号密码是否一致
        if(confirmPwd.equals(user.getUserPassword())){
            //先判断user表中的用户是否存在
            if(!userService.isSameByNameAndRole(user)){
                //注册
                userService.register(user);
            }else{
                //返回错误信息
                model.addAttribute("msg","用户已存在");
            }
        }else{
            model.addAttribute("msg","账号密码不一致");
        }
        return "login";
    }


    //访问登录页
    @GetMapping ("/login")
    public String login(Model model){
        return "login";
    }

    //注销用户
    @GetMapping("/layout")
    public String layout(HttpServletRequest request){
        HttpSession session = request.getSession();
        session.setMaxInactiveInterval(1000*60*60);
        System.out.println("sessionID "+ session.getId());
        if (session.getAttribute("user") !=null){
            session.removeAttribute("user");
        }
        return "login";
    }

    //简单的登录判断
    @PostMapping("/toLogin")
    public String login(@Validated User user , Model model){
        System.out.println("userName = " + user.getUserName());
        System.out.println(user.getUserPassword());
        System.out.println(user.getUserRole());

        //判断用户是否登录成功
        boolean flag = userService.login(user);
        model.addAttribute("msg","登录成功！");

        //判断用户是否存在，返回对应角色的页面
        if (!flag){
            model.addAttribute("msg","登录失败！");
            return "login";
        }else{
            if (user.getUserRole().equals("管理员")){
                return "index";
            } else if (user.getUserRole().equals("老人")){
                return "redirect:/olderPage";
            } else if (user.getUserRole().equals("家属")){
                return "redirect:/familyPage";
            }else{
                return "redirect:/nursePage";
            }
        }
    }
    //访问老人管理页
    @GetMapping("old")
    public String olderManage(Model model){
        return "olderManage";
    }
    //访问日程安排页面
    @GetMapping("schedule")
    public String schedule(Model model){
        return "schedule";
    }
    //护工页面
    @GetMapping("/nursePage")
    @ResponseBody
    public String nursePage(){
        return "护工页面";
    }
    //家属页面
    @GetMapping("/familyPage")
    @ResponseBody
    public String familyPage(){
        return "家属页面";
    }

}