package com.evol.listen;


import com.evol.pool.PoolUtil;

import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;
import javax.servlet.annotation.WebListener;

@WebListener
public class MyListener implements ServletContextListener {

    @Override
    public void contextInitialized(ServletContextEvent servletContextEvent) {
        System.out.println("初始化。。。。");
        PoolUtil.intiData();
    }

    @Override
    public void contextDestroyed(ServletContextEvent servletContextEvent) {

    }
}
