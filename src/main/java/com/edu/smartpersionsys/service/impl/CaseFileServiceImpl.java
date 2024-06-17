package com.edu.smartpersionsys.service.impl;

import com.edu.smartpersionsys.mapper.CaseFileMapper;
import com.edu.smartpersionsys.pojo.CaseFile;
import com.edu.smartpersionsys.service.CaseFileService;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.apache.ibatis.annotations.Param;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CaseFileServiceImpl implements CaseFileService {

    @Autowired
    private CaseFileMapper caseFileMapper;

    @Override
    public List<CaseFile> getAllCase() {
        return caseFileMapper.findALL();
    }


    @Override
    public List<CaseFile> findById(int olderId) {
        return caseFileMapper.findById(olderId);
    }

    @Override
    public int updateById(CaseFile caseFile) {
        return caseFileMapper.updateById(caseFile);
    }

    @Override
    public int deleteCaseById(int olderId) {
        return caseFileMapper.delByOlderId(olderId);
    }

    @Override
    public int add(CaseFile caseFile) {
        return caseFileMapper.add(caseFile);
    }



    @Override
    public PageInfo<CaseFile> queryAllByPage(int pageNum , int pageSize ) {
        PageHelper.startPage(pageNum,pageSize);
       List<CaseFile> caseFiles= caseFileMapper.queryAllByPage();
       PageInfo<CaseFile> pageInfo = new PageInfo<>(caseFiles);
       return pageInfo;

    }


}




