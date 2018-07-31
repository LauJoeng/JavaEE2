package com.example.springboo1.config;

import com.example.springboo1.filter.MyFilter;
import com.example.springboo1.listener.MyListener;
import com.example.springboo1.servlet.MyServlet;
import org.springframework.boot.web.server.WebServerFactoryCustomizer;
import org.springframework.boot.web.servlet.FilterRegistrationBean;
import org.springframework.boot.web.servlet.ServletListenerRegistrationBean;
import org.springframework.boot.web.servlet.ServletRegistrationBean;
import org.springframework.boot.web.servlet.server.ConfigurableServletWebServerFactory;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.Arrays;

@Configuration
public class MyServerConfig {

    //注册三大组件
    @Bean
    public ServletRegistrationBean<MyServlet> myServlet(){
        ServletRegistrationBean<MyServlet> servletRegistrationBean = new ServletRegistrationBean<>(new MyServlet(), "/myServlet");
        return servletRegistrationBean;
    }

    @Bean
    public FilterRegistrationBean<MyFilter>myFilter(){
        FilterRegistrationBean<MyFilter>myFilterFilterRegistrationBean = new FilterRegistrationBean<>();
        myFilterFilterRegistrationBean.setFilter(new MyFilter());
        myFilterFilterRegistrationBean.setUrlPatterns(Arrays.asList("/hello","/myServlet"));
        return myFilterFilterRegistrationBean;
    }

    @Bean
    public ServletListenerRegistrationBean myListener(){
        ServletListenerRegistrationBean<MyListener> myListenerServletRegistrationBean = new ServletListenerRegistrationBean<MyListener>(new MyListener());
        return myListenerServletRegistrationBean;
    }

    //配置嵌入式的Servlet容器
    @Bean
    public WebServerFactoryCustomizer<ConfigurableServletWebServerFactory> webServerFactoryCustomizer(){
        //定制servlet容器相关规则
        return  factory -> factory.setPort(8088);

    }

}

