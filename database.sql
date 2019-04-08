-- noinspection SqlNoDataSourceInspectionForFile

-- noinspection SqlDialectInspectionForFile

prompt PL/SQL Developer Export User Objects for user REVE@JIASHU
prompt Created by hspcadmin on 2019��4��8��
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
  is '֤ȯ�˻�����';
comment on column REVE.TSECURITIESACCOUNT.open_date
  is '��������';
comment on column REVE.TSECURITIESACCOUNT.securities_company_name
  is '֤ȯ��˾����';
comment on column REVE.TSECURITIESACCOUNT.account_id
  is '��Ӧ���ʽ��˺�';
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
  is '�ʽ��˺�';
comment on column REVE.TCAPITALACCOUNT.bank_name
  is '������';
comment on column REVE.TCAPITALACCOUNT.bank_card_number
  is '���п���';
comment on column REVE.TCAPITALACCOUNT.frozen_balance
  is '�����ʽ�';
comment on column REVE.TCAPITALACCOUNT.account_balance
  is '�˻����';
comment on column REVE.TCAPITALACCOUNT.enable_balance
  is '�������';
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
  is 'ί�б�';
comment on column REVE.TCURRENTENTRUST.entrust_no
  is 'ί�����';
comment on column REVE.TCURRENTENTRUST.entrust_date
  is 'ί������';
comment on column REVE.TCURRENTENTRUST.securities_account_id
  is '֤ȯ�˻�����';
comment on column REVE.TCURRENTENTRUST.stock_code
  is '��Ʊ����';
comment on column REVE.TCURRENTENTRUST.entrust_direction
  is 'ί�з���1 ���� 2 ����';
comment on column REVE.TCURRENTENTRUST.entrust_amount
  is '��������';
comment on column REVE.TCURRENTENTRUST.entrust_price
  is 'ί�м۸�';
comment on column REVE.TCURRENTENTRUST.amount_money
  is 'ί���ܶ����������';
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
  is '����ί�б�������ʷί�еȸ���״̬��ί��';
comment on column REVE.THISTORYENTRUST.entrust_no
  is '���';
comment on column REVE.THISTORYENTRUST.entrust_date
  is 'ί������';
comment on column REVE.THISTORYENTRUST.securities_account_id
  is '֤ȯ�˻�����';
comment on column REVE.THISTORYENTRUST.stock_code
  is '��Ʊ����';
comment on column REVE.THISTORYENTRUST.entrust_direction
  is 'ί�з���1 ���� 2 ����';
comment on column REVE.THISTORYENTRUST.entrust_amount
  is '��������';
comment on column REVE.THISTORYENTRUST.entrust_price
  is 'ί�м۸�';
comment on column REVE.THISTORYENTRUST.amount_money
  is 'ί���ܶ�';
comment on column REVE.THISTORYENTRUST.entrust_status
  is 'ί��״̬��0��ί��ִ���У�1���ѳɽ�  2���ѳ��� 3. ϵͳ����';

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
  is '֤ȯ�˻�����';
comment on column REVE.THOLDINGS.stock_code
  is '��Ʊ����';
comment on column REVE.THOLDINGS.stock_name
  is '��Ʊ����';
comment on column REVE.THOLDINGS.hold_amount
  is '��������';
comment on column REVE.THOLDINGS.buy_price
  is '����ʱ�ļ۸�';
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
  is '��ʷ�ɽ���Ϣ��';
comment on column REVE.TREALDEAL.deal_no
  is '�ɽ����';
comment on column REVE.TREALDEAL.entrust_no
  is '��ʷί�б��е�ί�����';
comment on column REVE.TREALDEAL.deal_date
  is '�ɽ�ʱ��';
comment on column REVE.TREALDEAL.securities_account_id
  is '�ɽ���֤ȯ�˻�id';
comment on column REVE.TREALDEAL.stock_code
  is '�ɽ���֤ȯ����';
comment on column REVE.TREALDEAL.deal_direction
  is '�ɽ��ķ���';
comment on column REVE.TREALDEAL.deal_amount
  is '�ɽ�������';
comment on column REVE.TREALDEAL.deal_price
  is '�ɽ�ʱ����';
comment on column REVE.TREALDEAL.deal_amount_money
  is '�ɽ����';
comment on column REVE.TREALDEAL.deal_capital_balance
  is '�ɽ���Ķ�Ӧ���ʽ��˻����';
comment on column REVE.TREALDEAL.capital_account_id
  is '�ʽ��˺�';

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
