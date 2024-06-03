package com.edu.smartpersionsys.pojo;

import lombok.Data;

import java.sql.Date;

/**
 * 志愿者任务表: Task
 *
 */
@Data
public class Task {
    private int id;
    //活动名称
    private String task;
    //描述
    private String description;
    //任务开始时间
    private Date taskStartTime;
    //任务持续时间
    private int taskContinueTime;
    //当前任务状态
    private String status;
    //接收者 志愿者id
    private Integer vId;
    //志愿者
    private Volunteer volunteer;

    public Task() {
    }

    public Task(int id, String task, String description, Date taskStartTime, int taskContinueTime, String status, int vId, Volunteer volunteer) {
        this.id = id;
        this.task = task;
        this.description = description;
        this.taskStartTime = taskStartTime;
        this.taskContinueTime = taskContinueTime;
        this.status = status;
        this.vId = vId;
        this.volunteer = volunteer;
    }
}
