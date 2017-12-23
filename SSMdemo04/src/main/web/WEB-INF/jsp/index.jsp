<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html>
<html lang="en">
<head>
  <title></title>
  <%--页面需要引入js和样式表--%>
  <%@include file="basic.jsp"%>
</head>

<body class="login">
<%--
    页面成功按钮
   <div class="row">
    <div class="col-md-2 col-md-offset-5">
      <button type="button" class="btn btn-success">（成功）Success</button>
    </div>
  </div>--%>
<div class="container">
  <div class="row">
    <div class="col-md-2 col-md-offset-6">
      <h1>登录</h1>
    </div>
  </div>
  <div class="row">
    <div class="col-md-8 col-md-offset-2">
      <form class="form-horizontal" action="${pageContext.request.contextPath}/user/login" method="post">
        <div class="form-group">
          <label  class="col-sm-2 control-label">用户名</label>
          <div class="col-sm-10">
            <input  class="form-control"  name="userName" placeholder="用户名">
          </div>
        </div>
        <div class="form-group">
          <label  class="col-sm-2 control-label">密码</label>
          <div class="col-sm-10">
            <input type="password" class="form-control"  name="password" placeholder="密码">
          </div>
        </div>
        <div class="form-group">
          <div class="col-sm-offset-2 col-sm-10">
            <button type="submit" class="btn btn-default">登录</button>
          </div>
        </div>
      </form>
    </div>
  </div>
  <div class="row">
    <div class="col-md-2 col-md-offset-6">${message}</div>
  </div>
</div>

</div>
</body>
</html>