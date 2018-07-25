package com.example.springboo1.bean;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.PropertySource;
import org.springframework.stereotype.Component;

import java.util.Date;
import java.util.List;
import java.util.Map;

/**
 * 将配置文件中配置的每一个属性的值，映射到这个组件中
 * @ConfigurationProperties:告诉Spring Boot将本类中的所有属性和配置文件中相关配置进行绑定
 *      prefix="person":配置文件中哪一个下面的属性进行一一映射
 *
 * 默认从全局配置文件中获取
 *
 *  只有这个组件是容器的组件才能使用上面注解提供的功能
 */
//@PropertySource(value = "classpath:person.properties")
@Component
//@ConfigurationProperties(prefix = "person")
public class Person {

    /**
     * <bean class="Person">
     *      <property name="lastName" value="?> </property>
     * </bean>
     */
    @Value("${person.lastName}")
    private String lastName;
    @Value("#{10/2}")
    private Integer age;
    @Value("true")
    private Boolean boss;
    private Date borth;
    private Dog dog;
    private Map<String,Object>maps;
    private List<Object> list;

    @Override
    public String toString() {
        return "Person{" +
                "lastName='" + lastName + '\'' +
                ", age=" + age +
                ", boss=" + boss +
                ", borth=" + borth +
                ", dog=" + dog +
                ", maps=" + maps +
                ", list=" + list +
                '}';
    }

    public Dog getDog() {
        return dog;
    }

    public void setDog(Dog dog) {
        this.dog = dog;
    }



    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public Integer getAge() {
        return age;
    }

    public void setAge(Integer age) {
        this.age = age;
    }

    public Boolean getBoss() {
        return boss;
    }

    public void setBoss(Boolean boss) {
        this.boss = boss;
    }

    public Date getBorth() {
        return borth;
    }

    public void setBorth(Date borth) {
        this.borth = borth;
    }

    public Map<String, Object> getMaps() {
        return maps;
    }

    public void setMaps(Map<String, Object> maps) {
        this.maps = maps;
    }

    public List<Object> getList() {
        return list;
    }

    public void setList(List<Object> list) {
        this.list = list;
    }

}
