package com.yang.service;

import java.util.List;

import com.yang.bean.UserAddress;

public interface OrderService {
    public List<UserAddress> initOrder(String userId);
}
