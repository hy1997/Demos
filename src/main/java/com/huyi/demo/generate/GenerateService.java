package com.huyi.demo.generate;

import com.huyi.demo.Utils.StringUtils;
import com.huyi.redis.RedisUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.sql.*;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class GenerateService {

    private Logger logger = LoggerFactory.getLogger(this.getClass());


    @Autowired
    DataSourceConfiguration dataSource;
    @Autowired
    Assemble assemble;
//    @Autowired
//    RedisUtil redisUtil;

    /**
     * 获取数据库连接并注入spring容器
     * @return
     * @throws SQLException
     * @throws ClassNotFoundException
     */

    public  Connection   getConnection() throws SQLException, ClassNotFoundException {
        logger.info("================  获取数据库连接开始！");
        Class.forName(dataSource.getDriver());
        String url = dataSource.getUrl();
        String user = dataSource.getName();
        String password = dataSource.getPwd();
        Connection conn = null;
//        conn=(Connection) redisUtil.get("conn");
        if (conn == null) {
            conn = DriverManager.getConnection(url, user, password);
//            redisUtil.set("conn",conn);
        }
        logger.info("=================获取数据库连接数据成功");
        return conn;
    }


    public List<StringBuffer> executQuery( Connection conn) throws IOException {
        ResultSet resultSet = null;
        List<StringBuffer> list = null;
        try {
            //查询所有表
            DatabaseMetaData metaData = conn.getMetaData();
            String dataBaseType = metaData.getDatabaseProductName();
            String dataBas = conn.getCatalog();
            logger.info("===============获取到" + dataBas + ":库查询语句");
            String sql = GenerateUtils.select(GenerateConstant.TABLES, dataBas);
            logger.info("===============执行查询sql" + sql);
            resultSet = GenerateUtils.getResultSet(conn, sql);
            logger.info("===============根据查询结果生成对应数据");
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
