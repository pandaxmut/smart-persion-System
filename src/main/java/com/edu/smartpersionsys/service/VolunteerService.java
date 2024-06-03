package com.edu.smartpersionsys.service;

import com.edu.smartpersionsys.pojo.Older;
import com.edu.smartpersionsys.pojo.Task;
import com.edu.smartpersionsys.pojo.Volunteer;
import com.github.pagehelper.PageInfo;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface VolunteerService {
    // 不带条件 - 分页查询
    PageInfo<Task> findAll(Integer pageIndex, Integer pageSize);
    // 带条件 - 分页查询
    PageInfo<Task> findByTaskName(Integer pageIndex,Integer pageSize,String task);

    /**
     * 新建一个任务
     * @param task
     * @return
     */
    int addTask(Task task);

    //志愿者增加时常,有则增加
    void addVolunteerTime( int taskId);

    Volunteer getVolunteerByVId(int vId);

    Volunteer getVolunteerByUserId(int userId);

    //更新头像+联系电话
    boolean updateVolunteerInfo(Volunteer volunteer);
    //更新联系电话
    boolean updateVolunteerPhone(Volunteer volunteer);

    //查看志愿者参与的活动
    List<Task> chechTask(int vId);

    //志愿者通过id获得项目vid
    boolean volunteerGetTask(int id,int vId);

}
