package com.yang.mybatis.dao;

import com.yang.mybatis.bean.Employee;
import org.apache.ibatis.annotations.Param;

import java.util.Map;

public interface EmployeeMapper {

    Employee getEmpByMap(Map<String,Object>map);

    Employee getEmployeeById(Integer id);
    Employee getEmployeeByIdAndLastName(@Param("id")Integer id,@Param("lastName")String lastName);//明确指出封装参数map使用的key
    void  addEmployee(Employee employee);
    void updateEmp(Employee employee);
    void deleteEmpById(Integer id);
}
