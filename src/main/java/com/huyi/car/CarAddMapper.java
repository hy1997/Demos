package com.huyi.car;

import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Component;

@Mapper
public interface CarAddMapper {

    //insert成功后才会有返回值
    int add(Car car);
}
