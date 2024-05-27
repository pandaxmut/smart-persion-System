package com.edu.smartpersionsys.mapper;

import com.edu.smartpersionsys.pojo.Task;
import com.edu.smartpersionsys.pojo.Volunteer;
import com.edu.smartpersionsys.service.VolunteerService;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface VolunteerMapper {
    //根据vid更新志愿时常
    int updateTotalTimeByVId(@Param("vId") int vId,@Param("count") int count);
    Volunteer getVolunteerById(int vid);
}
