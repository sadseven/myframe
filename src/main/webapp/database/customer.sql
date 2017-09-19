create table customer(
	id bigint(20) not null AUTO_INCREMENT,
	name varchar(255) default NULL,
	contact varchar(255) default null,
	telephone varchar(255) default null,
	email varchar(255) default null,
	remark text,
	primary key(id)
)engine=InnoDB default charset=utf8;


Insert Into customer values ('1','customer1','Jack','13512345678','jack@gmail.com',null);


Insert Into customer values ('2','customer2','Rose','13623456789','Rose@gmail.com',null);