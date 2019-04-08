-- noinspection SqlNoDataSourceInspectionForFile

-- noinspection SqlDialectInspectionForFile

prompt PL/SQL Developer Export User Objects for user REVE@JIASHU
prompt Created by hspcadmin on 2019年4月8日
set define off
spool database.log

prompt
prompt Creating table TUSER
prompt ====================
prompt
create table REVE.TUSER
(
  user_id               NUMBER(10) not null,
  telephone             VARCHAR2(11) not null,
  password              VARCHAR2(20) not null,
  nickname              VARCHAR2(40),
  email                 VARCHAR2(30),
  sex                   NUMBER(1),
  age                   NUMBER(3),
  securities_account_id VARCHAR2(12),
  role                  NUMBER(1)
)
tablespace XIAOWEI
  pctfree 10
  initrans 1
  maxtrans 255
  storage
  (
    initial 64K
    minextents 1
    maxextents unlimited
  );
alter table REVE.TUSER
  add constraint USER_ID primary key (USER_ID)
  using index 
  tablespace XIAOWEI
  pctfree 10
  initrans 2
  maxtrans 255
  storage
  (
    initial 64K
    minextents 1
    maxextents unlimited
  );
alter table REVE.TUSER
  add constraint SECURITIES_ACCOUNT_ID unique (SECURITIES_ACCOUNT_ID)
  using index 
  tablespace XIAOWEI
  pctfree 10
  initrans 2
  maxtrans 255
  storage
  (
    initial 64K
    minextents 1
    maxextents unlimited
  );
alter table REVE.TUSER
  add constraint TELEPHONE unique (TELEPHONE)
  using index 
  tablespace XIAOWEI
  pctfree 10
  initrans 2
  maxtrans 255
  storage
  (
    initial 64K
    minextents 1
    maxextents unlimited
  );

prompt
prompt Creating table TSECURITIESACCOUNT
prompt =================================
prompt
create table REVE.TSECURITIESACCOUNT
(
  securities_account_id   VARCHAR2(12) not null,
  open_date               VARCHAR2(20) not null,
  securities_company_name VARCHAR2(30) not null,
  account_id              VARCHAR2(12)
)
tablespace XIAOWEI
  pctfree 10
  initrans 1
  maxtrans 255
  storage
  (
    initial 64K
    minextents 1
    maxextents unlimited
  );
comment on column REVE.TSECURITIESACCOUNT.securities_account_id
  is '证券账户号码';
comment on column REVE.TSECURITIESACCOUNT.open_date
  is '开户日期';
comment on column REVE.TSECURITIESACCOUNT.securities_company_name
  is '证券公司名称';
comment on column REVE.TSECURITIESACCOUNT.account_id
  is '对应的资金账号';
alter table REVE.TSECURITIESACCOUNT
  add constraint SECURITIES_ACCOUNT_ID3 primary key (SECURITIES_ACCOUNT_ID)
  using index 
  tablespace XIAOWEI
  pctfree 10
  initrans 2
  maxtrans 255
  storage
  (
    initial 64K
    minextents 1
    maxextents unlimited
  );
alter table REVE.TSECURITIESACCOUNT
  add constraint ACCOUNT_ID1 unique (ACCOUNT_ID)
  using index 
  tablespace XIAOWEI
  pctfree 10
  initrans 2
  maxtrans 255
  storage
  (
    initial 64K
    minextents 1
    maxextents unlimited
  );
alter table REVE.TSECURITIESACCOUNT
  add constraint SECURITIES_ACCOUNT_ID2 foreign key (SECURITIES_ACCOUNT_ID)
  references REVE.TUSER (SECURITIES_ACCOUNT_ID);

prompt
prompt Creating table TCAPITALACCOUNT
prompt ==============================
prompt
create table REVE.TCAPITALACCOUNT
(
  account_id       VARCHAR2(12) not null,
  bank_name        VARCHAR2(30) not null,
  bank_card_number VARCHAR2(20) not null,
  frozen_balance   NUMBER(16,4) default 0,
  account_balance  NUMBER(16,4) default 0,
  enable_balance   NUMBER(16,4) default 0
)
tablespace XIAOWEI
  pctfree 10
  initrans 1
  maxtrans 255
  storage
  (
    initial 64K
    minextents 1
    maxextents unlimited
  );
comment on column REVE.TCAPITALACCOUNT.account_id
  is '资金账号';
