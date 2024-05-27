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
        //默认值等于0,不等于零说明有人接志愿活动了
        if(task.getVId()!=0){
            Volunteer v = volunteerMapper.getVolunteerById(task.getVId());
            int time = task.getTaskContinueTime()+v.getTotalTime();
            //给接活动的人增加志愿时常
            volunteerMapper.updateTotalTimeByVId(taskId,time);
        }
    }
}
