package com.edu.smartpersionsys.service.impl;

import com.edu.smartpersionsys.mapper.SymptomRecordMapper;
import com.edu.smartpersionsys.mapper.SymptomsMapper;
import com.edu.smartpersionsys.pojo.Symptoms;
import com.edu.smartpersionsys.service.SymptomsService;
import com.edu.smartpersionsys.utils.DataFromBackend;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
@Service
public class SymptomsServiceImpl implements SymptomsService {
    @Autowired
    private SymptomRecordMapper symptomRecordMapper;
    @Override
    public List<Symptoms> getAllSymptoms() {
        Date date = new Date();
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        String format = sdf.format(date);
        List<Symptoms> list = symptomRecordMapper.getSymptomNameAndCountByDate(format);
        //获取六个数据：前五名的数据+其他（前五名之后）
        List<Symptoms> newList = new ArrayList<>();
        Symptoms listPart = new Symptoms("其他",0);
        for(int i=0;i<list.size();i++){
            if(i<5){
                newList.add(list.get(i));
            }else{
                listPart.setCount(listPart.getCount()+ list.get(i).getCount());
            }
        }
        listPart.setSymptomName("其他");
        newList.add(listPart);
        System.out.println(newList.toString());
        return newList;
    }
}
