package com.example.springboo1.controller;

import com.example.springboo1.bean.Department;
import com.example.springboo1.bean.Employee;
import com.example.springboo1.dao.DepartmentDao;
import com.example.springboo1.dao.EmployeeDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import java.util.Collection;

@Controller
public class EmployeeController {

    @Autowired
    EmployeeDao employeeDao;

    @Autowired
    DepartmentDao departmentDao;

    //查询所有员工返回列表页面
    @GetMapping("/emps")
    public String list(Model model){
        Collection<Employee> employees = employeeDao.getAll();
        model.addAttribute("emps",employees);
        return "emp/list";
    }

    //去往添加页面
    @GetMapping("/emp")
    public String toAddPage(Model model){
        //先查出所有部门
        Collection<Department> departments = departmentDao.getDepartments();
        model.addAttribute("depts",departments);
        return "emp/add";
    }

    //员工添加
    //SpringMVC会自动封装，将请求参数和对象属性一一绑定：要求是请求参数的名字和JavaBean的属性名一样
    @PostMapping("/emp")
    public String addEmp(Employee employee){
        //添加完成后去往列表页面
//        System.out.println("保存的员工信息："+employee.toString());
        employeeDao.save(employee);
        return "redirect:/emps";
    }

    //去往修改页面，查出当前员工，在页面回显
    @GetMapping("/emp/{id}")
    public String toEditPage(@PathVariable("id") Integer id,Model model){
        Employee employee = employeeDao.getEmployee(id);
        model.addAttribute("emp",employee);

        Collection<Department> departments = departmentDao.getDepartments();
        model.addAttribute("depts",departments);
        //重用添加页面，直接去往添加页面
        return "emp/add";
    }
}
