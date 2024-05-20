package com.edu.smartpersionsys.mapper;

import com.edu.smartpersionsys.pojo.Symptoms;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.Date;
import java.util.List;

@Mapper
public interface SymptomRecordMapper {
    //指定日期，统计症状并对症状倒序排序
    List<Symptoms> getSymptomNameAndCountByDate(String date);

}
