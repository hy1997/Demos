package com.example.demo.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class indexController {

    @RequestMapping("/index")
    public String index(){
        return "index";
    }

    @RequestMapping("/index2")
    public String index1(){
        return "index";
    }
}
