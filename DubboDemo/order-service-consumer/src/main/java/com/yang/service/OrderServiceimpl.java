package com.yang.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.yang.bean.UserAddress;

/**
 * 1.服务提供者注册到中心(暴露服务)
 * 	1).导入Dubbo依赖  和操作zookeeper的客户端(curator)
 * 	2).配置服务提供者
 * 2.让服务消费者去注册中心订阅服务者的地址
 * @author Yang
 *
 */
@Service
public class OrderServiceimpl implements OrderService{
	
	@Autowired
	UserService userService;
	
	public void initOrder(String userId) {
		System.out.println("用户id:"+userId);
		List<UserAddress>address = userService.getUserAddressList(userId);
		for(UserAddress add:address) {
			System.out.println(add.getUserAddress());
		}
		
	}

	

}
