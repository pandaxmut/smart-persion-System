package com.edu.smartpersionsys.pojo;

import lombok.Data;

/**
 * 病例档案表
 */
@Data
public class CaseFile {
    private int id;
    private int olderId;
    private String name;
    private String diagnosis;
    private String treatment;
    private String recordDate;
    private String idCard;
}
