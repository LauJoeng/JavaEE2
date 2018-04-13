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
    <script type="text/javascript" src="${pageContext.request.contextPath}/scripts/jquery-3.3.1.js"></script>
    <script type="text/javascript">
        $(function () {
            //1.点击delete时，弹出确定要删除信息的对话框
            $(".delete").click(function () {
                var lastName = $(this).next(":input").val();
                var flag = confirm("确定要删除"+lastName+"的信息吗?");
                if(flag){
                    //删除，使用Ajax的方式
                    var $tr = $(this).parent().parent();
                    var url = this.href;
                    var args = {"time":new Date()};
                    $.post(url,args,function (data) {
                        //若data的返回值为1，则提示删除成功，且把当前行删除
                        if (data === "1"){
                            alert("删除成功！");
                            $tr.remove();
                        }else{
                            //若data的返回值不是1，提示删除失败
                            alert("删除失败!");
                        }
                    });
                }
                return false;
            });
        });
    </script>
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
                <td>DELETE</td>
                <td>EDIT</td>
            </tr>
            <s:iterator value="#request.employees">
                <tr>
                    <td>${id}</td>
                    <td>${lastName}</td>
                    <td>${email}</td>
                    <td>
                        <s:date name="birth" format="yyyy-MM-dd"/>
                    </td>
                    <td>${department.departmentName}</td>
                    <td>
                        <a href="emp-delete?id=${id}" class="delete">Delete</a>
                        <input type="hidden" value="${lastName}"><%--此处埋一个hidden，方便对话框获取员工姓名，虽然通过父结点子结点关系也可以找到lastName，但这样简便一些--%>
                    </td>
                    <td>
                        <a href="emp-input?id=${id}">EDIT</a>
                    </td>
                </tr>
            </s:iterator>
        </table>
    </s:else>
</body>
</html>
