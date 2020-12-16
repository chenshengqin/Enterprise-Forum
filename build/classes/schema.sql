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
insert into Manager (username, password, fullname, email, phoneNo, deleteNo) values (
	'admin',
	'123456',
	'adminname',
	'admin@123.com',
	0
);