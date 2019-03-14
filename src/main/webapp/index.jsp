<!DOCTYPE html>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html lang="en">
<head>
    <meta http-equiv="content-type" content="text/html" charset="utf-8">
    <meta name="viewport"
          content="width=device-width, user-scalable=no, initial-scale=1.0, maximum-scale=1.0, minimum-scale=1.0">
    <meta http-equiv="X-UA-Compatible" content="ie=edge,chrome=1">
    <link rel="stylesheet" href="css/bootstrap.css">
    <script src="js/jquery-3.3.1.js"></script>
    <script src="js/bootstrap.js"></script>
    <script src="js/vue.js"></script>
    <script src="js/vue-resource.js"></script>
    <title>首页</title>
</head>
<body>
    <%--<%@include file="WEB-INF/views/navbar.jsp" %>--%>
    <div id="app">
        <button type="button" class="btn btn-lg btn-primary" @click="register">注册页面</button>
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