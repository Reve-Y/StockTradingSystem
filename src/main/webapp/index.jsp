<!DOCTYPE html>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html lang="en">
<head>
    <meta http-equiv="content-type" content="text/html" charset="utf-8">
    <meta name="viewport"
          content="width=device-width, user-scalable=no, initial-scale=1.0, maximum-scale=1.0, minimum-scale=1.0">
    <meta http-equiv="X-UA-Compatible" content="ie=edge,chrome=1">
    <link rel="stylesheet" href="assets/css/bootstrap.css">
    <script src="assets/js/core/jquery-3.3.1.js"></script>
    <script src="assets/js/core/bootstrap.js"></script>
    <script src="assets/js/core/vue.js"></script>
    <script src="assets/js/core/vue-resource.js"></script>
    <title>首页</title>
</head>
<body>

    <div id="app">
        <button type="button" class="btn btn-lg btn-primary" @click="register">注册页面</button><br><br>
        <a class="btn btn-primary btn-lg" href="/start" role="button">首页</a><br><br>
        <a class="btn btn-primary btn-lg" href="/login" role="button">登录页</a>
        <a class="btn btn-primary btn-lg" href="${pageContext.request.contextPath}/admin" role="button">管理页</a>
    </div>
    <script type="text/javascript">
        var app = new Vue({
            el:"#app",
            data:{
                
            },
            methods:{
                register : function () {
                    window.location.href="/registerPage"
                }
            }
        })
    </script>
    <%--<%@include file="WEB-INF/views/footer.jsp" %>--%>
</body>
</html>