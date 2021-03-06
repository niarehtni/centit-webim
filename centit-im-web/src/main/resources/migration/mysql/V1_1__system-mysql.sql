DROP TABLE IF EXISTS F_WEB_IM_CUSTOMER;
DROP TABLE IF EXISTS F_WEB_IM_MESSAGE;
DROP TABLE IF EXISTS F_WEB_IM_READ_GROUP;
DROP VIEW IF EXISTS F_V_UNREAD_CHAT_MSG;
DROP VIEW IF EXISTS F_V_UNREAD_GROUP_MSG;

CREATE TABLE F_WEB_IM_CUSTOMER
(
   OS_ID                varchar(20) NOT NULL,
   USER_CODE            varchar(32) NOT NULL COMMENT '用户的ID为 各个系统的用户ID ，或者由前端根据 前端的硬件属性生产一个唯一的编号',
   USER_NAME            varchar(50) NOT NULL,
   CUSTOMER_SERVICE     varchar(32),
   CREATOR              VARCHAR(32),
   CREATE_TIME          DATETIME,
   PRIMARY KEY (OS_ID, USER_CODE)
);
ALTER TABLE F_WEB_IM_CUSTOMER COMMENT '匿名用户（ 用于客服模式）用户的ID为 各个系统的用户ID ，或者由前端根据 前端的硬件属性';

CREATE TABLE F_WEB_IM_MESSAGE
(
   MSG_ID               VARCHAR(32) NOT NULL,
   OS_ID                varchar(20) NOT NULL,
   MSG_TYPE             CHAR NOT NULL COMMENT 'C chat  G Group',
   SENDER               VARCHAR(32) NOT NULL,
   RECEIVER             VARCHAR(32) NOT NULL,
   SEND_TIME            TIME,
   MSG_STATE            CHAR COMMENT 'U 未读 C  已读',
   CONTENT              VARCHAR(1000),
   PRIMARY KEY (MSG_ID)
);

CREATE TABLE F_WEB_IM_READ_GROUP
(
   OS_ID                varchar(20) NOT NULL,
   USER_CODE            varchar(32) NOT NULL,
   UNIT_CODE            varchar(32) NOT NULL,
   LAST_PUST_TIME       DATE NOT NULL,
   PRIMARY KEY (OS_ID, USER_CODE, UNIT_CODE)
);
ALTER TABLE F_WEB_IM_READ_GROUP COMMENT '用来记录 组信息 成功推送到给这个用户的时间，这个时间之后的信息 都是这个用户 关于该组的未读信息';


 CREATE VIEW F_V_UNREAD_CHAT_MSG AS
select OS_ID,RECEIVER,count(1) as UNREAD_SUM 
from F_WEB_IM_MESSAGE
where MSG_TYPE = 'C' and MSG_STATE = 'U'
group by OS_ID,RECEIVER;


 CREATE VIEW F_V_UNREAD_GROUP_MSG AS
select a.OS_ID, b.USER_CODE,count(1) as UNREAD_SUM 
from F_WEB_IM_MESSAGE a join F_WEB_IM_READ_GROUP b on(a.OS_ID=b.OS_ID and a.RECEIVER = b.UNIT_CODE)
where a.MSG_TYPE = 'G' and a.send_time > LAST_PUST_TIME 
group by a.OS_ID, b.USER_CODE;
