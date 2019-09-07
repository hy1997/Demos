package com.huyi.demo.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class CommController {

    @RequestMapping("/index")
    public String index(){
        return "login-page";
    }

    @RequestMapping("/index1")
    public String index1(){
        return "adminlte";
    }

    @RequestMapping("/shrn")
    public String shrn() {
        return "shrn";
    }

}
