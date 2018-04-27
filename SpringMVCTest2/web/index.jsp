<%--
  Created by IntelliJ IDEA.
  User: Yang
  Date: 4/21/2018
  Time: 5:12 PM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>

<html>
  <head>
    <title>$Title$</title>
    <script type="text/javascript" src="scripts/jquery-1.10.1.js"></script>
    <script type="text/javascript">
      $(function () {
         $(".testJson").click(function () {
             var url = this.href;
             var args = {};
             $.post(url,args,function (data) {
                 alert(data.length);
                 for(var i=0; i < data.length;i++){
                     var id = data[i].id;
                     var lastName = data[i].lastName;
                     alert(id + " : " + lastName);
                 }
             });
             return  false;
         });
      });
    </script>
  </head>
  <body>
  <a href="emps">List All Employees</a><br/><br/>
  <a href="testJson" class="testJson">Test Json</a><br/><br/>

  <form action="testHttpMessageConverter" method="post" enctype="multipart/form-data">
    File:<input type="file" name="file"/>
    Desc:<input type="text" name="desc"/>
    <input type="submit" value="submit"/>
  </form>
  <br/><br/>
  <a href="${pageContext.request.contextPath}/testResponseEntity">testResponseEntity</a>

  <%--
      关于国际化:
      1.在页面上能够根据浏览器语言设置的情况对文本(不是内容)，时间，数值进行本地化处理
      2.可以在bean中获取国际化资源文件Local对应的消息
      3.可以通过超链接切换Local，而不再依赖于浏览器语言的设置

      解决:
      1.使用JSTL的fmt标签
      2.在bean中注入ResourceBundelMessageSource的示例，使用其对应的getMessage方法即可
      3.配置LocaReslover和LocalChangeInterceptor

      --%>

  <br/><br/>
  <a href="i18n">I18N Page</a>
  </body>
  <br/><br/>
  <form action="testFileUpload" method="post" enctype="multipart/form-data">
    File:<input type="file" name="file"/>
    Desc:<input type="text" name="desc"/>
    <input type="submit" value="submit"/>
  </form>

  <br/><br/>
  <a href="/testExceptionHandlerExceptionResolver?i=10">testExceptionHandlerExceptionResolver</a>
</html>
