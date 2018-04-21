package com.yang.springmvc.crud.dao;

import com.sun.org.apache.regexp.internal.RE;
import com.yang.springmvc.crud.entity.Department;
import com.yang.springmvc.crud.entity.Employee;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.Collection;
import java.util.HashMap;
import java.util.Map;

@Repository
public class EmployeeDao {
    private static Map<Integer,Employee>employees = null;
    private int initId = 8;

    @Autowired
    private DepartmentDao departmentDao;

    static {
        employees = new HashMap<>();

        employees.put(1001,new Employee(1001,"E-AA","aa@qq.com","m",new Department(101,"D-AA")));
        employees.put(1002,new Employee(1002,"E-BB","bb@qq.com","f",new Department(102,"D-BB")));
        employees.put(1003,new Employee(1003,"E-CC","cc@qq.com","m",new Department(103,"D-CC")));
        employees.put(1004,new Employee(1004,"E-DD","dd@qq.com","f",new Department(104,"D_DD")));
        employees.put(1005,new Employee(1005,"E-EE","ee@qq.com","f",new Department(105,"D-EE")));
        employees.put(1006,new Employee(1006,"E-FF","ff@qq.com","m",new Department(106,"D_FF")));
        employees.put(1007,new Employee(1007,"E-GG","gg@qq.com","m",new Department(107,"D-GG")));
    }

    public void save(Employee employee){
        if (employee.getId() == null){
            employee.setId(initId++);
        }
        employee.setDepartment(departmentDao.getDepartment(employee.getDepartment().getId()));
        employees.put(employee.getId(),employee);
    }

    public Collection<Employee>getAll(){
        return employees.values();
    }

    public Employee getEmployee(Integer id){
        return employees.get(id);
    }

    public void delete(Integer id){
        employees.remove(id);
    }
}
