package com.yang.springmvc.crud.converter;

import com.yang.springmvc.crud.entity.Department;
import com.yang.springmvc.crud.entity.Employee;
import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;

/**
 * ConversionService是Spring类型转换体系的核心接口。可以利用ConversionServiceFactoryBean在Spring的IOC容器中定义一个
 * ConversionService。Spring将自动识别出IOC容器中的ConversionService，并在bean属性配置及SpringMVC处理方法入参绑定等场合
 * 使用它进行数据的转化
 *
 * 可以通过ConversionServiceFactoryBean的converter属性注册自定义类型转换器
 */

@Component
public class EmployeeConverter implements Converter<String,Employee> {

    @Override
    public Employee convert(String s) {
        if (s != null){
            String[] vals = s.split("-");
            if (vals.length == 4){
                String lastName = vals[0];
                String email = vals[1];
                String gender = vals[2];
                Department department = new Department();
                department.setId(Integer.parseInt(vals[3]));
                Employee employee = new Employee(null,lastName,gender,email,department);
                System.out.println(s + " --convert-- " + employee);
                return employee;
            }
        }
        return null;
    }
}
