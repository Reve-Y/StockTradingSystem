var app1 = new Vue({
    el : "#holding",
    data : {

    },
    created : function () {
        this.getData()
    },
    methods : {
        getData : function () {
            var that = this
            axios.get('/getHoldingData',{
                params: {
                }
            }).then(function (resp) {
                console.log(resp)
                var resdata = resp.data

            }).catch(function (err) {
                console.log(err)
            })
        }
    }
})