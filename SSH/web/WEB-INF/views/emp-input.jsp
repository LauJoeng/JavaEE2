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
    <script type="text/javascript" src="${pageContext.request.contextPath}/scripts/jquery-3.3.1.js"></script>
    <script type="text/javascript">
        $(function () {
            $(":input[name=lastName]").change(function () {
                var val = $(this).val();
                val = $.trim(val);
                var $this = $(this);
                if(val!==""){
                    $this.nextAll("font").remove();
                    var url = "emp-validateName";
                    var args = {"lastName":val,"time":new Date()};
                    $.post(url,args,function (data) {
                       if(data === "1"){
                           //可用
                           $this.after("<font color='green'>LastName可用</font>");
                       }else if(data === "0"){
                           //表示不可用
                           $this.after("<font color='red'>LastName已存在</font>");
                       }else{
                           //传输错误
                           alert("服务器错误");
                           alert(data);
                       }
                    });
                }else{
                    alert("lastName不能为空");
                    this.focus();
                }
            });
        });
    </script>
</head>
<body>
    <h4>Employee Input Page</h4>
    <s:debug></s:debug>
    <s:form action="emp-save" method="POST">
        <s:textfield name="lastName" label="lastName"></s:textfield>
        <s:textfield name="email" label="email"></s:textfield>
        <s:textfield name="birth" label="birth"></s:textfield>
        <s:select list="#request.departments" listKey="id" listValue="departmentName" name="department.id" lable="department"></s:select>
        <s:submit>Submit</s:submit>
    </s:form>
</body>
</html>
