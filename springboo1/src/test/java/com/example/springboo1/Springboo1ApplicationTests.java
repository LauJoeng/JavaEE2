package com.example.springboo1;

import com.example.springboo1.bean.Person;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.ApplicationContext;
import org.springframework.test.context.junit4.SpringRunner;

/**
 * SpringBoot单元测试
 * 在测试期间很方便的类似编码一样进行自动注入等容器功能
 */
@RunWith(SpringRunner.class)
@SpringBootTest
public class Springboo1ApplicationTests {

    @Autowired
    Person person;

    @Autowired
    ApplicationContext ioc;

    //记录器
    Logger logger = LoggerFactory.getLogger(getClass());
    @Test
    public void contextLoads() {
        System.out.println(person);

        //日志的级别
        //由低到高 trace<debug<info<warn<error
        //可以调整日志输出级别，日志就只在这个级别以后的高级别生效
        //磨人使用info级别
        logger.trace("这是trace日志..");
        logger.debug("知识debug日志..");
        logger.info("这是info日志");
        logger.warn("这是warn日志");
        logger.error("这是error日志");
    }

    @Test
    public void testHelloService(){
        boolean b = ioc.containsBean("helloService");
        System.out.println(b);
    }

}
