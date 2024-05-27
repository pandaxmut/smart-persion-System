package com.edu.smartpersionsys.controller;


import com.edu.smartpersionsys.mapper.UserMapper;
import com.edu.smartpersionsys.pojo.Older;
import com.edu.smartpersionsys.pojo.User;
import com.edu.smartpersionsys.service.OlderService;
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
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

@Controller
public class UserController {

    @Autowired
    private UserService userService;
    @Autowired
    private OlderService olderService;






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
                //新增一个老人
                Older older = olderService.addOlder(user.getUserId(),user.getUserName());
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


    //简单的登录判断
    @PostMapping("/toLogin")
    public String login(@Validated User user , Model model, HttpSession session, RedirectAttributes redirect){
        System.out.println("userName = " + user.getUserName());
        System.out.println(user.getUserPassword());
        System.out.println(user.getUserRole());

        //判断用户是否登录成功
        User u = userService.login(user);
        model.addAttribute("msg","登录成功！");

        //判断用户是否存在，返回对应角色的页面
        if (u == null ){
            model.addAttribute("msg","登录失败！");
            return "login";
        }else{
            session.setAttribute("user",u);
            if (user.getUserRole().equals("管理员")){
                return "redirect:/index";
            } else if (user.getUserRole().equals("老人")){
                Older older = olderService.getOlderByUserId(u.getUserId());
                session.setAttribute("older",older);
                return "redirect:/"+older.getOlderId()+"/olderPage";
            } else if (user.getUserRole().equals("家属")){
                return "redirect:/familyPage";
            }else{
                return "redirect:/volunteers";
            }
        }
    }

    //注销用户
    @GetMapping("/layout")
    public String layout(HttpServletRequest request){
        HttpSession session = request.getSession();
        session.setMaxInactiveInterval(1000*60*60);
        System.out.println("sessionID "+ session.getId());
        User user = (User)session.getAttribute("user");
        //要求： 删除user 和 role的session
        if (user !=null){
            if(user.getUserRole() == "管理员") session.removeAttribute("admin");
            else if (user.getUserRole() == "老人") session.removeAttribute("older");
            else if (user.getUserRole() == "志愿者") session.removeAttribute("volunteer");
            else session.removeAttribute(" 家属");
        }
        session.removeAttribute("user");
        return "login";
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