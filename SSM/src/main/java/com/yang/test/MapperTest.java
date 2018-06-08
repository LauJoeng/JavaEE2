package com.yang.test;


import com.yang.bean.Department;
import com.yang.bean.Employee;
import com.yang.dao.DepartmentMapper;

import com.yang.dao.EmployeeMapper;
import org.apache.ibatis.session.SqlSession;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import java.util.UUID;

/**
 * 测试dao层
 * 这里使用Spring项目就可以使用Spring的单元测试，可以自动注入我们需要的组件
 * 1.导入Spring的Test模块
 * 2.@ContextConfiguration指定Spring配置文件的位置
 * 3.直接@Autowired
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = {"classpath:applicationContext.xml"})
public class MapperTest {

    @Autowired
    DepartmentMapper departmentMapper;

    @Autowired
    EmployeeMapper employeeMapper;

    @Autowired
    SqlSession sqlSession;
    /**
     * 测试DepartmentMapper
     */
    @Test
    public void testCRUD(){
       /* ApplicationContext ioc = new ClassPathXmlApplicationContext("applicationContext.xml");
        DepartmentMapper departmentMapper = ioc.getBean(DepartmentMapper.class);*/

       System.out.println(departmentMapper);
       //部门测试
//       departmentMapper.insertSelective(new Department(null,"开发部"));
//       departmentMapper.insertSelective(new Department(null,"测试部"));

       //员工测试
//        employeeMapper.insertSelective(new Employee(null,"Chandler","M","Chandler@163.com",7));
        //批量插入:批量:使用可以执行批量操作的SqlSession

        EmployeeMapper employeeMapper = sqlSession.getMapper(EmployeeMapper.class);
        for(int i=0;i<1000;i++){
            String uid = UUID.randomUUID().toString().substring(0,5)+i;
            employeeMapper.insertSelective(new Employee(null,uid,"M",uid+"@gmail.com",8));
        }
        System.out.println("批量完成");

    }
}
