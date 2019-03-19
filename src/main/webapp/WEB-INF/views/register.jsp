<%--
  Created by IntelliJ IDEA.
  User: hspcadmin
  Date: 2019-03-13
  Time: 17:05
  To change this template use File | Settings | File Templates.
--%>
<!DOCTYPE html>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html lang="en">
<head>
    <meta http-equiv="content-type" content="text/html" charset="utf-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <meta http-equiv="X-UA-Compatible" content="ie=edge">
    <link rel="stylesheet" href="../../css/bootstrap.css">
    <script src="../../js/jquery-3.3.1.js"></script>
    <script src="../../js/bootstrap.js"></script>
    <script src="../../js/vue.js"></script>
    <script src="../../js/vue-resource.js"></script>
    <style type="text/css">
        .form-inputsize {
            width: 320px;
        }
    </style>
    <title>注册</title>
</head>
<body>
<%@include file="navbar.jsp" %>
<div class="d1"  style="margin-left:523px">
    <h2>注册：</h2><br>
    <form action="/doregist" method="post" id="form1" @submit="checkForm" >
        <div class="form-group form-inputsize" :class="[telflag ? 'has-success' : 'has-error']" >
            <label class="control-label" for="telephone">手机号</label>
            <input type="text" class="form-control" id="telephone" v-model="telephone"
                   name="telephone" aria-describedby="helpBlock2" >
        </div>
        <div class="form-group form-inputsize" :class="[passflag ? 'has-success' : 'has-error']">
            <label class="control-label" for="password">密码</label>
            <input type="password" class="form-control" name="password"
                   v-model="password" id="password">
            <span id="helpBlock2" class="help-block">密码6-15位,由数字和字母组成，且由字母开头</span>
        </div>
        <div class="form-group form-inputsize" :class="[chekpassflag ? 'has-success' : 'has-error']">
            <label class="control-label" for="check_password">确认密码</label>
            <input type="password" class="form-control" id="check_password"
                   v-model="checkpassword" name="check_password">
        </div>
        <div class="form-group form-inputsize" :class="[emailflag ? 'has-success' : 'has-error']">
            <label class="control-label" for="email">邮箱</label>
            <input type="text" class="form-control" id="email" name="email" v-model="email" >
        </div>
        <div class="form-group form-inputsize has-success" >
            <label class="control-label" for="nickname">昵称</label>
            <input type="text" class="form-control" id="nickname" name="nickname"
                   v-model="nickname" >
        </div>
        <div class="form-group has-success" >
            <label class="control-label" >性别:&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</label>
            <label class="radio-inline">
                <input type="radio" name="sex" checked id="inlineRadio1" value="1"> 男
            </label>
            <label class="radio-inline">
                <input type="radio" name="sex" id="inlineRadio2" value="2"> 女
            </label>
        </div>
        <div class="form-group form-inputsize" :class="[ageflag ? 'has-success' : 'has-error']">
            <label class="control-label" for="age">年龄</label>
            <input type="text" class="form-control" id="age" v-model="age" name="age">
        </div>
        <button type="submit" class="btn btn-lg " :class="[btnflag ? 'btn-success':'btn-danger']">确认注册</button>
    </form>
</div>
<script type="text/javascript">
    var app = new Vue({
        el : "#form1",
        data : {
            telephone:'',
            password:'',
            checkpassword:'',
            email:'',
            nickname:'',
            age:''
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
            chekpassflag: function () {
                if(this.password=='')
                    return false;
                return (this.checkpassword==this.password)
            },
            emailflag: function () {
                if(this.email=='')
                    return true;
                var regex = /^(([^<>()\[\]\\.,;:\s@"]+(\.[^<>()\[\]\\.,;:\s@"]+)*)|(".+"))@((\[[0-9]{1,3}\.[0-9]{1,3}\.[0-9]{1,3}\.[0-9]{1,3}\])|(([a-zA-Z\-0-9]+\.)+[a-zA-Z]{2,}))$/;
                return regex.test(this.email);
            },
            ageflag: function () {
                return(this.age<140)   //  不能活过140吧
            },
            btnflag: function () {
                return this.telflag&&this.ageflag&&this.passflag&&this.chekpassflag&&this.emailflag
            }
        } ,
        methods : {
            checkForm : function (e){
                if (this.telflag&&this.ageflag&&this.passflag&&this.chekpassflag&&this.emailflag)
                    return true
                e.preventDefault()
            }
        }
    })
</script>
<%@include file="footer.jsp" %>
</body>
</html>