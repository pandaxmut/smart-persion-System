package com.edu.smartpersionsys.mapper;

import com.edu.smartpersionsys.pojo.Family;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface FamilyMapper {

    //查看所有famiLy 根据userId
    public List<Family> allFamilyByOlderId(Family family);
}
