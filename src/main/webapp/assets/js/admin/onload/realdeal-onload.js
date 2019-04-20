var deal = new Vue({
    el : "#dealData",
    data : {
        dealInfo: null,
        count_deal : 0,
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
            axios.get('/getRealDealData',{
                params: {
                    pageNum:1
                }
            }).then(function (resp) {
                // console.log(resp)
                var resdata = resp.data
                that.dealInfo = resdata.dealInfo
                that.count_deal = resdata.count_deal
                that.pageNum = resdata.pageNum
                that.total = resdata.total
            }).catch(function (err) {
                console.log(err)
            })
        },
        getPreviousPage : function () {
            console.log("getPreviousPage");
            var that = this
            if (that.pageNum == 1){
                alert("当前已经是第一页")
                return
            }
            that.pageNum = that.pageNum - 1
            axios.get('/getRealDealData',{
                params: {
                    pageNum:that.pageNum
                }
            }).then(function (resp) {
                // console.log(resp)
                var resdata = resp.data
                that.dealInfo = resdata.dealInfo
                that.count_deal = resdata.count_deal
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
            axios.get('/getRealDealData',{
                params: {
                    pageNum:that.pageNum
                }
            }).then(function (resp) {
                // console.log(resp)
                var resdata = resp.data
                that.dealInfo = resdata.dealInfo
                that.count_deal = resdata.count_deal
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
            axios.get('/getRealDealData',{
                params: {
                    pageNum:that.total
                }
            }).then(function (resp) {
                // console.log(resp)
                var resdata = resp.data
                that.dealInfo = resdata.dealInfo
                that.count_deal = resdata.count_deal
                that.pageNum = resdata.pageNum
                that.total = resdata.total
            }).catch(function (err) {
                console.log(err)
            })
        }
    }
})