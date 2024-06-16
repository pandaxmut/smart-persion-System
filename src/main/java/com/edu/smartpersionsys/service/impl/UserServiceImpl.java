package com.edu.smartpersionsys.service.impl;


import com.edu.smartpersionsys.mapper.OlderMapper;
import com.edu.smartpersionsys.mapper.UserMapper;
import com.edu.smartpersionsys.mapper.VolunteerMapper;
import com.edu.smartpersionsys.pojo.Older;
import com.edu.smartpersionsys.pojo.User;
import com.edu.smartpersionsys.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserServiceImpl implements UserService {
    @Autowired
    private UserMapper userMapper;
    @Autowired
    private OlderMapper olderMapper;
    @Autowired
    private VolunteerMapper volunteerMapper;

    @Override
    public User login(User user) {
        User u = userMapper.getUserByUserNameAndPasswordAndRole(user);
//        System.out.println(u.getName());
        return u;
    }

    @Override
    public boolean register(User user) {
        //用户注册
        int flag = userMapper.insert(user);
        //判断是什么角色，在特定的表中注册信息
        User u = userMapper.selectByNameAndRole(user);
        String role = u.getUserRole();
        if(role.equals("老人")){
            //新增一个老人
            flag = olderMapper.insert(u.getUserId(),u.getUserName());
        } else if (role.equals("志愿者")) {
            flag = volunteerMapper.insert(u.getUserId(),u.getUserName());
        } else {
            return false;
        }
        if (flag==0) return false;
        else return true;
    }

    @Override
    public boolean isSameByNameAndRole(User user) {
        if (userMapper.selectByNameAndRole(user)!=null) return true;
        return false;
    }

    @Override
    public boolean bindPhone(int id,String phoneNum) {
        int i = userMapper.insertPhoneNum(id, phoneNum);
        if (i==1) return true;
        else return false;
    }
}
