CREATE DATABASE students;

\c students;

CREATE TABLE Student (
                            id SERIAL,
                            name varchar(50),
                            email varchar(50),
                            grade varchar(50),
                            role varchar(50),
                            password varchar(50),
                            primary key(id)
);

CREATE SEQUENCE student_id
    START 1
    INCREMENT 1
    OWNED BY Student.id;