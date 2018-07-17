package com.yang.shiro.handlers;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.web.servlet.HandlerExceptionResolver;
import org.springframework.web.servlet.ModelAndView;

public class ExceptionHandler implements HandlerExceptionResolver{

	@Override
	public ModelAndView resolveException(HttpServletRequest arg0, HttpServletResponse arg1, Object arg2,
			Exception arg3) {
		ModelAndView mv = new ModelAndView();
		String msg = "";
		if(arg3 instanceof org.springframework.web.util.NestedServletException) {
			msg = arg3.getMessage();
		}else {
			msg = "Î´Öª´íÎó";
		}
		mv.addObject("msg",msg);
		mv.setViewName("erro");
		return mv;
	}

}
