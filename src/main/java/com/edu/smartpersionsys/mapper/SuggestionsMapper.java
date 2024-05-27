package com.edu.smartpersionsys.mapper;

import com.edu.smartpersionsys.pojo.Suggestions;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface SuggestionsMapper {
    //插入建议
    int insertSuggestion(Suggestions suggestion);

    //查看所有建议
    List<Suggestions> selectOlderAndSuggestion();


}
