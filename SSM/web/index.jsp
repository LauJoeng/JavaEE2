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
<%--员工修改模态框--%>
<div id="empUpdateModal" class="modal fade" tabindex="-1" role="dialog" aria-labelledby="gridSystemModalLabel">
    <div class="modal-dialog" role="document">
        <div class="modal-content">
            <div class="modal-header">
                <button type="button" class="close" data-dismiss="modal" aria-label="Close"><span aria-hidden="true">&times;</span></button>
                <h4 class="modal-title" >员工修改</h4>
            </div>
            <div class="modal-body">
                <form class="form-horizontal">
                    <div class="form-group">
                        <label for="empName_update_static" class="col-sm-2 control-label">empName</label>
                        <div class="col-sm-10">
                            <p class="form-control-static" id="empName_update_static">email@example.com</p>
                        </div>
                    </div>
                    <div class="form-group">
                        <label for="email_update_input" class="col-sm-2 control-label">email</label>
                        <div class="col-sm-10">
                            <input type="email" name="email" class="form-control" id="email_update_input" placeholder="email@gmail.com">
                            <span  class="help-block"></span>
                        </div>
                    </div>
                    <div class="form-group">
                        <label  class="col-sm-2 control-label">性别</label>
                        <div class="col-sm-10">
                            <div class="radio">
                                <label>
                                    <input type="radio" name="gender" id="gender1_update_input" value="M" checked="checked">
                                    男
                                </label>
                            </div>
                            <div class="radio">
                                <label>
                                    <input type="radio" name="gender" id="gender2_update_input" value="F">
                                    女
                                </label>
                            </div>
                        </div>
                    </div>
                    <div class="form-group">
                        <label for="dept_update_select" class="col-sm-2 control-label">deptName</label>
                        <div class="col-sm-4">
                            <select class="form-control" name="dId" id="dept_update_select">
                            </select>
                        </div>
                    </div>
                </form>
            </div>
            <div class="modal-footer">
                <button type="button" class="btn btn-default" data-dismiss="modal">关闭</button>
                <button type="button" class="btn btn-primary" id="emp_update_btn">更新</button>
            </div>
        </div><!-- /.modal-content -->
    </div><!-- /.modal-dialog -->
</div><!-- /.modal -->

<%--员工添加的弹出模态框--%>
<div id="empAddModel" class="modal fade" tabindex="-1" role="dialog" aria-labelledby="gridSystemModalLabel">
    <div class="modal-dialog" role="document">
        <div class="modal-content">
            <div class="modal-header">
                <button type="button" class="close" data-dismiss="modal" aria-label="Close"><span aria-hidden="true">&times;</span></button>
                <h4 class="modal-title" id="gridSystemModalLabel">员工添加</h4>
            </div>
            <div class="modal-body">
                <form class="form-horizontal">
                    <div class="form-group">
                        <label for="empName_add_input" class="col-sm-2 control-label">empName</label>
                        <div class="col-sm-10">
                            <input type="text" name="empName" class="form-control" id="empName_add_input" placeholder="empName">
                            <span  class="help-block"></span>
                        </div>
                    </div>
                    <div class="form-group">
                        <label for="email_add_input" class="col-sm-2 control-label">email</label>
                        <div class="col-sm-10">
                            <input type="email" name="email" class="form-control" id="email_add_input" placeholder="email@gmail.com">
                            <span  class="help-block"></span>
                        </div>
                    </div>
                    <div class="form-group">
                        <label  class="col-sm-2 control-label">性别</label>
                        <div class="col-sm-10">
                            <div class="radio">
                                <label>
                                    <input type="radio" name="gender" id="gender1_add_input" value="M" checked="checked">
                                    男
                                </label>
                            </div>
                            <div class="radio">
                                <label>
                                    <input type="radio" name="gender" id="gender2_add_input" value="F">
                                    女
                                </label>
                            </div>
                        </div>
                    </div>
                    <div class="form-group">
                        <label for="dept_add_select" class="col-sm-2 control-label">deptName</label>
                        <div class="col-sm-4">
                            <select class="form-control" name="dId" id="dept_add_select">
                            </select>
                        </div>
                    </div>
                </form>
            </div>
            <div class="modal-footer">
                <button type="button" class="btn btn-default" data-dismiss="modal">关闭</button>
                <button type="button" class="btn btn-primary" id="emp_save_btn">保存</button>
            </div>
        </div><!-- /.modal-content -->
    </div><!-- /.modal-dialog -->
