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
    <%--<script src="../../assets/js/highcharts/sand-signika.js"></script>--%>

</head>
<body>
    <%@include file="navbar.jsp"%>
    <div id="stockinfo" class="chart">加载中</div>
    <div class="container1">
        <div class="d1 dd"  id="basicInfo">
            <div class="dd"><h4 style="text-align:center">基本信息</h4></div>
            <div style="height:30px"></div>
            <ul>
                <li>名称：{{stock_name}}</li>
                <%--<li>总股本:{{total_equity}}</li>--%>
                <%--<li>流通股本{{circulation_capital}}</li>--%>
                <%--<li>总市值:{{total_market_value}}</li>--%>
                <%--<li>流通市值:{{circulation_market_value}}</li>--%>
                <li>今开：{{today_open}}</li>
                <li>昨收：{{yes_close}}</li>
                <li>现价：{{current_price}}</li>
                <li>今高：{{today_high}}</li>
                <li>今低：{{today_low}}</li>
            </ul>
        </div>
        <div class="d2 dd"  id="basicInfo2">
            <div class="dd"><h4 style="text-align:center">十档行情</h4></div>
            <div style="height:10px"></div>
            <table class=" table-condensed table-style">
                <tr>
                    <td class="tdhead">买一</td>
                    <td>{{buy_amount1}}</td>
                    <td>{{buy_price1}}元</td>
                </tr>
                <tr>
                    <td class="tdhead">买二</td>
                    <td>{{buy_amount2}}</td>
                    <td>{{buy_price2}}元</td>
                </tr>
                <tr>
                    <td class="tdhead">买三</td>
                    <td>{{buy_amount3}}</td>
                    <td>{{buy_price3}}元</td>
                </tr>
                <tr>
                    <td class="tdhead">买四</td>
                    <td>{{buy_amount4}}</td>
                    <td>{{buy_price4}}元</td>
                </tr>
                <tr>
                    <td class="tdhead">买五</td>
                    <td>{{buy_amount5}}</td>
                    <td>{{buy_price5}}元</td>
                </tr>
            </table>
            <br>
            <!-- <hr style="size: 2px"> -->
            <table class=" table-condensed table-style">
                <tr>
                    <td class="tdhead">卖一</td>
                    <td>{{sell_amount1}}</td>
                    <td>{{sell_price1}}元</td>
                </tr>
                <tr>
                    <td class="tdhead">卖二</td>
                    <td>{{sell_amount2}}</td>
                    <td>{{sell_price2}}元</td>
                </tr>
                <tr>
                    <td class="tdhead">卖三</td>
                    <td>{{sell_amount3}}</td>
                    <td>{{sell_price3}}元</td>
                </tr>
                <tr>
                    <td class="tdhead">卖四</td>
                    <td>{{sell_amount4}}</td>
                    <td>{{sell_price4}}元</td>
                </tr>
                <tr>
                    <td class="tdhead">卖五</td>
                    <td>{{sell_amount5}}</td>
                    <td>{{sell_price5}}元</td>
                </tr>
            </table>
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
                    <div class="dd"><h4 style="text-align:center">限价交易</h4></div>
                    <div class="d3-2 dd">
                        <br>
                        <ul>
                            <div class="input-group input-compo" style="width:281px">
                                <span class="input-group-addon" id="basic-addon1">证券代码</span>
                                <input type="text" class="form-control" style="width:200px"
                                       v-model="stock_code" aria-describedby="basic-addon1" @blur="getStockName">
                            </div>
                            <div class="box"></div>
                            <div class="message" id="m1"><a @click="getStockDetail" class="info">{{message_stockname}}</a></div>
                        </ul>
                        <ul>
                            <div class="input-group input-compo" style="width:281px">
                                <span class="input-group-addon" id="basic-addon2">委托方向</span>
                                <select class="form-control" style="width:200px"
                                        v-model="entrust_direction" @blur="getInfo" >
                                    <option value="1" >买入</option>
                                    <option value="2" >卖出</option>
                                </select>
                            </div>
                            <div class="box"></div>
                            <div class="message" id="m2"><a id="alertinfo"></a></div>
                        </ul>
                        <ul>
                            <div class="input-group input-compo" style="width:281px">
                                <span class="input-group-addon" id="basic-addon3">委托数量</span>
                                <input type="text" class="form-control"  style="width:114px"
                                       v-model="entrust_amount" aria-describedby="basic-addon3">
                                <span class="input-group-addon">手(100股)</span>
                            </div>
                            <div class="box"></div>
                            <div class="message" id="m3">{{message_amount}}</div>
                        </ul>
                        <ul>
                            <div class="input-group input-compo" style="width:281px">
                                <span class="input-group-addon" id="basic-addon4">委托价格</span>
                                <input type="text" class="form-control" style="width:161px"
                                       v-model="entrust_price" aria-describedby="basic-addon4">
                                <span class="input-group-addon">元</span>
                            </div>
                            <div class="box"></div>
                            <div class="message" id="m4">{{message_price}}</div>
                        </ul>
                        <ul>
                            <div class="input-group input-compo" style="width:281px">
                                <span class="input-group-addon" id="basic-addon5">委托金额</span>
                                <input type="text" class="form-control" style="width:161px"
                                       v-model="amount_money" aria-describedby="basic-addon5">
                                <span class="input-group-addon">元</span>
                            </div>
                            <div class="box"></div>
                            <div class="message" id="m5">{{message_balance}}</div>
                        </ul>
                        <ul>
                            <button @click="checkForm" class="btn btn-default" style="width:281px">{{submit_info}}</button>
                        </ul>
                    </div>
                </div>
            </c:otherwise>
        </c:choose>
    </div>

    <script src="../../assets/js/stockinfo/stocktrade.js"></script>
</body>
</html>
