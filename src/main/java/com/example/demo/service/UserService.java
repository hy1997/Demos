package com.example.demo.service;

import com.example.demo.po.User;

import java.util.List;

public interface UserService {
    /**
     * 根据用户名查用户信息
     *
     * @return
     */
    public List<User> userByUsername(String username);

    /**
     * 根据用户密码查用户信息
     *
     * @return
     */
    public User userByUserPassword(String passwoer);


    /**
     * 查询所有用户
     *
     * @return
     */
    public User queryAll();

    /**
     * 新增用户
     *
     * @return
     */
    public void insert(User user);
}
