package com.edu.smartpersionsys.service;

import com.edu.smartpersionsys.mapper.BloodPressureMapper;
import com.edu.smartpersionsys.pojo.BloodPressure;
import com.edu.smartpersionsys.pojo.Older;
import com.edu.smartpersionsys.pojo.Suggestions;
import com.edu.smartpersionsys.utils.OlderIncrease;
import com.github.pagehelper.PageInfo;

import java.util.List;

public interface OlderService {
    // 不带条件 - 分页查询
    PageInfo<Older> findAll(Integer pageIndex, Integer pageSize);
    // 带条件 - 分页查询
    PageInfo<Older> findByOlderName(Integer pageIndex,Integer pageSize,String olderName);

    //更新老人身份信息
    int updateOlderInfo(Older older);

    //更新老人信息，不包括头像ID
    int updateOlderInfoExcludeOlderPhoto(Older older);

    //查找近多少天入住率
    OlderIncrease getIncrease(int num);

    //查找近三十天老人的体温，最高、最低血糖
    List<BloodPressure> getBloodPressureByTime(int olderId,int num);

    //添加一位老人,并返回该Older
    Older addOlder(int userId,String name);

    //根据olderId查找老人信息
    Older getOlderByOlderId(int olderId);
    //根据userId查找老人信息
    public Older getOlderByUserId(int userId);

    /**
     * 添加一条老人建议
     */
    int addSuggestionByOlder(Suggestions suggestion);

    /**
     * 查找老人反馈的信息
     */
    List<Suggestions> getAllSuggestions();

}