comment on column REVE.TCAPITALACCOUNT.bank_name
  is '开户行';
comment on column REVE.TCAPITALACCOUNT.bank_card_number
  is '银行卡号';
comment on column REVE.TCAPITALACCOUNT.frozen_balance
  is '冻结资金';
comment on column REVE.TCAPITALACCOUNT.account_balance
  is '账户余额';
comment on column REVE.TCAPITALACCOUNT.enable_balance
  is '可用余额';
alter table REVE.TCAPITALACCOUNT
  add constraint ACCOUNT_ID3 primary key (ACCOUNT_ID)
  using index 
  tablespace XIAOWEI
  pctfree 10
  initrans 2
  maxtrans 255
  storage
  (
    initial 64K
    minextents 1
    maxextents unlimited
  );
alter table REVE.TCAPITALACCOUNT
  add constraint ACCOUNT_ID2 foreign key (ACCOUNT_ID)
  references REVE.TSECURITIESACCOUNT (ACCOUNT_ID);

prompt
prompt Creating table TCURRENTENTRUST
prompt ==============================
prompt
create table REVE.TCURRENTENTRUST
(
  entrust_no            NUMBER(10) not null,
  entrust_date          VARCHAR2(25),
  securities_account_id VARCHAR2(12) not null,
  stock_code            VARCHAR2(10) not null,
  entrust_direction     NUMBER(2) not null,
  entrust_amount        NUMBER(10) not null,
  entrust_price         NUMBER(16,8) not null,
  amount_money          NUMBER(16,8) not null
)
tablespace XIAOWEI
  pctfree 10
  initrans 1
  maxtrans 255
  storage
  (
    initial 64K
    minextents 1
    maxextents unlimited
  );
comment on table REVE.TCURRENTENTRUST
  is '委托表';
comment on column REVE.TCURRENTENTRUST.entrust_no
  is '委托序号';
comment on column REVE.TCURRENTENTRUST.entrust_date
  is '委托日期';
comment on column REVE.TCURRENTENTRUST.securities_account_id
  is '证券账户号码';
comment on column REVE.TCURRENTENTRUST.stock_code
  is '股票代码';
comment on column REVE.TCURRENTENTRUST.entrust_direction
  is '委托方向：1 买入 2 卖出';
comment on column REVE.TCURRENTENTRUST.entrust_amount
  is '交易数量';
comment on column REVE.TCURRENTENTRUST.entrust_price
  is '委托价格';
comment on column REVE.TCURRENTENTRUST.amount_money
  is '委托总额，即被冻结金额';
alter table REVE.TCURRENTENTRUST
  add constraint INSTRUCTION_NO1 primary key (ENTRUST_NO)
  using index 
  tablespace XIAOWEI
  pctfree 10
  initrans 2
  maxtrans 255
  storage
  (
    initial 64K
    minextents 1
    maxextents unlimited
  );

prompt
prompt Creating table THISTORYENTRUST
prompt ==============================
prompt
create table REVE.THISTORYENTRUST
(
  entrust_no            NUMBER(10) not null,
  entrust_date          VARCHAR2(25) not null,
  securities_account_id VARCHAR2(12) not null,
  stock_code            VARCHAR2(10) not null,
  entrust_direction     NUMBER(2) not null,
  entrust_amount        NUMBER(10) not null,
  entrust_price         NUMBER(16,8) not null,
  amount_money          NUMBER(16,8) not null,
  entrust_status        NUMBER(2) default 0 not null
)
tablespace XIAOWEI
  pctfree 10
  initrans 1
  maxtrans 255
  storage
  (
    initial 64K
    minextents 1
    maxextents unlimited
  );
comment on table REVE.THISTORYENTRUST
  is '所有委托表，包括历史委托等各种状态的委托';
comment on column REVE.THISTORYENTRUST.entrust_no
  is '序号';
comment on column REVE.THISTORYENTRUST.entrust_date
  is '委托日期';
comment on column REVE.THISTORYENTRUST.securities_account_id
  is '证券账户号码';
comment on column REVE.THISTORYENTRUST.stock_code
  is '股票代码';
comment on column REVE.THISTORYENTRUST.entrust_direction
  is '委托方向：1 买入 2 卖出';
comment on column REVE.THISTORYENTRUST.entrust_amount
  is '交易数量';
comment on column REVE.THISTORYENTRUST.entrust_price
  is '委托价格';
