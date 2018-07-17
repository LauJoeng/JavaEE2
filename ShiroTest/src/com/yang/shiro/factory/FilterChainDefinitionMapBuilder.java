package com.yang.shiro.factory;

import java.util.LinkedHashMap;

public class FilterChainDefinitionMapBuilder {
	
	public LinkedHashMap<String, String>buildFilterChainDefinationMap(){
		LinkedHashMap<String, String>map = new LinkedHashMap<>();
		
//				/login.jsp = anon
//                /shiro/login = anon
//                /shiro/logout = logout
//                
//                /user.jsp = roles[user]
//               	/admin.jsp = roles[admin] 
//                # everything else requires authentication:
//                /** = authc
		
		map.put("/login.jsp", "anon");
		map.put("/shiro/login","anon");
		map.put("/shiro/logout","logout");
		map.put("/user.jsp", "authc,roles[user]");
		map.put("/admin.jsp", "authc,roles[admin]");
		map.put("/list.jsp","user");
		map.put("/**", "authc");
		
		return map;
	}
}
