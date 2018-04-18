<%--
  Created by IntelliJ IDEA.
  User: Yang
  Date: 2018/4/14
  Time: 20:43
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@taglib prefix="fmt" uri="http://java.sun.com/jstl/fmt" %>
<html>
<head>
    <title>Title</title>
</head>
<body>
<h4>this is success page</h4>
time:${requestScope.time}

<br/><br/>

names:${requestScope.names}
<br/><br/>
request user:${requestScope.user}
<br/><br/>
session user:${sessionScope.names}
<br/><br/>
request school:${requestScope.school}
<br/><br/>
session school:${sessionScope.school}

<br/><br/>
<br/><br/>
<fmt:message key="i18n.username"></fmt:message>
<fmt:message key="i18n.password"></fmt:message>
</body>
</html>
