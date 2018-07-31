package com.example.springboo1.listener;


import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;


public class MyListener implements ServletContextListener {
    @Override
    public void contextInitialized(ServletContextEvent servletContextEvent) {
        System.out.println("context initialized,启动web应用");
    }

    @Override
    public void contextDestroyed(ServletContextEvent servletContextEvent) {
        System.out.println("context destroyed，服务器关闭");
    }
}
