package com.edu.smartpersionsys.mapper;

import com.edu.smartpersionsys.pojo.Outgoing;
import com.edu.smartpersionsys.service.OutgoingService;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

import java.util.List;

@Mapper
public interface OutgoingMapper {
    /**
     * 查取Outgoing表全部数据
     * @return
     */
    List<Outgoing> getOutgoings();
    //删除
    int del(Integer id);
    //新增
    int insert(Outgoing record);

    //根据老人姓名查找老人外出记录
    List<Outgoing> findByName(@Param("name") String name);


}
