package com.edu.smartpersionsys.mapper;

import com.edu.smartpersionsys.pojo.User;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface UserMapper {

    //通过账号密码判断用户是否登录
//    @Select("select * from user where user_name=#{name} AND user_password=#{password}")
//    @Results(id = "userMap",value = {
//            //id字段默认为false，表示不是主键
//            //column表示数据库表字段，property表示实体类属性名。
//            @Result(id = true, column = "user_id", property = "id"),
//            @Result(column = "user_name", property = "name"),
//            @Result(column = "user_password", property = "password"),
//            @Result(column = "user_email", property = "email"),
//            @Result(column = "user_role", property = "role"),
//            @Result(column = "user_status", property = "status")
//    })
    User getUserByUserNameAndPasswordAndRole(User user);

    List<User> allSelect();

    //插入
    int insert(User user);

    //通过name和 role 查找是否有相同的用户
    User selectByNameAndRole(User user);

}
