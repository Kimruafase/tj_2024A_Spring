   drop database if exists springweb;
   create database springweb;
   use springweb;

   drop tables if exists member;
   create table member(
      no bigint auto_increment ,            -- 회원번호
          id varchar(30) not null unique ,            -- 회원 아이디
          pw varchar(30) not null ,            -- 회원 비밀번호
          name varchar(20) not null ,            -- 회원 이름
          email varchar(50) ,               -- 회원 이메일
          phone varchar(13) not null unique,         -- 회원 핸드폰 번호
          constraint member_no_pk primary key(no )       -- 회원 번호 pk
   );
   
select *from member;   
# 샘플
insert member(id,pw,name,email,phone) values("qwe","1234","김민석","pp@naver.com","010-0011-0011");

select *from member where id = "qwe" and pw = "1234";

# 아이디 찾기
select *from member where name = "김민석" and phone = "010-0011-0011";
# select *from member where name = ? and phone = ?

# 비밀번호 찾기
select *from member where id="qwe" and phone = "010-0011-0011";
# select *from member where id= ? and phone = ?


