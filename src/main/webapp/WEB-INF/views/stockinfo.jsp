<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html>
<html>
<head>
    <meta http-equiv="content-type" content="text/html" charset="utf-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <meta http-equiv="X-UA-Compatible" content="ie=edge">
    <title>股票信息与交易</title>
    <link rel="stylesheet" href="../../assets/css/bootstrap3.min.css">
    <link rel="stylesheet" href="../../assets/css/stockinfo/stocktrade.css">
    <style>
        #stockinfo {
            width:99%;
            height: 280px;
            margin: 0 auto;
        }
    </style>
    <script src="../../assets/js/core/vue.js"></script>
    <script src="../../assets/js/core/vue-resource.js"></script>
    <script src="../../assets/js/core/axios.js"></script>
    <script src="../../assets/js/core/jquery-3.3.1.js"></script>
    <script src="../../assets/js/core/bootstrap.js"></script>
    <script src="../../assets/js/highcharts/highstock.js"></script>
    <script src="../../assets/js/highcharts/sand-signika.js"></script>

</head>
<body>
    <%@include file="navbar.jsp"%>
    <div id="stockinfo" class="chart">加载中</div>
    <div class="container1">
        <div class="d1 dd">
            基本信息
        </div>
        <div class="d2 dd">
            十档行情、其他信息
        </div>
        <c:choose>
            <c:when test="${empty user}">
                <div class="d3 dd" id="entrust">
                    <div class="d3-1 dd">
                        <h4>点此<a href="/login">登录</a>以进行交易...</h4>
                    </div>
                    <div class="d3-2 dd">
                    </div>
                </div>
            </c:when>
            <c:otherwise>
                <div class="d3 dd" id="entrust">
                    <div class="d3-1 dd">

                    </div>
                    <div class="d3-2 dd">
                        <form action="/doentrust" method="POST" @submit="checkForm">
                            <br>
                            <ul>
                                <div class="input-group input-compo" style="width:281px">
                                    <span class="input-group-addon" id="basic-addon1">证券代码</span>
                                    <input type="text" class="form-control" style="width:200px" name="stock_code"
                                           v-model="stock_code" aria-describedby="basic-addon1" @blur="getStockName">
                                </div>
                                <div class="box"></div>
                                <div class="message" id="m1"><a @click="getStockDetail" class="info">{{message_stockname}}</a></div>
                            </ul>
                            <ul>
                                <div class="input-group input-compo" style="width:281px">
                                    <span class="input-group-addon" id="basic-addon2">委托方向</span>
                                    <select class="form-control" style="width:200px" name="entrust_direction"
                                            v-model="entrust_direction" @blur="getInfo" >
                                        <option value="1" >买入</option>
                                        <option value="2" >卖出</option>
                                    </select>
                                </div>
                                <div class="box"></div>
                                <div class="message" id="m2"></div>
                            </ul>
                            <ul>
                                <div class="input-group input-compo" style="width:281px">
                                    <span class="input-group-addon" id="basic-addon3">委托数量</span>
                                    <input type="text" class="form-control"  style="width:114px" name="entrust_amount"
                                           v-model="entrust_amount" aria-describedby="basic-addon3">
                                    <span class="input-group-addon">手(100股)</span>
                                </div>
                                <div class="box"></div>
                                <div class="message" id="m3">{{message_amount}}</div>
                            </ul>
                            <ul>
                                <div class="input-group input-compo" style="width:281px">
                                    <span class="input-group-addon" id="basic-addon4">委托价格</span>
                                    <input type="text" class="form-control" style="width:161px" name="entrust_price"
                                           v-model="entrust_price" aria-describedby="basic-addon4">
                                    <span class="input-group-addon">元</span>
                                </div>
                                <div class="box"></div>
                                <div class="message" id="m4">{{message_price}}</div>
                            </ul>
                            <ul>
                                <div class="input-group input-compo" style="width:281px">
                                    <span class="input-group-addon" id="basic-addon5">委托金额</span>
                                    <input type="text" class="form-control" style="width:161px" name="amount_money"
                                           v-model="amount_money" aria-describedby="basic-addon5">
                                    <span class="input-group-addon">元</span>
                                </div>
                                <div class="box"></div>
                                <div class="message" id="m5">{{message_balance}}</div>
                            </ul>
                        </form>
                    </div>
                </div>
            </c:otherwise>
        </c:choose>
    </div>

    <script src="../../assets/js/stockinfo/stocktrade.js"></script>
</body>
</html>
