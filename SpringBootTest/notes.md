# 一、Spring Boot入门

## 1、Spring Boot简介

Spring Boot是由Pivotal团队提供的全新框架，其设计目的是用来简化新Spring应用的初始搭建以及开发过程。该框架使用了特定的方式来进行配置，从而使开发人员不再需要定义样板化的配置。

## 2、微服务

微服务：架构风格（服务微化）

一个应用应该是一组小型服务，可以通过HTTP的方式进行互通

单体应用：ALL IN ONE

微服务：每个功能元素最终都是一个可以独立替换和升级的软件单元

## 3、环境准备

环境约束

- jdk1.8
- maven 3.x :maven3.3以上
- IDEA2017
- SpringBoot 1.5.9RELEASE

### 1、MAVEN设置

```xml
<!-- 配置JDK版本 -->
<profile>    
    <id>jdk18</id>    
    <activation>    
        <activeByDefault>true</activeByDefault>    
        <jdk>1.8</jdk>    
    </activation>    
    <properties>    
        <maven.compiler.source>1.8</maven.compiler.source>    
        <maven.compiler.target>1.8</maven.compiler.target>    
        <maven.compiler.compilerVersion>1.8</maven.compiler.compilerVersion>    
    </properties>     
</profile>
   <!-- 当 nexus-aliyun 下不了的包，或许这个镜像能下，
        才开放它，这个实在太慢，而且要把它放在首位，即 nexus-aliyun 之前，做过测试。
        所以它的用途只有那么一瞬间，就是或许它能下载，可以通过 url 去查找确定一下
    -->
    <!-- <mirror>
        <id>spring-libs-milestone</id>
        <mirrorOf>central</mirrorOf>
        <name>Spring Milestones</name>
        <url>http://repo.spring.io/libs-milestone</url>
    </mirror> -->

    <!-- nexus-aliyun 首选，放第一位,有不能下载的包，再去做其他镜像的选择  -->
    <mirror>
        <id>nexus-aliyun</id>
        <mirrorOf>central</mirrorOf>
        <name>Nexus aliyun</name>
        <url>http://maven.aliyun.com/nexus/content/groups/public</url>
    </mirror>

    <!-- 备选镜像，也是可以通过 url 去查找确定一下，
        该镜像是否含有你想要的包，它比 spring-libs-milestone 快  -->
    <mirror>
        <id>central-repository</id>
        <mirrorOf>*</mirrorOf>
        typor<name>Central Repository</name>
        <url>http://central.maven.org/maven2/</url>
    </mirror>  
```

### 2、IDEA设置

​    配置IDEA的Maven，指定Setting的Maven目录和MAVEN的setting.xml文件

​	快捷键：

​	Ctrl+D 复制一行

​	Ctrl+Y 删除一行

​	Ctrl+P 参数提示

​	Ctrl+Alt+V 自动补齐方法

​	Ctrl+N 查找类方法

​	Alt+Ins 构造器、getter/setter toString

​	Ctrl+O 重载方法提示

​	Alt+Enter 提示导入类etc

​	Shift+F6 :文件重命名

## 4、Spring Boot的Hello World

### 1、创建一个Maven工程



### 2、导入Spring Boot的相关依赖

```xml
<parent>
    <groupId>org.springframework.boot</groupId>
    <artifactId>spring-boot-starter-parent</artifactId>
    <version>2.0.1.RELEASE</version>
    <relativePath/> <!-- lookup parent from repository -->
</parent>

<properties>
    <project.build.sourceEncoding>UTF-8</project.build.sourceEncoding>
    <project.reporting.outputEncoding>UTF-8</project.reporting.outputEncoding>
    <java.version>1.8</java.version>
</properties>

<dependencies>
    <dependency>
        <groupId>org.springframework.boot</groupId>
        <artifactId>spring-boot-starter-web</artifactId>
    </dependency>

    <dependency>
        <groupId>org.springframework.boot</groupId>
        <artifactId>spring-boot-starter-test</artifactId>
        <scope>test</scope>
    </dependency>
</dependencies>

```

### 3、编写个主程序

```java
@SpringBootApplication
public class SpringBoot01HelloQuickApplication {

    public static void main(String[] args) {
        SpringApplication.run(SpringBoot01HelloQuickApplication.class, args);
    }
}
```

### 4、编写相应的Controller和Service

```java
@Controller
public class HelloController {

    @ResponseBody
    @RequestMapping("/hello")
    public  String  hello(){
        return "hello world";
    }
}
```

### 5、运行主程序测试

访问 localhost:8080/hello

### 6、简化部署

在pom.xml文件中，导入build插件

```xml
<build>
    <plugins>
        <plugin>
            <groupId>org.springframework.boot</groupId>
            <artifactId>spring-boot-maven-plugin</artifactId>
        </plugin>
    </plugins>
</build>
```

