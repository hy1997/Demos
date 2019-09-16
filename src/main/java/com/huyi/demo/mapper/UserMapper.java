package com.huyi.demo.mapper;

import com.huyi.demo.po.User;
import com.huyi.demo.po.UserEntity;
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


    //通过openid得到用户信息
    UserEntity getOpenid(String openid);


    //添加信息
    int insertQQ(UserEntity userEntity);

    //修改信息
    int updateQQ(UserEntity userEntity);

    //QQ授权过之后会给一个openid，通过openid进行查找，如果没有就是首次登陆直接添，如果有先修改信息

}
