<%@ page import="java.util.Map" %>
<%@ page import="java.util.HashMap" %><%--
  Created by IntelliJ IDEA.
  User: Yang
  Date: 4/21/2018
  Time: 8:33 PM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<head>
    <title>Title</title>
</head>
<body>

    <form action="testConversionServiceConverter" method="post">
        <%--lastname-email-gender-department.id   例如:gg-gg@163.com-102-f-102--%>
        Employee:<input type="text" name="employee"/>
            <input type="submit" value="submit">
    </form>
    <br/>
    <br/>
    <%--使用spring的表单标签，可以更快的开发表单页面，也可以更快的进行表单回显--%>
    <%--注意：可以使用ModelAttribute属性指定绑定的模型属性，若没有指定该属性，则默认从request域对像中读取command
    的表单bean，如果该属性也不存在，会报错--%>
    <form:form action="${pageContext.request.contextPath}/emp" method="post" modelAttribute="employee">

        <c:if test="${employee.id == null}">
            <%--path属性对应html表单标签的name属性值--%>
            LastName:<form:input path="lastName"/><form:errors path="lastName"/><br/>
        </c:if>
        <c:if test="${employee.id != null}">
            <form:hidden path="id"/>
            <%--对于_method不能使用<form:hidden>标签， 为modelAttribute对应的bean中没有_method这个属性--%>
            <%--<form:hidden path="_method" value="put"/>--%>
            <input type="hidden" name="_method" value="PUT"/>
        </c:if>
        Email:<form:input path="email"/><form:errors path="email"/><br/>
        <%
            Map<String,String>genders = new HashMap<>();
            genders.put("f","female");
            genders.put("m","male");
            request.setAttribute("genders",genders);
        %>
        Gender:<form:radiobuttons path="gender" items="${genders}"/><br/>
        Department:<form:select path="department.id" items="${departments}" itemLabel="departmentName" itemValue="id"/><br/>
        <%--
            1.数据类型转换
            2.数据类型格式化
            3.数据校验的问题
            1).如何校验  ：注解
            ①.使用JSR 303验证标准
            ②.加入Hibernate validator验证框架的jar包
            ③.在SpringMVC配置问文件中添加<mvc:annotaion-driven/>
            ④.需要在bean的属性上添加注解
            ⑤在目标方法bean类型前面添加@Valid注解
            2).验证出错转向哪一个页面。
            注意:需要校验的Bean对象和其他绑定的结果对象或错误对象是成对出的，他们之间不允许声明其他入参
            3).错误消息 ？ 如何显示，如何把错误进行国际化
        --%>
        birth:<form:input path="birth"/><form:errors path="birth"/><br/>
        salary:<form:input path="salary"/><br/>
        <input type="submit" value="submit">
    </form:form>
</body>
</html>
