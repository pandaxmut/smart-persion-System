package com.edu.smartpersionsys.pojo;

import lombok.Data;

import java.sql.Date;

@Data
public class SymptomRecord {
    private Integer id;
    private Integer olderId;
    private String SymptomName;
    private String description;
    private Date date;
    private String nurse;
}
