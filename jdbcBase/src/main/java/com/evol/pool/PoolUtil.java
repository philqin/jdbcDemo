package com.evol.pool;


import com.mchange.v2.c3p0.ComboPooledDataSource;

import javax.sql.DataSource;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;


public class PoolUtil {
    private static DataSource dataSources;
    public static void intiData(){
        ComboPooledDataSource pooledDataSource = new ComboPooledDataSource("c3p0-config.xml");
        dataSources = pooledDataSource;
    }
    public static Connection getConnection() {
        if(dataSources!=null){
            try {
                return dataSources.getConnection();
            } catch (SQLException throwables) {
                throwables.printStackTrace();
            }
        }else {
            try {
                throw new Exception("获取连接池异常");
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        return null;
    }

    //释放查询操作相关的资源（结果集对象，SQL语句对象，归还数据库连接）
    public static void close(Connection con, Statement stat, ResultSet rs)  {
        if (con != null) {
            try {
                con.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }

        if (stat != null) {
            try {
                stat.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }

        if (rs != null) {
            try {
                rs.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }
}
