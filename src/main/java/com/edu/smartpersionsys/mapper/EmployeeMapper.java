package com.edu.smartpersionsys.mapper;

import com.edu.smartpersionsys.pojo.Employee;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface EmployeeMapper {
    List<Employee> allSelect();
}
