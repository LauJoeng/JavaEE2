package com.yang;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import com.alibaba.dubbo.config.spring.context.annotation.EnableDubbo;

/**
 * 1.导入依赖
 * 	1).导入dubbo-starter
 * 	2).导入dubbo的其他依赖
 * @author Yang
 *
 */
@EnableDubbo//开启基于注解的Dubbo功能
@SpringBootApplication
public class BootUserServiceApplication {

	public static void main(String[] args) {
		SpringApplication.run(BootUserServiceApplication.class, args);
	}
}
