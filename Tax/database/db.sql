--修改时间：2017.09.08 10:37
--修改者:Smileyes
--修改内容：添加投诉与受理表
drop table if exists complain;

drop table if exists reply;

/*==============================================================*/
/* Table: complain                                              */
/*==============================================================*/
create table complain
(
   comId                varchar(32) not null,
   comCompany           varchar(20) not null,
   comName              varchar(20) not null,
   comMobile            varchar(15) not null,
   isNm                 varchar(1),
   comTIme              timestamp not null,
   comTItle             varchar(20) not null,
   toComDept            varchar(20) not null,
   toComName            varchar(20),
   toComContent         text not null,
   comState             varchar(1) not null,
   primary key (comId)
);

/*==============================================================*/
/* Table: reply                                                 */
/*==============================================================*/
create table reply
(
   repId                varchar(32) not null,
   comId                varchar(32) not null,
   repName              varchar(20) not null,
   repDept              varchar(20) not null,
   repTime              timestamp not null,
   repContent           text not null,
   primary key (repId)
);

alter table reply add constraint FK_com_rep foreign key (comId)
      references complain (comId) on delete restrict on update restrict;
