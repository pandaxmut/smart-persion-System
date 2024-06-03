package com.edu.smartpersionsys.mapper;

import com.edu.smartpersionsys.pojo.Task;
import com.edu.smartpersionsys.pojo.Volunteer;
import com.edu.smartpersionsys.service.VolunteerService;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

@Mapper
public interface VolunteerMapper {
    //根据vid更新志愿时常
    int updateTotalTimeByVId(@Param("vId") int vId,@Param("totalTime") int totalTime,@Param("taskTotal") int taskTotal);

    //通过志愿者id查询志愿者
    Volunteer getVolunteerByVId(@Param("vId") int vId);

    //通过用户id查询志愿者
    Volunteer getVolunteerByUserId(@Param("userId") int userId);

    //插入
    int insert(@Param("userId") int userId, @Param("vName")String name);

    //更新头像+联系电话
    int updatePhoneAndPhoto(Volunteer volunteer);

    //更新联系电话
    int updatePhone(Volunteer volunteer);
}
