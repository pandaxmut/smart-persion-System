package com.edu.smartpersionsys.utils;

import lombok.Data;

import java.util.List;
@Data
public class DataFromBackend {
    private List<Integer> values;
    private List<String> labels;

    // 构造函数、getter和setter方法

    public DataFromBackend(List<Integer> values, List<String> labels) {
        this.values = values;
        this.labels = labels;
    }

    public DataFromBackend() {
    }

}