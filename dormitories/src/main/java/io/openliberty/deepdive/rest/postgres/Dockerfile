FROM postgres:16.1

# set env variable for password to docker
ENV POSTGRES_PASSWORD 1234
# create database called database
ENV POSTGRES_USER postgres

# Use dumpfile to create table schema and populate it with data
COPY schema.sql /docker-entrypoint-initdb.d/1-schema.sql

EXPOSE 5435
