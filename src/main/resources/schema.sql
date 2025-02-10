
--DROP TABLE IF EXISTS DATATASK.users CASCADE;

CREATE SCHEMA
IF NOT EXISTS DATATASK;
-- create users table
create table
if not exists DATATASK.users
(
    id_user varchar
(100) primary key,
    name varchar
(100),
    email varchar
(100),
    password varchar
(100),
tokenlogin varchar
(100)
);

--create tugas table
create table
if not exists DATATASK.tugassaya
(
    id_tugas serial primary key,
    namatugas varchar
(100),
    mulaitugas timestamp not null,
    berakhirtugas timestamp not null,
    deskripsi text,
    id_user varchar
(100) ,
    foreign key
(id_user) references DATATASK.users
(id_user)
);