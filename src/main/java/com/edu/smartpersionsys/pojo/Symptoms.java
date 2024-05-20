package com.edu.smartpersionsys.pojo;

import lombok.Data;

/**
 * 症状统计档案类
 * 症状统计档案表：symptom_statistics
 */
@Data
public class Symptoms {
    //症状编号
    private Integer id;
    //症状名称
    private String symptomName;
    //患病人数
    private Integer count;

    public Symptoms() {
    }

    public Symptoms(Integer id, String symptomName, Integer count) {
        this.id = id;
        this.symptomName = symptomName;
        this.count = count;
    }

    public Symptoms(String symptomName, Integer count) {
        this.symptomName = symptomName;
        this.count = count;
    }
}