comment on column REVE.THISTORYENTRUST.amount_money
  is '委托总额';
comment on column REVE.THISTORYENTRUST.entrust_status
  is '委托状态：0，委托执行中，1，已成交  2，已撤单 3. 系统废弃';

prompt
prompt Creating table THOLDINGS
prompt ========================
prompt
create table REVE.THOLDINGS
(
  securities_account_id VARCHAR2(12) not null,
  stock_code            VARCHAR2(10) not null,
  stock_name            VARCHAR2(40) not null,
  hold_amount           NUMBER(10) not null,
  buy_price             NUMBER(16,8) default -1
)
tablespace XIAOWEI
  pctfree 10
  initrans 1
  maxtrans 255
  storage
  (
    initial 64K
    minextents 1
    maxextents unlimited
  );
comment on column REVE.THOLDINGS.securities_account_id
  is '证券账户号码';
comment on column REVE.THOLDINGS.stock_code
  is '股票代码';
comment on column REVE.THOLDINGS.stock_name
  is '股票名称';
comment on column REVE.THOLDINGS.hold_amount
  is '持有数量';
comment on column REVE.THOLDINGS.buy_price
  is '买入时的价格';
alter table REVE.THOLDINGS
  add constraint SECURITIES_ACCOUNT_ID5 primary key (SECURITIES_ACCOUNT_ID)
  using index 
  tablespace XIAOWEI
  pctfree 10
  initrans 2
  maxtrans 255
  storage
  (
    initial 64K
    minextents 1
    maxextents unlimited
  );
alter table REVE.THOLDINGS
  add constraint SECURITIES_ACCOUNT_ID4 foreign key (SECURITIES_ACCOUNT_ID)
  references REVE.TSECURITIESACCOUNT (SECURITIES_ACCOUNT_ID);

prompt
prompt Creating table TREALDEAL
prompt ========================
prompt
create table REVE.TREALDEAL
(
  deal_no               NUMBER(10) not null,
  entrust_no            NUMBER(10) not null,
  deal_date             VARCHAR2(25) not null,
  securities_account_id VARCHAR2(12) not null,
  stock_code            VARCHAR2(10) not null,
  deal_direction        NUMBER(2) not null,
  deal_amount           NUMBER(10) not null,
  deal_price            NUMBER(16,8) not null,
  deal_amount_money     NUMBER(16,8) not null,
  deal_capital_balance  NUMBER(16,8) not null,
  capital_account_id    VARCHAR2(12) not null
)
tablespace XIAOWEI
  pctfree 10
  initrans 1
  maxtrans 255
  storage
  (
    initial 64K
    minextents 1
    maxextents unlimited
  );
comment on table REVE.TREALDEAL
  is '历史成交信息表';
comment on column REVE.TREALDEAL.deal_no
  is '成交序号';
comment on column REVE.TREALDEAL.entrust_no
  is '历史委托表中的委托序号';
comment on column REVE.TREALDEAL.deal_date
  is '成交时间';
comment on column REVE.TREALDEAL.securities_account_id
  is '成交的证券账户id';
comment on column REVE.TREALDEAL.stock_code
  is '成交的证券代码';
comment on column REVE.TREALDEAL.deal_direction
  is '成交的方向';
comment on column REVE.TREALDEAL.deal_amount
  is '成交的数量';
comment on column REVE.TREALDEAL.deal_price
  is '成交时单价';
comment on column REVE.TREALDEAL.deal_amount_money
  is '成交金额';
comment on column REVE.TREALDEAL.deal_capital_balance
  is '成交后的对应的资金账户余额';
comment on column REVE.TREALDEAL.capital_account_id
  is '资金账号';

prompt
prompt Creating sequence AI
prompt ====================
prompt
create sequence REVE.AI
minvalue 1
maxvalue 999999999999999999999999999
start with 21
increment by 1
cache 20;

prompt
prompt Creating sequence CE
prompt ====================
prompt
create sequence REVE.CE
minvalue 1
maxvalue 10000000
start with 1
increment by 1
cache 20;

prompt
prompt Creating sequence DEAL
prompt ======================
prompt
create sequence REVE.DEAL
minvalue 1
maxvalue 10000000
start with 1
increment by 1
cache 20;

prompt
prompt Creating sequence HE
prompt ====================
prompt
create sequence REVE.HE
minvalue 1
maxvalue 10000000
start with 1
increment by 1
cache 20;


prompt Done
spool off
set define on
