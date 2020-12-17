create table Poster (
	id identity,
	username varchar(20) unique not null,
	password varchar(20) not null,
	truename varchar(30) not null,
	email varchar(30) not null,
	locked boolean not null,
	deleted boolean not null
);
create table Post (
	id identity,
	poster integer not null,
	postname varchar(20) unique not null,
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
	'admin',
	'123456',
	'adminname',
	'admin@123.com',
	0
);
insert into Poster (username, password, truename, email, locked, deleted) values (
	'user1',
	'user1',
	'xiaoming1',
	'xiaoming@qq.com',
	false,
	false
);
insert into Poster (username, password, truename, email, locked, deleted) values (
	'user2',
	'user2',
	'xiaoming2',
	'xiaoming@qq.com',
	false,
	false
);
insert into Post (poster, postname, message, postedTime, follow, click, topped, deleted) values (
	1,
	'homework1',
	'123456789123456789',
	'2020-12-17 02:00:00',
	0,
	0,
	false,
	false
);
insert into Post (poster, postname, message, postedTime, follow, click, topped, deleted) values (
	2,
	'homework2',
	'123456789123456789',
	'2020-12-17 03:00:00',
	1,
	1,
	true,
	false
);
