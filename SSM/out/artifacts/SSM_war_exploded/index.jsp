<%--
  Created by IntelliJ IDEA.
  User: Yang
  Date: 2018/6/8
  Time: 20:50
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<html>
<head>
    <title>员工列表</title>
    <%--web路径
       不以 / 开始的相对路径，以当前资源的路径为基准，经常容易出问题
       以 / 开始的相对路径，找资源，以服务器的路径为标准
   --%>
    <%--<link href="${pageContext.request.contextPath}/static/bootstrap-3.3.7-dist/css/bootstrap.min.css" rel="stylesheet">--%>
    <%--<link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/4.0.0/css/bootstrap.min.css" integrity="sha384-Gn5384xqQ1aoWXA+058RXPxPg6fy4IWvTNh0E263XmFcJlSAwiGgFAW/dAiS6JXm" crossorigin="anonymous">
    <script type="text/javascript" src="https://cdn.bootcss.com/jquery/1.12.4/jquery.min.js"></script>
    <!-- 加载 Bootstrap 的所有 JavaScript 插件。你也可以根据需要只加载单个插件。 -->
    <script src="https://cdn.bootcss.com/bootstrap/3.3.7/js/bootstrap.min.js"></script>--%>

    <!-- jQuery (Bootstrap 的所有 JavaScript 插件都依赖 jQuery，所以必须放在前边) -->
    <script src="https://cdn.bootcss.com/jquery/1.12.4/jquery.min.js"></script>

    <!-- 最新的 Bootstrap 核心 JavaScript 文件 -->
    <script src="https://cdn.bootcss.com/bootstrap/3.3.7/js/bootstrap.min.js" integrity="sha384-Tc5IQib027qvyjSMfHjOMaLkfuWVxZxUPnCJA7l2mCWNIpG9mGCD8wGNIcPD7Txa" crossorigin="anonymous"></script>

    <!-- 最新版本的 Bootstrap 核心 CSS 文件 -->
    <link rel="stylesheet" href="https://cdn.bootcss.com/bootstrap/3.3.7/css/bootstrap.min.css" integrity="sha384-BVYiiSIFeK1dGmJRAkycuHAHRg32OmUcww7on3RYdg4Va+PmSTsz/K68vbdEjh4u" crossorigin="anonymous">
</head>
<body>
<div class="container">
    <%--标题--%>
    <div class="row">
        <div class="col-lg-12">
            <h1>SSM-CRUD</h1>
        </div>
    </div>
    <%--按钮--%>
    <div class="row">
        <div class="col-md-4 col-md-offset-10">
            <button class="btn btn-primary">新增</button>
            <button class="btn btn-danger">删除</button>
        </div>
    </div>
    <%--显示表格信息--%>
    <div class="row">
        <div class="col-lg-12">
            <table class="table table-hover" id="emps_table">
                <thead>
                    <tr>
                        <th>#</th>
                        <th>empName</th>
                        <th>gender</th>
                        <th>email</th>
                        <th>deptName</th>
                        <th>操作</th>
                    </tr>
                </thead>
                <tbody>

                </tbody>
            </table>
        </div>
    </div>
    <%--显示分页信息--%>
    <div class="row">
        <%--分页文字信息--%>
        <div  class="col-lg-6" id="page_info_area">
        </div>
        <%--分页条--%>
        <div  class="col-md-6" id="page_nav_area">

        </div>
    </div>
</div>
<script type="text/javascript">
    //页面加载完成后，直接发ajax请求，拿到分页信息
    $(function () {
       to_page(1);
    });
    
    function to_page(pn) {
        $.ajax({
            url:"${pageContext.request.contextPath}/emps",
            data:"pn="+pn,
            type:"GET",
            success:function (result) {
                console.log(result);
                //1.解析并显示员工数据
                build_emps_table(result);
                //2.解析并显示分页数据
                build_page_info(result);
                build_page_nav(result);
            }
        })
    }
    
    function build_emps_table(result) {
        //清空table表格
        $("#emps_table").empty();
        var emps = result.extend.pageInfo.list;
        $.each(emps,function (index,item) {
            var empIdTd = $("<td></td>").append(item.empId);
            var empNameId = $("<td></td>").append(item.empName);
            var empGenderTd = $("<td></td>").append(item.gender === '男' ? "男" : "女");
            var empEmailTd = $("<td></td>").append(item.email);
            var empDeptNameTd = $("<td></td>").append(item.department.deptName);
        // <button class="btn btn-primary btn-sm">
            //         <span class="glyphicon glyphicon-pencil" aria-hidden="true"></span>
            //     编辑
            //     </button>
            var editBtn = $("<button></button>").addClass("btn btn-primary btn-sm")
                .append($("<span></span>").addClass("glyphicon glyphicon-pencil")).append("编辑");
            var delBtn = $("<button></button>").addClass("btn btn-danger btn-sm")
                .append($("<span></span>").addClass("glyphicon glyphicon-trash")).append("删除");
            var btnTd = $("<td></td>").append(editBtn).append(" ").append(delBtn);
            $("<tr></tr>").append(empIdTd)
                .append(empNameId)
                .append(empGenderTd)
                .append(empEmailTd)
                .append(empDeptNameTd)
                .append(btnTd)
                .appendTo("#emps_table");
        })
    }
    //解析显示分页信息
    function build_page_info(result) {
        $("#page_info_area").empty();
        $("#page_info_area").append("当前"+result.extend.pageInfo.pageNum+"页," +
            "总共"+result.extend.pageInfo.pages+" 页,总共"+result.extend.pageInfo.total+"记录");
    }
    //解析显示分页条,点击分页要能响应动作
    function build_page_nav(result) {
        $("#page_nav_area").empty();
        var ul = $("<ul></ul>").addClass("pagination");
        var firstPageLi = $("<li></li>").append($("<a></a>").append("首页").attr("href","#"));
        var prepageLi = $("<li></li>").append($("<a></a>").append("&laquo;"));
        if(!result.extend.pageInfo.hasPreviousPage){
            firstPageLi.addClass("disabled");
            prepageLi.addClass("disabled");
        }else{
            //为元素添加点击翻页事件
            firstPageLi.click(function () {
                to_page(1);
            });
            prepageLi.click(function () {
                to_page(result.extend.pageInfo.pageNum-1)
            });
        }
        var nextpageLi = $("<li></li>").append($("<a></a>").append("&raquo;"));
        var lastPageLi = $("<li></li>").append($("<a></a>").append("末页").attr("href","#"));
        if(!result.extend.pageInfo.hasNextPage){
            nextpageLi.addClass("disabled");
            lastPageLi.addClass("disabled");
        }else{
            nextpageLi.click(function () {
                to_page(result.extend.pageInfo.pageNum+1);
            });
            lastPageLi.click(function () {
                to_page(result.extend.pageInfo.pages);
            });
        }
        //添加首页和前一页的提示
        ul.append(firstPageLi).append(prepageLi);
        //遍历个ul中添加页码提示
        $.each(result.extend.pageInfo.navigatepageNums,function (index,item) {
            var numLi = $("<li></li>").append($("<a></a>").append(item));
            if(result.extend.pageInfo.pageNum === item){
                numLi.addClass("active");
            }
            numLi.click(function () {
                to_page(item);
            });
            ul.append(numLi);
        });
        //给ul添加下一页和末页的提示
        ul.append(nextpageLi).append(lastPageLi);
        var navEle = $("<nav></nav>").append(ul);
        navEle.appendTo("#page_nav_area");
    }
</script>
</body>
</html>
