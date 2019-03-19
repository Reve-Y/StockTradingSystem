<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%--
  Created by IntelliJ IDEA.
  User: Reve
  Date: 2019-03-14
  Time: 13:35
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>

<nav class="navbar navbar-default navbar-static-top">
    <div class="container">
        <div class="navbar-header">
            <button type="button" class="navbar-toggle collapsed" data-toggle="collapse" data-target="#navbar" aria-expanded="false" aria-controls="navbar">
                <span class="sr-only">Toggle navigation</span>
                <span class="icon-bar"></span>
                <span class="icon-bar"></span>
                <span class="icon-bar"></span>
            </button>
            <a class="navbar-brand" href="/home">StockTradingSystem</a>
        </div>
        <div id="navbar" class="navbar-collapse collapse">
            <ul class="nav navbar-nav">
                <li class="active"><a href="/home">Home</a></li>
                <li><a href="#about">About</a></li>
                <li><a href="#contact">Contact</a></li>
            </ul>
            <c:choose>
                <c:when test="${empty user}">
                    <ul class=" nav navbar-nav navbar-right">
                        <li><a href="/registerPage">注册</a></li>
                        <li class="active"><a href="/login">登录<span class="sr-only">(current)</span></a></li>
                    </ul>
                </c:when>
                <c:otherwise>
                    <ul class=" nav navbar-nav navbar-right">
                        <li><a href="#">hello,</a></li>
                        <c:choose>
                            <c:when test="${user.nickname != ''}">
                                <li class="active"><a href="#">${user.nickname} <span class="sr-only">(current)</span></a></li>
                            </c:when>
                            <c:otherwise>
                                <li class="active"><a href="#">${user.telephone} <span class="sr-only">(current)</span></a></li>
                            </c:otherwise>
                        </c:choose>
                    </ul>
                </c:otherwise>
            </c:choose>

        </div><!--/.nav-collapse -->
    </div>
</nav>

