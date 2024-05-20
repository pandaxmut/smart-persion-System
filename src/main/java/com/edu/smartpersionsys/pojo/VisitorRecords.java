package com.edu.smartpersionsys.pojo;

import lombok.Data;

import java.sql.Date;

/**
 * 访客记录表 :visitor_records
 */
@Data
public class VisitorRecords {
    //访客记录ID
    private Integer id;
    //老人ID
    private Integer olderId;
    //访客名称
    private  String visitorName;
    //访问日期
    private Date visitDate;
    //访问目的
    private String purpose;
}
