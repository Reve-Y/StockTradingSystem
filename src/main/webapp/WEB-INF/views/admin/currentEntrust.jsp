<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!doctype html>
<html class="no-js h-100" lang="en">
  <head>
    <meta charset="utf-8">
    <meta http-equiv="x-ua-compatible" content="ie=edge">
    <title>当前委托</title>
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
                  <span>基本信息 </span>
                </a>
              </li>
              <li class="nav-item">
                <a class="nav-link active" href="/currentEntrust">
                  <i class="material-icons">vertical_split</i>
                  <span>当前委托 </span>
                </a>
              </li>
              <li class="nav-item">
                <a class="nav-link " href="/holdings">
                  <i class="material-icons">table_chart</i>
                  <span>持仓信息 </span>
                </a>
              </li>
              <li class="nav-item">
                <a class="nav-link " href="/dealRecord">
                  <i class="material-icons">note_add</i>
                  <span>成交记录</span>
                </a>
              </li>
              <li class="nav-item">
                <a class="nav-link " href="/historyEntrust">
                  <i class="material-icons">view_module</i>
                  <span>历史委托 </span>
                </a>
              </li>

              <li class="nav-item">
                <a class="nav-link" href="/userProfile">
                  <i class="material-icons">person</i>
                  <span>更新信息</span>
                </a>
              </li>
              <li class="nav-item">
                <a class="nav-link" href="/createAccount">
                  <i class="material-icons">edit</i>
                  <span>自助开户</span>
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
                        <a class="dropdown-item" href="/currentEntrust">
                          <i class="material-icons">vertical_split</i>Current Entrusts</a>
                        <a class="dropdown-item" href="/stockinfo">
                          <i class="material-icons">note_add</i>Start Trading</a>
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
          <!-- /End .main-navbar -->

          <div class="main-content-container container-fluid px-4" id="entrustData">
            <!-- Page Header -->
            <div class="page-header row no-gutters py-4">
              <div class="col-12 col-sm-4 text-center text-sm-left mb-0">
                <span class="text-uppercase page-subtitle">Overview</span>
                <h3 class="page-title">当前委托查询</h3>
              </div>
            </div>
            <!-- End Page Header -->
            <!-- Default Light Table -->
            <div class="row" id="holding">
              <div class="col">
                <div class="card card-small mb-4">
                  <div class="card-header border-bottom">
                    <h6 class="m-0">当前委托总条数：&nbsp;<span style="color:#ff130e">{{count_entrust}}</span>&nbsp;</h6>
                  </div>
                  <div class="card-body p-0 pb-3 text-center">
                    <table class="table mb-0 table-striped">
                      <thead class="bg-light">
                      <tr>
                        <th scope="col" class="border-0">#</th>
                        <th scope="col" class="border-0">委托时间</th>
                        <th scope="col" class="border-0">股票代码</th>
                        <th scope="col" class="border-0">股票名称</th>
                        <th scope="col" class="border-0">委托方向</th>
                        <th scope="col" class="border-0">委托数量</th>
                        <th scope="col" class="border-0">委托价格</th>
                        <th scope="col" class="border-0">委托金额</th>
                        <th scope="col" class="border-0">操作</th>
                      </tr>
                      </thead>
                      <tbody>
                        <tr v-for="(item,index) in entrustInfo">
                          <td>{{index+1}}</td>
                          <td>{{item.entrust_date}}</td>
                          <td>{{item.stock_code}}</td>
                          <td>{{item.stock_name}}</td>
                          <td>{{item.entrust_direction==1 ? '买入' : '卖出'}}</td>
                          <td>{{item.entrust_amount}}</td>
                          <td>{{item.entrust_price}}</td>
                          <td>{{item.amount_money}}</td>
                          <td><button class="btn-primary" @click="withdraw" :id="item.entrust_key">撤单</button></td>
                        </tr>
                      </tbody>
                    </table>
                  </div>
                </div>
              </div>
            </div>
            <!-- End Default Light Table -->
            <!-- 分页条 -->
            <div class="container">
              <ul class="pagination pagination-lg " style="position:relative;left:50%;margin-left:-250px;">
                <li class="page-item"><a class="page-link" @click="getFirstPage()"><span style="color: #0cdbff">First</span></a></li>
                <li class="page-item"><a class="page-link" @click="getPreviousPage()"><span style="color: #0cdbff">Previous</span></a></li>
                <li class="page-item"><a class="page-link" >当前第&nbsp;<span style="color: purple">{{pageNum}}</span>&nbsp;页,共&nbsp;<span style="color:purple">{{total}}</span>页</a></li>
                <li class="page-item"><a class="page-link" @click="getNextPage()"><span style="color: #0cdbff">Next</span></a></li>
                <li class="page-item"><a class="page-link" @click="getLastPage()"><span style="color: #0cdbff">Last</span></a></li>
              </ul>
            </div>
            <!-- 分页条 end -->
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
    <script src="../../../assets/js/admin/onload/current-entrust.js"></script>
  </body>
</html>