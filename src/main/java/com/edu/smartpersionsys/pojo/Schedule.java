package com.edu.smartpersionsys.pojo;

import lombok.Data;

import java.sql.Date;

/**
 * 日程活动表: schedule_activities
 *
 */
@Data
public class Schedule {
    private Integer id;
    //活动名称
    private String activityName;
    //活动日期
    private Date activityDate;
    //描述
    private String description;
}
