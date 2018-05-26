package com.yang.mybatis.test;


import com.yang.mybatis.bean.Department;
import com.yang.mybatis.bean.Employee;
import com.yang.mybatis.config.mappers.DepartmentMapper;
import com.yang.mybatis.config.mappers.EmployeeMapperAnnotation;
import com.yang.mybatis.config.mappers.EmployeeMapperPlus;
import com.yang.mybatis.dao.EmployeeMapper;
import com.yang.mybatis.dao.EmployeeMapperDynamicSQL;
import jdk.nashorn.internal.runtime.FindProperty;
import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;
import org.junit.Test;

import java.io.IOException;
import java.io.InputStream;
import java.util.*;

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
//            Employee employee = employeeMapper.getEmployeeById(1);

            /*Map<String,Object> map = new HashMap<>();
            map.put("id",1);
            map.put("lastName","tom");
            Employee employee = employeeMapper.getEmpByMap(map);
            System.out.println(employeeMapper.getClass());
            System.out.println(employee);*/

            List<Employee> employees = employeeMapper.getEmpsByLastNameLike("%e%");
            for(Employee  employee:employees){
                System.out.println(employee);
            }

            Map<String,Object> map = employeeMapper.getEmplByIdReturnMap(1);
            System.out.println(map);
            System.out.println("------------------------------------------------");
            Map<Integer,Employee> emp = employeeMapper.getEmployeeByLastNameReturnMap("%e%");
            System.out.println(emp);
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
     * 2.sqlSessionFactory.openSession() ---> 手动提交
     *   sqlSessionFactory.openSession(true)  --->自动提交
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


    @Test
    public void test04(){
        SqlSession sqlSession = getSqlSessionFactory().openSession();


        try {
            EmployeeMapperPlus mapperPlus = sqlSession.getMapper(EmployeeMapperPlus.class);
//            Employee employee = mapperPlus.getEmpById(1);
//            Employee employee = mapperPlus.getEmpAndDept(1);
            Employee employee = mapperPlus.getEmpByIdStep(1);
            System.out.println(employee);
            System.out.println(employee.getDept());
        } finally {
            sqlSession.close();
        }
    }


    @Test
    public void test06(){
        SqlSession sqlSession = getSqlSessionFactory().openSession();

        try {
            DepartmentMapper departmentMapper = sqlSession.getMapper(DepartmentMapper.class);
            Department department = departmentMapper.getDepartmentByIdPlus(1);
            System.out.println(department);
            System.out.println(department.getEmps());
        }finally {
            sqlSession.close();
        }
    }

    @Test
    public void testDynamicSql(){
        SqlSession sqlSession = getSqlSessionFactory().openSession();

        try {
            EmployeeMapperDynamicSQL mapper = sqlSession.getMapper(EmployeeMapperDynamicSQL.class);
            Employee employee = new Employee(1,"tom2","tom2@qq.com",null);

//            List<Employee> emps = mapper.getEmpsByConditionTrim(employee);
//            for (Employee e:emps){
//                System.out.println(e);
//            }

            //测试choose
//            List<Employee> emps = mapper.getEmpsByConditionChoose(employee);
//            for (Employee emp:emps){
//                System.out.println(emp);
//            }


            //测试set
//            mapper.updateEmp(employee);


            List<Employee> employees = mapper.getEmpsByConditionForeach(Arrays.asList(1,2,3,4));
            for (Employee emp:employees){
                System.out.println(emp);
            }
            sqlSession.commit();
        }finally {
            sqlSession.close();
        }
    }


    @Test
    public void testBatchSave(){
        SqlSession sqlSession = getSqlSessionFactory().openSession();

        try {
            EmployeeMapperDynamicSQL mapper = sqlSession.getMapper(EmployeeMapperDynamicSQL.class);
            List<Employee>emps = new ArrayList<>();
            emps.add(new Employee(null,"Smith","smith@gmail.com","1",new Department(1)));
            emps.add(new Employee(null,"Marry","marry@gmail.com","0",new Department(1)));
            mapper.addEmps(emps);
            sqlSession.commit();
        }finally {
            sqlSession.close();
        }
    }


    /**
     * 一级缓存(本地缓存)sqlSession级别的缓存，默认开启的无法关闭
     *          与数据库一次会话期间查询到的数据库都会放在本地缓存中，以后如果需要获取相同的数据，直接从缓存中拿，不需要再去数据库
     *
     *          一级缓存失效的情况(没有使用到以及缓存的情况就还需要向数据库查询数据)
     *          1.sqlSession不同
     *          2.sqlSession相同但查询条件不同
     *          3.sqlSession相同，但两次查询之间执行了增删改
     *          4.sqlSession相同，但两次查询之间进行了手动清空缓存，sqlSession.clearCache()方法
     *
     * 二级缓存(全局缓存)基于namespace级别的缓存，一个namespace可以对应一个二级缓存
     *      工作机制:
     *      1.一个会话，查询一条数据，这个数据就会放在当前会话的以及缓存中
     *      2.如果关闭会话(注意必须等会话关闭才会存入二级缓存)，一级缓存的数据会被保存到二级缓存中，新的会话查询信息就可以参照二级缓存中的数据
     *      3.sqlSession===EmployeeMapper==》Employee
     *                      DepartmentMapper==》Department
     *       不同的namespace查出的数据放在自己对应的缓存中
     *
     *       使用:
     *       1.开启全局二级缓存
     *       2.去mapper.xml中配置使用二级缓存
     *       3.把POJO视线序列化接口
     *
     * 和缓存有关的设置/属性
     *          1.cacheEnabled设置为false只关闭了二级缓存
     *          2.每个select标签都有useCache选项，在这个里可以关闭二级缓存
     *          3.每个增删改标签的flushCache="true",增删改执行完后就会清空缓存，包括二级缓存
     *          4.查询标签里也有flushCache值默认为false
     *          5.sqlSession.clearCache()方法只会清空一级缓存，二级缓存不受影响
     *          6.localCacheScope：本地缓存作用域，(一级缓存session，当前会话所有数据都会保存在会话中)
     */

    @Test
    public void testFirstLevelCache(){
        SqlSession sqlSession = getSqlSessionFactory().openSession();

        try {
            EmployeeMapper mapper = sqlSession.getMapper(EmployeeMapper.class);
            Employee emp01 = mapper.getEmployeeById(1);
            System.out.println(emp01);
            System.out.println("___________________________________________________________");
            mapper.getEmployeeById(1);
            System.out.println(emp01);
        }finally {
            sqlSession.close();
        }
    }

    @Test
    public void testSecondLevelCache(){
        SqlSession sqlSession = getSqlSessionFactory().openSession();
        SqlSession sqlSession1 = getSqlSessionFactory().openSession();
        try {
           EmployeeMapper mapper = sqlSession.getMapper(EmployeeMapper.class);
           EmployeeMapper mapper1 = sqlSession1.getMapper(EmployeeMapper.class);

           Employee emp01 = mapper.getEmployeeById(1);
           System.out.println(emp01);
           sqlSession.close();
           Employee emp02 = mapper1.getEmployeeById(1);
           System.out.println(emp02);
           sqlSession1.close();
        }finally {

        }
    }

}
