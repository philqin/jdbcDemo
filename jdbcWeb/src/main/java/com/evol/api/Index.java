package com.evol.api;

import com.evol.pool.PoolUtil;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

@WebServlet("/home")
public class Index extends HttpServlet {
    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        //super.doPost(req, resp);


    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        Connection connection = PoolUtil.getConnection();
        try {
            PreparedStatement statement= connection.prepareStatement("select * from user where id=?");
            statement.setInt(1,1);
            ResultSet resultSet = statement.executeQuery();
            while (resultSet.next()){
                System.out.println(resultSet.getString("name"));
            }
            PoolUtil.close(connection,statement,resultSet);
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        resp.getWriter().write("Hello Servlet");
    }
}
