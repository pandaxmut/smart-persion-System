package com.edu.smartpersionsys.service;

import com.edu.smartpersionsys.mapper.SymptomsMapper;
import com.edu.smartpersionsys.pojo.Symptoms;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;

public interface SymptomsService {

    //获取老人症状统计数据
    List<Symptoms> getAllSymptoms();

}
