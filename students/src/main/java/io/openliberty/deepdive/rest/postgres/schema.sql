CREATE DATABASE students;

\c students;

CREATE TABLE Student (
                         id SERIAL,
                         name varchar(50),
                         username varchar(50),
                         grade varchar(50),
                         dormitory varchar(50),
                         room varchar(50),
                         primary key(id)
);

CREATE SEQUENCE student_id
    START 1
    INCREMENT 1
    OWNED BY Student.id;

CREATE TABLE Preference (
    id preference_id,
    dormitory varchar(50) not null,
    room varchar(50) not null,
    student_name varchar(50) not null references Student(name),
    primary key(id)
);

CREATE SEQUENCE preference_id
    START 1
    INCREMENT 1
    OWNED BY Preference.id;