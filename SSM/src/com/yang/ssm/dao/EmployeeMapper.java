package com.yang.ssm.dao;


import com.yang.ssm.bean.Employee;

import java.util.List;


public interface EmployeeMapper {

    Employee getEmpById(Integer id);
    List<Employee>getEmps();
}
