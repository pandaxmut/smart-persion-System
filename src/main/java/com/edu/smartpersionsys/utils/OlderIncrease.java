package com.edu.smartpersionsys.utils;

public class OlderIncrease {
    //增长率
    private  Integer conversionRate;
    //入住率
    private Integer progressPercentage;
    //阶段性老人入住人数
    private Integer olderNumber;
    //养老院最多入住人数
    private final Integer finalNumber = 500;

    public OlderIncrease(Integer conversionRate, Integer progressPercentage, Integer olderNumber) {
        this.conversionRate = conversionRate;
        this.progressPercentage = progressPercentage;
        this.olderNumber = olderNumber;
    }

    public OlderIncrease() {
    }

    public Integer getFinalNumber() {
        return finalNumber;
    }

    public Integer getConversionRate() {
        return conversionRate;
    }

    public void setConversionRate(Integer conversionRate) {
        this.conversionRate = conversionRate;
    }

    public Integer getProgressPercentage() {
        return progressPercentage;
    }

    public void setProgressPercentage(Integer progressPercentage) {
        this.progressPercentage = progressPercentage;
    }

    public Integer getOlderNumber() {
        return olderNumber;
    }

    public void setOlderNumber(Integer olderNumber) {
        this.olderNumber = olderNumber;
    }
}
