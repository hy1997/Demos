package com.huyi.car;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.PostMapping;

@Service
public class CarAddServer {

    @Autowired
    CarAddMapper carAddMapper;

    public int add(Car car) {
        return carAddMapper.add(car);
    }
}
