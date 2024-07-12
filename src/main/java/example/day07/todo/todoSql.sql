drop database if exists todo;
create database todo;

use todo;

drop table if exists todoList;
create table todoList(
tno int auto_increment,
todo varchar(20),
tstate int default 0,
primary key(tno)
);

select *from todoList;


insert into todoList(todo) values("밥먹기");
insert into todoList(todo) values("공부");
insert into todoList(todo) values("운동");
