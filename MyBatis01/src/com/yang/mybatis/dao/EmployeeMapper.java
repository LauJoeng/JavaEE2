package com.yang.mybatis.dao;

import com.yang.mybatis.bean.Employee;
import org.apache.ibatis.annotations.MapKey;
import org.apache.ibatis.annotations.Param;

import java.util.List;
import java.util.Map;

public interface EmployeeMapper {

    Employee getEmpByMap(Map<String,Object>map);
    List<Employee>getEmpsByLastNameLike(String lastName);

    //返回一条记录的Map，key就是列名，值就是对应的值
    Map<String,Object>getEmplByIdReturnMap(Integer id);

    //多条记录封装的map:Map<Integer,Employee>,键就是这条记录的主键，值就是这个JavaBean
    @MapKey("id")//告诉mybatis封装map的时候使用哪个属性作为map的key
    Map<Integer,Employee>getEmployeeByLastNameReturnMap(String lastName);

    Employee getEmployeeById(Integer id);
    Employee getEmployeeByIdAndLastName(@Param("id")Integer id,@Param("lastName")String lastName);//明确指出封装参数map使用的key
    void  addEmployee(Employee employee);
    void updateEmp(Employee employee);
    void deleteEmpById(Integer id);
}
