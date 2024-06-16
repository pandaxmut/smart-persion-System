package com.edu.smartpersionsys.controller;


import com.edu.smartpersionsys.mapper.UserMapper;
import com.edu.smartpersionsys.pojo.Older;
import com.edu.smartpersionsys.pojo.User;
import com.edu.smartpersionsys.pojo.Volunteer;
import com.edu.smartpersionsys.service.OlderService;
import com.edu.smartpersionsys.service.UserService;
import com.edu.smartpersionsys.service.VolunteerService;
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
    @Autowired
    private VolunteerService volunteerService;




    //注册页面
    @GetMapping("/reg")
    public String reg(){
        return "register";
    }
    //注册
    @PostMapping("/toReg")
    public String toReg(Model model, User user,String confirmPwd){
        System.out.println("userRole:"+user.getUserRole());
        System.out.println("confirmPwd:"+confirmPwd);
        //判断账号密码是否一致
        if(confirmPwd.equals(user.getUserPassword())){
            //先判断user表中的用户是否存在
            if(!userService.isSameByNameAndRole(user)){
                //注册
                boolean register = userService.register(user);
                if(register){
                    model.addAttribute("msg","注册成功");
                }else{
                    model.addAttribute("msg","注册失败");
                }
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
            }else{
                Volunteer volunteer = volunteerService.getVolunteerByUserId(u.getUserId());
                session.setAttribute("volunteer",volunteer);
                return "redirect:/"+ volunteer.getVId()+ "/volunteers";
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




}