drop table TODOLIST;
CREATE TABLE TODOLIST(ID BIGINT auto_increment , NAME VARCHAR(255),completed boolean default false);
INSERT INTO TODOLIST(name,completed) VALUES( 'Hello',true);
INSERT INTO TODOLIST(name) VALUES( 'World');
