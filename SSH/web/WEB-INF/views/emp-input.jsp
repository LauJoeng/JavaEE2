<%--
  Created by IntelliJ IDEA.
  User: Yang
  Date: 2018/4/8
  Time: 20:01
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@taglib prefix="s" uri="/struts-tags" %>
<html>
<head>
    <title>Title</title>
</head>
<body>
    <h4>Employee Input Page</h4>
    <s:form action="emp-save" method="POST">
        <s:textfield name="lastName" label="lastName"></s:textfield>
        <s:textfield name="email" label="email"></s:textfield>
        <s:textfield name="birth" label="birth"></s:textfield>
        <s:select list="#request.departments" listKey="id" listValue="departmentName" name="department.id" lable="department"></s:select>
        <s:submit>Submit</s:submit>
    </s:form>
</body>
</html>
