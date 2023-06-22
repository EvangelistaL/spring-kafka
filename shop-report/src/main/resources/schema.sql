-- o arquivo schema.sql deve ter apenas comandos DDL (Data Definition Language), que são os
-- arquivos de definição da base de dados, como CREATE e DROP
create table shop_report (
id bigint primary key auto_increment,
purchase_status varchar(100) not null,
amount int not null
);