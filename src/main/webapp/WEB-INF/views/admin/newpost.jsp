<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!doctype html>
<html class="no-js h-100" lang="en">
  <head>
    <meta charset="utf-8">
    <meta http-equiv="x-ua-compatible" content="ie=edge">
    <title>Shards Dashboard Lite - Free Bootstrap Admin Template – DesignRevision</title>
    <meta name="description" content="A high-quality &amp; free Bootstrap admin dashboard template pack that comes with lots of templates and components.">
    <meta name="viewport" content="width=device-width, initial-scale=1, shrink-to-fit=no">
    <link href="https://use.fontawesome.com/releases/v5.0.6/css/all.css" rel="stylesheet">
    <link href="https://fonts.googleapis.com/icon?family=Material+Icons" rel="stylesheet">
    <link rel="stylesheet" href="../../../assets/css/bootstrap.min.css">
    <link rel="stylesheet" id="main-stylesheet" data-version="1.1.0" href="../../../assets/css/admin/shards-dashboards.1.1.0.min.css">
    <link rel="stylesheet" href="../../../assets/css/admin/extras.1.1.0.min.css">
    <script async defer src="../../../assets/js/admin/buttons.js"></script>
    <link rel="stylesheet" href="../../../assets/css/admin/quill.snow.css"> </head>
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
                <a class="nav-link active" href="/newPost">
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
                <a class="nav-link " href="/userProfile">
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
                <span class="text-uppercase page-subtitle">Blog Posts</span>
                <h3 class="page-title">Add New Post</h3>
              </div>
            </div>
            <!-- End Page Header -->
            <div class="row">
              <div class="col-lg-9 col-md-12">
                <!-- Add New Post Form -->
                <div class="card card-small mb-3">
                  <div class="card-body">
                    <form class="add-new-post">
                      <input class="form-control form-control-lg mb-3" type="text" placeholder="Your Post Title">
                      <div id="editor-container" class="add-new-post__editor mb-1"></div>
                    </form>
                  </div>
                </div>
                <!-- / Add New Post Form -->
              </div>
              <div class="col-lg-3 col-md-12">
                <!-- Post Overview -->
                <div class='card card-small mb-3'>
                  <div class="card-header border-bottom">
                    <h6 class="m-0">Actions</h6>
                  </div>
                  <div class='card-body p-0'>
                    <ul class="list-group list-group-flush">
                      <li class="list-group-item p-3">
                        <span class="d-flex mb-2">
                          <i class="material-icons mr-1">flag</i>
                          <strong class="mr-1">Status:</strong> Draft
                          <a class="ml-auto" href="#">Edit</a>
                        </span>
                        <span class="d-flex mb-2">
                          <i class="material-icons mr-1">visibility</i>
                          <strong class="mr-1">Visibility:</strong>
                          <strong class="text-success">Public</strong>
                          <a class="ml-auto" href="#">Edit</a>
                        </span>
                        <span class="d-flex mb-2">
                          <i class="material-icons mr-1">calendar_today</i>
                          <strong class="mr-1">Schedule:</strong> Now
                          <a class="ml-auto" href="#">Edit</a>
                        </span>
                        <span class="d-flex">
                          <i class="material-icons mr-1">score</i>
                          <strong class="mr-1">Readability:</strong>
                          <strong class="text-warning">Ok</strong>
                        </span>
                      </li>
                      <li class="list-group-item d-flex px-3">
                        <button class="btn btn-sm btn-outline-accent">
                          <i class="material-icons">save</i> Save Draft</button>
                        <button class="btn btn-sm btn-accent ml-auto">
                          <i class="material-icons">file_copy</i> Publish</button>
                      </li>
                    </ul>
                  </div>
                </div>
                <!-- / Post Overview -->
                <!-- Post Overview -->
                <div class='card card-small mb-3'>
                  <div class="card-header border-bottom">
                    <h6 class="m-0">Categories</h6>
                  </div>
                  <div class='card-body p-0'>
                    <ul class="list-group list-group-flush">
                      <li class="list-group-item px-3 pb-2">
                        <div class="custom-control custom-checkbox mb-1">
                          <input type="checkbox" class="custom-control-input" id="category1" checked>
                          <label class="custom-control-label" for="category1">Uncategorized</label>
                        </div>
                        <div class="custom-control custom-checkbox mb-1">
                          <input type="checkbox" class="custom-control-input" id="category2" checked>
                          <label class="custom-control-label" for="category2">Design</label>
                        </div>
                        <div class="custom-control custom-checkbox mb-1">
                          <input type="checkbox" class="custom-control-input" id="category3">
                          <label class="custom-control-label" for="category3">Development</label>
                        </div>
                        <div class="custom-control custom-checkbox mb-1">
                          <input type="checkbox" class="custom-control-input" id="category4">
                          <label class="custom-control-label" for="category4">Writing</label>
                        </div>
                        <div class="custom-control custom-checkbox mb-1">
                          <input type="checkbox" class="custom-control-input" id="category5">
                          <label class="custom-control-label" for="category5">Books</label>
                        </div>
                      </li>
                      <li class="list-group-item d-flex px-3">
                        <div class="input-group">
                          <input type="text" class="form-control" placeholder="New category" aria-label="Add new category" aria-describedby="basic-addon2">
                          <div class="input-group-append">
                            <button class="btn btn-white px-2" type="button">
                              <i class="material-icons">add</i>
                            </button>
                          </div>
                        </div>
                      </li>
                    </ul>
                  </div>
                </div>
                <!-- / Post Overview -->
              </div>
            </div>
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
    
    <script src="../../../assets/js/core/jquery-3.3.1.js"></script>
    <script src="../../../assets/js/core/popper.min.js" ></script>
    <script src="../../../assets/js/core/bootstrap.js"></script>
    <script src="../../../assets/js/admin/Chart.min.js"></script>
    <script src="../../../assets/js/admin/shards.min.js"></script>
    <script src="../../../assets/js/admin/jquery.sharrre.min.js"></script>
    <script src="../../../assets/js/admin/extras.1.1.0.min.js"></script>
    <script src="../../../assets/js/admin/shards-dashboards.1.1.0.min.js"></script>
    <script src="../../../assets/js/admin/quill.min.js"></script>
    <script src="../../../assets/js/admin/app/app-blog-new-post.1.1.0.js"></script>
  </body>
</html>