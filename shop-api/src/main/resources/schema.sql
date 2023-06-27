-- o arquivo schema.sql deve ter apenas comandos DDL (Data Definition Language), que são os
-- arquivos de definição da base de dados, como CREATE e DROP
create table shop (
				id bigint primary key auto_increment,
				buyer_identifier varchar(100) not null,
				identifier varchar not null,
				status varchar not null,
				date_shop date
);
create table shop_item (
				id bigint primary key auto_increment,
				product_identifier varchar(100) not null,
				amount int not null,
				price double not null,
				shop_id	bigint REFERENCES shop(id)
);
