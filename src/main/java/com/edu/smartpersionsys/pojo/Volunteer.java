package com.edu.smartpersionsys.pojo;

import lombok.Data;


//这个志愿者类包括了user中的字段，
@Data
public class Volunteer {
    private Integer vId; //志愿者ID
    private Integer userId; //用户id
    private Integer totalTime;//志愿总时常
    private Integer taskTotal; //接收任务数量
    private String vName;//志愿者姓名
    private String vNamePhoto;//志愿者头像
    private String vPhone; // 联系电话

    public Volunteer() {
    }

    public Volunteer(Integer vId, Integer userId, Integer totalTime, Integer taskTotal, String vName, String vNamePhoto, String vPhone) {
        this.vId = vId;
        this.userId = userId;
        this.totalTime = totalTime;
        this.taskTotal = taskTotal;
        this.vName = vName;
        this.vNamePhoto = vNamePhoto;
        this.vPhone = vPhone;
    }
}
