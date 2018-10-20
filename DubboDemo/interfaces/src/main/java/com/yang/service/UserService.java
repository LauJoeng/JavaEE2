package com.yang.service;

import com.yang.bean.UserAddress;

import java.util.List;

public interface UserService {
    public List<UserAddress>getUserAddressList(String userId);
}
