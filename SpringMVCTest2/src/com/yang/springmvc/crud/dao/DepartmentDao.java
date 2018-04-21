package com.yang.springmvc.crud.dao;

import com.yang.springmvc.crud.entity.Department;
import org.springframework.stereotype.Repository;

import java.util.Collection;
import java.util.HashMap;
import java.util.Map;

@Repository
public class DepartmentDao {
    private static Map<Integer,Department>departments = null;

    static {
        departments = new HashMap<>();
        departments.put(101,new Department(101,"D-AA"));
        departments.put(102,new Department(101,"D-BB"));
        departments.put(103,new Department(101,"D-CC"));
        departments.put(104,new Department(101,"D-DD"));
        departments.put(105,new Department(101,"D-EE"));
        departments.put(106,new Department(101,"D-FF"));
        departments.put(107,new Department(101,"D-GG"));
    }

    public Collection<Department>getDepartments(){
        return departments.values();
    }

    public Department getDepartment(Integer id){
        return departments.get(id);
    }

}
