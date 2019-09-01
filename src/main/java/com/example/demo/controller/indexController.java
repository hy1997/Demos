package com.example.demo.controller;

import com.example.demo.Utils.MD5Util;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.thymeleaf.util.DateUtils;

import javax.servlet.http.HttpServletRequest;

@Controller
public class indexController {

    @RequestMapping("/index")
    public String index(){
        return "index";
    }

    @RequestMapping("/index1")
    public String index1(){
        return "head";
    }

    @RequestMapping(value = "/login", produces = "text/json;charset=UTF-8")
    @ResponseBody
    public String login(HttpServletRequest request) {

        return returnSuccess(null);
    }

    public String returnSuccess(String str) {
        StringBuilder sb = new StringBuilder(128);
        sb.append("{\"type\":true,\"obj\":");
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
}
