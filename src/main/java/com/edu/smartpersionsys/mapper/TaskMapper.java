package com.edu.smartpersionsys.mapper;

import com.edu.smartpersionsys.pojo.Older;
import com.edu.smartpersionsys.pojo.Task;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface TaskMapper {
    //插入一条数据
    int insert(Task task);

    //获取所有数据
    List<Task> findAll();

    /**
     * 模糊查询 通过任务名获取相关task信息
     */
    List<Task> getTaskByTask(@Param("task") String task);

    //根据id查有没有人接项目
    Task getTaskById(int id);

    //更新任务为"结束"
    int updateTaskById(@Param("id")int id, @Param("status") String status);

}
