package com.edu.smartpersionsys.mapper;

import com.edu.smartpersionsys.pojo.Dish;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

import java.util.List;

@Mapper
public interface DishMapper {
    /**
     * 获取所有数据
     */
    @Select("select * from dish order by id ASC ")
    List<Dish> findAll();
}
