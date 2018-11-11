package com.yang.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.alibaba.dubbo.config.annotation.Reference;
import com.yang.bean.UserAddress;

@Service
public class OrderServiceimpl implements OrderService{
	
	//@Autowired
	@Reference
	UserService userService;
	
	public List<UserAddress> initOrder(String userId) {
		System.out.println("用户id:"+userId);
		List<UserAddress>address = userService.getUserAddressList(userId);
		return address;
		
	}

	

}
