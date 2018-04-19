<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%--
  Created by IntelliJ IDEA.
  User: Yang
  Date: 2018/4/14
  Time: 20:19
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
  <head>
    <title>$Title$</title>
  </head>
  <body>
  <a href="helloworld">HelloWorld</a>
  <br/><br/>
  <a href="springmvc/requestMapping">Test Request Mapping</a>
  <br/><br/>
  <a href="springmvc/testMethod">TestMethod</a>
  <form action="springmvc/testMethod" method="post">
      <input type="submit" value="submit">
  </form>
  <br/><br/>
  <a href="springmvc/testParamsAndHeaders?username=yang&age=101">TesParamsAndHeaders</a>

  <br/><br/>
  <a href="/springmvc/testAntPath/fdsfds/abc">TesAntPath</a>

  <br/><br/>
  <a href="/springmvc/testPathVariable/1">TesPathVariable</a>

  <br/><br/>
  <br/><br/><br/><br/>
  <a href="/springmvc/testRest/1">TesRestGet</a>

  <br/><br/>
  <form action="/springmvc/testRest" method="post">
    <input type="submit" value="TestRest POST">
  </form>
  <br/><br/>
  <form action="/springmvc/testRest/1" method="post">
    <input type="hidden" name="_method" value="DELETE">
    <input type="submit" value="TestRest DELETE">
  </form>
  <br/><br/>
  <form action="/springmvc/testRest/1" method="post">
    <input type="hidden" name="_method" value="PUT">
    <input type="submit" value="TestRest PUT">
  </form>
  <br/><br/>
  <a href="${pageContext.request.contextPath}/springmvc/testRequestParam?username=yang&age=11">testRequestParam</a>

  <br/><br/>
  <a href="/springmvc/testCookieValue">testCookieValue</a>
  <br/><br/>
  <br/><br/>
  <form action="springmvc/testPojo" method="post">
      username:<input type="text" name="username"/>
      <br/>
      password:<input type="password" name="password">
      <br/>
      email:<input type="text" name="email">
      <br/>
      age:<input type="text" name="age">
      <br/>
      city:<input type="text" name="address.city">
      <br/>
      province:<input type="text" name="address.province">
      <br/>
    <input type="submit" value="submit">
  </form>
    <br/><br/>
    <a href="springmvc/testServletAPI">Test ServletAPI</a>
      <br/><br/>
      <a href="/springmvc/testModelAndView">testModelAndView</a>

      <br/><br/>
      <a href="/springmvc/testMap">testMap</a>

      <br/><br/>
      <a href="/springmvc/testSessionAttributes">testSessionAttributes</a>
      <br/><br/><br/><br/>
<hr/>
      <%--
         模拟修改操作
         1.原始数据。1：Tom,123456,tom@123.com ,12
         2.密码不能被修改
         3.表单回显，模拟操作直接在表单写对应属性值
       --%>
        <form action="/springmvc/testModelAttribute" method="post">
            <input type="hidden" name="id" value="1"/>
            username:<<input type="text" name="username" value="Tom">
            <br/>
            email:<input type="text" name="email" value="tom@123.com"/>
            <br/>
            age:<input type="text" name="age" value="12"/>
            <br/>
            <input type="submit" value="submit"/>
        </form>

  <br/><br/><br/>
  <a href="/springmvc/testViewAndViewResolver">testViewAndViewResolver</a>
  <br/>
  <a href="/springmvc/testView">testView</a>
  <br/>
  <a href="/springmvc/testRedirect">testRedirect</a>
  <br/>
  <br/><br/><br/>
  </body>
</html>
