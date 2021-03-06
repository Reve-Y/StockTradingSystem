<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html>
<html lang="en">

<head>
    <meta charset="utf-8" />
    <link rel="apple-touch-icon" sizes="76x76" href="../../assets/img/loginpage/favicon.png">
    <link rel="icon" type="image/png" href="../../assets/img/loginpage/favicon.png">
    <meta http-equiv="X-UA-Compatible" content="IE=edge,chrome=1" />
    <title>
        Login
    </title>
    <meta content='width=device-width, initial-scale=1.0, maximum-scale=1.0, user-scalable=0, shrink-to-fit=no' name='viewport' />
    <!--     Fonts and icons     -->
    <link href="../../assets/css/google-font-family.css" rel="stylesheet">
    <link href="../../assets/css/font-awesome.css" rel="stylesheet">
    <!-- CSS Files -->
    <link href="../../assets/css/bootstrap.min.css" rel="stylesheet" />
    <link href="../../assets/css/now-ui-kit.css?v=1.2.0" rel="stylesheet" />

    <link href="../../assets/css/login/demo.css" rel="stylesheet" />
</head>

<body class="login-page sidebar-collapse">
<!-- Navbar -->
<nav class="navbar navbar-expand-lg bg-primary fixed-top navbar-transparent " color-on-scroll="400">
    <div class="container">

        <div class="navbar-translate">
            <a class="navbar-brand" href="#" rel="tooltip" title="" data-placement="bottom" target="_blank">
                Stock Trade System
            </a>
            <button class="navbar-toggler navbar-toggler" type="button" data-toggle="collapse" data-target="#navigation" aria-controls="navigation-index" aria-expanded="false" aria-label="Toggle navigation">
                <span class="navbar-toggler-bar top-bar"></span>
                <span class="navbar-toggler-bar middle-bar"></span>
                <span class="navbar-toggler-bar bottom-bar"></span>
            </button>
        </div>
        <div class="collapse navbar-collapse justify-content-end" id="navigation" data-nav-image="../../assets/img/loginpage/blurred-image-1.jpg">
            <ul class="navbar-nav">
                <li class="nav-item">
                    <a class="nav-link" href="/start">Back to Home</a>
                </li>
                <li class="nav-item">
                    <a class="nav-link" href="/registerPage">Want Regist?</a>
                </li>

            </ul>
        </div>
    </div>
</nav>
<!-- End Navbar -->
<div class="page-header clear-filter" filter-color="orange">
    <div class="page-header-image" style="background-image:url(../../assets/img/loginpage/bg11.jpg)"></div>
    <div class="content">
        <div class="container">
            <div class="col-md-4 ml-auto mr-auto">
                <div class="card card-login card-plain">
                    <form action="/dologin" method="POST" id="form1" @submit="checkForm" >
                        <div class="card-header text-center">
                            <div class="logo-container">
                                <!-- <img src="../../assets/img/loginpage/now-logo.png" alt=""> -->
                            </div>
                        </div>
                        <div class="card-body">
                            <div class="input-group no-border input-lg" >
                                <div class="input-group-prepend">
                                  <span class="input-group-text">
                                    <i class="now-ui-icons users_circle-08"></i>
                                  </span>
                                </div>
                                <input type="text" class="form-control" id="telephone" v-model="telephone"
                                       name="telephone" placeholder="Telephone...">
                            </div>
                            <div class="input-group no-border input-lg" >
                                <div class="input-group-prepend">
                                  <span class="input-group-text">
                                    <i class="now-ui-icons text_caps-small"></i>
                                  </span>
                                </div>
                                <input type="password" placeholder="Password..."  name="password"
                                       v-model="password" id="password" class="form-control" />
                            </div>
                        </div>
                        <button type="submit" class="btn btn-round btn-lg btn-block " :class="[btnflag ? 'btn-neutral':'btn-default']">Get Started</button>
                        <div class="pull-left">
                            <h6>
                                <a href="/registerPage" class="link">Create Account</a>
                            </h6>
                        </div>
                        <div class="pull-right">
                            <h6>
                                <a href="/help" class="link">Need Help?</a>
                            </h6>
                        </div>
                    </form>
                </div>
            </div>
        </div>
    </div>
</div>

</div>

<script src="../../assets/js/core/vue.js"></script>
<script src="../../assets/js/core/jquery.min.js"></script>
<script src="../../assets/js/core/popper.min.js"></script>
<script src="../../assets/js/core/bootstrap.min.js"></script>
<script src="../../assets/js/plugins/bootstrap-switch.js"></script>
<script src="../../assets/js/plugins/nouislider.min.js"></script>
<script src="../../assets/js/plugins/bootstrap-datepicker.js"></script>
<script src="../../assets/js/now-ui-kit.js?v=1.2.0"></script>

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
                if (this.telflag&&this.passflag)
                    return true
                e.preventDefault()
            }
        }
    })
</script>
</body>

</html>