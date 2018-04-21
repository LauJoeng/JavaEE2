package com.yang.springmvc.crud.handler;

import com.yang.springmvc.crud.dao.EmployeeDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.Map;

@Controller
public class EmployeeHandler {

    @Autowired
    private EmployeeDao employeeDao;

    @RequestMapping("/emps")
    public String list(Map<String,Object> map){
        map.put("emplyees",employeeDao.getAll());
        return "list";
    }
}
