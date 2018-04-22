package com.yang.springmvc.crud.test;

import com.yang.springmvc.crud.dao.EmployeeDao;
import com.yang.springmvc.crud.entity.Employee;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.Collection;
import java.util.List;

@Controller
public class SpringMVCTest {

    @Autowired
    private EmployeeDao employeeDao;

    @RequestMapping("/testConversionServiceConverter")
    public String testConverter(@RequestParam("employee")Employee employee){
        System.out.println("save"+employee);
        employeeDao.save(employee);
        return "redirect:/emps";
    }

    @ResponseBody
    @RequestMapping("/testJson")
    public Collection<Employee> testJson(){
        System.out.println("testJson");
        return employeeDao.getAll();
    }
}
