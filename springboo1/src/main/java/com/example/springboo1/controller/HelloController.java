package com.example.springboo1.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.Map;

/*这里直接在类上加ResponseBody注解表示这个类所有返回的数据直接返回给浏览器*/
//@ResponseBody
@Controller
public class HelloController {

    @RequestMapping("/hello")
    public  String hello(){
        return "hello world";
    }

    //查找一些数据在页面显示
    @RequestMapping("success")
    public  String success(Map<String,Object> map){
        map.put("hello","hello thymeleaf");
        //classpath:/templates/success.html
        return "success";
    }
}
