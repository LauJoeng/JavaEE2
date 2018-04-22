<%--
  Created by IntelliJ IDEA.
  User: Yang
  Date: 4/21/2018
  Time: 5:24 PM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<head>
    <title>Title</title>
    <%--
        SpringMVC处理静态资源：
        1.为什么会有这个问题
        优雅的REST风格的资源url不希望带.html或.do等后缀，若将DispatcherServlet请求映射配置为/，则SpringMVC将捕获
        WEB容器的所有请求，包括静态资源的请求，SpringmVC会将他们当成一个普通的请求处理，因此找不到处理其器导致错误
        2.解决：在SpringMVC配置文件中配置<mvc:default-servlet-handler/>方式解决静态资源问题
        --%>
    <script type="text/javascript" src="${pageContext.request.contextPath}/scripts/jquery-1.10.1.js"></script>
    <script type="text/javascript">
        $(function () {
            $(".delete").click(function () {
                var href = $(this).attr("href");
                $("form").attr("action",href).submit();
                return false;
            });
        });
    </script>
</head>
<body>

    <form action="" method="post">
        <input type="hidden" name="_method" value="DELETE">
    </form>
    <c:if test="${empty requestScope.employees}">
        没有任何员工信息
    </c:if>
    <c:if test="${!empty requestScope.employees}">
        <table border="1" cellpadding="10" cellspacing="0">
            <tr>
                <th>ID</th>
                <th>lastname</th>
                <th>email</th>
                <th>gender</th>
                <th>department</th>
                <th>Edit</th>
                <th>Delete</th>
            </tr>
            <c:forEach items="${requestScope.employees}" var="emp">
                <tr>
                    <td>${emp.id}</td>
                    <td>${emp.lastName}</td>
                    <td>${emp.email}</td>
                    <td>${emp.gender}</td>
                    <td>${emp.department.departmentName}</td>
                    <td><a href="emp/${emp.id}">Edit</a></td>
                    <td><a class="delete" href="emp/${emp.id}">Delete</a></td>
                </tr>
            </c:forEach>
        </table>
    </c:if>
<br/><br/>
    <a href="emp">Add New Employee</a>
</body>
</html>
