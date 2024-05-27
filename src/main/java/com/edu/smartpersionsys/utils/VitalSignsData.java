package com.edu.smartpersionsys.utils;

import java.util.List;

public class VitalSignsData {
    private List<Integer> maxBP;
    private List<Integer> lowBP;// 最低血糖
    private List<Float> temperature; // 老人体温；
    private List<String> days;

    public VitalSignsData(List<Integer> maxBP, List<Integer> lowBP, List<Float> temperature, List<String> days) {
        this.maxBP = maxBP;
        this.lowBP = lowBP;
        this.temperature = temperature;
        this.days = days;
    }

    public VitalSignsData() {
    }

    public List<Integer> getMaxBP() {
        return maxBP;
    }

    public void setMaxBP(List<Integer> maxBP) {
        this.maxBP = maxBP;
    }

    public List<Integer> getLowBP() {
        return lowBP;
    }

    public void setLowBP(List<Integer> lowBP) {
        this.lowBP = lowBP;
    }

    public List<Float> getTemperature() {
        return temperature;
    }

    public void setTemperature(List<Float> temperature) {
        this.temperature = temperature;
    }

    public List<String> getDays() {
        return days;
    }

    public void setDays(List<String> days) {
        this.days = days;
    }
}
