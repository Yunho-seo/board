create table board(
 num int primary key auto_increment,
 title varchar(30) not null,
 content varchar(500) not null,
 writer varchar(20) not null,
 regdate timestamp,
 regcount int default 0,
 pw varchar(10) not null
);

select * from board;


select title from board;
drop table board;

alter table board add column count int not null;

alter table board modify coulmn 'count' int default 0;
delete from board where num=8;
select * from board where title like '%호%';

alter table board add column pw varchar(10) not null;

select count(*) from board;