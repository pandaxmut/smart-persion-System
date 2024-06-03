package com.edu.smartpersionsys.utils;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class OlderIncrease {
    //增长率
    private  Integer conversionRate;
    //入住率
    private Integer progressPercentage;
    //阶段性老人入住人数
    private Integer olderNumber;
    //养老院最多入住人数
    private final Integer finalNumber = 500;

}
