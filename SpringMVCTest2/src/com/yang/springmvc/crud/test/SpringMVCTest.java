package com.yang.springmvc.crud.test;

import com.yang.springmvc.crud.dao.EmployeeDao;
import com.yang.springmvc.crud.entity.Employee;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cglib.core.Local;
import org.springframework.context.support.ResourceBundleMessageSource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.ServletContext;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.io.InputStream;
import java.util.*;

@Controller
public class SpringMVCTest {

    @Autowired
    private EmployeeDao employeeDao;

    @Autowired
    private ResourceBundleMessageSource messageSource;

    @RequestMapping("/testConversionServiceConverter")
    public String testConverter(@RequestParam("employee")Employee employee){
        System.out.println("save"+employee);
        employeeDao.save(employee);
        return "redirect:/emps";
    }

    @ResponseBody
    @RequestMapping("/testJson")
    public Collection<Employee> testJson(){
        System.out.println("testJson");
        return employeeDao.getAll();
    }

    @ResponseBody
    @RequestMapping("/testHttpMessageConverter")
    public String testHttpMessageConverter(@RequestBody String body){
        System.out.println(body);
        return "helloWorld!" + new Date();
    }

    @RequestMapping("/testResponseEntity")
    public ResponseEntity<byte[]>testResponseEntity(HttpSession session) throws IOException {
        byte[] body = null;
        ServletContext servletContext = session.getServletContext();
        InputStream in = servletContext.getResourceAsStream("/file/abc.txt");
        body = new byte[in.available()];
        in.read(body);

        HttpHeaders headers = new HttpHeaders();
        headers.add("Content-Disposition","filename=abc.txt");

        HttpStatus statusCode = HttpStatus.OK;

        ResponseEntity<byte[]>responseEntity = new ResponseEntity<>(body,headers,statusCode);
        return responseEntity;
    }


    @RequestMapping("/i18n")
    public String testI18n(Locale locale){
        String val = messageSource.getMessage("i18n.user",null,locale);
        System.out.println(val);
        return "i18n";
    }

    @RequestMapping("/testFileUpload")
    public String testFileUpload(@RequestParam("desc")String desc, @RequestParam("file")MultipartFile file) throws IOException {
        System.out.println("desc: " + desc);
        System.out.println("OriginFileName:  "+file.getOriginalFilename());
        System.out.println("InputStream:"+file.getInputStream());
        return "success";
    }

    @RequestMapping("/testExceptionHandlerExceptionResolver")
    public String testExceptionHandlerExceptionResolver(@RequestParam("i")int i){
        System.out.println("result:  "+(10/i));
        return "success";
    }

    /**
     * 1.@ExceptionHandler方法中可以加入Exception入参类型参数，该参数即对应发生异常的对象
     * 2.@ExceptionHandler方法中不能传入Map，若希望把异常信心传到页面上，需要使用ModelAndView作为返回值
     * 3.@ExceptionHandler方法标记的异常有优先级的问题
     * 4.@ExceptionHandler:如果在当前Handler中找不到@ExceptionHandler方法来解决当前方法的异常，则去@ControllorAdvice标记的
     * 类中查找@ExceptionHandler标记的方法来处理异常
     * @param e
     * @return
     */
//    @ExceptionHandler({ArithmeticException.class})
//    public ModelAndView handleArithmeticException(Exception e){
//        System.out.println("出异常了 : "+e);
//        ModelAndView mv = new ModelAndView("error");
//        mv.addObject("exception",e);
//        return mv;
//    }

//    @ExceptionHandler({RuntimeException.class})
//    public ModelAndView handleArithmeticException2(Exception e){
//        System.out.println("出异常了 : "+e);
//        ModelAndView mv = new ModelAndView("error");
//        mv.addObject("exception",e);
//        return mv;
//    }
}
