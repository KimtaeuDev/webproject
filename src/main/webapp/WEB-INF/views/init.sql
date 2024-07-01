--drop table board_tb;
--board
create table board_tb(
    board_no number(16) not null primary key,
    board_title varchar2(64) not null,
    board_writer varchar2(32) not null,
    board_content varchar2(2048) not null,
    board_type varchar2(32) not null,
    board_views number(16) default 0,
    board_good number(8) default 0,
    board_bad number(8) default 0,
    board_regdate date default sysdate,
    board_updatedate date default sysdate,
    CONSTRAINT fk_board_type FOREIGN KEY(board_type) 
    REFERENCES board_type_tb(board_type)
);

--drop table board_type_tb;
--boardType
create table board_type_tb(
    board_type varchar2(32) not null primary key,
    board_name varchar2(32) not null
);

--drop table reply_tb;
--reply
create table reply_tb(
    board_no number(16) not null,
    reply_no number(16) not null,
    reply_group number(16) not null,
    reply_dept number(2) default 1 not null,
    reply_writer varchar2(32) not null,
    reply_content varchar2(128) not null,
    reply_regdate date default sysdate,
    reply_good number(8) default 0,
    primary key(board_no, reply_no),
    CONSTRAINT fk_reply_bno FOREIGN KEY(board_no) 
    REFERENCES board_tb(board_no)
);

--drop table file_tb;
--file
create table file_tb(
    file_no number(16) not null primary key,
    file_type varchar2(16) not null,
    file_name varchar2(32) not null,
    file_addr varchar2(256) not null,
    file_order number(8) not null
);

--파일 seq
drop sequence file_seq;
create sequence file_seq 
        increment by 1 
        start with 1;

   