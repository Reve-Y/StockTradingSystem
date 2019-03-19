<%--
  Created by IntelliJ IDEA.
  User: Reve
  Date: 2019-03-14
  Time: 14:21
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html>
<html>
<head>
    <meta http-equiv="content-type" content="text/html" charset="utf-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <meta http-equiv="X-UA-Compatible" content="ie=edge">
    <link rel="stylesheet" type="text/css" href="../../css/highcharts/gui.css">
    <link rel="stylesheet" type="text/css" href="../../css/highcharts/popup.css">
    <link rel="stylesheet" href="../../css/bootstrap.css">
    <script src="../../js/vue.js"></script>
    <script src="../../js/vue-resource.js"></script>
    <script src="../../js/axios.js"></script>
    <script src="../../js/jquery-3.3.1.js"></script>
    <script src="../../js/bootstrap.js"></script>
    <script src="../../js/highcharts/highstock.js"></script>
    <script src="../../js/highcharts/indicators-all.js"></script>
    <script src="../../js/highcharts/drag-panes.js"></script>
    <script src="../../js/highcharts/annotations-advanced.js"></script>
    <script src="../../js/highcharts/price-indicator.js"></script>
    <script src="../../js/highcharts/full-screen.js"></script>
    <script src="../../js/highcharts/stock-tools.js"></script>

    <style>
        #stockinfo {
            height: 300px;
            width: 100%
        }
        /* Conflict with Bootstrap, not needed after v7.0.1 */
        .highcharts-bindings-wrapper * {
            box-sizing: content-box;
        }
    </style>
    <title>股票交易模拟系统首页</title>
</head>
<body>
    <%@include file="navbar.jsp"%>

    <div id="stockinfo" class="chart">加载中</div>
    <div id="stock"></div>
    <script>
        var ohlc = []
        var volume = []
        Highcharts.setOptions({
            lang: {
                rangeSelectorZoom: ' '
            },
            credits:{
                enabled: false // 禁用版权信息
            }
        });
        var chart = null
        var app = new Vue({
            el:"#stock",
            created : function () {
               this.getData()
            },
            methods : {
                getData : function () {
                    axios.get('/getHistoryData',{
                        params: {
                            symbol:'sh600570',
                            scale:'5',
                            ma:'5',
                            datalen:'1023',
                        }
                    }).then(function (resp) {
                        console.log(resp.data)
                        var stockdata =eval('('+resp.data+')')
                        console.log(stockdata.length)
                        console.log(stockdata)
                        // split the data set into ohlc and volume
                        var i = 0
                        for (i; i < stockdata.length; i += 1) {
                            ohlc.push([
                                (new Date(stockdata[i].day)).getTime(),
                                parseFloat(stockdata[i].open),
                                parseFloat(stockdata[i].high),
                                parseFloat(stockdata[i].low),
                                parseFloat(stockdata[i].close)
                            ]);
                            volume.push([
                                (new Date(stockdata[i].day)).getTime(),
                                parseInt(stockdata[i].volume)
                            ]);
                        }
                        chart = Highcharts.stockChart('stockinfo', {
                            yAxis: [{
                                labels: {
                                    align: 'left'
                                },
                                height: '80%',
                                resize: {
                                    enabled: true
                                }
                            }, {
                                labels: {
                                    align: 'left'
                                },
                                top: '80%',
                                height: '20%',
                                offset: 0
                            }],
                            tooltip: {
                                split: false,
                                shared: true,
                            },
                            series: [{
                                type: 'candlestick',
                                id: 'HS-ohlc',
                                name: '恒生电子',
                                data: ohlc
                            }, {
                                type: 'column',
                                id: 'HS-volume',
                                name: 'HS Volume',
                                data: volume,
                                yAxis: 1
                            }],
                        });
                    }).catch(function (err) {
                        console.log(err)
                    })
                }
            }
        })
    </script>
    <%--<%@include file="footer.jsp" %>--%>
</body>
</html>
