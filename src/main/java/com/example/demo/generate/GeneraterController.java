package com.example.demo.generate;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import java.io.IOException;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;


@Controller
public class GeneraterController {

    @Autowired
    public GenerateService service;

    @RequestMapping("/generater")
    public void  generaterFile() throws SQLException, IOException, ClassNotFoundException {
        //获取connection连接
       Connection connection= service.getConnection();
        //获取数据库字段名称，备注，类型，表名
        List<StringBuffer> buffers= service.executQuery(connection);

       /* for (StringBuffer str : stringBufferLists) {
            System.out.println(str);
        }*/
    }
}
