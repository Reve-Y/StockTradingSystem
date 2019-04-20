var he = new Vue({
    el : "#history-entrust",
    data : {
        historyInfo: null,
        count_history: 0,
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
            axios.get('/getHistoryEntrustData',{
                params: {
                    pageNum:1
                }
            }).then(function (resp) {
                // console.log(resp)
                var resdata = resp.data
                that.historyInfo = resdata.historyInfo
                that.count_history = resdata.count_history
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
            axios.get('/getHistoryEntrustData',{
                params: {
                    pageNum:that.pageNum
                }
            }).then(function (resp) {
                // console.log(resp)
                var resdata = resp.data
                that.historyInfo = resdata.historyInfo
                that.count_history = resdata.count_history
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
            axios.get('/getHistoryEntrustData',{
                params: {
                    pageNum:that.pageNum
                }
            }).then(function (resp) {
                // console.log(resp)
                var resdata = resp.data
                that.historyInfo = resdata.historyInfo
                that.count_history = resdata.count_history
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
            axios.get('/getHistoryEntrustData',{
                params: {
                    pageNum:that.total
                }
            }).then(function (resp) {
                // console.log(resp)
                var resdata = resp.data
                that.historyInfo = resdata.historyInfo
                that.count_history = resdata.count_history
                that.pageNum = resdata.pageNum
                that.total = resdata.total
            }).catch(function (err) {
                console.log(err)
            })
        }
    }
})