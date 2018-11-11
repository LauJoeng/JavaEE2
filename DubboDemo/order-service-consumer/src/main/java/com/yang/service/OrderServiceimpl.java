package com.yang.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.yang.bean.UserAddress;

/**
 * 1.�����ṩ��ע�ᵽ����(��¶����)
 * 	1).����Dubbo����  �Ͳ���zookeeper�Ŀͻ���(curator)
 * 	2).���÷����ṩ��
 * 2.�÷���������ȥע�����Ķ��ķ����ߵĵ�ַ
 * @author Yang
 *
 */
@Service
public class OrderServiceimpl implements OrderService{
	
	@Autowired
	UserService userService;
	
	public void initOrder(String userId) {
		System.out.println("�û�id:"+userId);
		List<UserAddress>address = userService.getUserAddressList(userId);
		for(UserAddress add:address) {
			System.out.println(add.getUserAddress());
		}
		
	}

	

}
