package com.yang.ssm.service;

import com.yang.ssm.bean.Employee;
import com.yang.ssm.dao.EmployeeMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class EmployeeService {

    @Autowired
    private EmployeeMapper employeeMapper;

    public List<Employee>getEmps(){
        return employeeMapper.getEmps();
    }
}
