package com.yang.springmvc.crud.test;

import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.servlet.ModelAndView;

@ControllerAdvice
public class HandleException {
    @ExceptionHandler({ArithmeticException.class})
    public ModelAndView handleArithmeticException(Exception e){
        System.out.println("---> 出异常了 : "+e);
        ModelAndView mv = new ModelAndView("error");
        mv.addObject("exception",e);
        return mv;
    }
}
