create user community identified by community;
grant connect, resource, dba to community;
commit;

SELECT *
FROM all_tab_comments;
 
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

insert into board_type_tb (board_type, board_name) values ('free', '자유');

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

rollback;
commit;



select * from board_tb;
select * from board_type_tb;
select * from reply_tb;
select * from file_tb;


merge into board_tb 
using dual 
on (1 > 0) 
when matched then 
update set board_writer = 'new_writer392', board_content = 'new_content392' where board_no = 392 
when not matched then 
insert (board_no, board_title, board_writer, board_content, board_type) values (392, 'title392', 'writer392', 'content392', 'free');

rollback;

SELECT *  FROM all_tables  WHERE table_name = 'board_type_tb'; 

delete from board_tb where board_type = 'null';
select count((select nvl(max(board_no), 0) + 1 from board_tb)) from board_tb;
select nvl(max(board_no), 0) + 1 from board_tb;
select nvl(max(reply_no), 0) + 1 from reply_tb
where board_no = 1 and board_type = 'free';
update board_tb set board_good = 8, board_bad = 3 where board_no = 391;

select count(*) from reply_tb where board_type = 'free' and board_no = 391;
select * from reply_tb where board_no = 391;
select * from board_tb where board_title like 'title%';
select * from board_tb group by board_title having board_title like 'title%';

select count(*) from reply_tb where board_no = 391;
select 1
from dual 
where exists (select 1 from board_tb where board_no = 4);

select * from user_tb;
commit;
rollback;
insert into user_tb(user_no, user_id, user_pw, user_chr, user_email) 
values (get_seq('user_seq'), 'abcd', '1234', '에비시디', 'sp@na.co');

insert into user_tb(user_no, user_id, user_pw, user_chr, user_email) 
select get_seq('user_seq'), 'abcde', '1234', '에비시디', 'sp@na.com' 
from dual 
where not exists (select 1 from user_tb where user_id = 'abcde') and 
not exists (select 1 from user_tb where user_email = 'sp@na.com');

merge into board_tb 
using dual 
on (1 > 0) 
when matched then 
update set board_writer = 'writer', 
board_content = 'content', 
board_updatedate = sysdate 
where board_no = '392' and board_type = 'free' 
when not matched then 
insert (board_no, board_title, board_writer, board_content, board_type) 
values (392, 'title392', 'writer392', 'content392', 'free');

rollback;