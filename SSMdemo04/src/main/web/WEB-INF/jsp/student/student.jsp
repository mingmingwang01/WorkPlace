<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Title</title>
    <%@include file="../basic.jsp"%>
    <script type="text/javascript">
        $(function () {
            //单条删除学生
            $("button[name=deleteStudentButton]").click(function () {
                var studentId=$(this).attr("studentId");
                $.post("${pageContext.request.contextPath}/student/deleteStudentList",
                    {"ids":studentId},function (data) {
                        alert(data.msg);
                        location.href=location.href;
                    });
            })

            //批量删除
            $("#deleteStudentByIdsButton").click(function () {
                var studentIds="";
                $.each($("input:checkbox"),function () {
                    if(this.checked){
                        studentIds=studentIds+$(this).val()+",";
                    }
                })
                if(studentIds==""){
                    alert("请选择要删除的学生");
                    return;
                }
                if(confirm("确定要删除吗？")){
                    $.post("${pageContext.request.contextPath}/student/deleteStudentList",
                        {"ids":studentIds},function (data) {
                            alert(data.msg);
                            location.href=location.href;
                        })
                }
            })

            //展示添加学生模态框
            $("#addStudentButton").click(function () {
                //加载班级
                showGrade($("#addStudentSelect"),null);
                $("#addStudentModal").modal("show");
            })

            //保存添加学生信息按钮
            $("#addStudentSaveButton").click(function () {
                var gradeName=$("#addStudentSelect").find("option:selected").text();
                $("#addGradeName").val(gradeName);

                //取from表单中的值
                var student=$("#addStudentForm").serialize();

                $.post("${pageContext.request.contextPath}/student/addStudent",
                    student,function (data) {
                        alert(data.msg);
                        $("#addStudentModal").modal("hide");
                        location.href=location.href;
                    })
            })

            //学生详情
            $("button[name=queryStudentButton]").click(function () {
                //查询学生信息
                var studentId=$(this).attr("studentId");
                $.get("${pageContext.request.contextPath}/student/queryStudentById",
                    {"studentId":studentId},function (data) {
                        $("#studentName").html(data.studentName);
                        $("#studentNum").html(data.studentNum);
                        $("#age").html(data.age);
                        $("#gender").html(data.gender);
                        $("#gradeName").html(data.grade.gradeName);
                    })
                //展示学生详情模态框
                $("#queryStudentModal").modal("show");
            })


        //修改按钮单击事件 展示修改学生模态框
        $("button[name=updateStudentButton]").click(function () {
            var studentId=$(this).attr("studentId");
            //1. 获取学员信息
            $.get("${pageContext.request.contextPath}/student/queryStudentById",
                {"studentId":studentId},function (data) {
                    //给studentForm赋值
                    $("#updateStudentId").val(studentId);
                    $("#updateStudentName").val(data.studentName);
                    $("#updateStudentNum").val(data.studentNum);
                    $("#updateAge").val(data.age);
                    $("#updateStudentGender").val(data.gender);
                    //2. 加载班级信息
                    showGrade($("#updateStudentSelect"),data.grade.id);
                })
            //3. 将学员信息展示在模态框中
            $("#updateStudentModal").modal("show");
        })

        //保存修改学生按钮
        $("#saveUpdateStudentButton").click(function () {
            var student=$("#updateStudentForm").serialize();
            $.post("${pageContext.request.contextPath}/student/updateStudent",
                student,function (data) {
                    alert(data.msg);
                    $("#updateStudentModal").modal("hide");
                    location.href = location.href;
                })
        })
        })

        //班级信息  查询所有班级信息
        function showGrade(ele,gradeId) {
            //清空下拉列表之前的内容
            ele.empty();
            //1.获取所有班级的信息
            $.get("${pageContext.request.contextPath}/student/queryAllGrade",function (data) {
                for(var i=0;i<data.length;i++){
                    var option="<option value='"+data[i].id+"'>"+data[i].gradeName+"</option>";
                    ele.append(option);
                }
                if(null!=gradeId){
                    ele.val(gradeId);
                }
            })
        }
    </script>
