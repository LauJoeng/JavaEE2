package com.yang.springmvc.handler;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@RequestMapping("/springmvc")
@Controller
public class SpringMVCTest {

    private static final String SUCCESS = "success";


    /**
     * 1.@RequestMapping除了修饰方法外，还可以修饰类
     * 2.类定义处:提供初步的请求映射信息。相对于WEB应用的根目录
     * 方法处:提供进一步的细分信息。相对于类定义处的URL。若定义处未标注@RequestMapping，则方法处标记的URL相对于WEB应用的根目录
     * @return
     */
    @RequestMapping("/requestMapping")
    public String requestMapping(){
        System.out.println("testRequestMapping");
        return SUCCESS;
    }
}
