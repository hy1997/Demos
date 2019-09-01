package com.example.demo.mapper;

import com.example.demo.po.User;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

import java.util.List;

@Mapper
public interface UserMapper {
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
}
