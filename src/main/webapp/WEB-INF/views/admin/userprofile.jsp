<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!doctype html>
<html class="no-js h-100" lang="en">
  <head>
    <meta charset="utf-8">
    <meta http-equiv="x-ua-compatible" content="ie=edge">
    <title>后台管理-用户信息</title>
    <meta name="description" content="A high-quality &amp; free Bootstrap admin dashboard template pack that comes with lots of templates and components.">
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
                <a class="nav-link active" href="/userProfile">
                  <i class="material-icons">person</i>
                  <span>User Profile</span>
                </a>
              </li>
              <li class="nav-item">
                <a class="nav-link" href="/createAccount">
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

          <div class="main-content-container container-fluid px-4">
            <!-- Page Header -->
            <div class="page-header row no-gutters py-4">
              <div class="col-12 col-sm-4 text-center text-sm-left mb-0">
                <span class="text-uppercase page-subtitle">Overview</span>
                <h3 class="page-title">User Profile</h3>
              </div>
            </div>
            <!-- End Page Header -->
            <!-- Default Light Table -->
            <div class="row">
              <div class="col-lg-4">
                <div class="card card-small mb-4 pt-3">
                  <div class="card-header border-bottom text-center">
                    <div class="mb-3 mx-auto">
                      <div class="rounded-circle"  alt="User Avatar" width="110"></div> </div>
                    <h4 class="mb-0">${user.telephone}</h4>
                    <span class="text-muted d-block mb-2">${user.nickname}</span>
                    <button type="button" class="mb-2 btn btn-sm btn-pill btn-outline-primary mr-2">
                      <i class="material-icons mr-1">person_add</i>Follow</button>
                  </div>
                  <ul class="list-group list-group-flush">
                    <li class="list-group-item px-4">
                      <div class="progress-wrapper">
                        <strong class="text-muted d-block mb-2">Workload</strong>
                        <div class="progress progress-sm">
                          <div class="progress-bar bg-primary" role="progressbar" aria-valuenow="74" aria-valuemin="0" aria-valuemax="100" style="width: 74%;">
                            <span class="progress-value">74%</span>
                          </div>
                        </div>
                      </div>
                    </li>
                    <li class="list-group-item p-4">
                      <strong class="text-muted d-block mb-2">Description</strong>
                      <span> A Handsome Man Or a Beautiful Girl ...</span>
                    </li>
                  </ul>
                </div>
              </div>
              <div class="col-lg-8">
                <div class="card card-small mb-4">
                  <div class="card-header border-bottom">
                    <h6 class="m-0">Account Details</h6>
                  </div>
                  <ul class="list-group list-group-flush">
                    <li class="list-group-item p-3">
                      <div class="row">
                        <div class="col">
                          <form action="/updateUserInfo" method="post" id="form1" @submit="checkForm">
                              <div class="form-row">
                                  <div class="form-group col-md-6">
                                      <label for="email">Email</label>
                                      <input type="text" class="form-control" id="email" placeholder="Email"
                                      v-model="email" name="email"> </div>
                                  <div class="form-group col-md-6">
                                      <label for="age">age</label>
                                      <input type="text" class="form-control" id="age" placeholder="18"
                                      v-model="age" name="age">
                                  </div>
                              </div>
                              <div class="form-group">
                                  <label class="control-label" >性别:&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</label>
                                  <label class="radio-inline">
                                      <input type="radio" name="sex" checked id="inlineRadio1" value="1"> 男
                                  </label>
                                  <label class="radio-inline">
                                      <input type="radio" name="sex" id="inlineRadio2" value="2"> 女
                                  </label>
                              </div>
                              <div class="form-group">
                                  <label for="nickname">nickname</label>
                                  <input type="text" class="form-control" id="nickname" placeholder="tiger" name="nickname">
                              </div>
                            <div class="form-row">
                              <div class="form-group col-md-6">
                                <label for="feInputCity">City</label>
                                <input type="text" class="form-control" id="feInputCity"> </div>
                              <div class="form-group col-md-4">
                                <label for="feInputState">State</label>
                                <select id="feInputState" class="form-control">
                                  <option selected>Choose...</option>
                                  <option>...</option>
                                </select>
                              </div>
                              <div class="form-group col-md-2">
                                <label for="inputZip">Zip</label>
                                <input type="text" class="form-control" id="inputZip"> </div>
                            </div>
                            <button type="submit" class="btn btn-accent">Update Account</button>
                          </form>
                        </div>
                      </div>
                    </li>
                  </ul>
                </div>
              </div>
            </div>
            <!-- End Default Light Table -->
          </div>
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
    <script src="../../../assets/js/core/vue-resource.js"></script>
    <script src="../../../assets/js/core/axios.js"></script>
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
          el : "#form1",
          data : {
              age : 18,
              email: ''
          },
          computed : {
              ageflag : function () {
                  return (this.age<140)
              },
              emailflag: function () {
                  var regex = /^(([^<>()\[\]\\.,;:\s@"]+(\.[^<>()\[\]\\.,;:\s@"]+)*)|(".+"))@((\[[0-9]{1,3}\.[0-9]{1,3}\.[0-9]{1,3}\.[0-9]{1,3}\])|(([a-zA-Z\-0-9]+\.)+[a-zA-Z]{2,}))$/;
                  return regex.test(this.email);
              }
          },
          methods : {
              checkForm : function (e){
                  if (this.ageflag&&this.emailflag)
                      return true
                  else{
                      if(!this.emailflag)
                          alert("邮箱格式不正确")
                      else
                          alert("年龄输入不合法")
                      e.preventDefault()
                  }
              }
          }
      })
  </script>
  </body>
</html>