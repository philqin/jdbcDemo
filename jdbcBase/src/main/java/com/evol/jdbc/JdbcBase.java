package com.evol.jdbc;

import org.junit.Test;

import java.sql.*;

public class JdbcBase {

    @Test
    public  void test() {
        JdbcBase jdbcUtil = new JdbcBase();
        try {
            jdbcUtil.testData("jdbc:mysql://124.221.143.233:3306/jdbcdemo?" +
                    "allowMultiQueries=true&" +
                    "useSSL=false&" +
                    "useUnicode=true&" +
                    "characterEncoding=UTF-8&" +
                    "autoReconnect=true&" +
                    "zeroDateTimeBehavior=convertToNull&" +
                    "useJDBCCompliantTimezoneShift=true&" +
                    "useLegacyDatetimeCode=false&" +
                    "serverTimezone=GMT%2B8&" +
                    "nullCatalogMeansCurrent=true", "root", "evol123456");
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
    }

    public Connection testData(String url, String name, String passwd) throws ClassNotFoundException, SQLException {
        Connection connection = null;
        //TODO 1导入jar
        //TODO 2注册驱动  任何class都要装载在虚拟机上才能运行。这句话就是装载类用的(和new 不一样，要分清楚)。
        // 要求JVM查找并加载指定的类，也就是说JVM会执行该类的静态代码段;
        // 静态代码是和class绑定的，class装载成功就表示执行了你的静态代码了。而且以后不会再走这段静态代码了
        Class.forName("com.mysql.cj.jdbc.Driver");
        //TODO 3获取数据库连接
        connection = DriverManager.getConnection(url, name, passwd);
        System.out.println("连接数据库成功");

        //TODO 4获取执行sql对象；对sql进行封装。发送给数据库
        Statement statement = connection.createStatement();
        String sql = "select * from user";

        //TODO 5执行sql.并且返回结果集
        ResultSet resultSet = statement.executeQuery(sql);
        //TODO 6对结果处理
        while (resultSet.next()){
            System.out.println(resultSet.getString("name"));
        }

        //TODO 7关闭相关资源。先开后关
        resultSet.close();
        statement.close();
        connection.close();
        return connection;
    }
}
