<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!doctype html>
<html class="no-js h-100" lang="en">
<head>
    <meta charset="utf-8">
    <meta http-equiv="x-ua-compatible" content="ie=edge">
    <title>管理员后台</title>
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
                            <span class="d-none d-md-inline ml-1">Super Admin Page</span>
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
                        <a class="nav-link active" href="/superadmin">
                            <i class="material-icons">edit</i>
                            <span>Home </span>
                        </a>
                    </li>
                    <li class="nav-item">
                        <a class="nav-link " href="/adminmanager">
                            <i class="material-icons">vertical_split</i>
                            <span>管理 </span>
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
                        <li class="nav-item dropdown">
                            <a class="nav-link dropdown-toggle text-nowrap px-3" data-toggle="dropdown" href="/superadmin" role="button" aria-haspopup="true" aria-expanded="false">
                                <!-- <img class="user-avatar rounded-circle mr-2" src="images/avatars/0.jpg" alt="User Avatar"> -->
                                <span class="d-none d-md-inline-block">${user.telephone}:${user.nickname}</span>
                            </a>
                            <div class="dropdown-menu dropdown-menu-small">
                                <a class="dropdown-item text-danger" href="/invalidate">
                                    <i class="material-icons text-danger">&#xE879;</i> Logout </a>
                            </div>
                        </li>
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
                        <span class="text-uppercase page-subtitle">Dashboard</span>
                        <h3 class="page-title">Blog Overview</h3>
                    </div>
                </div>
                <!-- End Page Header -->
                <!-- Small Stats Blocks -->
                <div class="row">
                    <div class="col-lg col-md-6 col-sm-6 mb-4">
                        <div class="stats-small stats-small--1 card card-small">
                            <div class="card-body p-0 d-flex">
                                <div class="d-flex flex-column m-auto">
                                    <div class="stats-small__data text-center">
                                        <span class="stats-small__label text-uppercase">Posts</span>
                                        <h6 class="stats-small__value count my-3">2,390</h6>
                                    </div>
                                    <div class="stats-small__data">
                                        <span class="stats-small__percentage stats-small__percentage--increase">4.7%</span>
                                    </div>
                                </div>
                                <canvas height="120" class="blog-overview-stats-small-1"></canvas>
                            </div>
                        </div>
                    </div>
                    <div class="col-lg col-md-6 col-sm-6 mb-4">
                        <div class="stats-small stats-small--1 card card-small">
                            <div class="card-body p-0 d-flex">
                                <div class="d-flex flex-column m-auto">
                                    <div class="stats-small__data text-center">
                                        <span class="stats-small__label text-uppercase">Pages</span>
                                        <h6 class="stats-small__value count my-3">182</h6>
                                    </div>
                                    <div class="stats-small__data">
                                        <span class="stats-small__percentage stats-small__percentage--increase">12.4%</span>
                                    </div>
                                </div>
                                <canvas height="120" class="blog-overview-stats-small-2"></canvas>
                            </div>
                        </div>
                    </div>
                    <div class="col-lg col-md-4 col-sm-6 mb-4">
                        <div class="stats-small stats-small--1 card card-small">
                            <div class="card-body p-0 d-flex">
                                <div class="d-flex flex-column m-auto">
                                    <div class="stats-small__data text-center">
                                        <span class="stats-small__label text-uppercase">Comments</span>
                                        <h6 class="stats-small__value count my-3">8,147</h6>
                                    </div>
                                    <div class="stats-small__data">
                                        <span class="stats-small__percentage stats-small__percentage--decrease">3.8%</span>
                                    </div>
                                </div>
                                <canvas height="120" class="blog-overview-stats-small-3"></canvas>
                            </div>
                        </div>
                    </div>
                    <div class="col-lg col-md-4 col-sm-6 mb-4">
                        <div class="stats-small stats-small--1 card card-small">
                            <div class="card-body p-0 d-flex">
                                <div class="d-flex flex-column m-auto">
                                    <div class="stats-small__data text-center">
                                        <span class="stats-small__label text-uppercase">Users</span>
                                        <h6 class="stats-small__value count my-3">2,413</h6>
                                    </div>
                                    <div class="stats-small__data">
                                        <span class="stats-small__percentage stats-small__percentage--increase">12.4%</span>
                                    </div>
                                </div>
                                <canvas height="120" class="blog-overview-stats-small-4"></canvas>
                            </div>
                        </div>
                    </div>
                    <div class="col-lg col-md-4 col-sm-12 mb-4">
                        <div class="stats-small stats-small--1 card card-small">
                            <div class="card-body p-0 d-flex">
                                <div class="d-flex flex-column m-auto">
                                    <div class="stats-small__data text-center">
                                        <span class="stats-small__label text-uppercase">Subscribers</span>
                                        <h6 class="stats-small__value count my-3">17,281</h6>
                                    </div>
                                    <div class="stats-small__data">
                                        <span class="stats-small__percentage stats-small__percentage--decrease">2.4%</span>
                                    </div>
                                </div>
                                <canvas height="120" class="blog-overview-stats-small-5"></canvas>
                            </div>
                        </div>
                    </div>
                </div>
                <!-- End Small Stats Blocks -->
                <div class="row">
                    <!-- Users Stats -->
                    <div class="col-lg-8 col-md-12 col-sm-12 mb-4">
                        <div class="card card-small">
                            <div class="card-header border-bottom">
                                <h6 class="m-0">Users</h6>
                            </div>
                            <div class="card-body pt-0">
                                <div class="row border-bottom py-2 bg-light">
                                    <div class="col-12 col-sm-6">
                                        <div id="blog-overview-date-range" class="input-daterange input-group input-group-sm my-auto ml-auto mr-auto ml-sm-auto mr-sm-0" style="max-width: 350px;">
                                            <input type="text" class="input-sm form-control" name="start" placeholder="Start Date" id="blog-overview-date-range-1">
                                            <input type="text" class="input-sm form-control" name="end" placeholder="End Date" id="blog-overview-date-range-2">
                                            <span class="input-group-append">
                                                <span class="input-group-text">
                                                    <i class="material-icons"></i>
                                                </span>
                                            </span>
                                        </div>
                                    </div>
                                    <div class="col-12 col-sm-6 d-flex mb-2 mb-sm-0">
                                        <button type="button" class="btn btn-sm btn-white ml-auto mr-auto ml-sm-auto mr-sm-0 mt-3 mt-sm-0">View Full Report &rarr;</button>
                                    </div>
                                </div>
                                <canvas height="130" style="max-width: 100% !important;" class="blog-overview-users"></canvas>
                            </div>
                        </div>
                    </div>
                    <!-- End Users Stats -->
                    <!-- Users By Device Stats -->
                    <div class="col-lg-4 col-md-6 col-sm-12 mb-4">
                        <div class="card card-small h-100">
                            <div class="card-header border-bottom">
                                <h6 class="m-0">Users by device</h6>
                            </div>
                            <div class="card-body d-flex py-0">
                                <canvas height="220" class="blog-users-by-device m-auto"></canvas>
                            </div>
                            <div class="card-footer border-top">
                                <div class="row">
                                    <div class="col">
                                        <select class="custom-select custom-select-sm" style="max-width: 130px;">
                                            <option selected>Last Week</option>
                                            <option value="1">Today</option>
                                            <option value="2">Last Month</option>
                                            <option value="3">Last Year</option>
                                        </select>
                                    </div>
                                    <div class="col text-right view-report">
                                        <a href="#">Full report &rarr;</a>
                                    </div>
                                </div>
                            </div>
                        </div>
                    </div>
                    <!-- End Users By Device Stats -->
                </div>

            </div>
            <footer class="main-footer d-flex p-2 px-3 bg-white border-top">
                <ul class="nav">
                    <li class="nav-item">
                        <a class="nav-link" href="/superadmin">Home</a>
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

    <script src="../../../assets/js/core/jquery-3.3.1.js"></script>
    <script src="../../../assets/js/core/popper.min.js" ></script>
    <script src="../../../assets/js/core/bootstrap.js"></script>
    <script src="../../../assets/js/admin/Chart.min.js"></script>
    <script src="../../../assets/js/admin/shards.min.js"></script>
    <script src="../../../assets/js/admin/jquery.sharrre.min.js"></script>
    <script src="../../../assets/js/admin/extras.1.1.0.min.js"></script>
    <script src="../../../assets/js/admin/shards-dashboards.1.1.0.min.js"></script>
    <script src="../../../assets/js/admin/quill.min.js"></script>
    <script src="../../../assets/js/admin/app/app-blog-overview.1.1.0.js"></script>
</body>
</html>