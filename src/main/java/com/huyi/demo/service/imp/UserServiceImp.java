package com.huyi.demo.service.imp;

import com.huyi.demo.mapper.UserMapper;
import com.huyi.demo.po.User;
import com.huyi.demo.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserServiceImp implements UserService {
    @Autowired
    UserMapper userMapper;

    @Override
    public List<User> userByUsername(String username) {
        List<User> user = userMapper.userByUsername(username);
        return user;
    }

    @Override
    public User userByUserPassword(String passwoer) {
        return null;
    }


    @Override
    public List<User> queryAll() {
        return userMapper.queryAll();
    }

    @Override
    public void insert(User user) {
        userMapper.insert(user);
    }
}
