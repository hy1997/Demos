package com.example.demo.controller;

import com.example.demo.Utils.MD5Util;
import com.example.demo.constant.Constants;
import com.example.demo.po.User;
import com.example.demo.service.imp.UserServiceImp;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.Collections;
import java.util.List;

@RestController
public class IndexController {
    @Autowired
    UserServiceImp userServiceImp;
//    @Autowired
//    RedisUtil redisUtil;

    @RequestMapping(value = "/login", produces = "text/json;charset=UTF-8")
    public String login(@RequestParam("username") String username, @RequestParam("password") String passwrod, @RequestParam("flg") String flg) {
        if (Constants.REGISTER_O.equals(flg)) {
            List<User> users = userServiceImp.userByUsername(username);
            if (users.isEmpty()) {
                User user = new User();
                user.setUsername(username);
                user.setPassword(MD5Util.MD5(passwrod));
                userServiceImp.insert(user);
                return returnSuccess(stringToJson("新增用户成功"), false);
            } else {
                return returnFailed(stringToJson("该用户已存在!请更换用户名"));
            }
        } else if (Constants.LOGIN_1.equals(flg)) {
            List<User> users = userServiceImp.userByUsername(username);
            for (User u : users) {
                if (MD5Util.MD5(passwrod).equals(u.getPassword())) {
                    //设置缓存时间为90000秒
//                    redisUtil.set("username",username,9000);
                    return returnSuccess(null);
                } else {
                    return returnFailed(stringToJson("用户密码不正确!"));
                }
            }
            return returnFailed(stringToJson("用户名不正确!"));
        }
        return null;
    }

    public String returnSuccess(String str) {
        return returnSuccess(str, true);
    }

    public String returnSuccess(String str, boolean b) {
        StringBuilder sb = new StringBuilder(128);
        sb.append("{\"type\":" + b + ",\"obj\":");
        if (str != null) {
            sb.append(str);
        } else {
            sb.append("\"成功\"");
        }
        sb.append("}");
        return sb.toString();
    }

    public String returnFailed(String str) {
        StringBuilder sb = new StringBuilder(128);
        sb.append("{\"type\":false,\"obj\":");
        if (str != null) {
            sb.append(str);
        } else {
            sb.append("\"失败\"");
        }
        sb.append("}");
        return sb.toString();
    }

    public String stringToJson(String sta) {
        return "\"" + sta + "\"";
    }
}
