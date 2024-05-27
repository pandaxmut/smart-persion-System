package com.edu.smartpersionsys.mapper;

import com.edu.smartpersionsys.pojo.Older;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.List;

@Mapper
public interface OlderMapper {
    /**
     * 不带条件 - 分页查询所有
     * @return
     */
    List<Older> findAll();
    /**
     * 带条件 - 分页查询所应聘
     * @param orderSn
     * @param orderStatus
     * @return
     */

    /**
     * 通过用户名获取用户信息
     */
    List<Older> getOlderByOlderName(String olderName);

    /**
     * 更新老人信息
     * @param older
     * @return
     */
    int updateOlderinfo(Older older);

    /**
     * 更新老人信息，不包括头像ID
     */
    int updateOlderInfoExcludeOlderPhoto(Older older);

    /**
     * 根据时间段查询老人的人数
     */
    int getOlderCountByTimes(@Param("pre") String pre, @Param("today") String today);


    /**
     * 新增一条记录，添加userId, name
     */
    int insert(@Param("userId") int userId, @Param("userName")String name);

    /**
     * 通过userId查找老人
     * @param userId
     * @return
     */
    Older selectByUserId(@Param("userId")  int userId);


    /**
     * 通过olderId查找老人
     * @param olderId
     * @return
     */
    Older selectByOlderId(@Param("olderId")  int olderId);


}
