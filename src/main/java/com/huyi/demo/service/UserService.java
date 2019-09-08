package com.huyi.demo.service;

import com.huyi.demo.po.User;

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
    public List<User> queryAll();

    /**
     * 新增用户
     *
     * @return
     */
    public void insert(User user);
}
