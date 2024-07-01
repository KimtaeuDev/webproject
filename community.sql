create user community identified by community;
grant connect, resource, dba to community;

--게시판
drop table board_tb;
create table board_tb(
    board_no number(16) not null,
    board_title varchar2(64) not null,
    board_writer varchar2(32) not null,
    board_content varchar2(2048) not null,
    board_type varchar2(32) not null,
    board_views number(16) default 0,
    board_good number(8) default 0,
    board_bad number(8) default 0,
    board_regdate date default sysdate,
    board_updatedate date default sysdate,
    primary key(board_no, board_type)
);

--게시판 종류
drop table board_type_tb;
create table board_type_tb(
    board_type varchar2(32) not null primary key,
    board_name varchar2(32) not null
);

insert into board_type_tb(board_type, board_name) values ('free', '자유');

--댓글
drop table reply_tb;
create table reply_tb(
    board_no number(16) not null,
    board_type varchar2(32) not null,
    reply_no number(16) not null,
    reply_group number(16) not null,
    reply_dept number(2) default 1 not null,
    reply_writer varchar2(32) not null,
    reply_content varchar2(128) not null,
    reply_regdate date default sysdate,
    reply_good number(8) default 0,
    primary key(board_no, board_type, reply_no)
);

--파일
drop table file_tb;
create table file_tb(
    file_no number not null primary key,
    board_no number not null,
    board_type varchar2(32) not null,
    file_name varchar2(64) not null
);

--파일 seq
drop sequence file_seq;
create sequence file_seq
        increment by 1
        start with 1;

--유저
drop table user_tb;
create table user_tb(
    user_no number not null primary key,
    user_id varchar(32) not null, 
    user_pw varchar(128) not null, 
    user_chr varchar(32) not null, 
    user_email varchar(64) not null, 
    user_regdate date default sysdate,
    user_image varchar(256) default null
);

--유저 seq
drop sequence user_seq;
create sequence user_seq 
        increment by 1 
        start with 1;

commit;