create table board(
 num int primary key auto_increment,
 title varchar(30) not null,
 content varchar(500) not null,
 writer varchar(20) not null,
 regdate timestamp,
 regcount int default 0
);

select * from board;


select title from board;

insert into board (title, content, writer) values ('안녕', '안녕하세요 반갑습니다.', 'yhseo');

drop table board;

select num, title, writer, regdate from board;

alter table board add column count int not null;

alter table board modify coulmn 'count' int default 0;

delete from board where num=8;

select * from board where title like '%호%';