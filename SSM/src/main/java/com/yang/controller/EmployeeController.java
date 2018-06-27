package com.yang.controller;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.yang.bean.Employee;
import com.yang.service.EmployeeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

/**
 * 处理员工CRUD请求
 */
@Controller
public class EmployeeController {

    @Autowired
    EmployeeService employeeService;

    //查询员工数据(分页查询)
    @RequestMapping("/emps")
    public String getEmps(@RequestParam(value = "pn",defaultValue = "1")Integer pageNum,Model model){
        //引入PageHelper分页插件
        //在查询之前只需要调用,传入页码，以及每页大小
        PageHelper.startPage(pageNum,5);
        //startPage后面紧跟的这个查询就是分页查询
        List<Employee>emps = employeeService.getAll();
        //使用pageInfo包装查询后的结果，只需将pageinfo交给页面就行了，里面封装了查询出来的数据,可以传入连续显示页数
        PageInfo<Employee> pageInfo = new PageInfo<>(emps,5);
        model.addAttribute("pageInfo",pageInfo);
        return "list";
    }
}
