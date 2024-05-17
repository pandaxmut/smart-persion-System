package com.edu.smartpersionsys.service.impl;


import com.edu.smartpersionsys.mapper.UserMapper;
import com.edu.smartpersionsys.pojo.User;
import com.edu.smartpersionsys.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserServiceImpl implements UserService {
    @Autowired
    private UserMapper userMapper;
    @Override
    public boolean login(User user) {
        User u = userMapper.isLoginByUserNameAndPassword(user);
//        System.out.println(u.getName());
        if (u != null) return true;
        else return false;

    }
}
