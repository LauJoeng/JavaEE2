package com.yang.service;

import java.util.Arrays;
import java.util.List;

import org.springframework.stereotype.Component;

import com.alibaba.dubbo.config.annotation.Service;
import com.yang.bean.UserAddress;

@Service//暴露服务
@Component
public class UserServiceImpl implements UserService {

	public List<UserAddress> getUserAddressList(String userId) {
		UserAddress address1 = new UserAddress(1,"̫太原市中北大学","1","liuyang","17573456789","Y");
		UserAddress address2 = new UserAddress(2,"台州市温岭市","2","liuwang","444444","Y");
		return Arrays.asList(address1,address2);
	}

}