</head>


<body>
<%@include file="../top.jsp"%>
<div class="container">
    <div class="row">
        <%@include file="../left.jsp"%>
        <div class="col-lg-2 col-lg-offset-2">
            <h3>学生管理</h3>
        </div>
        <div class="col-lg-4 col-lg-offset-2">
            <button class="btn btn-danger" id="deleteStudentByIdsButton">
                <span class="glyphicon glyphicon-trash" aria-hidden="true"></span>
                批量删除
            </button>
            <button class="btn btn-info" id="addStudentButton">
                <span class="glyphicon glyphicon-plus" aria-hidden="true"></span>
                添加
            </button>
        </div>

    </div>
    <div class="row">
        <div class="col-lg-8 col-lg-offset-2">
            <table class="table table-striped">
                <tr>
                    <td></td>
                    <td>学生id</td>
                    <td>姓名</td>
                    <td>性别</td>
                    <td>年龄</td>
                    <td>学生编号</td>
                    <td>班级</td>
                    <td>操作</td>
                </tr>
                <c:forEach items="${pageInfo.list}" var="student">
                    <tr>
                        <td>
                            <input type="checkbox" name="studentCheckBox" value="${student.id}"/>
                        </td>
                        <td>${student.id}</td>
                        <td>${student.studentName}</td>
                        <td>${student.gender}</td>
                        <td>${student.age}</td>
                        <td>${student.studentNum}</td>
                        <td>${student.grade.gradeName}</td>
                        <td>
                           <button class="btn btn-info" name="queryStudentButton"  studentId="${student.id}">详情</button>
                           <button class="btn btn-info" name="updateStudentButton"  studentId="${student.id}">修改</button>
                            <button class="btn btn-danger" name="deleteStudentButton"  studentId="${student.id}">删除</button>
                        </td>
                    </tr>
                </c:forEach>
            </table>
        </div>
    </div>
    <%@include file="../page.jsp"%>

    <!--添加学生模态框-->
    <div class="modal fade" tabindex="-1" role="dialog" id="addStudentModal">
        <div class="modal-dialog" role="document">
            <div class="modal-content">
                <div class="modal-header">
                    <button type="button" class="close" data-dismiss="modal" aria-label="Close">
                        <span aria-hidden="true">&times;</span>
                    </button>
                    <h4 class="modal-title">添加学生</h4>
                </div>
                <div class="modal-body">
                   <form class="form-horizontal" id="addStudentForm">
                       <div class="form-group">
                         <label class="col-sm-3 control-label">学生姓名：</label>
                           <div class="col-sm-8">
                             <input type="text" class="form-control" name="studentName" placeholder="学生姓名">
                           </div>
                        </div>
