package com.example.demo.generate;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

@Component
public class Assemble {


    @Autowired
    DataSourceConfiguration dataSourceConfiguration;

    public List<StringBuffer> generateJvavaClass(ResultSet resultSet, String dataBas, Connection conn) throws SQLException, IOException {
        StringBuffer stringBuffer = null;
        List<StringBuffer> list = new ArrayList<>();
        ResultSet resultSet1=null;
            String tableName = null;
            try {
                while (resultSet.next()) {
                    tableName = resultSet.getString("TABLE_NAME");
                    String sql1 = GenerateUtils.select(GenerateConstant.COLUMN, tableName, dataBas);
                    resultSet1 = GenerateUtils.getResultSet(conn, sql1);
                    stringBuffer = new StringBuffer();
                    String[] address=dataSourceConfiguration.getAddress().split("/");
                    String addresss=address[address.length-3]+"."+address[address.length-2]+"."+address[address.length-1]+";";
                    stringBuffer .append("package "+addresss+"\r\n"+"\r\n"+"\r\n");
                    stringBuffer.append("public  class "+ GenerateUtils.toUpper(tableName )+"{");
                    while (resultSet1.next()) {
                        stringBuffer.append("\r\n"+"/**"+"\r\n"+"*" +resultSet1.getString("COMMENTS") + "\r\n"+"*/");
                        stringBuffer.append("\r\n"+"private " + GenerateUtils.toTypeByName(resultSet1.getString("DATA_TYPE")) + "    " + GenerateUtils.toUpper(resultSet1.getString("COLUMN_NAME")) + ";");
                    }
                    stringBuffer.append("\r\n"+"}");
                    list.add(stringBuffer);
                    for (StringBuffer buffer: list  ) {
                        readJaveFile(buffer, GenerateUtils.toUpper(resultSet.getString("TABLE_NAME")));
                    }
                }
            } catch (SQLException e) {
                e.printStackTrace();
            }
        return list;
    }


    public static StringBuffer generaterJvavaBean(List<ResultSet> rSets) throws SQLException {
        StringBuffer buffer = new StringBuffer();
        ResultSet rSet = rSets.get(0);
        ResultSet rSet1 = rSets.get(1);
        while (rSet.next()) {
            buffer.append("public " + GenerateUtils.toTypeByName(rSet1, rSet.getString("COLUMN_NAME")) + GenerateUtils.toUpper(rSet.getString("COLUMN_NAME")) + ";     //" + rSet.getString("COMMENTS"));
            buffer.append("\r\n");
        }
        return buffer;
    }
    public   void readJaveFile(StringBuffer buffer,String tableName) throws IOException {
        File file2 = new File(dataSourceConfiguration.getAddress()+tableName+".java");
        FileOutputStream stream = new FileOutputStream(file2);
        stream.write(buffer.toString().getBytes());
        stream.close();
        System.out.println(buffer.toString());
    }
}
