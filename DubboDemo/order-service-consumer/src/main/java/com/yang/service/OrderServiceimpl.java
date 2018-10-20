package com.yang.service;

import java.util.List;

import com.yang.bean.UserAddress;

public class OrderServiceimpl implements OrderService{
	
	UserService userService;
	

	public void initOrder(String userId) {
		List<UserAddress>address = userService.getUserAddressList(userId);
		System.out.println(address);
		
	}

	

}
