package com.example.springboo1.dao;

import com.example.springboo1.bean.Department;
import com.example.springboo1.bean.Employee;
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

        employees.put(1001,new Employee(1001,"E-AA","1","maa@qq.com",new Department(101,"D-AA")));
        employees.put(1002,new Employee(1002,"E-BB","0","fbb@qq.com",new Department(102,"D-BB")));
        employees.put(1003,new Employee(1003,"E-CC","1","mcc@qq.com",new Department(103,"D-CC")));
        employees.put(1004,new Employee(1004,"E-DD","0","fdd@gmail.com",new Department(104,"D_DD")));
        employees.put(1005,new Employee(1005,"E-EE","0","fee@qq.com",new Department(105,"D-EE")));
        employees.put(1006,new Employee(1006,"E-FF","1","mff@qq.com",new Department(106,"D_FF")));
        employees.put(1007,new Employee(1007,"E-GG","0","mgg@qq.com",new Department(107,"D-GG")));
    }

    public void save(Employee employee){
        if (employee.getEmpId() == null){
            employee.setEmpId(initId++);
        }
        if(employee.getDepartment()==null){
            employee.setDepartment(departmentDao.getDepartment(employee.getdId()));
        }else if(employee.getdId() == null){
            employee.setdId(employee.getDepartment().getId());
        }
        employees.put(employee.getEmpId(),employee);
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
