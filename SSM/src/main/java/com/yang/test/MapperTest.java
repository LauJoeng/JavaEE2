package com.yang.test;


import com.yang.bean.Department;
import com.yang.dao.DepartmentMapper;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

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
    /**
     * 测试DepartmentMapper
     */
    @Test
    public void testCRUD(){
       /* ApplicationContext ioc = new ClassPathXmlApplicationContext("applicationContext.xml");
        DepartmentMapper departmentMapper = ioc.getBean(DepartmentMapper.class);*/

       System.out.println(departmentMapper);
       departmentMapper.insertSelective(new Department(null,"开发部"));
       departmentMapper.insertSelective(new Department(null,"测试部"));
    }
}
