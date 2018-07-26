package com.example.springboo1.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.Arrays;
import java.util.Map;

/*这里直接在类上加ResponseBody注解表示这个类所有返回的数据直接返回给浏览器*/
//@ResponseBody
@Controller
public class HelloController {


//    @RequestMapping({"/","/index.html"})
//    public String index(){
//        return "index";
//    }

    @RequestMapping("/hello")
    public  String hello(){
        return "hello world";
    }

    //查找一些数据在页面显示
    @RequestMapping("success")
    public  String success(Map<String,Object> map){
        map.put("hello","<h1>hello thymeleaf</h1>");
        map.put("users", Arrays.asList("zhangsan","lisi","wangwu"));
        //classpath:/templates/success.html
        return "success";
    }
}
