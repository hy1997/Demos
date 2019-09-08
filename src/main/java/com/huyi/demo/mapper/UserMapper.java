package com.huyi.demo.mapper;

import com.huyi.demo.po.User;
import org.apache.ibatis.annotations.Mapper;

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
    public List<User> queryAll();

    /**
     * 新增用户
     *
     * @return
     */
    public void insert(User user);


}
