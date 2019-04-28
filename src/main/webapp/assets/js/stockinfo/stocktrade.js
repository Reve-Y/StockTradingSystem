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


var entrust = new Vue({
    el : "#entrust",
    data : {
        stock_code : '',
        stock_name : '',
        entrust_direction : null,
        entrust_amount : null,
        entrust_price : null,

        //  显示帮助信息
        message_stockname:null,
        message_amount:null,
        message_price:null,
        message_balance:null
    },
    computed : {
        amount_money : function () {
            return parseFloat(this.entrust_amount * this.entrust_price * 100)
        }
    },
    methods : {
        //  输入代码后获取股票名称
        getStockName : function () {
            var that = this
            alert("getStockName by code:"+that.stock_code)

            axios.get('/getStockNameByCode',{
                params: {
                    stock_code : that.stock_code
                }
            }).then(function (resp) {
                that.stock_name = resp.data.stock_name
            }).catch(function (err) {
                console.log(err)
            })
        },

        // 输入委托方向后，根据委托方向来获取信息
        // 1. 买入时  获取这只股票的即时信息，并自动计算最大可买入值
        // 2. 卖出时  获取持仓情况
        getInfo : function (e) {
            var that = this
            that.entrust_direction = e.target.value

            if (that.entrust_direction == 1){
                console.log("根据代码"+that.stock_code+"获取即使信息")
                axios.get('/getBuyInfoByStockCode',{
                    params: {
                        stock_code : that.stock_code
                    }
                }).then(function (resp) {
                    var resdata = resp.data

                }).catch(function (err) {
                    console.log(err)
                })
            }else{
                console.log("根据代码"+that.stock_code+"获取该支股票持仓信息")
                axios.get('/getHoldingInfoByStockCode',{
                    params: {
                        stock_code : that.stock_code
                    }
                }).then(function (resp) {
                    var resdata = resp.data

                }).catch(function (err) {
                    console.log(err)
                })
            }
        },

        // confirm确认
        checkForm : function (e){
            if (!confirm("确认这笔委托？"))
                e.preventDefault()
        }
    }
})
