var ce = new Vue({
    el : "#entrustData",
    data : {
        entrustInfo: null,
        count_entrust: 0,
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
            axios.get('/getCurrentEntrustData',{
                params: {
                    pageNum:1
                }
            }).then(function (resp) {
                // console.log(resp)
                var resdata = resp.data
                that.entrustInfo = resdata.entrustInfo
                that.count_entrust = resdata.count_entrust
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
            axios.get('/getCurrentEntrustData',{
                params: {
                    pageNum:that.pageNum
                }
            }).then(function (resp) {
                // console.log(resp)
                var resdata = resp.data
                that.entrustInfo = resdata.entrustInfo
                that.count_entrust = resdata.count_entrust
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
            axios.get('/getCurrentEntrustData',{
                params: {
                    pageNum:that.pageNum
                }
            }).then(function (resp) {
                // console.log(resp)
                var resdata = resp.data
                that.entrustInfo = resdata.entrustInfo
                that.count_entrust = resdata.count_entrust
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
            axios.get('/getCurrentEntrustData',{
                params: {
                    pageNum:that.total
                }
            }).then(function (resp) {
                // console.log(resp)
                var resdata = resp.data
                that.entrustInfo = resdata.entrustInfo
                that.count_entrust = resdata.count_entrust
                that.pageNum = resdata.pageNum
                that.total = resdata.total
            }).catch(function (err) {
                console.log(err)
            })
        },

        // 撤单
        withdraw : function (e) {
            var flag = confirm("确定撤单？")
            if (!flag)
                return

            var that = this
            var btn = e.target
            // console.log(btn.getAttribute("id"))
            var entrust_key = btn.getAttribute("id");

            axios.get('/withdrawEntrust',{
                params: {
                    entrust_key:entrust_key
                }
            }).then(function (resp) {
                // console.log(resp)
                var resdata = resp.data
                console.log(resdata.status)
                if (resdata.status == "ok")
                    alert("撤单成功")
                else
                    alert("撤单失败")
                that.getFirstPage()
            }).catch(function (err) {
                console.log(err)
            })

        }
    }
})