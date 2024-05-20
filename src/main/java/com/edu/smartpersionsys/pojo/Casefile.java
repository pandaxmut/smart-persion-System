package com.edu.smartpersionsys.pojo;

import lombok.Data;

import java.sql.Date;

/**
 * 病例档案表
 */
@Data
public class Casefile {
    private Integer id;
    private Integer olderId;
    //诊断结果
    private String diagnosis;
    //治疗方案
    private String treatment;
    //记录日期
    private Date recordDate;
}
