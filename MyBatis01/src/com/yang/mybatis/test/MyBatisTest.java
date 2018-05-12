package com.yang.mybatis.test;


import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;
import org.junit.Test;

import javax.sql.DataSource;
import java.io.IOException;
import java.io.InputStream;

public class MyBatisTest {

    /**
     * 1.根据xml创建一个SqlSessionFactory对象
     * 2.
     * @throws IOException
     */
    @Test
    public void test() throws IOException {
        String resource = "com/yang/mybatis/config/mybatis-config.xml";
        InputStream inputStream = Resources.getResourceAsStream(resource);
        SqlSessionFactory sqlSessionFactory = new SqlSessionFactoryBuilder().build(inputStream);
    }
}
