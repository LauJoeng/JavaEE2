package com.yang.mybatis.dao;

import com.yang.mybatis.bean.Employee;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface EmployeeMapperDynamicSQL {
    List<Employee>getEmpsByConditionIF(Employee employee);
    List<Employee>getEmpsByConditionTrim(Employee employee);

    List<Employee>getEmpsByConditionChoose(Employee employee);

    void updateEmp(Employee employee);

    List<Employee> getEmpsByConditionForeach(List<Integer> ids);

    void addEmps(@Param("emps") List<Employee>emps);

    List<Employee>getEmpsTestInnerParameter(Employee employee);
}
