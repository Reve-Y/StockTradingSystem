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
                        <a class="nav-link " href="/superadmin">
                            <i class="material-icons">edit</i>
                            <span>Home </span>
                        </a>
                    </li>
                    <li class="nav-item">
                        <a class="nav-link active" href="/adminmanager">
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
                        </li>
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
                        <span class="text-uppercase page-subtitle">Overview</span>
                        <h3 class="page-title">Data Tables</h3>
                    </div>
                </div>
                <!-- End Page Header -->
                <!-- Default Light Table -->
                <div class="row">
                    <div class="col">
                        <div class="card card-small mb-4">
                            <div class="card-header border-bottom">
                                <h6 class="m-0">Active Users</h6>
                            </div>
                            <div class="card-body p-0 pb-3 text-center">
                                <table class="table mb-0">
                                    <thead class="bg-light">
                                    <tr>
                                        <th scope="col" class="border-0">#</th>
                                        <th scope="col" class="border-0">First Name</th>
                                        <th scope="col" class="border-0">Last Name</th>
                                        <th scope="col" class="border-0">Country</th>
                                        <th scope="col" class="border-0">City</th>
                                        <th scope="col" class="border-0">Phone</th>
                                    </tr>
                                    </thead>
                                    <tbody>
                                    <tr>
                                        <td>1</td>
                                        <td>Ali</td>
                                        <td>Kerry</td>
                                        <td>Russian Federation</td>
                                        <td>Gdańsk</td>
                                        <td>107-0339</td>
                                    </tr>
                                    <tr>
                                        <td>2</td>
                                        <td>Clark</td>
                                        <td>Angela</td>
                                        <td>Estonia</td>
                                        <td>Borghetto di Vara</td>
                                        <td>1-660-850-1647</td>
                                    </tr>
                                    <tr>
                                        <td>3</td>
                                        <td>Jerry</td>
                                        <td>Nathan</td>
                                        <td>Cyprus</td>
                                        <td>Braunau am Inn</td>
                                        <td>214-4225</td>
                                    </tr>
                                    <tr>
                                        <td>4</td>
                                        <td>Colt</td>
                                        <td>Angela</td>
                                        <td>Liberia</td>
                                        <td>Bad Hersfeld</td>
                                        <td>1-848-473-7416</td>
                                    </tr>
                                    </tbody>
                                </table>
                            </div>
                        </div>
                    </div>
                </div>
                <!-- End Default Light Table -->
                <!-- Default Dark Table -->
                <div class="row">
                    <div class="col">
                        <div class="card card-small overflow-hidden mb-4">
                            <div class="card-header bg-dark">
                                <h6 class="m-0 text-white">Inactive Users</h6>
                            </div>
                            <div class="card-body p-0 pb-3 bg-dark text-center">
                                <table class="table table-dark mb-0">
                                    <thead class="thead-dark">
                                    <tr>
                                        <th scope="col" class="border-bottom-0">#</th>
                                        <th scope="col" class="border-bottom-0">First Name</th>
                                        <th scope="col" class="border-bottom-0">Last Name</th>
                                        <th scope="col" class="border-bottom-0">Country</th>
                                        <th scope="col" class="border-bottom-0">City</th>
                                        <th scope="col" class="border-bottom-0">Phone</th>
                                    </tr>
                                    </thead>
                                    <tbody>
                                    <tr>
                                        <td>1</td>
                                        <td>Graham</td>
                                        <td>Brent</td>
                                        <td>Benin</td>
                                        <td>Ripabottoni</td>
                                        <td>1-512-760-9094</td>
                                    </tr>
                                    <tr>
                                        <td>2</td>
                                        <td>Clark</td>
                                        <td>Angela</td>
                                        <td>Estonia</td>
                                        <td>Borghetto di Vara</td>
                                        <td>1-660-850-1647</td>
                                    </tr>
                                    <tr>
                                        <td>3</td>
                                        <td>Wylie</td>
                                        <td>Joseph</td>
                                        <td>Korea, North</td>
                                        <td>Guelph</td>
                                        <td>325-4351</td>
                                    </tr>
                                    <tr>
                                        <td>4</td>
                                        <td>Garth</td>
                                        <td>Clementine</td>
                                        <td>Indonesia</td>
                                        <td>Narcao</td>
                                        <td>722-8264</td>
                                    </tr>
                                    </tbody>
                                </table>
                            </div>
                        </div>
                    </div>
                </div>
                <!-- End Default Dark Table -->
            </div>
            <footer class="main-footer d-flex p-2 px-3 bg-white border-top">
                <ul class="nav">
                    <li class="nav-item">
                        <a class="nav-link" href="/start">Home</a>
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

</body>
</html>