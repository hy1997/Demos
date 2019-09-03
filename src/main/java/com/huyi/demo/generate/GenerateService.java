package com.huyi.demo.generate;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.sql.*;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class GenerateService {

    @Autowired
    DataSourceConfiguration dataSource;
    @Autowired
    Assemble assemble;
    /**
     * 获取数据库连接并注入spring容器
     * @return
     * @throws SQLException
     * @throws ClassNotFoundException
     */

    public  Connection   getConnection() throws SQLException, ClassNotFoundException {
        Map<String,Connection> mapConn= new HashMap<>();
        Class.forName(dataSource.getDriver());
        String url = dataSource.getUrl();
        String user = dataSource.getName();
        String password = dataSource.getPwd();
        Connection conn = DriverManager.getConnection(url, user, password);
        if(mapConn.containsKey("conn")){
            conn=mapConn.get("conn");
        }else {
            mapConn.put("conn", conn);
        }
        System.out.println("获取数据库连接成功");
        return mapConn.get("conn");
    }


    public List<StringBuffer> executQuery( Connection conn) throws IOException {
        ResultSet resultSet = null;
        List<StringBuffer> list = null;
        try {
            //查询所有表
            DatabaseMetaData metaData = conn.getMetaData();
            String dataBaseType = metaData.getDatabaseProductName();
            String dataBas = conn.getCatalog();
            String sql = GenerateUtils.select(GenerateConstant.TABLES, dataBas);
            resultSet = GenerateUtils.getResultSet(conn, sql);
            list= assemble.generateJvavaClass(resultSet,dataBas,conn);
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            try {
                resultSet.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }

        return list;
    }
}