</div><!-- /.modal -->

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
            <button class="btn btn-primary" id="emp_add_modal_btn">新增</button>
            <button class="btn btn-danger" id="emp_delete_all_btn">删除</button>
        </div>
    </div>
    <%--显示表格信息--%>
    <div class="row">
        <div class="col-lg-12">
            <table class="table table-hover" id="emps_table">
                <thead>
                    <tr>
                        <th>
                            <input type="checkbox" id="check_all"/>
                        </th>
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
    var totalRecourd,currentPage;
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
            var checkBoxTd = $("<td><input type='checkbox' class='check_item'/></td>")
            var empIdTd = $("<td></td>").append(item.empId);
            var empNameId = $("<td></td>").append(item.empName);
            var empGenderTd = $("<td></td>").append(item.gender === 'M' ? "男" : "女");
            var empEmailTd = $("<td></td>").append(item.email);
            var empDeptNameTd = $("<td></td>").append(item.department.deptName);
        // <button class="btn btn-primary btn-sm">
            //         <span class="glyphicon glyphicon-pencil" aria-hidden="true"></span>
            //     编辑
            //     </button>
            var editBtn = $("<button></button>").addClass("btn btn-primary btn-sm edit_btn")
                .append($("<span></span>").addClass("glyphicon glyphicon-pencil")).append("编辑");
            //为编辑按钮添加一个自定义的属性，方便取出员工id
            editBtn.attr("edit-id",item.empId);
            var delBtn = $("<button></button>").addClass("btn btn-danger btn-sm delete_btn")
                .append($("<span></span>").addClass("glyphicon glyphicon-trash")).append("删除");
            //为删除按钮添加一个自定义的属性，来表示当前删除的员工id
            delBtn.attr("del-id",item.empId);
            var btnTd = $("<td></td>").append(editBtn).append(" ").append(delBtn);
            $("<tr></tr>")
                .append(checkBoxTd)
                .append(empIdTd)
                .append(empNameId)
                .append(empGenderTd)
                .append(empEmailTd)
                .append(empDeptNameTd)
                .append(btnTd)
                .appendTo("#emps_table:last");
        })
    }
    //解析显示分页信息
    function build_page_info(result) {
        $("#page_info_area").empty();
        $("#page_info_area").append("当前"+result.extend.pageInfo.pageNum+"页," +
            "总共"+result.extend.pageInfo.pages+" 页,总共"+result.extend.pageInfo.total+"记录");
        totalRecourd = result.extend.pageInfo.total;
        currentPage = result.extend.pageInfo.pageNum
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

    //清空表单样式及内容
    function reset_form(ele){
        $(ele)[0].reset();
        //清空表单样式
        $(ele).find("*").removeClass("has-error has-success");
        $(ele).find(".help-block").text("");
    }

    //点击新增按钮弹出模态框
    $("#emp_add_modal_btn").click(function () {
        reset_form("#empAddModel form");
        //发送ajax请求，查出部门信息，显示则下拉列表中
        getDepts("#empAddModel select");
        $('#empAddModel').modal({
            backdrop:"static"
        });
    });

    //查出所有部门信息
    function getDepts(ele) {
        //清空下拉列表的值
        $(ele).empty();

        $.ajax({
            url:"${pageContext.request.contextPath}/depts",
            type:"GET",
            async:false,
            success:function (result) {
                console.log(result);
                $.each(result.extend.depts,function () {
                    var optionEle = $("<option></option>").append(this.deptName).attr("value",this.id);
                    optionEle.appendTo(ele);
                })
            }
        });
    }

    function validate_add_form(){
        //1.拿到要校验的数据，是用正则表达式
        var empName = $("#empName_add_input").val();
        var regName = /(^[a-zA-Z0-9_-]{6,16}$)|(^[\u2E80-\u9FFF]{2,5}$)/;
        if(!regName.test(empName)){
            // alert("用户名为2-5位中文或者6-16位英文数字的组合");
            show_validate_msg("#empName_add_input","error","用户名为2-5位中文或者6-16位英文数字的组合");
            return false;
        }else{
            show_validate_msg("#empName_add_input","success","");
        }
        var email = $("#email_add_input").val();
        var regEmail = /^([a-z0-9_\.-]+)@([\da-z\.-]+)\.([a-z\.]{2,6})$/;
        if(!regEmail.test(email)){
            // alert("邮箱格式不正确");
            //每次显示数据之前都应该清空之前的样式
            show_validate_msg("#email_add_input","error","邮箱格式不正确");
            return false;
        }else{
            show_validate_msg("#email_add_input","success","");
        }
        return true;
    }

    function show_validate_msg(ele,status,msg){
        //清除当前元素校验状态
        $(ele).parent().removeClass("has-success has-error");
        $(ele).next("span").text("");
        if("success" === status){
            $(ele).parent().addClass("has-success");
            $(ele).next("span").text(msg);
        }else if("error" === status){
            $(ele).parent().addClass("has-error");
            $(ele).next("span").text(msg);
        }

    }

    $("#empName_add_input").change(function () {
        //发送ajax请求校验用户名是否可用
        var empName = this.value;
        $.ajax({
            url:"${pageContext.request.contextPath}/checkuser",
            data:"empName="+empName,
            type:"POST",
            success:function (result) {
                if(result.code === 100){
                    show_validate_msg("#empName_add_input","success","用户名可用");
                    $("#emp_save_btn").attr("ajax-va","success");
                }else{
                    show_validate_msg("#empName_add_input","error",result.extend.val_msg);
                    $("#emp_save_btn").attr("ajax-va","error");
                }
            }
        });
    });

    //点击保存，保存员工
    $("#emp_save_btn").click(function () {
        //先对提交给服务器的数据进行校验
        if(!validate_add_form()){
            return false;
        }
        //先判断之前的ajax校验是否成功
        if($(this).attr("ajax-va") === "error"){
            return false;
        }
        //将模态框填写的表单数据提交给服务器进行保存
        $.ajax({
            url:"${pageContext.request.contextPath}/emp",
            type:"POST",
            data:$("#empAddModel form").serialize(),
            success:function (result) {
                // alert(result.msg);
                if(result.code === 100){
                    //保存成功后要关闭模态框并跳转到最后一页查看新添加的数据
                    $("#empAddModel").modal('hide');
                    to_page(totalRecourd);/*pageHelper插件会自动调整成合理的数组，跳转到最后一页*/
                }else{
                    //显示失败信息
                    console.log(result);
                    if(undefined === result.extend.errorFields.email){
                        //显示邮箱错误信息
                        show_validate_msg("#email_add_input","error",result.extend.errorFields.email);
                    }
                    if(undefined === result.extend.errorFields.empName){
                        show_validate_msg("#empName_add_input","error",result.extend.errorFields.empName)
                    }
                }

            }
        });
    });
    /*此处form的serialize方法时jQuery提供的快速获取表单并处理成 “key=value”字符串形式*/


    //点击员工编辑按钮，修改员工信息
    //.on方法可以让后面加载出来的组件也能监听到事件
    $(document).on("click",".edit_btn",function () {
        // alert("sss");
        //查询部门信息
        getDepts("#empUpdateModal select");
        //查出员工信息，显示员工信息
        getEmp($(this).attr("edit-id"));
        //把员工id传递给模态框的更新按钮
        $("#emp_update_btn").attr("edit-id",$(this).attr("edit-id"));
        $("#empUpdateModal").modal({
            backdrop:"static"
        });
    });

    //点击当删除
    $(document).on("click",".delete_btn",function () {
        //弹出确认删除对话框
        var empName = $(this).parents("tr").find("td:eq(2)").text();
        var empId = $(this).attr("del-id");
        if(confirm("确认删除【"+empName+"】吗?")){
            //确认发送Ajax请求
            $.ajax({
                url:"${pageContext.request.contextPath}/emp/"+empId,
                type:"DELETE",
                success:function (result) {
                    alert(result.msg);
                    to_page(currentPage);
                }
            });
        }
        // alert($(this).parents("tr").find("td:eq(1)").text());
    });

    $(document).on("click",".check_item",function () {

    });

    //点击全部删除
    $("#emp_delete_all_btn").click(function () {
        var empNames = "";
        var del_idstr="";
        $.each($(".check_item:checked"),function () {
            empNames += $(this).parents("tr").find("td:eq(2)").text()+",";
            //组装装员工id字符串
            del_idstr += $(this).parents("tr").find("td:eq(1)").text()+"-";
        });
        //去除empNames多余的逗号
        empNames = empNames.substring(0,empNames.length-1);
        del_idstr = del_idstr.substring(0,del_idstr.length-1);
        if(confirm("确认删除【"+empNames+"】吗？")){
            $.ajax({
                url:"${pageContext.request.contextPath}/emp/"+del_idstr,
                type:"DELETE",
                success:function (result) {
                    alert(result.msg);
                    to_page(currentPage);
                }
            });
        }
    });

    //获取员工信息
    function getEmp(id){
        $.ajax({
            url:"${pageContext.request.contextPath}/emp/"+id,
            type:"GET",
            success:function (result) {
                console.log(result);
                var empData = result.extend.emp;
                // console.log($("#empUpdateModal select").val()  +"::::"+empData.dId);

                //此处有一个问题，前面的获取部门函数和这个获取员工的函数是异步执行，可能会发生部门还没加载出来员工已经加载了
                //，所以在设置部门下拉框时会找不到下拉框,可以给获取部门这个方法的ajax请求async属性设置为false，即不让它
                //异步执行，想到的一个解决办法
                $("#empName_update_static").text(empData.empName);
                $("#email_update_input").val(empData.email);
                $("#empUpdateModal input[name=gender]").val([empData.gender]);
                $("#empUpdateModal select").val([empData.dId]);
            }
        });
    }

    $("#emp_update_btn").click(function () {
        // alert("sdd")
        //验证邮箱是否合法
        var email = $("#email_update_input").val();
        var regEmail = /^([a-zA-Z0-9_\.-]+)@([\da-z\.-]+)\.([a-z\.]{2,6})$/;
        if(!regEmail.test(email)){
            // alert("邮箱格式不正确");
            //每次显示数据之前都应该清空之前的样式
            show_validate_msg("#email_update_input","error","邮箱格式不正确");
            return false;
        }else{
            show_validate_msg("#email_update_input","success","");
        }
        //发送ajax请求更新员工信息
        $.ajax({
            url:"${pageContext.request.contextPath}/emp/"+$(this).attr("edit-id"),
            type:"PUT",
            data:$("#empUpdateModal form").serialize(),
            success:function (result) {
                //1关闭对话框
                $("#empUpdateModal").modal("hide");
                to_page(currentPage)
            }
        });
    });
    
</script>
</body>
</html>
