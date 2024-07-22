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

# 아이디 중복검사
select * from member where id ="qwe";
select * from member where id ="QWE";
	# 만일 대소문자를 구분하는 데이터 검색을 할때는 binary(필드명) 사용
    # binary(필드명) : 문자가 아닌 byte를 기준으로 비교, 검색을 한다.
select *from member where binary(id) = "qwe"; # 소문자 qwe
select *from member where binary(id) = "QWE"; # 대문자 QWE
	# JDBC : select *from member where binary(id) = ?

