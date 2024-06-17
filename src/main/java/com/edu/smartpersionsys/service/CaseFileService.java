package com.edu.smartpersionsys.service;

import com.edu.smartpersionsys.pojo.CaseFile;
import com.github.pagehelper.PageInfo;

import java.util.List;

public interface CaseFileService {
    //获取所有的病例档案
    List<CaseFile> getAllCase();

    List<CaseFile> findById(int olderId);
    //修改病例
    int updateById(CaseFile caseFile);
    //根据id删除病例档案
    int deleteCaseById(int olderId);
   //添加病例
    int add(CaseFile caseFile);

//    List<CaseFile> queryAllByPage(RowBounds rowBounds);

    PageInfo<CaseFile> queryAllByPage(int pageNum, int pageSize);
    //int update(CaseFile caseFile);
}
