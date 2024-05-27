package com.edu.smartpersionsys.controller;

import com.edu.smartpersionsys.mapper.TaskMapper;
import com.edu.smartpersionsys.pojo.Older;
import com.edu.smartpersionsys.pojo.Task;
import com.edu.smartpersionsys.pojo.Volunteer;
import com.edu.smartpersionsys.service.VolunteerService;
import com.github.pagehelper.PageInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
public class VolunteersController {
    @Autowired
    private VolunteerService volunteerService;

    //志愿者启动页
    @GetMapping("/volunteers")
    public String volunteers(Model model){
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
            System.out.println(all);
            //要求前端输出"没有查找到任务"
            if (all.getSize()==0){
                model.addAttribute("findMsg","0");
            }
        }
        model.addAttribute("page", all);
        model.addAttribute("path", "/schedule?pageIndex=");
        return "schedule";
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

    @Autowired
    private TaskMapper taskMapper;
    //管理员结束任务
    @GetMapping("/schedule/over/{id}")
    public String overTask(@PathVariable("id") int id){
        //志愿者增加时常,有则增加
        volunteerService.addVolunteerTime(id);
        //将任务的状态改成"结束"
        taskMapper.updateTaskById(id,"结束");
        return "redirect:/schedule";
    }
}
