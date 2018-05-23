package com.yang.mybatis.config.mappers;

import com.yang.mybatis.bean.Employee;

public interface EmployeeMapperPlus {
    Employee getEmpById(Integer id);
}
