package com.edu.smartpersionsys.mapper;

import com.edu.smartpersionsys.pojo.Outgoing;
import com.edu.smartpersionsys.service.OutgoingService;
import org.apache.ibatis.annotations.Mapper;

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

}
