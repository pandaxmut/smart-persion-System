package com.edu.smartpersionsys.pojo;

public class BloodPressure {
    private int  olderId;//老人ID
    private int maxBP;// 最大血糖
    private int lowBP;// 最低血糖
    private float temperature; // 老人体温；
    private String date;

    public BloodPressure() {
    }

    public BloodPressure(int olderId, int maxBP, int lowBP, float temperature, String date) {
        this.olderId = olderId;
        this.maxBP = maxBP;
        this.lowBP = lowBP;
        this.temperature = temperature;
        this.date = date;
    }

    public BloodPressure(int olderId, int maxBP, int lowBP, float temperature) {
        this.olderId = olderId;
        this.maxBP = maxBP;
        this.lowBP = lowBP;
        this.temperature = temperature;
    }

    public int getOlderId() {
        return olderId;
    }

    public void setOlderId(int olderId) {
        this.olderId = olderId;
    }

    public int getMaxBP() {
        return maxBP;
    }

    public void setMaxBP(int maxBP) {
        this.maxBP = maxBP;
    }

    public int getLowBP() {
        return lowBP;
    }

    public void setLowBP(int lowBP) {
        this.lowBP = lowBP;
    }

    public float getTemperature() {
        return temperature;
    }

    public void setTemperature(float temperature) {
        this.temperature = temperature;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }
}
