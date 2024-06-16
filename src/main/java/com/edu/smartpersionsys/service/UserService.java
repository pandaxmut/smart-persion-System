package com.edu.smartpersionsys.service;


import com.edu.smartpersionsys.pojo.User;

public interface UserService {
    //登录
    User login(User user);
    //注册
    boolean register(User user);
    //判断用户是否重复。条件：username,role
    boolean isSameByNameAndRole(User user);

    boolean bindPhone(int id,String phoneNum);

}
