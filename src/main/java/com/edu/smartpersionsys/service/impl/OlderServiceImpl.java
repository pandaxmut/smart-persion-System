package com.edu.smartpersionsys.service.impl;

import com.edu.smartpersionsys.mapper.BloodPressureMapper;
import com.edu.smartpersionsys.mapper.OlderMapper;
import com.edu.smartpersionsys.mapper.SuggestionsMapper;
import com.edu.smartpersionsys.pojo.BloodPressure;
import com.edu.smartpersionsys.pojo.Older;
import com.edu.smartpersionsys.pojo.Suggestions;
import com.edu.smartpersionsys.service.OlderService;
import com.edu.smartpersionsys.utils.OlderIncrease;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.apache.ibatis.annotations.Param;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;

@Service
public class OlderServiceImpl implements OlderService {

    @Autowired
    private OlderMapper olderMapper;

    @Autowired
    BloodPressureMapper bloodPressureMapper;

    @Autowired
    SuggestionsMapper suggestionsMapper;

    @Override
    public PageInfo<Older> findAll(Integer pageIndex, Integer pageSize) {
        PageHelper.startPage(pageIndex, pageSize);
        List<Older> all = olderMapper.findAll();
        PageInfo<Older> olderPageInfo = new PageInfo<>(all);
        return olderPageInfo;
    }

    @Override
    public PageInfo<Older> findByOlderName(Integer pageIndex, Integer pageSize, String olderName) {
        PageHelper.startPage(pageIndex, pageSize);
        List<Older> olderByOlderName = olderMapper.getOlderByOlderName(olderName);
        PageInfo<Older> pageInfo = new PageInfo<>(olderByOlderName);
        return pageInfo;
    }

    @Override
    public int updateOlderInfoExcludeOlderPhoto(Older older) {
        if (older.getOlderStatus().equals("健康")) older.setOlderStatus("1");
        else older.setOlderStatus("2");
        int flag = olderMapper.updateOlderInfoExcludeOlderPhoto(older);
        return flag;
    }

    @Override
    public int updateOlderInfo(Older older) {
        if (older.getOlderStatus().equals("健康")) older.setOlderStatus("1");
        else older.setOlderStatus("2");
        int flag = olderMapper.updateOlderinfo(older);
        return flag;
    }

    //查找近多少天入住率
    @Override
    public OlderIncrease getIncrease(int num) {
        OlderIncrease olderIncrease = new OlderIncrease();
        //获取近七天的注册的人数
        LocalDate today = LocalDate.now(); //当前日期
        String today_time = today.toString();
        String previousDate = today.minusDays(num).toString();
        System.out.println(previousDate);
        int count = olderMapper.getOlderCountByTimes(previousDate, today_time);
        olderIncrease.setOlderNumber(count);
        System.out.println(count);
        //获取上七天的注册人数
        String previousDate2 = today.minusDays(num * 2).toString();
        System.out.println(previousDate2);
        int count2 = olderMapper.getOlderCountByTimes(previousDate2, previousDate);
        //增长比率 = 近七天/上七天
        int d = 0;
        if (count2 == 0) {
            d = count * 100;
        } else {
            d = (count - count2) * 100 / count2;
        }
        olderIncrease.setConversionRate(d);
        //入住率
        int total = olderMapper.findAll().size();
        int rate = total * 100 / olderIncrease.getFinalNumber();
        olderIncrease.setProgressPercentage(rate);
        return olderIncrease;
    }

    @Override
    public List<BloodPressure> getBloodPressureByTime(int olderId, int num) {
        LocalDate now = LocalDate.now();
        LocalDate prep = now.minusMonths(num);
        System.out.println(prep + "  " + now);
        return bloodPressureMapper.getDateByTimes(olderId, prep.toString(), now.toString());
    }

    @Override
    public Older addOlder(int userId, String name) {
        int flag = olderMapper.insert(userId, name);
        Older older = new Older();
        if (flag == 1) {
            older = olderMapper.selectByUserId(userId);
        }
        return older;
    }

    @Override
    public Older getOlderByUserId(int userId) {
        return olderMapper.selectByUserId(userId);
    }
    @Override
    public Older getOlderByOlderId(int olderId) {
        return olderMapper.selectByOlderId(olderId);
    }

    @Override
    public int addSuggestionByOlder(Suggestions suggestion) {
        int flag = suggestionsMapper.insertSuggestion(suggestion);
        return flag;
    }

    @Override
    public List<Suggestions> getAllSuggestions() {
        List<Suggestions> suggestions = suggestionsMapper.selectOlderAndSuggestion();
        return suggestions;
    }
}
