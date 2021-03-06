var app1 = new Vue({
    el : "#holding",
    data : {
        holdingInfo : null,
        count_company : 0,
        count_stock : 0,
        market_value : 0.00,
        pageNum : 1 ,
        total : 1
    },
    created : function () {
        this.getData()
    },
    methods : {
        getData : function () {
            this.getFirstPage()
        },
        getFirstPage : function () {
            var that = this
            axios.get('/getHoldingData',{
                params: {
                    pageNum : 1
                }
            }).then(function (resp) {
                var resdata = resp.data
                that.holdingInfo = resdata.holdingInfo
                that.count_company = resdata.count_company
                that.count_stock = resdata.count_stock
                that.market_value = resdata.marketValue
                that.pageNum = resdata.pageNum
                that.total = resdata.total
            }).catch(function (err) {
                console.log(err)
            })
        },
        getPreviousPage : function () {
            console.log("getHoldingData");
            var that = this
            if (that.pageNum == 1){
                alert("当前已经是第一页")
                return
            }
            that.pageNum = that.pageNum - 1
            axios.get('/getHoldingData',{
                params: {
                    pageNum:that.pageNum
                }
            }).then(function (resp) {
                var resdata = resp.data
                that.holdingInfo = resdata.holdingInfo
                that.count_company = resdata.count_company
                that.count_stock = resdata.count_stock
                that.market_value = resdata.marketValue
                that.pageNum = resdata.pageNum
                that.total = resdata.total
            }).catch(function (err) {
                console.log(err)
            })
        },
        getNextPage : function () {
            console.log("getNextPage");
            var that = this
            if (that.pageNum == that.total){
                alert("已经是最后一页")
                return
            }
            that.pageNum = that.pageNum + 1
            axios.get('/getHoldingData',{
                params: {
                    pageNum:that.pageNum
                }
            }).then(function (resp) {
                var resdata = resp.data
                that.holdingInfo = resdata.holdingInfo
                that.count_company = resdata.count_company
                that.count_stock = resdata.count_stock
                that.market_value = resdata.marketValue
                that.pageNum = resdata.pageNum
                that.total = resdata.total
            }).catch(function (err) {
                console.log(err)
            })
        },
        getLastPage : function () {
            var that = this
            if (that.pageNum == that.total){
                alert("已经是最后一页")
                return
            }
            axios.get('/getHoldingData',{
                params: {
                    pageNum:that.total
                }
            }).then(function (resp) {
                var resdata = resp.data
                that.holdingInfo = resdata.holdingInfo
                that.count_company = resdata.count_company
                that.count_stock = resdata.count_stock
                that.market_value = resdata.marketValue
                that.pageNum = resdata.pageNum
                that.total = resdata.total
            }).catch(function (err) {
                console.log(err)
            })
        }
    }
})