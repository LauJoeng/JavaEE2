package com.example.springboo1.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

/*这里直接在类上加ResponseBody注解表示这个类所有返回的数据直接返回给浏览器*/
@ResponseBody
@Controller
public class HelloController {

    @RequestMapping("/hello")
    public  String hello(){
        return "hello world";
    }
}
