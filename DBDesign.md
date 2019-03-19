### 数据库表设计：  
#### 模拟流程    
    注册(User) --> 开立证券账户(SecuritiesAccount)--> 开立资金账户(CapitalAccount) --> 委托


#### 1. tUSER 用户基本信息表    
* 用户user_id number(10)                 主键 非空  自增
* 手机号 telephone  varchar(11)           唯一
* 密码 password varchar(20)                     非空
* 用户昵称 nickname varchar(40)  
* 邮箱  email  varchar(30)                
* 性别 sex number(1)                      
* 年龄 age number(3)                      

#### 2. tUserAccount 用户-证券账户对应表   
* 用户id           user_id                varchar(20)   主键
* 证券公司id      securities_company_id    number(2)       
* 证券账户号码     securities_account_id    varchar(20)    

#### 3. tSecuritiesAccount 证券账户信息表    
* 证券账户号码：     securities_account_id      varchar(20)  主键  非空 
* 开户日期：         open_date                  varchar(8)    
* 开户证券公司       securities_company_name    varchar(30) 
* 资金账户id        account_id                  number(8)

#### 4. tCapitalAccount 资金账户表
* 资金账户序号       account_id             number(8) 主键 
* 开户行            bank_name              varchar(30) 
* 银行卡号           bank_card_number       varchar(20) 
* 账户余额          account_balance         number(10)  

#### 5. tSecurityCompany  券商对照表   
* 证券公司id       securities_company_id          number(2)
* 证券公司名称     securities_company_name        varchar(30)

#### 6. tStockHolderInfo  某一证券账户持仓信息表
* 证券账户号码：     securities_account_id      varchar(20)  主键  非空 
* 证券id：          stock_code                 number(6)
* 证券名称          stock_name                  varchar(40)
* 持有数量          hold_amount                 number(12)

#### 7. tStockInfo 证券信息表
* 证券代码：     stock_code                  number(6)
* 证券名称       stock_name                  varchar(40)
* 所属交易市场   market_name                  varchar(30)
* 总股本         total_amount                 varchar(16)  
* 流通股本       turnover_amount              varchar(16)
* 昨收盘价       yesterday_closed_price       number(16,8)
* 今开盘价       open_price                   number(16,8)
* 发行公司        company_name                varchar(50)
* 停牌标志       stop_flag                     number(1)
* 最新价/今收盘价 last_price                   number(16,8)
* 平均价格       avg_price                     number(16,8)
* 今最高价       max_price                      number(16,8)
* 今最低价       min_price                      number(16,8)
* 发行日期       publish_date                   varchar(8)
* 买一价        buy_price1                      number(16,8)
* 买一数量       buy_amount1                    number(8)
* 买二价        buy_price2                      number(16,8)
* 买二数量       buy_amount2                    number(8)
* 买三价        buy_price3                      number(16,8)
* 买三数量       buy_amount3                    number(8)
* 买四价        buy_price4                      number(16,8)
* 买四数量       buy_amount4                    number(8)
* 买五价        buy_price5                      number(16,8)
* 买五数量       buy_amount5                    number(8)
* 卖一价         sale_price1                    number(16,8)
* 卖一数量       sale_amount1                   number(8)
* 卖二价         sale_price2                    number(16,8)
* 卖二数量       sale_amount2                   number(8)
* 卖三价         sale_price3                    number(16,8)
* 卖三数量       sale_amount3                   number(8)
* 卖四价         sale_price4                    number(16,8)
* 卖四数量       sale_amount4                   number(8)
* 卖五价         sale_price5                    number(16,8)
* 卖五数量       sale_amount5                   number(8)
* 成交量         deal_amount                    number(16)
