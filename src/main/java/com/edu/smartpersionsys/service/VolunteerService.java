package com.edu.smartpersionsys.service;

import com.edu.smartpersionsys.pojo.Older;
import com.edu.smartpersionsys.pojo.Task;
import com.github.pagehelper.PageInfo;

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
    void addVolunteerTime(int taskId);

}
