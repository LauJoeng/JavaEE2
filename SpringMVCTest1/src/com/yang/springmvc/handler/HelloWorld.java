package com.yang.springmvc.handler;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class HelloWorld {

    /**
     * 1.使用@RequestMapping注解来映射请求的url
     * 2.返回值会通过解析器解析为实际的物理视图，对于InternalResourceViewResolver视图解析器，会做如下的解析
     * 通过prefix + return + suffix 这样的方法得到实际的物理视图，然后转发操作
     * @return
     */
    @RequestMapping("/helloworld")
    public String hello(){
        System.out.println("hello world");
        return "success";
    }
}
