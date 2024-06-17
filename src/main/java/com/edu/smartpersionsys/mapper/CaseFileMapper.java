package com.edu.smartpersionsys.mapper;

import com.edu.smartpersionsys.pojo.CaseFile;
import org.apache.ibatis.annotations.*;

import java.util.List;

@Mapper
public interface CaseFileMapper {

    //根据id查询
    @Select("select * from casefile where older_id=#{olderId}")
    List<CaseFile> findById(@Param("olderId") int olderId);
    //查询所有数据
    @Select("select * from casefile")
    List<CaseFile> findALL();

    //新增
    @Insert("insert into casefile values (null,#{olderId},#{diagnosis},#{treatment},#{recordDate},#{name},#{idCard})")
    int add(CaseFile caseFile);
    //根据id修改
//    @Update("update casefile set #{olderId},#{diagnosis},#{treatment},#{recordDate},#{name},#{idCard} where id=#{id}")
//    int update(CaseFile caseFile);
    ///根据id删除
    @Delete("delete from casefile where id=#{id}")
    int delByOlderId (int olderId);

    //根据id修改
    @Update("update casefile set older_id=#{olderId}, diagnosis=#{diagnosis}, treatment=#{treatment}, record_date=#{recordDate}, name=#{name}, idcard=#{idCard} where older_id=#{olderId}")
    int updateById(CaseFile caseFile);

    @Select("select * from casefile where older_id=#{id}")
    List<CaseFile> queryAllByPage();

}
