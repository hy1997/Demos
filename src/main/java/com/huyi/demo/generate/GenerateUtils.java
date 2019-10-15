package com.huyi.demo.generate;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class GenerateUtils {

    public static String toUpper(String str) {
        if (str != null) {
            StringBuffer buffer = new StringBuffer();
            String str1 = str.toLowerCase();
            if (str1.contains("_")) {// 包含下划线
                String[] String4 = str1.split("_");
                buffer.append(String4[0]);
                for (int j = 1; j < String4.length; j++) {
                    // 首字母变大写
                    buffer.append(String4[j].substring(0, 1).toUpperCase() + String4[j].substring(1, String4[j].length()));
                }
                return buffer.toString();
            } else {
                return str1;
            }
        }
        return str;
    }

    public static String toTypeByName(ResultSet rSet, String name) throws SQLException {
        while (rSet.next()) {
            String columnName = rSet.getString("COLUMN_NAME").trim();
            String dataType = rSet.getString("DATA_TYPE");
            if (columnName.equals(name.trim())) {
                switch (dataType) {
                    case "VARCHAR2":
                        return "String";
                    case "NUMBER":
                        return "BigDecimal";
                    case "CHAR":
                        return "String";
                    default:
                        return "String";
                }
            }

        }
        return name;

    }


    public static String toTypeByName(String dataType) throws SQLException {
        switch (dataType) {
            case "VARCHAR2":
                return "String";
            case "NUMBER":
                return "BigDecimal";
            case "CHAR":
                return "String";
            default:
                return "String";
        }
    }

    public static String select(String str, String tableName, String schema) throws SQLException {
        if (GenerateConstant.TABLES.equals(str)) {
            return "select TABLE_NAME from INFORMATION_SCHEMA.Columns where table_schema='" + schema + "'   group by TABLE_NAME";
        } else if (GenerateConstant.COLUMN.equals(str)) {
            return "select TABLE_NAME,COLUMN_NAME,COLUMN_COMMENT AS COMMENTS ,DATA_TYPE from INFORMATION_SCHEMA.Columns where table_schema='" + schema + "'  AND  table_name ='" + tableName + "'";
        } else {
            return null;
        }


    }
    public static String select(String str, String schema) throws SQLException {
        return select(str, null, schema);
    }


    public static  ResultSet getResultSet(Connection conn, String sql) throws SQLException {

        PreparedStatement prepareStatement = conn.prepareStatement(sql);
        ResultSet rSet = prepareStatement.executeQuery(sql);
        return rSet;

    }
}
