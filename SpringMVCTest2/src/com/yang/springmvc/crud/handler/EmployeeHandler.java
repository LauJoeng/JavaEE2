package com.yang.springmvc.crud.handler;

import com.yang.springmvc.crud.dao.DepartmentDao;
import com.yang.springmvc.crud.dao.EmployeeDao;
import com.yang.springmvc.crud.entity.Employee;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import java.util.Map;

@Controller
public class EmployeeHandler {

    @Autowired
    private EmployeeDao employeeDao;

    @Autowired
    private DepartmentDao departmentDao;

    @RequestMapping("/emps")
    public String list(Map<String,Object> map){
        map.put("employees",employeeDao.getAll());

        return "list";
    }

    @RequestMapping(value = "emp",method = RequestMethod.GET)
    public String input(Map<String,Object>map){
        map.put("employees",new Employee());
        map.put("departments",departmentDao.getDepartments());
        return "input";
    }

    @RequestMapping(value = "emp",method = RequestMethod.POST)
    public String save(Employee employee){
        employeeDao.save(employee);
        return "redirect:/emps";
    }
}
