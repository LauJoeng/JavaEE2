package com.example.springboo1.config;

import com.example.springboo1.component.LoginHandlerInterceptor;
import com.example.springboo1.component.MyLocaleResolver;
import org.apache.tomcat.util.descriptor.LocalResolver;
import org.springframework.boot.web.embedded.tomcat.TomcatServletWebServerFactory;
import org.springframework.boot.web.server.ConfigurableWebServerFactory;
import org.springframework.boot.web.server.WebServerFactory;
import org.springframework.boot.web.server.WebServerFactoryCustomizer;
import org.springframework.boot.web.servlet.server.ConfigurableServletWebServerFactory;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.LocaleResolver;
import org.springframework.web.servlet.config.annotation.*;

//使用WebMvcConfigurerAdapter可以来扩展SpringMVC功能,webMvcConfigurationSupport会屏蔽SpringBoot对SpringMVC的配置
@Configuration
//@EnableWebMvc
public class MyMvcConfig extends WebMvcConfigurerAdapter {

//    @Bean
//    public ConfigurableWebServerFactory webServerFactory(){
//        //定制嵌入式servlet容器相关规则
//        TomcatServletWebServerFactory factory = new TomcatServletWebServerFactory();
//
//        return factory;
//    }

    @Bean
    public WebServerFactoryCustomizer webServerFactoryCustomizer(){
       return  new WebServerFactoryCustomizer<ConfigurableServletWebServerFactory>(){
           //定制servlet容器相关规则
            @Override
            public void customize(ConfigurableServletWebServerFactory factory) {
                factory.setPort(8089);
            }
        };
    }

    @Override
    public void addViewControllers(ViewControllerRegistry registry) {
        //浏览器发送/yang 请求来到success页面
        registry.addViewController("/yang").setViewName("success");
        registry.addViewController("/").setViewName("login");
        registry.addViewController("/index.html").setViewName("login");
        registry.addViewController("/main.html").setViewName("dashboard");
//        registry.addViewController("/main1.html").setViewName("login");
    }

    @Bean
    public LocaleResolver localeResolver(){
        return new MyLocaleResolver();
    }

    //注册拦截器
    @Override
    public void addInterceptors(InterceptorRegistry registry) {
//        super.addInterceptors(registry);
        //静态资源
        //SpringBoot已经做好了静态资源映射
//        registry.addInterceptor(new LoginHandlerInterceptor()).addPathPatterns("/**")
//                .excludePathPatterns("/index.html","/user/login","/","/**/*.css","/**/*.js","/**/*.icon","/**/*.svg","/**/*.jpg");

    }


}
