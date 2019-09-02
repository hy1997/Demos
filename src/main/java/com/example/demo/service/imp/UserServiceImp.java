package com.example.demo.service.imp;

import com.example.demo.mapper.UserMapper;
import com.example.demo.po.User;
import com.example.demo.service.UserService;
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
    public User queryAll() {
        return null;
    }

    @Override
    public void insert(User user) {
        userMapper.insert(user);
    }
}
