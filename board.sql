create table board(
 num int primary key auto_increment,
 title varchar(30) not null,
 content varchar(500) not null,
 writer varchar(20) not null,
 regdate timestamp,
 regcount int default 0
);


// 테이블 생성하기 ( board )

// eGovFrame-4.1.0/bin/mysql-5.7.32
// startup.bat  ==> DB 실행 이후 이클립스에서 Data Source Explorer > Database Connections > New MySQL(MySQL v. 5.7.0) 실행시켜주어야 함
