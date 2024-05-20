package com.edu.smartpersionsys.mapper;

import com.edu.smartpersionsys.pojo.Symptoms;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface SymptomsMapper {
    //获取所有数据
    List<Symptoms> selectAll();
}
