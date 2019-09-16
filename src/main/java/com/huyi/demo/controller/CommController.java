package com.huyi.demo.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class CommController {

    @RequestMapping("/")
    public String login() {
        return "login-page";
    }

    @RequestMapping("/api/index")
    public String index() {
        return "index";
    }

}
