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
    <%--使用spring的表单标签，可以更快的开发表单页面，也可以更快的进行表单回显--%>
    <%--注意：可以使用ModelAttribute属性指定绑定的模型属性，若没有指定该属性，则默认从request域对像中读取command
    的表单bean，如果该属性也不存在，会报错--%>
    <form:form action="${pageContext.request.contextPath}/emp" method="post" modelAttribute="employee">
        <c:if test="${employee.id == null}">
            <%--path属性对应html表单标签的name属性值--%>
            LastName:<form:input path="lastName"/><br/>
        </c:if>
        <c:if test="${employee.id != null}">
            <form:hidden path="id"/>
            <%--对于_method不能使用<form:hidden>标签， 为modelAttribute对应的bean中没有_method这个属性--%>
            <%--<form:hidden path="_method" value="put"/>--%>
            <input type="hidden" name="_method" value="PUT"/>
        </c:if>
        Email:<form:input path="email"/><br/>
        <%
            Map<String,String>genders = new HashMap<>();
            genders.put("f","female");
            genders.put("m","male");
            request.setAttribute("genders",genders);
        %>
        Gender:<form:radiobuttons path="gender" items="${genders}"/><br/>
        Department:<form:select path="department.id" items="${departments}" itemLabel="departmentName" itemValue="id"/><br/>
        <input type="submit" value="submit">
    </form:form>
</body>
</html>
