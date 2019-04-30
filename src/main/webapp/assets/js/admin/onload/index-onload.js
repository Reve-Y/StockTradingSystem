var app1 = new Vue({
    el : "#infobox",
    data : {
        total_market_value : '0',
        earnings : '0' ,
        enable_balance : '0' ,
        frozen_balance : '0' ,
        entrust_num : '0' ,
        telephone : '1',
        nickname : '1',
        securitiesAccount : '1',
        capitalAccount : '1',
        email : '1',
    },
    created: function () {
        this.getData()
    },
    methods : {
        getData : function (){
            var that = this // 坑比Vue
            axios.get('/getAdminHomePageData',{
                params: {
                }
            }).then(function (resp) {
                console.log(resp.data)
                var resdata = resp.data

                that.total_market_value = resdata.total_market_value
                that.earnings = resdata.earnings
                that.enable_balance = resdata.enable_balance
                that.frozen_balance = resdata.frozen_balance
                that.entrust_num = resdata.entrust_num
                that.telephone = resdata.telephone
                that.nickname = resdata.nickname
                that.securitiesAccount = resdata.securitiesAccount
                that.capitalAccount = resdata.capitalAccount
                that.email = resdata.email

            }).catch(function (err) {
                console.log(err)
            })
        }
    }

})