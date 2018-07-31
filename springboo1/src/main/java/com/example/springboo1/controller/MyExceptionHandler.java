package com.example.springboo1.controller;

import com.example.springboo1.exception.UsernameNotExistException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import java.util.HashMap;
import java.util.Map;

@ControllerAdvice
public class MyExceptionHandler {

    //1.浏览器和客户端都返回json数据
//    @ResponseBody
//    @ExceptionHandler(UsernameNotExistException.class)
//    public Map<String, Object> handleException(Exception e){
//        Map<String,Object>map = new HashMap<>();
//        map.put("code","user not exist");
//        map.put("message",e.getMessage());
//        return map;
//    }

//    @ResponseBody
    @ExceptionHandler(UsernameNotExistException.class)
    public String handleException(Exception e,HttpServletRequest request){
        Map<String,Object>map = new HashMap<>();
        //需要传入我们自己的状态码
        request.setAttribute("javax.servlet.error.status_code",500);
        map.put("code","user not exist");
        map.put("message",e.getMessage());
        request.setAttribute("ext",map);
        //转发到error
        return "forward:/error";
    }
}
