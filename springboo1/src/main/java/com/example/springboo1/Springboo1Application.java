package com.example.springboo1;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ImportResource;

//@ImportResource(value = "classpath:applicationContext.xml")
@SpringBootApplication
public class Springboo1Application {

    public static void main(String[] args) {
        SpringApplication.run(Springboo1Application.class, args);
    }
}
