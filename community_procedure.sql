create or replace procedure getBoard(num in out number)
is
    cnt number := 0;
begin
    loop
    exit when cnt > num;
    cnt := cnt + 1;
    
    insert into board_tb(
        board_no,
        board_title,
        board_writer,
        board_content,
        board_type,
        board_regdate,
        board_updatedate ) 
        values (
            (num-cnt+1),
            concat('title',to_char(num-cnt+1)),
            'writer'||to_char(num-cnt+1),
            'content'||to_char(num-cnt+1),
            'free',
            sysdate - num-cnt+1/(24),
            sysdate - num-cnt+1/(24)
     );
    
    end loop;
    commit;
end;

/

create or replace procedure getReply(bno in out number, rno in out number)
is
    cnt1 number := 0;
    cnt2 number := 0;
    cnt3 number := 1;
    cnt4 number := 0;
begin
    loop
    exit when cnt1 = bno;
    cnt1 := cnt1 + 1;
    cnt2 := 0;
    
    insert into reply_tb(
        board_no,
        board_type,
        reply_no,
        reply_group,
        reply_writer,
        reply_content,
        reply_good,
        reply_regdate ) 
    values (
        cnt1,
        'free',
        cnt3,
        cnt3,
        'writer'||to_char(cnt1),
        'content'||to_char(cnt1),
        cnt3,
        sysdate - (48/(24)) + (cnt1/(24*60))
    );
    cnt3 := cnt3 + 1;
    cnt4 := cnt3;
    
        loop
        exit when cnt2 = rno;
        cnt2 := cnt2 + 1;
            insert into reply_tb(
            board_no,
            board_type,
            reply_no,
            reply_group,
            reply_writer,
            reply_content,
            reply_good,
            reply_regdate ) 
        values (
            cnt1,
            'free',
            cnt3,
            cnt4,
            'rewriter'||to_char(cnt2),
            'recontent'||to_char(cnt2),
            cnt3,
            sysdate - (48/(24)) + (cnt1/(24*60) + (cnt2/24*60))
        );
        cnt3 := cnt3 + 1;
        end loop;
    
    end loop;
    commit;
end;

/

declare
    bno number := 391;
    rno number := 3;
begin
    getBoard(bno);
    getReply(bno, rno);
    dbms_output.put_line('complete');
end;
/

create or replace function get_seq(seq_name in varchar2) 
return 
    number 
is
    seq_num number;
    sql_stmt varchar2(64);
begin
    sql_stmt := 'select ' || seq_name || '.nextval from dual';
    execute immediate sql_stmt into seq_num;
    return seq_num;
end;

commit;
