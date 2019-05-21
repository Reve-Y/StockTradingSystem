var ohlc = []
var volume = []
var chart = null
var stock = {
    symbol:'sh600570',  //默认值
    scale : '5',
    ma:'5',
    datalen:'1023',
    name:'恒生电子'
}

Highcharts.setOptions({
    lang: {
        rangeSelectorZoom: ' '
    },
    credits:{
        enabled: false // 禁用版权信息
    }
});

var app = new Vue({
    el:"#stockinfo",
    created : function () {
        this.getData()
    },
    methods : {
        // 获取股票信息，参数保存在stock对象里
        getData : function () {
            chart = null  //  以后会再次调用这个方法 所以先把之前的数据清空。
            ohlc = []
            volume = []
            axios.get('/getHistoryData',{
                params: {
                    symbol:stock.symbol,
                    scale:stock.scale,
                    ma:stock.ma,
                    datalen:stock.datalen,
                }
            }).then(function (resp) {
                // console.log(resp.data)
                var stockdata = eval('('+resp.data+')')
                console.log("获取到数据，数组长度为"+stockdata.length)
                // console.log(stockdata)
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
                        id: 'ohlc',
                        name: stock.name,
                        data: ohlc
                    }, {
                        type: 'column',
                        id: 'volume',
                        name: 'Volume',
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
        enable_amount : -1,
        price_flag:false
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
        },
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
            stock.name = this.message_stockname
            if (this.stock_code[0] == '6')
                stock.symbol = "sh" + this.stock_code
            else
                stock.symbol = "sz" + this.stock_code
            app.getData()
            basicInfo.getBasicInfo()
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
            this.price_flag = false
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

        // 检查委托价格是否在开盘价波动百分之十以内
        checkPrice : function () {
            var that = this
            var code = '';
            if (this.stock_code[0] == '6')
                code = "sh" + this.stock_code
            else
                code = "sz" + this.stock_code
            axios.get('/getBasicStockInfo',{
                params: {
                    stock_code : code
                }
            }).then(function (resp) {
                var resdata = resp.data
                var open_price = parseFloat(resdata.open_price)
                console.log(that.stock_code+"开盘价为"+open_price)
                var price_tmp = parseFloat(that.entrust_price)
                if ((price_tmp > (open_price * 1.1)) || (price_tmp < (open_price * 0.9))) {
                    that.price_flag = false
                } else {
                    that.price_flag = true
                }
                console.log("price_flag: "+that.price_flag)
            }).catch(function (err) {
                console.log(err)
            })
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

            this.checkPrice()
            if (!this.price_flag){
                alert("委托价格应该限制在开盘价上下浮动的百分之十范围内！");
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

var basicInfo2 = new Vue({
    el:"#basicInfo2",
    data : {
        buy_amount1:0,               // 买一数量
        buy_price1:0,                // 买一价格
        buy_amount2:0,               // 买二数量
        buy_price2:0,                // 买二价格
        buy_amount3:0,               // 买三数量
        buy_price3:0,                // 买三价格
        buy_amount4:0,               // 买四数量
        buy_price4:0,                // 买四价格
        buy_amount5:0,               // 买五数量
        buy_price5:0,                // 买五价格
        sell_amount1:0,               // 卖一数量
        sell_price1:0,                // 卖一价格
        sell_amount2:0,               // 卖二数量
        sell_price2:0,                // 卖二价格
        sell_amount3:0,               // 卖三数量
        sell_price3:0,                // 卖三价格
        sell_amount4:0,               // 卖四数量
        sell_price4:0,                // 卖四价格
        sell_amount5:0,               // 卖五数量
        sell_price5:0,                // 卖五价格
    }
})

var basicInfo = new Vue({
    el:"#basicInfo",
    data:{
        stock_name:'',               // 证券名称
        // total_equity:0,              // 总股本
        // circulation_capital:0,       // 流通股本
        // total_market_value:0,        // 总市值
        // circulation_market_value:0 , // 流通市值
        today_open:0,                   // 今日开盘价
        yes_close:0,                    // 昨日收盘价
        current_price:0,                // 当前价格
        today_high:0,                   // 今日最高价
        today_low:0,                    // 今日最低价
    },
    created : function () {
      this.getBasicInfo()
    },
    methods : {
        // 获取股票基本信息
        getBasicInfo : function () {
            var that = this
            axios.get('/getBasicStockInfo',{
                params: {
                    stock_code:stock.symbol
                }
            }).then(function (resp) {
                var resdata = resp.data

                that.stock_name = resdata.company_name
                that.today_open = resdata.open_price
                that.yes_close = resdata.yesterday_closed_price
                that.current_price = resdata.last_price
                that.today_high = resdata.max_price
                that.today_low = resdata.min_price

                basicInfo2.buy_amount1 = resdata.buy_amount1
                basicInfo2.buy_price1 = resdata.buy_price1
                basicInfo2.buy_amount2 = resdata.buy_amount2
                basicInfo2.buy_price2 = resdata.buy_price2
                basicInfo2.buy_amount3 = resdata.buy_amount3
                basicInfo2.buy_price3 = resdata.buy_price3
                basicInfo2.buy_amount4 = resdata.buy_amount4
                basicInfo2.buy_price4 = resdata.buy_price4
                basicInfo2.buy_amount5 = resdata.buy_amount5
                basicInfo2.buy_price5 = resdata.buy_price5

                basicInfo2.sell_amount1 = resdata.sale_amount1
                basicInfo2.sell_price1 = resdata.sale_price1
                basicInfo2.sell_amount2 = resdata.sale_amount2
                basicInfo2.sell_price2 = resdata.sale_price2
                basicInfo2.sell_amount3 = resdata.sale_amount3
                basicInfo2.sell_price3 = resdata.sale_price3
                basicInfo2.sell_amount4 = resdata.sale_amount4
                basicInfo2.sell_price4 = resdata.sale_price4
                basicInfo2.sell_amount5 = resdata.sale_amount5
                basicInfo2.sell_price5 = resdata.sale_price5

            }).catch(function (err) {
                console.log(err)
            })
        }
    }
})