package com.edu.smartpersionsys.mapper;

import com.edu.smartpersionsys.pojo.BloodPressure;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

@Mapper
public interface BloodPressureMapper {
    //获取所有数据
    List<BloodPressure> getAll();
    //根据olderId获取近三十天的所有数据
    List<BloodPressure> getDateByTimes(@Param("olderId") int olderId,@Param("prep") String prep, @Param("now") String now);


}
