CREATE DATABASE dormitories;

\c dormitories;

CREATE TABLE Dormitory (
                           id SERIAL,
                           name varchar(50),
                           room varchar(50),
                           primary key(id)
);

CREATE SEQUENCE dormitory_id
    START 1
    INCREMENT 1
    OWNED BY Dormitory.id;