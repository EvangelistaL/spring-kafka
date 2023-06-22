-- o arquivo schema.sql deve ter apenas comandos DDL (Data Definition Language), que são os
-- arquivos de definição da base de dados, como CREATE e DROP

create schema if not exists shop;

create table product (
id bigint primary key auto_increment,
product_identifier varchar(100) not null,
amount int not null
);