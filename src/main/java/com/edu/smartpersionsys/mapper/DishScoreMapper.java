package com.edu.smartpersionsys.mapper;

import com.edu.smartpersionsys.pojo.DishScore;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

import java.util.List;

@Mapper
public interface DishScoreMapper {
    /**
     *  获取所有评分，以olderId升序排序,并且dish_id也是升序排序
     * @return
     */
    @Select("select * from dish_scores order by older_id Asc, dish_id Asc")
    List<DishScore> findAll();


}
