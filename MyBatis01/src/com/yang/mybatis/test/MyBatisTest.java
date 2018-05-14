package com.yang.mybatis.test;


import com.yang.mybatis.bean.Employee;
import com.yang.mybatis.config.mappers.EmployeeMapperAnnotation;
import com.yang.mybatis.dao.EmployeeMapper;
import jdk.nashorn.internal.runtime.FindProperty;
import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;
import org.junit.Test;

import java.io.IOException;
import java.io.InputStream;

/**
 * 1.SqlSession代表和数据库的一次会话，使用完必须关闭
 * 2.SqlSession和Connection一样都是非线层安全，每次使用完必须去获取新对象
 * 3.mapper接口没有实现类，但mybatis会为接口生成一个代理对象(将接口和配置文件绑定)
 * 4.两个重要的配置文件
 * mybatis的全局配置文件，包含数据库连接池信息，事务管理器。。。
 */

public class MyBatisTest {

    private SqlSessionFactory getSqlSessionFactory() {
        String resource = "com/yang/mybatis/config/mybatis-config.xml";
        InputStream inputStream = null;
        try {
            inputStream = Resources.getResourceAsStream(resource);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return new SqlSessionFactoryBuilder().build(inputStream);
    }
    /**
     * 1.根据xml创建一个SqlSessionFactory对象
     * 2.sql映射文件，配置了每一个sql，以及sql的封装规则
     * 3.将sql映射文件注册到全局配置文件
     * 4.写代码，调用MyBatis的api执行sql语句(记得关闭sqlSession)
     *
     * @throws IOException
     */
    @Test
    public void test() throws IOException {
        SqlSession sqlSession = getSqlSessionFactory().openSession();
        try {
            Employee employee = sqlSession.selectOne("com.yang.mybatis.dao.EmployeeMapper.getEmployeeById", 1);
            System.out.println(employee);
        } finally {
            sqlSession.close();
        }
    }



    @Test
    public void test01() {
        SqlSessionFactory sqlSessionFactory = getSqlSessionFactory();

        SqlSession sqlSession = sqlSessionFactory.openSession();
        try {
            //会为接口自动创建一个代理对象，代理对象去执行增删改查
            EmployeeMapper employeeMapper = sqlSession.getMapper(EmployeeMapper.class);
            Employee employee = employeeMapper.getEmployeeById(1);
            System.out.println(employeeMapper.getClass());
            System.out.println(employee);
        } finally {
            sqlSession.close();
        }
    }

    @Test
    public void test02(){
        SqlSession sqlSession = getSqlSessionFactory().openSession();
        try {
            EmployeeMapperAnnotation mapper = sqlSession.getMapper(EmployeeMapperAnnotation.class);
            Employee employee = mapper.getEmployeeById(1);
            System.out.println(employee);
        }finally {
            sqlSession.close();
        }
    }

    /**
     * 测试增删改
     * 1.mybatis允许增删改直接定义以下类型的返回值
     *      Integer，Long，Boolean void
     */
    @Test
    public void test03(){
        SqlSession sqlSession = getSqlSessionFactory().openSession();
        try {
            EmployeeMapper mapper = sqlSession.getMapper(EmployeeMapper.class);

            Employee employee = new Employee(null,"jerry","jerry@163.com","1");
            //增
            //mapper.addEmployee(employee);

            //改
//            mapper.updateEmp(employee);

            //删
            mapper.deleteEmpById(2);
            sqlSession.commit();
        }finally {
            sqlSession.close();
        }
    }
}
