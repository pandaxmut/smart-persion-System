package com.edu.smartpersionsys.controller;

import com.edu.smartpersionsys.mapper.TaskMapper;
import com.edu.smartpersionsys.pojo.Older;
import com.edu.smartpersionsys.pojo.Task;
import com.edu.smartpersionsys.pojo.Volunteer;
import com.edu.smartpersionsys.service.VolunteerService;
import com.edu.smartpersionsys.utils.FaceUtil;
import com.github.pagehelper.PageInfo;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.util.ResourceUtils;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.io.File;
import java.io.IOException;
import java.util.List;

@Controller
public class VolunteersController {
    @Autowired
    private VolunteerService volunteerService;
    @Autowired
    private TaskMapper taskMapper;

    //志愿者启动页
    @GetMapping("/{vId}/volunteers")
    public String volunteers(Model model, @PathVariable int vId){
        //志愿者基本信息
        Volunteer volunteer = volunteerService.getVolunteerByVId(vId);
        //查看志愿者参与的活动
        List<Task> tasks = volunteerService.chechTask(vId);

        //上传到前端的数据
        model.addAttribute("tasks",tasks);
        model.addAttribute("volunteer",volunteer);
        return "volunteers";
    }
    //访问志愿者大厅启动页
    @RequestMapping("/schedule")
    public String Schedule(Model model,
                         @RequestParam(value = "pageIndex", required = false, defaultValue = "1") Integer pageIndex,
                         @RequestParam(value = "pageSize", required = false, defaultValue = "5") Integer pageSize,
                         @RequestParam(value = "findTaskName", required =false,defaultValue = "无") String findTaskName){

        System.out.println(findTaskName);
        PageInfo<Task> all = new PageInfo<>();
        if(findTaskName.equals("无")){
            all = volunteerService.findAll(pageIndex, pageSize);
        }else {
            all = volunteerService.findByTaskName(pageIndex, pageSize,findTaskName);
            //要求前端输出"没有查找到任务"
            if (all.getSize()==0){
                model.addAttribute("findMsg","0");
            }
        }

        model.addAttribute("page", all);
        model.addAttribute("path", "/schedule?pageIndex=");
        return "schedule";
    }


    //修改志愿者信息
    @PostMapping(value = "/editVolunteerInfo")
    public String editOlderInfo(Model model,
                                RedirectAttributes redirect,
                                @Validated  Volunteer volunteer,
                                @RequestParam("photoFile") MultipartFile photoFile) throws IOException {
        System.out.println(volunteer.toString());
        //判断用户是否上传了文件 ：区别在于需不需要 更新头像地址
        boolean flag = false;
        if (!photoFile.isEmpty()) { //存在
            //文件上传的地址
            String path = ResourceUtils.getURL("classpath:").getPath() + "static/media/photo";
            String realPath = path.replace('/', '\\').substring(1, path.length());
            //获取文件的名称
            final String fileName = photoFile.getOriginalFilename();
            //限制文件上传的类型

            //文件上传
            File file = new File(realPath, fileName);
            //完成文件上传
            photoFile.transferTo(file);
            System.out.println("文件上传成功");
            //设置头像路径
            volunteer.setVNamePhoto("../media/photo/"+fileName);
            //更新老人身份信息
            flag = volunteerService.updateVolunteerInfo(volunteer);
        }else{
            flag = volunteerService.updateVolunteerPhone(volunteer);
        }

        String updateInfoMsg;
        if (flag){
            updateInfoMsg="1";
            redirect.addFlashAttribute("updateInfoMsg",updateInfoMsg);
        }else{
            updateInfoMsg="0";
            redirect.addFlashAttribute("updateInfoMsg",updateInfoMsg);
        }
        // 将消息添加到Flash属性中
        return "redirect:/"+ volunteer.getVId()+ "/volunteers";
    }




    //管理员发布志愿活动任务
    @PostMapping("/addTask")
    public String addTask(Task task,Model model){
        System.out.println("task:"+task.toString());
        int i = volunteerService.addTask(task);
        //返回一个值 addTaskMsg 前端判断是否插入成功
        if (i==1) model.addAttribute("addTaskMsg","1");
        else model.addAttribute("addTaskMsg","0");
        return "redirect:/schedule";
    }


    //管理员结束任务
    @GetMapping("/schedule/over/{id}")
    public String overTask(@PathVariable("id") int id){
        //志愿者增加时常,有则增加
        volunteerService.addVolunteerTime(id);
        //将任务的状态改成"结束"
        taskMapper.updateTaskById(id,"结束");
        return "redirect:/schedule";
    }

    /**
     * 志愿者进行人脸识别
     * @param faceBase
     * @param vName
     * @param request
     * @return 返回完整的百度API验证结果数据
     * @throws IOException
     */
    @PostMapping("/volunteers/faceLogin")
    @ResponseBody
    public String faceLogin(@RequestParam("faceBase") String faceBase,
                            @RequestParam("vName") String vName,
                            HttpServletRequest request) throws IOException {
        System.out.println(faceBase);
        System.out.println("----------------------------------------------------");
        FaceUtil faceUtil = new FaceUtil();
        String face = faceUtil.face(vName, faceBase);
        System.out.println(face);
        //这里实现你的其他操作
        return face;
    }

    @GetMapping("/volunteerGetTask/{id}")
    public String volunteerGetTask(@PathVariable("id") int id, Model model, HttpSession session){
        Volunteer volunteer = (Volunteer)session.getAttribute("volunteer");
        boolean b = volunteerService.volunteerGetTask(id, volunteer.getVId());
        if (b) model.addAttribute("volunteerGetTaskMsg","1");
        else model.addAttribute("volunteerGetTaskMsg","0");
        return "redirect:/schedule";
    }



}
