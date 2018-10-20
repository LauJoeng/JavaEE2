package com.yang.service;

import java.util.Arrays;
import java.util.List;

import com.yang.bean.UserAddress;

public class UserServiceImpl implements UserService {

	public List<UserAddress> getUserAddressList(String userId) {
		UserAddress address1 = new UserAddress(1,"太原市中北大学","1","liuyang","17573456789","Y");
		UserAddress address2 = new UserAddress(2,"衡南县相市乡","2","liuwang","444444","Y");
		return Arrays.asList(address1,address2);
	}

}
