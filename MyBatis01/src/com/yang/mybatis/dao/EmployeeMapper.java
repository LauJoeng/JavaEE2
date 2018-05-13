package com.yang.mybatis.dao;

import com.yang.mybatis.bean.Employee;

public interface EmployeeMapper {
    Employee getEmployeeById(Integer id);
}
