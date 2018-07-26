package com.example.springboo1.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import org.springframework.web.servlet.config.annotation.ViewControllerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurationSupport;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;

//使用WebMvcConfigurerAdapter可以来扩展SpringMVC功能,webMvcConfigurationSupport会屏蔽SpringBoot对SpringMVC的配置
@Configuration
//@EnableWebMvc
public class MyMvcConfig extends WebMvcConfigurerAdapter {
    @Override
    public void addViewControllers(ViewControllerRegistry registry) {
        //浏览器发送/yang 请求来到success页面
        registry.addViewController("/yang").setViewName("success");
        registry.addViewController("/").setViewName("login");
        registry.addViewController("/index.html").setViewName("login");
    }

    //所有的WebMvcConfigurationSupport组件会一起起作用
    //组件必须在容器注册才能被容器使用
//    @Bean
//    public WebMvcConfigurationSupport webMvcConfigurationSupport(){
//        return new WebMvcConfigurationSupport(){
//            @Override
//            protected void addViewControllers(ViewControllerRegistry registry) {
//                registry.addViewController("/").setViewName("index");
//                registry.addViewController("/index.html").setViewName("index");
//            }
//        };
//    }
}
