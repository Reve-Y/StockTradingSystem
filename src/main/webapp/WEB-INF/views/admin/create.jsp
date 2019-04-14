<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!doctype html>
<html class="no-js h-100" lang="en">
  <head>
    <meta charset="utf-8">
    <meta http-equiv="x-ua-compatible" content="ie=edge">
    <title>Create Your Account</title>
    <meta name="viewport" content="width=device-width, initial-scale=1, shrink-to-fit=no">
    <link href="https://use.fontawesome.com/releases/v5.0.6/css/all.css" rel="stylesheet">
    <link href="https://fonts.googleapis.com/icon?family=Material+Icons" rel="stylesheet">
    <link rel="stylesheet" href="../../../assets/css/bootstrap.min.css">
    <link rel="stylesheet" id="main-stylesheet" data-version="1.1.0" href="../../../assets/css/admin/shards-dashboards.1.1.0.min.css">
    <link rel="stylesheet" href="../../../assets/css/admin/extras.1.1.0.min.css">
    <script async defer src="../../../assets/js/admin/buttons.js"></script>
  </head>
  <body class="h-100">
     
    <div class="container-fluid">
      <div class="row">
        <!-- Main Sidebar -->
        <!-- Main Sidebar -->
        <aside class="main-sidebar col-12 col-md-3 col-lg-2 px-0">
          <div class="main-navbar">
            <nav class="navbar align-items-stretch navbar-light bg-white flex-md-nowrap border-bottom p-0">
              <a class="navbar-brand w-100 mr-0" href="#" style="line-height: 25px;">
                <div class="d-table m-auto">
                  <img id="main-logo" class="d-inline-block align-top mr-1" style="max-width: 25px;" src="../../../assets/img/admin/shards-dashboards-logo.svg" alt="Shards Dashboard">
                  <span class="d-none d-md-inline ml-1">Admin Page</span>
                </div>
              </a>
              <a class="toggle-sidebar d-sm-inline d-md-none d-lg-none">
                <i class="material-icons">&#xE5C4;</i>
              </a>
            </nav>
          </div>
          <form action="#" class="main-sidebar__search w-100 border-right d-sm-flex d-md-none d-lg-none">
            <div class="input-group input-group-seamless ml-3">
              <div class="input-group-prepend">
                <div class="input-group-text">
                  <i class="fas fa-search"></i>
                </div>
              </div>
              <input class="navbar-search form-control" type="text" placeholder="Search for something..." aria-label="Search"> </div>
          </form>
          <div class="nav-wrapper">
            <ul class="nav flex-column">
              <li class="nav-item">
                <a class="nav-link " href="/admin">
                  <i class="material-icons">edit</i>
                  <span>Home  </span>
                </a>
              </li>
              <li class="nav-item">
                <a class="nav-link " href="/blog">
                  <i class="material-icons">vertical_split</i>
                  <span>待定 </span>
                </a>
              </li>
              <li class="nav-item">
                <a class="nav-link " href="/newPost">
                  <i class="material-icons">note_add</i>
                  <span>Add New Post</span>
                </a>
              </li>
              <li class="nav-item">
                <a class="nav-link " href="/adminForm">
                  <i class="material-icons">view_module</i>
                  <span>Forms </span>
                </a>
              </li>
              <li class="nav-item">
                <a class="nav-link " href="/holdings">
                  <i class="material-icons">table_chart</i>
                  <span>Holdings </span>
                </a>
              </li>
              <li class="nav-item">
                <a class="nav-link" href="/userProfile">
                  <i class="material-icons">person</i>
                  <span>User Profile</span>
                </a>
              </li>
              <li class="nav-item">
                <a class="nav-link active" href="/createAccount">
                  <i class="material-icons">edit</i>
                  <span>Create Account</span>
                </a>
              </li>
            </ul>
          </div>
        </aside>
        <!-- End Main Sidebar -->
        <main class="main-content col-lg-10 col-md-9 col-sm-12 p-0 offset-lg-2 offset-md-3">
          <div class="main-navbar sticky-top bg-white">
            <!-- Main Navbar -->
            <nav class="navbar align-items-stretch navbar-light flex-md-nowrap p-0">
              <form action="#" class="main-navbar__search w-100 d-none d-md-flex d-lg-flex">
                <div class="input-group input-group-seamless ml-3">
                  <div class="input-group-prepend">
                    <div class="input-group-text">
                      <i class="fas fa-search"></i>
                    </div>
                  </div>
                  <input class="navbar-search form-control" type="text" placeholder="Search for something..." aria-label="Search"> </div>
              </form>
              <ul class="navbar-nav border-left flex-row ">
                <c:choose>
                  <c:when test="${empty user}">
                    <li class="nav-item border-right dropdown notifications">
                      <a class="nav-link nav-link-icon text-center" href="/login" role="button">
                        <div class="nav-link-icon__wrapper">
                          <span class="d-none d-md-inline-block">Login</span>
                        </div>
                      </a>
                    </li>
                    <li class="nav-item border-right dropdown notifications">
                      <a class="nav-link nav-link-icon text-center" href="/registerPage" role="button">
                        <div class="nav-link-icon__wrapper">
                          <span class="d-none d-md-inline-block">Register</span>
                        </div>
                      </a>
                    </li>
                  </c:when>
                  <c:otherwise>
                    <li class="nav-item dropdown">
                      <a class="nav-link dropdown-toggle text-nowrap px-3" data-toggle="dropdown" href="/userProfile" role="button" aria-haspopup="true" aria-expanded="false">
                        <img class="user-avatar rounded-circle mr-2" src="../../../assets/img/admin/usericon.JPG" alt="Am I Beautiful?">
                        <c:choose>
                          <c:when test="${user.nickname != ''}">
                            <span class="d-none d-md-inline-block">hi,&nbsp;<strong>${user.nickname}</strong></span>
                          </c:when>
                          <c:otherwise>
                            <span class="d-none d-md-inline-block">hi,&nbsp;<strong>${user.telephone}</strong></span>
                          </c:otherwise>
                        </c:choose>
                      </a>
                      <div class="dropdown-menu dropdown-menu-small">
                        <a class="dropdown-item" href="/userProfile">
                          <i class="material-icons">&#xE7FD;</i> Profile</a>
                        <a class="dropdown-item" href="/blog">
                          <i class="material-icons">vertical_split</i> Blog Posts</a>
                        <a class="dropdown-item" href="/newPost">
                          <i class="material-icons">note_add</i> Add New Post</a>
                        <div class="dropdown-divider"></div>
                        <a class="dropdown-item text-danger" href="/invalidate">
                          <i class="material-icons text-danger">&#xE879;</i> Logout </a>
                      </div>
                    </li>
                  </c:otherwise>
                </c:choose>
              </ul>
              <nav class="nav">
                <a href="#" class="nav-link nav-link-icon toggle-sidebar d-md-inline d-lg-none text-center border-left" data-toggle="collapse" data-target=".header-navbar" aria-expanded="false" aria-controls="header-navbar">
                  <i class="material-icons">&#xE5D2;</i>
                </a>
              </nav>
            </nav>
          </div>
          <!-- / .main-navbar -->
            <c:choose>
                <c:when test="${empty user}">
                    <div class="error">
                        <div class="error__content">
                            <h2>Woops...</h2>
                            <h3>You have not logged in yet</h3>
                            <p>Please login to open this page</p>
                            <button type="button" class="btn btn-accent btn-pill">&larr; Go Back</button>
                        </div>
                    </div>
                </c:when>
                <c:otherwise>
                    <c:choose>
                        <c:when test="${user.securities_account_id == '' || user.securities_account_id == null}">
                            <!-- 开户  -->
                            <div class="main-content-container container-fluid px-4">
                                <!-- Page Header -->
                                <div class="page-header row no-gutters py-4">
                                    <div class="col-12 col-sm-4 text-center text-sm-left mb-0">
                                        <span class="text-uppercase page-subtitle">Overview</span>
                                        <h3 class="page-title">Create Account </h3>
                                    </div>
                                </div>
                                <!-- End Page Header -->
                                <!-- Default Light Table -->
                                <form action="/openaccount" id="form1" method="post" @submit="checkForm">
                                    <div class="row">
                                        <div class="col-lg-6">
                                            <div class="card card-small mb-4">
                                                <div class="card-header border-bottom">
                                                    <h6 class="m-0">Securities Account </h6>
                                                </div>
                                                <ul class="list-group list-group-flush">
                                                    <li class="list-group-item p-3">
                                                        <div class="row">
                                                            <div class="col">
                                                                <div class="form-group">
                                                                    <label for="securities">证券账户号码</label>
                                                                    <input type="text" class="form-control" id="securities" name="securities"
                                                                           v-model="securities" placeholder="10 characters beginning with A">
                                                                </div>
                                                                <div class="form-group">
                                                                    <label for="opendate">开户日期</label>
                                                                    <input type="text" class="form-control" id="opendate" name="opendate"
                                                                           v-model="opendate" placeholder="today">
                                                                </div>
                                                                <div class="form-group">
                                                                    <label for="company">证券公司</label>
                                                                    <input type="text" class="form-control" id="company" name="company"
                                                                           v-model="company" placeholder="Company name">
                                                                </div>
                                                                <div class="form-group">
                                                                    <label for="capital1">资金账号</label>
                                                                    <input type="text" class="form-control" id="capital1" name="capital1"
                                                                           v-model="capital" placeholder="capital account">
                                                                </div>
                                                                <button type="button" class="btn btn-secondary" @click="autoSecurity">Automatic generated</button>
                                                            </div>
                                                        </div>
                                                    </li>
                                                </ul>
                                            </div>
                                        </div>
                                        <div class="col-lg-6">
                                            <div class="card card-small mb-4">
                                                <div class="card-header border-bottom">
                                                    <h6 class="m-0">Capital Account </h6>
                                                </div>
                                                <ul class="list-group list-group-flush">
                                                    <li class="list-group-item p-3">
                                                        <div class="row">
                                                            <div class="col">
                                                                <div class="form-group">
                                                                    <label for="capital2">资金账户号码</label>
                                                                    <input type="text" class="form-control" id="capital2" name="capital2"
                                                                           v-model="capital" placeholder="capital account">
                                                                </div>
                                                                <div class="form-group">
                                                                    <label for="bank">开户行</label>
                                                                    <input type="text" class="form-control" id="bank" name="bank"
                                                                           v-model="bank" placeholder="China bank">
                                                                </div>
                                                                <div class="form-group">
                                                                    <label for="bankcard">银行卡号</label>
                                                                    <input type="text" class="form-control" id="bankcard" name="bankcard"
                                                                           v-model="bankcard" placeholder="bank card number">
                                                                </div>
                                                                <div class="form-group">
                                                                    <label for="balance">初始余额</label>
                                                                    <input type="text" class="form-control" id="balance" name="balance"
                                                                           v-model="balance" placeholder="0.00">
                                                                </div>
                                                                <button type="button" class="btn btn-secondary" @click="autoCapital">Automatic generated</button>
                                                                <button type="submit" class="btn btn-accent">Create Account</button>
                                                            </div>
                                                        </div>
                                                    </li>
                                                </ul>
                                            </div>
                                        </div>
                                    </div>
                                </form>
                                <!-- End Default Light Table -->
                            </div>
                            <!-- 开户结束 -->
                        </c:when>
                        <c:otherwise>
                          <div class="error">
                            <div class="error__content">
                              <h2>NO</h2>
                              <h3>You already have an account!</h3>
                              <p>One person can only register one account, don't be greedy.</p>
                              <button type="button" class="btn btn-accent btn-pill">&larr; Go Back</button>
                            </div>
                          </div>
                        </c:otherwise>
                    </c:choose>
                </c:otherwise>
            </c:choose>
            <footer class="main-footer d-flex p-2 px-3 bg-white border-top">
                <ul class="nav">
                    <li class="nav-item">
                        <a class="nav-link" href="/admin">Home</a>
                    </li>
                    <li class="nav-item">
                        <a class="nav-link" href="/about">About</a>
                    </li>
                    <li class="nav-item">
                        <a class="nav-link" href="/stockinfo">StockInfo</a>
                    </li>
                    <c:if test="${empty user}">
                        <li class="nav-item">
                            <a class="nav-link" href="/login">Login</a>
                        </li>
                        <li class="nav-item">
                            <a class="nav-link" href="/registerPage">Regist</a>
                        </li>
                    </c:if>
                    <li class="nav-item">
                        <a class="nav-link" href="/help">Help</a>
                    </li>
                </ul>
                <span class="copyright ml-auto my-auto mr-2">Copyright © 2019
              <a href="" rel="nofollow">Reve</a>
            </span>
            </footer>
        </main>
      </div>
    </div>
    <script src="../../../assets/js/core/vue.js"></script>
    <script src="../../../assets/js/core/jquery-3.3.1.js"></script>
    <script src="../../../assets/js/core/popper.min.js" ></script>
    <script src="../../../assets/js/core/bootstrap.js"></script>
    <script src="../../../assets/js/admin/Chart.min.js"></script>
    <script src="../../../assets/js/admin/shards.min.js"></script>
    <script src="../../../assets/js/admin/jquery.sharrre.min.js"></script>
    <script src="../../../assets/js/admin/extras.1.1.0.min.js"></script>
    <script src="../../../assets/js/admin/shards-dashboards.1.1.0.min.js"></script>

    <script type="text/javascript">
        var app = new Vue({
            el : '#form1',
            data : {
                securities : '',
                opendate : '',
                company : '',
                capital : '',
                bank : '',
                bankcard : '',
                balance : 0.0
            },
            methods : {
                autoSecurity : function (){
                    this.securities = 'A'+this.randomNumber(9);   //  生成随机以A开头的证券账号
                    var year = new Date().getFullYear().toString();
                    var month = '0'+(new Date().getMonth()+1).toString();
                    var day = new Date().getDate().toString();
                    this.opendate = year + month + day;           //  获取字符串格式的日期
                    this.company = 'Kangge.Inc';                  //  待改进
                    this.capital = this.randomNumber(8)           //  随机生成9为纯数字串。
                },
                autoCapital : function (){
                    this.bank = 'China Bank'
                    this.bankcard = '6'+this.randomNumber(14)
                    this.balance = 100000000.0
                },
                checkForm : function (e){
                    if(this.securities.trim()=='' || this.opendate.trim()=='' || this.company.trim()=='' || this.capital.trim()==''|| this.bank.trim()=='' || this.bankcard.trim()=='' )
                    {
                        alert("除初始余额外其余不能为空")
                        e.preventDefault()
                    }else{
                        return true
                    }
                },
                randomNumber : function (number){
                    //  自动生成数字串,参数number为长度
                    var arr = new Array;
                    var arr1 = new Array("0","1","2","3","4","5","6","7","8","9");
                    for(var i=0;i<number;i++){
                        var n = Math.floor(Math.random()*10);
                        arr[i] =arr1[n];
                    }
                    val = arr.join("")
                    return val;
                }
            }
        })
    </script>
  </body>
</html>