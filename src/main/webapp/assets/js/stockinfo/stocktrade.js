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
        entrust_direction : 1,
        entrust_amount : null,      // 注意，单位是手（100）股
        entrust_price : null,

        //  显示帮助信息
        message_stockname:null,
        message_amount:null,
        message_price:null,
        message_balance:null,

        enable_balance : -1,
        enable_amount : -1
    },
    computed : {
        amount_money : function () {
            // 若委托方向为 1 ，则计算委托金额,否则不计算
            if (this.entrust_direction == 1)
                return parseFloat(this.entrust_amount * this.entrust_price * 100)
            else{
                return 0;
            }
        },
        submit_info : function () {
            if (this.entrust_direction == 1)
                return "确认买入"
            else{
                return "确认卖出"
            }
        }
    },
    methods : {
        //  输入代码后获取股票名称
        getStockName : function () {
            var that = this
            if (this.ifClearMsg())
                return

            axios.get('/getStockNameByCode',{
                params: {
                    stock_code : that.stock_code
                }
            }).then(function (resp) {
                if (resp.data.errCode == "2"){
                    that.justClearMsg()
                    that.message_stockname = "错误的证券代码"
                    return
                }
                var stock_name = resp.data.stock_name
                that.message_stockname = stock_name
                that.getInfo()
            }).catch(function (err) {
                console.log(err)
            })
        },

        // 输入委托方向后，根据委托方向来获取信息
        // 1. 买入时  获取这只股票的即时信息，并自动计算最大可买入值
        // 2. 卖出时  获取持仓情况
        getInfo : function (e) {
            if (this.ifClearMsg())
                return
            if (this.entrust_direction == 1){
                this.getBuyInfo()
            }else{
                this.getHoldingInfo()
            }
        },

        // 获取股票详细信息，即刷新折线图等
        getStockDetail : function (){
            if (this.ifClearMsg())
                return
            alert("stock-detail")
        },

        // 获取这只股票的即时信息
        getBuyInfo : function (){
            var that = this
            console.log("根据代码"+that.stock_code+"获取即时信息")
            axios.get('/getBuyInfoByStockCode',{
                params: {
                    stock_code : that.stock_code
                }
            }).then(function (resp) {
                var resdata = resp.data
                if (resdata.errCode == "1"){
                    that.showAccountError()
                    return
                }else if (resdata.errCode == "2"){
                    that.justClearMsg()
                    that.message_stockname = "错误的证券代码"
                    return
                } else {
                    that.enable_balance = parseFloat(resdata.enable_balance)
                    var current_price = parseFloat(resdata.current_price)
                    var buy_max = (that.enable_balance/current_price).toFixed(2)
                    that.message_price = "现价为： "+current_price
                    that.message_amount = "最多可买入 "+buy_max+" 股"
                    that.message_balance = "当前可用余额 "+that.enable_balance+" 元"
                }
            }).catch(function (err) {
                console.log(err)
            })
        },

        // 获取该支股票的可用数量等
        getHoldingInfo : function (){
            var that = this
            console.log("根据代码"+that.stock_code+"获取该支股票持仓信息")
            axios.get('/getHoldingInfoByStockCode',{
                params: {
                    stock_code : that.stock_code
                }
            }).then(function (resp) {
                var resdata = resp.data
                if (resdata.errCode == "1"){
                    that.showAccountError()
                    return
                }else if(resdata.errCode == "2"){
                    that.justClearMsg();
                    that.message_stockname = "错误的证券代码"
                    return
                }

                var current_price = parseFloat(resdata.current_price)
                that.enable_amount = parseInt(resdata.enable_amount)
                if (that.enable_amount == 0) {
                    that.message_amount = "未持有这只股票"
                    that.enable_amount = -1
                }
                else {
                    that.message_amount = "该支股票当前持有 " + that.enable_amount + " 股"
                    that.message_price = "现价为 " + current_price + " 元，"
                }
                that.message_balance = ""
            }).catch(function (err) {
                console.log(err)
            })
        },

        // 股票代码为空时擦除提示信息
        ifClearMsg : function (){
            if (this.stock_code == "" || this.stock_code == null){
                this.justClearMsg()
                return true
            }
            return false
        },

        // 清除信息
        justClearMsg : function () {
            this.message_amount = ""
            this.message_balance = ""
            this.message_price = ""
            this.message_stockname = ""
            this.entrust_amount = null
            this.entrust_price = null
            this.enable_balance = -1
            this.enable_amount = -1
        },

        // 未开户时提示开户
        showAccountError : function () {
            // 提示之前先清除之前可能动态创建的标签
            var m2 = document.getElementById("m2")
            var nod = document.getElementById("alertinfo")
            m2.removeChild(nod)
            var newLable = document.createElement("span")
            newLable.innerHTML="当前尚未开户，请先<a id='alertinfo' href='/createAccount'>开户</a>以进行交易"
            m2.appendChild(newLable)
        },

        // 试图提交时检查数据合法性，并alert确认
        checkForm : function (e) {
            // 校验表单输入是否合法
            if (this.entrust_direction == 1) {
                // 买入时，委托金额不能大于可用余额
                if (this.amount_money > this.enable_balance) {
                    alert("余额不足，而且你也无法充值")
                    e.preventDefault()
                    return
                }
            } else {
                // 卖出时，卖出数量应小于当前可用数量
                if (this.entrust_amount * 100 > this.enable_amount) {
                    alert("当前持仓可用数量不足,无法卖出")
                    e.preventDefault()
                    return
                }
            }
            if (this.entrust_amount == null || this.entrust_price == null){
                alert("信息不完整")
                return
            }

            if (!confirm("确认这笔委托？")) {
                e.preventDefault()
                return
            }

            var that = this
            axios.get('/doentrust',{
                params: {
                    stock_code:that.stock_code,
                    entrust_direction:that.entrust_direction,
                    entrust_amount:that.entrust_amount*100,
                    entrust_price:that.entrust_price,
                    amount_money:that.amount_money
                }
            }).then(function (resp) {
                var resdata = resp.data
                if (resdata == "fail"){
                    alert("委托失败")
                    return
                }
                alert("委托成功")
                that.stock_code = ""
                that.ifClearMsg()
            }).catch(function (err) {
                console.log(err)
            })
        }
    }
})