<%--                       <div class="form-group">
                           <label  class="col-sm-3 control-label">学生编号：</label>
                           <div class="col-sm-8">
                               <input type="text" class="form-control" name="studentNum" placeholder="学生编号">
                           </div>
                       </div>--%>
                        <div class="form-group">
                            <label  class="col-sm-3 control-label">年龄：</label>
                            <div class="col-sm-8">
                                <input type="number" min="1" class="form-control" name="age" >
                            </div>
                        </div>
                        <div class="form-group">
                            <label  class="col-sm-3 control-label">性别：</label>
                            <div class="col-sm-8">
                                <select class="form-control" name="gender" >
                                    <option value="男">男</option>
                                    <option value="女">女</option>
                                </select>
                            </div>
                        </div>
                        <div class="form-group">
                            <label  class="col-sm-3 control-label">班级：</label>
                            <div class="col-sm-8">
                                <select class="form-control" name="grade.id" id="addStudentSelect">
                                </select>
                                <input type="hidden" name="grade.gradeName" id="addGradeName">
                            </div>
                        </div>
                    </form>
                </div>
                <div class="modal-footer">
                    <button type="button" class="btn btn-default" data-dismiss="modal">关闭</button>
                    <button type="button" class="btn btn-primary" id="addStudentSaveButton">保存</button>
                </div>
            </div><!-- /.modal-content -->
        </div><!-- /.modal-dialog -->
    </div><!-- /.modal -->

    <!--修改学生模态框-->
    <div class="modal fade" tabindex="-1" role="dialog" id="updateStudentModal">
        <div class="modal-dialog" role="document">
            <div class="modal-content">
                <div class="modal-header">
                    <button type="button" class="close" data-dismiss="modal" aria-label="Close"><span aria-hidden="true">&times;</span></button>
                    <h4 class="modal-title">修改学生</h4>
                </div>
                <div class="modal-body">
                    <form class="form-horizontal" id="updateStudentForm">
                        <div class="form-group">
                            <label class="col-sm-3 control-label">学生姓名：</label>
                            <div class="col-sm-9">
                                <input type="text" class="form-control"  name="studentName" id="updateStudentName" >
                                <input type="hidden" name="id" id="updateStudentId">
                            </div>
                        </div>
                        <div class="form-group">
                            <label  class="col-sm-3 control-label">学生编号：</label>
                            <div class="col-sm-9">
                                <input type="text" class="form-control" name="studentNum" id="updateStudentNum" readonly>
                            </div>
                        </div>
                        <div class="form-group">
                            <label  class="col-sm-3 control-label">学生年龄：</label>
                            <div class="col-sm-9">
                                <input type="number" class="form-control"  name="age" min="1" id="updateAge">
                            </div>
                        </div>
                        <div class="form-group">
                            <label  class="col-sm-3 control-label">学生性别：</label>
                            <div class="col-sm-9">
                                <select class="form-control" name="gender" id="updateStudentGender">
                                    <option value="男" >男</option>
                                    <option value="女">女</option>
                                </select>
                            </div>
                        </div>
                        <div class="form-group">
                            <label  class="col-sm-3 control-label">学生班级：</label>
                            <div class="col-sm-9">
                                <select class="form-control" name="grade.id" id="updateStudentSelect">
                                </select>
                            </div>
                        </div>
                    </form>
                </div>
                <div class="modal-footer">
                    <button type="button" class="btn btn-default" data-dismiss="modal">关闭</button>
                    <button type="button" class="btn btn-primary" id="saveUpdateStudentButton">保存</button>
                </div>
            </div><!-- /.modal-content -->
        </div><!-- /.modal-dialog -->
    </div><!-- /.modal -->


    <!--学生详情模态框-->
    <div class="modal fade" tabindex="-1" role="dialog" id="queryStudentModal">
        <div class="modal-dialog" role="document">
            <div class="modal-content">
                <div class="modal-header">
                    <button type="button" class="close" data-dismiss="modal" aria-label="Close"><span aria-hidden="true">&times;</span></button>
                    <h4 class="modal-title">学生详情</h4>
                </div>
                <div class="modal-body">
                    <table class="table table-striped table-bordered">
                        <tr >
                            <td>学生姓名：</td>
                            <td>
                                <div id="studentName"></div>
                            </td>
                        </tr>
                        <tr >
                            <td>性别：</td>
                            <td>
                                <div id="gender"></div>
                            </td>
                        </tr>
                        <tr>
                            <td>年龄：</td>
                            <td>
                                <div id="age"></div>
                            </td>
                        </tr>
                        <tr>
                            <td>学生编号：</td>
                            <td>
                                <div id="studentNum"></div>
                            </td>
                        </tr>
                        <tr >
                            <td>班级：</td>
                            <td>
                                <div id="gradeName"></div>
                            </td>
                        </tr>
                    </table>
                </div>
                <div class="modal-footer">
                    <button type="button" class="btn btn-default" data-dismiss="modal">关闭</button>
                </div>
            </div><!-- /.modal-content -->
        </div><!-- /.modal-dialog -->
    </div><!-- /.modal -->

</div>

</div>
</body>
</html> 