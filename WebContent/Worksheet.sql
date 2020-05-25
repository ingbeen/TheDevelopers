create table member (
    member_id VARCHAR2(15),
    member_pw VARCHAR2(12),
    member_email VARCHAR2(30),
    PRIMARY KEY(member_id)
);

create table memberboard (
    board_num number,
    board_id varchar2(20),
    board_subject varchar2(50),
    board_content varchar2(2000),
    board_file varchar2(50),
    board_re_ref number,
    board_re_lev number,
    board_re_seq number,
    board_readcount number,
    board_date date,
    primary key(board_num)
);