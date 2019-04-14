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
        <div class="d3 dd">
            输入表单
        </div>
    </div>
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
            el:"#stockinfo",
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
                        var stockdata = eval('('+resp.data+')')
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
</body>
</html>
