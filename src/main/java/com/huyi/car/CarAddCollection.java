package com.huyi.car;

import com.huyi.demo.Utils.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class CarAddCollection {

    @Autowired
    CarAddServer carAddServer;

    @Autowired
    Rsp rsp;

    @PostMapping("/api/resource/v1/vehicle/batch/add")
    public void add(Car car) {
        int add = carAddServer.add(car);
        if (add > 0) {
            //成功  组装Json数据
            rsp.setCode("0");
            rsp.setMsg("success");
            //格式！！！！！
//            {
//                "code": "0",
//                    "msg": "success",
//                    "data": {
//                "successes": [
//                {
//                    "clientId": "123456789",
//                        "vehicleId": "5668f44c-ae23-4674-9b79-d8f79f77fd41"
//                }
//        ],
//                "failures": [
//                {
//                    "clientId": "555",
//                        "code": "-1",
//                        "msg": "error"
//                }
//        ]
//            }
//            }

            //TODO  return你组装的JSON数据！
//            return
        } else {
            //失败

        }
    }

}
