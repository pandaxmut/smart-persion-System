package com.edu.smartpersionsys.utils;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;
@Data
@AllArgsConstructor
@NoArgsConstructor
public class DataFromBackend {
    private List<Integer> values;
    private List<String> labels;

}