package com.example.springboo1;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ImportResource;
import org.springframework.web.servlet.View;
import org.springframework.web.servlet.ViewResolver;

import java.util.Locale;

//@ImportResource(value = "classpath:applicationContext.xml")
@SpringBootApplication
public class Springboo1Application {

    public static void main(String[] args) {
        SpringApplication.run(Springboo1Application.class, args);
    }

    @Bean
    public ViewResolver myViewResolver(){
        return new MyViewResolver() ;
    }

    private static class MyViewResolver implements ViewResolver{

        @Override
        public View resolveViewName(String s, Locale locale) throws Exception {
            return null;
        }
    }
}