## 5、HelloWorld深度理解

### 1.POM.xml文件

#### 1、父项目

```xml
<parent>
    <groupId>org.springframework.boot</groupId>
    <artifactId>spring-boot-starter-parent</artifactId>
    <version>2.0.1.RELEASE</version>
    <relativePath/> <!-- lookup parent from repository -->
</parent>
```

这个父项目**spring-boot-starter-parent**又依赖一个父项目

```xml
<parent>
    <groupId>org.springframework.boot</groupId>
    <artifactId>spring-boot-dependencies</artifactId>
    <version>2.0.1.RELEASE</version>
    <relativePath>../../spring-boot-dependencies</relativePath>
</parent>
```

下面有个属性，定义了对应的版本号

```xml
<properties>
    <activemq.version>5.15.3</activemq.version>
    <antlr2.version>2.7.7</antlr2.version>
    <appengine-sdk.version>1.9.63</appengine-sdk.version>
    <artemis.version>2.4.0</artemis.version>
    <aspectj.version>1.8.13</aspectj.version>
    <assertj.version>3.9.1</assertj.version>
    <atomikos.version>4.0.6</atomikos.version>
    <bitronix.version>2.1.4</bitronix.version>
    <build-helper-maven-plugin.version>3.0.0</build-helper-maven-plugin.version>
    。。。。。。。
```



**spring-boot-starter-web:**帮我们导入web模块正常运行所依赖的组件

**spring boot**将所有的功能场景都抽取出来，做成一个个的starter(启动器)，只需要在项目里引入这些starter相关场景的所有依赖都会被导入进来，要用什么功能就导入什么场景的启动器。


@SpringBootApplication:SpringBoot应用标注在某类上说明这个类是SpringBoot的主配置类，SpringBoot应该运行这个类的main方法来启动SpringBoot应用

##使用Spring Initializer快速创建SpringBoot项目

IDE都支持使用Spring的项目向导快速创建一个SpringBoot项目，选择我们需要的模块，向导会联网创建SpringBoot项目默认生成的SpringBoot项目：
- 主程序已经生成好了，我们只要写自己的逻辑
- resource文件夹中的目录结构
 - static：保存所有的静态资源：js，css，images
 - templates:保存所有的模板页面：(SpringBoot默认使用嵌入式的Tomcat，默认不支持JSP页面，可以)可以使用模板引擎(freemarker,thymeleaf)
 - application.properties:SpringBoot应用的配置文件，可以修改一些默认配置

##Spring Boot配置文件

SpringBoot使用一个全局的配置文件，配置文件名字是固定的

application.properties
application.yml

配置文件的作用：修改SpringBoot的自动配置默认值

###配置文件的注入

配置文件

```
person:
    lastName: zhangsan
    age: 19
    boss: false
    borth: 2018/07/25
    maps: {k1: v1,k2: v2}
    list:
      - lisi
      - zhaoliu
    dog:
      name: 小狗
      age: 12
```

javaBean

```
@Component
@ConfigurationProperties(prefix = "person")
public class Person {
    private String lastName;
    private Integer age;
    private Boolean boss;
    private Date borth;
    private Dog dog;
    private Map<String,Object>maps;
    private List<Object> list;

```

@Value和@ConfigurationProperties获取值比较

Name | @ConfigurationProperties | @Value
- | :-: | -: 
功能 | 批量注入配置文件中的属性| 一个个指定 
松散语法 | 支持 | 不支持 
SpEL | 不支持 | 支持
JSR303数据校验 | 支持 | 不支持
复杂类型封装 | 支持 |不支持

如果只是在某个业务逻辑中要获取一下配置文件中的某项值，使用@Value,如果专门编写了一个JavaBean来个配置文件进行映射，就使用@ConfigurationProperties

###@PropertySource和@ImportSource

@PropertySource:加载指定的配置文件
@ImportResource：导入Spring的配置文件，让配置文件里面的内容生效

SpringBoot里面没有Spring配置文件，我们自己编写的配置文件也不能自动识别，想让Spring的配置文件生效，加载进来；@ImportResource标注在一个配置类上

SpringBoot推荐给容器添加组件的方式
1. 配置类===Spring配置文件
2. 是用

```
/**
 * @Configuration: 指明当前类是一个配置类，就是来替代之前的Spring配置文件
 *
 * 在配置文件中用<bean></bean>标签添加组件
 */
@Configuration
public class MyAppConfig {

    /**
     * 将方法的返回值添加到容器中。容器中这个组件默认的id就是方法名
     * @return
     */
    @Bean
    public HelloService helloService(){
        System.out.println("容器添加组件");
        return new HelloService();
    }
}
```


