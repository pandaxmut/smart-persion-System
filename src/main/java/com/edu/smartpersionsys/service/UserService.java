package com.edu.smartpersionsys.service;


import com.edu.smartpersionsys.pojo.User;

public interface UserService {
    //通过User的用户账号和用户密码查询用户信息

    boolean login(User user);
}
