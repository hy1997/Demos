package com.huyi.demo.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

@Controller
public class DemoB {

    @Autowired
    DemoA demoA;


    public DemoB() {
        System.out.println("DemoB  实例化");
    }
}
