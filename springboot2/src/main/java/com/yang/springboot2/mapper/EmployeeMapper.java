package com.yang.springboot2.mapper;

import com.yang.springboot2.bean.Employee;

public interface EmployeeMapper {

    public Employee getEmpById(Integer id);

    public void insertEmp(Employee employee);
}
