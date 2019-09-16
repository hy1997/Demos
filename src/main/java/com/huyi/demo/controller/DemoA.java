package com.huyi.demo.controller;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

@Controller
public class DemoA {

    @Autowired
    DemoB demoB;

    public DemoA() {
        System.out.println("DemoA  实例化");
    }

}
