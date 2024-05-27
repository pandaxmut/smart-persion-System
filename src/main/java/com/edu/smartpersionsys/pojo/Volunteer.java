package com.edu.smartpersionsys.pojo;

import lombok.Data;

@Data
public class Volunteer {
    private Integer vId; //志愿者ID
    private Integer totalTime;//志愿总时常
    private String vName;//志愿者姓名
    private String vNamePhoto;//志愿者头像

    public Volunteer() {
    }

    public Volunteer(Integer vId, Integer totalTime, String vName, String vNamePhoto) {
        this.vId = vId;
        this.totalTime = totalTime;
        this.vName = vName;
        this.vNamePhoto = vNamePhoto;
    }
}
