create table Poster (
	id identity,
	username varchar(20) unique not null,
	password varchar(20) not null,
	truename varchar(30) not null,
	email varchar(30),
	locked boolean not null,
	deleted boolean not null
);
create table Post (
	id identity,
	poster integer not null,
	postname varchar(20) not null,
	message varchar(2000) not null,
	postedTime datetime not null,
	follow integer not null,
	click integer not null,
	topped boolean not null,
	deleted boolean not null,
	foreign key (poster) references poster(id)
);
create table Reply (
	id identity,
	poster integer not null,
	postId integer not null,
	message varchar(2000) not null,
	postedTime datetime not null,
	deleted boolean not null,
	foreign key (poster) references poster(id),
	foreign key (postId) references post(id)
);
create table Manager (
	id identity,
	username varchar(20) unique not null,
	password varchar(20) not null,
	truename varchar(30),
	email varchar(30),
	deleted boolean not null
);
insert into Manager (username, password, truename, email, deleted) values (
	'manager1',
	'manager1',
	'SK',
	'manager1@qq.com',
	false
);
insert into Manager(username , password , truename, email,deleted)values(
	'manager2',
	'manager2',
	'CSQ',
	'manager2@qq.com',
	false
);
insert into Manager (username, password, truename, email, deleted) values (
	'manager3',
	'manager3',
	'LST',
	'manager3@qq.com',
	false
);
insert into Manager(username , password , truename, email,deleted)values(
	'manager4',
	'manager4',
	'WSH',
	'manager4@qq.com',
	false
);
insert into Manager (username, password, truename, email, deleted) values (
	'manager5',
	'manager5',
	'XWJ',
	'manager5@qq.com',
	false
);
insert into Manager(username , password , truename, email,deleted)values(
	'manager6',
	'manager6',
	'LWK',
	'manager6@qq.com',
	false
);
insert into Poster (username, password, truename, email, locked, deleted) values (
	'user1',
	'user1',
	'Zhang San',
	'zhangsan@qq.com',
	false,
	false
);
insert into Poster (username, password, truename, email, locked, deleted) values (
	'user2',
	'user2',
	'Li Si',
	'lisi@qq.com',
	false,
	false
);
insert into Poster (username, password, truename, email, locked, deleted) values (
	'user3',
	'user3',
	'Wang Wu',
	'wangwu@qq.com',
	false,
	false
);
insert into Post (poster, postname, message, postedTime, follow, click, topped, deleted) values (
	1,
	'Post1',
	'大家加油！',
	'2020-12-17 01:00:00',
	0,
	0,
	false,
	false
);
insert into Post (poster, postname, message, postedTime, follow, click, topped, deleted) values (
	2,
	'Post2',
	'努力努力！',
	'2020-12-17 02:00:00',
	0,
	0,
	false,
	false
);
insert into Post (poster, postname, message, postedTime, follow, click, topped, deleted) values (
	3,
	'Post3',
	'冲冲冲！',
	'2020-12-17 03:00:00',
	0,
	0,
	false,
	false
);
insert into Post (poster, postname, message, postedTime, follow, click, topped, deleted) values (
	1,
	'Post4',
	'大家加油！',
	'2020-12-17 04:00:00',
	0,
	0,
	false,
	false
);
insert into Post (poster, postname, message, postedTime, follow, click, topped, deleted) values (
	2,
	'Post5',
	'努力努力！',
	'2020-12-17 05:00:00',
	0,
	0,
	false,
	false
);
insert into Post (poster, postname, message, postedTime, follow, click, topped, deleted) values (
	3,
	'Post6',
	'冲冲冲！',
	'2020-12-17 06:00:00',
	0,
	0,
	false,
	false
);
insert into Post (poster, postname, message, postedTime, follow, click, topped, deleted) values (
	1,
	'Post7',
	'大家加油！',
	'2020-12-17 07:00:00',
	0,
	0,
	true,
	false
);
insert into Post (poster, postname, message, postedTime, follow, click, topped, deleted) values (
	2,
	'Post8',
	'努力努力！',
	'2020-12-17 08:00:00',
	0,
	0,
	true,
	false
);
insert into Post (poster, postname, message, postedTime, follow, click, topped, deleted) values (
	3,
	'Post9',
	'冲冲冲！',
	'2020-12-17 09:00:00',
	0,
	0,
	false,
	false
);
insert into Post (poster, postname, message, postedTime, follow, click, topped, deleted) values (
	1,
	'Post10',
	'努力努力！',
	'2020-12-17 10:00:00',
	6,
	6,
	false,
	false
);
insert into Post (poster, postname, message, postedTime, follow, click, topped, deleted) values (
	2,
	'Post11',
	'冲冲冲！',
	'2020-12-17 11:00:00',
	0,
	0,
	false,
	false
);
insert into Reply (poster, postId, message, postedTime, deleted) values (
	1,
	10,
	'666',
	'2020-12-17 10:10:00',
	false
);
insert into Reply (poster, postId, message, postedTime, deleted) values (
	2,
	10,
	'加油',
	'2020-12-17 10:11:00',
	false
);
insert into Reply (poster, postId, message, postedTime, deleted) values (
	3,
	10,
	'冲',
	'2020-12-17 10:13:00',
	false
);
insert into Reply (poster, postId, message, postedTime, deleted) values (
	1,
	10,
	'666',
	'2020-12-17 10:20:20',
	false
);
insert into Reply (poster, postId, message, postedTime, deleted) values (
	2,
	10,
	'努力',
	'2020-12-17 10:32:45',
	false
);
insert into Reply (poster, postId, message, postedTime, deleted) values (
	3,
	10,
	'Go',
	'2020-12-17 10:56:07',
	false
);