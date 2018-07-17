package com.yang.shiro.service;

import java.util.Date;

import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authz.annotation.RequiresRoles;
import org.apache.shiro.session.Session;

public class ShiroService {
	
	@RequiresRoles("admin")
	public void testMethod() {
		System.out.println("testMethod,time: "+new Date());
		Session session = SecurityUtils.getSubject().getSession();
		Object value = session.getAttribute("key");
		System.out.println("service session value:"+value);
	}
}
