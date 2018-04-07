<%--
  Created by IntelliJ IDEA.
  User: Yang
  Date: 2018/4/7
  Time: 16:56
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@taglib prefix="s" uri="/struts-tags" %>
<html>
<head>
    <title>Title</title>
</head>
<body>
    <h4>Employee List Page</h4>
    <s:if test="#request.employees == null || #request.emloyees.size()==0">
        没有任何员工信息
    </s:if>
    <s:else>
        <table border="1" cellspacing="0" cellpadding="10">
            <tr>
                <td>ID</td>
                <td>LASTNAME</td>
                <td>EMAIL</td>
                <td>BIRTH</td>
                <td>部门</td>
            </tr>
            <s:iterator value="#request.employees">
                <tr>
                    <td>${id}</td>
                    <td>${lastName}</td>
                    <td>${email}</td>
                    <td>${birth}</td>
                    <td>${department.departmentName}</td>
                </tr>
            </s:iterator>
        </table>
    </s:else>
</body>
</html>
