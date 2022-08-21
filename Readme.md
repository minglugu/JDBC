MySQL Queries:

-- show exisiting databases
show databases;

-- create a database (`` can be used for using the keywords in database)
create database db_name;

-- use arrow keys on the keyboard to find the previous queries

-- if the db_name is not created, create it; if exists, it won't be created. 
create database if not exists db_name character set uft8mb4;

-- select a database
use db_name;

-- delete a database(Warning!) do not use this!
drop database db_name;

-- be careful of this query

data types:
https://dev.mysql.com/doc/refman/5.7/en/data-types.html



  
