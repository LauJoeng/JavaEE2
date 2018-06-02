package com.yang.ssm.controller;

import com.yang.ssm.bean.Employee;
import com.yang.ssm.service.EmployeeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;
import java.util.Map;

@Controller
public class EmployeeController {

    @Autowired
    EmployeeService employeeService;

    @RequestMapping("/emps")
    public String emps(Map<String,Object>map){
        List<Employee> emps = employeeService.getEmps();
        map.put("allEmps",emps);
        return "list";
    }

}
