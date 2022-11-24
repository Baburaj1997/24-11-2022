create database springDB;
use springDB;

CREATE TABLE category (
  id int(11) unsigned NOT NULL ,
  name nvarchar(50) NOT NULL,
  description nvarchar(100) DEFAULT NULL,
  PRIMARY KEY (id)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

CREATE TABLE product(
id int(11) unsigned NOT NULL,
name varchar(50) NOT NULL,
price double NOT NULL,
unit int,
discontinued boolean,
PRIMARY KEY(id)
)ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- drop table category;
-- drop table product;

select * from category;
select * from product;