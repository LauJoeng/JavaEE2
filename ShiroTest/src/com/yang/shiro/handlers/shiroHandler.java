package com.yang.shiro.handlers;

import javax.servlet.http.HttpSession;

import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.subject.Subject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.yang.shiro.service.ShiroService;

@Controller
@RequestMapping("/shiro")
public class shiroHandler {
	
	
	@Autowired
	private ShiroService service;
	
	@RequestMapping("/testShiroAnnotation")
	public String testShiroAnnotation(HttpSession session) {
		session.setAttribute("key", "value1234");
		service.testMethod();
		return "redirect:/list.jsp";
	}
	
	@RequestMapping("/login")
	public String login(@RequestParam("username")String username,
			@RequestParam("password")String password) {
//		System.out.println("");
		Subject currentUser = SecurityUtils.getSubject();
		if(!currentUser.isAuthenticated()) {
			UsernamePasswordToken token = new UsernamePasswordToken(username,password);
			token.setRememberMe(true);
			try {
//				System.out.println("1. "+token.hashCode());
//				currentUser.hasRole("admin");
				currentUser.login(token);
			} catch (AuthenticationException e) {
				// TODO Auto-generated catch block
//				e.printStackTrace();
				System.out.println("µÇÂ¼Ê§°Ü£º"+e.getMessage());
			}
		}
		return "redirect:/list.jsp";
	}
}
