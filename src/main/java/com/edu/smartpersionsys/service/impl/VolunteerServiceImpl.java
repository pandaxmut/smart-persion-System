package com.edu.smartpersionsys.service.impl;

import com.edu.smartpersionsys.mapper.TaskMapper;
import com.edu.smartpersionsys.mapper.VolunteerMapper;
import com.edu.smartpersionsys.pojo.Older;
import com.edu.smartpersionsys.pojo.Task;
import com.edu.smartpersionsys.pojo.Volunteer;
import com.edu.smartpersionsys.service.VolunteerService;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class VolunteerServiceImpl implements VolunteerService {
    @Autowired
    private VolunteerMapper volunteerMapper;
    @Autowired
    private TaskMapper taskMapper;

    @Override
    public PageInfo<Task> findAll(Integer pageIndex, Integer pageSize) {
        PageHelper.startPage(pageIndex, pageSize);
        List<Task> all = taskMapper.findAll();
        PageInfo<Task> olderPageInfo = new PageInfo<>(all);
        return olderPageInfo;
    }

    @Override
    public PageInfo<Task> findByTaskName(Integer pageIndex, Integer pageSize, String task) {
        PageHelper.startPage(pageIndex, pageSize);
        List<Task> all = taskMapper.getTaskByTask(task);
        PageInfo<Task> pageInfo = new PageInfo<>(all);
        return pageInfo;
    }

    @Override
    public int addTask(Task task) {
        int flag = taskMapper.insert(task);
        return flag;
    }

    @Override
    public void addVolunteerTime(int taskId) {
        Task task = taskMapper.getTaskById(taskId);
        System.out.println(task);
        //默认值等于0,不等于零说明有人接志愿活动了
        if(task.getVolunteer()!=null){
            Volunteer v = volunteerMapper.getVolunteerByVId(task.getVolunteer().getVId());
            int totalTime = 0;
            int taskTotal = 0;
            if(v.getTotalTime()!=null){
                totalTime = v.getTotalTime() + task.getTaskContinueTime();
                taskTotal = 1+v.getTaskTotal();
            }else{
                totalTime = task.getTaskContinueTime();
                taskTotal +=1;
            }
            System.out.println("---------------------------------------------------");
            System.out.println("taskTime = " + totalTime);
            //给接活动的人增加志愿时常
            volunteerMapper.updateTotalTimeByVId(v.getVId(),totalTime,taskTotal);
        }
    }

    @Override
    public Volunteer getVolunteerByUserId(int userId) {
        Volunteer volunteer = volunteerMapper.getVolunteerByUserId(userId);
        return volunteer;
    }

    @Override
    public Volunteer getVolunteerByVId(int vId) {
        Volunteer volunteer = volunteerMapper.getVolunteerByVId(vId);
        return volunteer;
    }

    @Override
    public boolean updateVolunteerInfo(Volunteer volunteer) {
        int flag = volunteerMapper.updatePhoneAndPhoto(volunteer);
        if (flag==1)return true;
        return false;
    }

    @Override
    public boolean updateVolunteerPhone(Volunteer volunteer) {
        int flag = volunteerMapper.updatePhone(volunteer);
        if (flag==1)return true;
        return false;
    }

    @Override
    public List<Task> chechTask(int vId) {
        return taskMapper.findTaskByVId(vId);
    }

    @Override
    public boolean volunteerGetTask(int id, int vId) {
        int i = taskMapper.updateTaskVIdById(id, vId);
        if (i==1) return true;
        return false;
    }
}
