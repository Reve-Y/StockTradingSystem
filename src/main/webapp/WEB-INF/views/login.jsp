<%--
  Created by IntelliJ IDEA.
  User: hspcadmin
  Date: 2019-03-19
  Time: 14:07
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <meta http-equiv="X-UA-Compatible" content="ie=edge">
    <script src="../../js/vue.js"></script>
    <script src="../../js/jquery.min.js"></script>
    <script src="../../js/bootstrap.js"></script>
    <link rel="stylesheet" href="../../css/bootstrap.css"/>
    <style type="text/css">
        .form-inputsize {
            width: 320px;
        }
    </style>
    <title>LOGIN</title>
</head>
<body>
<%@include file="navbar.jsp"%>
<div class="d1"  style="margin-left:523px">
    <h2>登录：</h2><br>
    <form action="/dologin" method="POST" id="form1" @submit="checkForm" >
        <div class="form-group form-inputsize" :class="[telflag ? 'has-success' : 'has-error']" >
            <label class="control-label" for="telephone">手机号</label>
            <input type="text" class="form-control" id="telephone" v-model="telephone"
                   name="telephone" aria-describedby="helpBlock2" >
        </div>
        <div class="form-group form-inputsize" :class="[passflag ? 'has-success' : 'has-error']">
            <label class="control-label" for="password">密码</label>
            <input type="password" class="form-control" name="password"
                   v-model="password" id="password">
        </div>
        <button type="submit" class="btn btn-lg " :class="[btnflag ? 'btn-success':'btn-danger']">登录</button>
        <br>

    </form>
</div>

<script type="text/javascript">
    var app = new Vue({
        el : "#form1",
        data : {
            telephone:'',
            password:'',
        },
        computed : {
            telflag : function () {
                var regex = /1[0-9]{10}$/
                return regex.test(this.telephone)
            },
            passflag: function () {
                var regex = /[a-zA-Z][a-zA-Z0-9]{5,14}$/
                return regex.test(this.password)
            },
            btnflag: function () {
                return this.telflag&&this.passflag
            }
        } ,
        methods : {
            checkForm : function (e){
                if (this.telflag&&this.passfla)
                    return true
                e.preventDefault()
            }
        }
    })
</script>
</body>
</html>